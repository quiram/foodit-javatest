package com.foodit.test.sample.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class CreateOrdersWithArrayOfTwoItems
{
	@Test
	public void returnsSumOfValuesInArray() throws JSONException
	{
		Orders orders = new Orders(new JSONArray("[{\"totalValue\":7.30}, {\"totalValue\":5.30}]"));
		JSONObject expectedResponse = new JSONObject("{\"total-sales\":12.60}");

		assertEquals(expectedResponse.toString(), orders.totalSales().toString());
	}
}
