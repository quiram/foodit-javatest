package com.foodit.test.sample.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class CreateOrdersWithArrayOfOneItem
{
	
	
	private Orders orders;
	
	@Before
	public void setup() throws JSONException
	{
		orders = new Orders(new JSONArray("[{\"totalValue\":7.30}]"));
		
	}

	@Test
	public void containsOneOrder() throws JSONException
	{
		JSONObject expectedResponse = new JSONObject("{\"total-orders\":1}");

		assertEquals(expectedResponse.toString(), orders.totalOrders().toString());
	}
	
	@Test
	public void returnsValueOfThatItem() throws JSONException
	{
		JSONObject expectedResponse = new JSONObject("{\"total-sales\":7.30}");

		assertEquals(expectedResponse.toString(), orders.totalSales().toString());
	}
}
