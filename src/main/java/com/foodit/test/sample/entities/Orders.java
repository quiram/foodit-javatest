package com.foodit.test.sample.entities;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * Contains orders for a specific restaurant. This class is not apt to aggregate
 * orders from multiple restaurants.
 * 
 */
public class Orders
{
	private JSONArray orders;
	private JSONObject menu;

	public Orders(JSONArray arrayOfOrders)
	{
		orders = arrayOfOrders;
	}

	public Orders(JSONArray arrayOfOrders, JSONObject menu)
	{
		orders = arrayOfOrders;
		this.menu = menu;
	}

	public JSONObject totalOrders()
	{
		Map<String, Integer> values = new HashMap<String, Integer>();
		values.put("total-orders", orders.length());

		return new JSONObject(values);
	}

	public JSONObject totalSales()
	{
		Map<String, BigDecimal> values = new HashMap<String, BigDecimal>();
		BigDecimal total = new BigDecimal(0);

		for (int i = 0; i < orders.length(); i++)
		{
			try
			{
				BigDecimal thisValue = new BigDecimal(orders.getJSONObject(i).getString("totalValue"));
				total = total.add(thisValue);
			}
			catch (JSONException e)
			{
				throw runtimeException(e);
			}
		}

		values.put("total-sales", total);

		return new JSONObject(values);
	}

	public JSONObject mostFrequentMeal()
	{
		if (orders.length() == 0)
			return new JSONObject();

		Map<Integer, Integer> mealCounter = countMealOrders();

		int mealId = -1;
		int timesOrdered = -1;

		for (int thisMealId : mealCounter.keySet())
		{
			int thisTimesOrdered = mealCounter.get(thisMealId);

			if (thisTimesOrdered > timesOrdered)
			{
				timesOrdered = thisTimesOrdered;
				mealId = thisMealId;
			}
		}

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("restaurantId", getRestaurantId());
		values.put("mealId", mealId);
		values.put("timesOrdered", timesOrdered);

		return new JSONObject(values);
	}

	private String getRestaurantId()
	{
		if (menu == null)
			throw new IllegalStateException("Cannot obtain most frequent meal with no menu information.");

		String restaurantId;
		try
		{
			restaurantId = menu.getString("restaurantId");
		}
		catch (JSONException e)
		{
			throw runtimeException(e);
		}
		return restaurantId;
	}

	private Map<Integer, Integer> countMealOrders()
	{
		Map<Integer, Integer> mealCounter = new HashMap<Integer, Integer>();
		JSONObject item = null;

		for (int i = 0; i < orders.length(); i++)
		{
			JSONArray itemsInOrder;
			try
			{
				itemsInOrder = orders.getJSONObject(i).getJSONArray("lineItems");
			}
			catch (JSONException e)
			{
				throw runtimeException(e);
			}

			for (int j = 0; j < itemsInOrder.length(); j++)
			{
				try
				{
					item = itemsInOrder.getJSONObject(j);
					int mealId = item.getInt("id");
					int timesOrdered = item.getInt("quantity");

					int prevTimesOrdered = (mealCounter.get(mealId) == null) ? 0 : mealCounter.get(mealId);
					mealCounter.put(mealId, timesOrdered + prevTimesOrdered);
				}
				catch (JSONException e)
				{
					/*
					 * Some items within the order do not correspond to any
					 * meals (eg "Delivery Charge"). These items do not have an
					 * id field and will cause this exception to be thrown. We
					 * silently ignore these cases and carry on...
					 */
				}

			}

		}

		return mealCounter;
	}

	private RuntimeException runtimeException(JSONException e)
	{
		return new RuntimeException("Corrupt data found, cannot process request.", e);
	}

}
