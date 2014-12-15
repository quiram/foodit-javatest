package com.foodit.test.sample.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class CreateOrdersWithEmptyArray
{
	private Orders orders;

	@Before
	public void setup() throws JSONException
	{
		orders = new Orders(new JSONArray("[]"), new JSONObject("{}"));
	}
	
	@Test
	public void containsNoOrders() throws JSONException
	{
		JSONObject expectedResponse = new JSONObject("{\"total-orders\":0}");

		assertEquals(expectedResponse.toString(), orders.totalOrders().toString());
	}

	@Test
	public void returnsZeroSales() throws JSONException
	{
		JSONObject expectedResponse = new JSONObject("{\"total-sales\":0.0}");

		assertEquals(expectedResponse.toString(), orders.totalSales().toString());
	}
	
	@Test
	public void doesNotHaveFrequentMeals() throws JSONException
	{
		JSONObject expectedResponse = new JSONObject("{}");

		assertEquals(expectedResponse.toString(), orders.mostFrequentMeal().toString());
	}
}
