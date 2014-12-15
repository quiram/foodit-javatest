package com.foodit.test.sample.entities;

import java.util.Collection;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class MostFrequentMeal
{
	Collection<Orders> collectionOfOrders;

	public MostFrequentMeal(Collection<Orders> collectionOfOrders)
	{
		this.collectionOfOrders = collectionOfOrders;
	}

	public JSONObject obtain()
	{
		if (collectionOfOrders.isEmpty())
			return new JSONObject();

		int timesOrdered = -1;
		JSONObject result = null;

		for (Orders orders : collectionOfOrders)
		{
			JSONObject thisResult = orders.mostFrequentMeal();
			int thisTimesOrdered;
			try
			{
				thisTimesOrdered = thisResult.getInt("timesOrdered");
			}
			catch (JSONException e)
			{
				throw new RuntimeException(e);
			}
			
			if(thisTimesOrdered > timesOrdered)
			{
				timesOrdered = thisTimesOrdered;
				result = thisResult;
			}
		}

		return result;
	}

}
