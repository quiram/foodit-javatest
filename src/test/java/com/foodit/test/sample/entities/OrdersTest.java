package com.foodit.test.sample.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class OrdersTest
{
	@Test
	public void testEmptyListOfOrders() throws JSONException
	{
		Orders orders = new Orders(new JSONArray("[]"));
		JSONObject expectedResponse = new JSONObject("{\"total-orders\":0}");

		assertEquals(expectedResponse.toString(), orders.totalOrders().toString());
	}

	@Test
	public void testListOfOneItem() throws JSONException
	{
		Orders orders = new Orders(new JSONArray("[{}]"));
		JSONObject expectedResponse = new JSONObject("{\"total-orders\":1}");

		assertEquals(expectedResponse.toString(), orders.totalOrders().toString());
	}
	
	@Test
	public void testSalesOfEmptyOrdersList() throws JSONException
	{
		Orders orders = new Orders(new JSONArray("[]"));
		JSONObject expectedResponse = new JSONObject("{\"total-sales\":0.0}");

		assertEquals(expectedResponse.toString(), orders.totalSales().toString());
	}
	
	@Test
	public void testSalesListOfOneItem() throws JSONException
	{
		Orders orders = new Orders(new JSONArray("[{\"totalValue\":7.30}]"));
		JSONObject expectedResponse = new JSONObject("{\"total-sales\":7.30}");

		assertEquals(expectedResponse.toString(), orders.totalSales().toString());
	}
	
	@Test
	public void testSalesListOfTwoItem() throws JSONException
	{
		Orders orders = new Orders(new JSONArray("[{\"totalValue\":7.30}, {\"totalValue\":5.30}]"));
		JSONObject expectedResponse = new JSONObject("{\"total-sales\":12.60}");

		assertEquals(expectedResponse.toString(), orders.totalSales().toString());
	}
	
}
