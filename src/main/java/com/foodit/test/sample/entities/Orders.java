package com.foodit.test.sample.entities;

import java.util.HashMap;
import java.util.Map;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
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

}
