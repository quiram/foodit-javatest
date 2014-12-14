package com.foodit.test.sample.entities;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Orders
{
	JSONArray data;

	public Orders(JSONArray jsonArray)
	{
		data = jsonArray;
	}

	public JSONObject totalOrders()
	{
		Map<String, Integer> values = new HashMap<String, Integer>();
		values.put("total-orders", data.length());

		return new JSONObject(values);
	}

	public JSONObject totalSales()
	{
		Map<String, BigDecimal> values = new HashMap<String, BigDecimal>();
		BigDecimal total = new BigDecimal(0);

		for (int i = 0; i < data.length(); i++)
		{
			try
			{
				BigDecimal thisValue = new BigDecimal(data.getJSONObject(i).getString("totalValue"));
				total = total.add(thisValue);
			}
			catch (JSONException e)
			{
				throw new RuntimeException("Corrupt data found, cannot process request.", e);
			}
		}

		values.put("total-sales", total);

		return new JSONObject(values);
	}
}
