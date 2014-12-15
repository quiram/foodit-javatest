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
		JSONArray arrayOfOrders = new JSONArray("[{\"totalValue\":7.30,\"lineItems\":[{\"id\":5,\"quantity\":1}]}]");
		JSONObject menu = new JSONObject("{\"menu\":{\"Kebabs (Kebabs)\":[{\"id\":5,\"name\":\"Mixed Kebab\"}]},\"restaurantId\":\"bbqgrill\"}");
		
		orders = new Orders(arrayOfOrders, menu);
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
	
	@Test
	public void returnsThatMealAsMostFrequent() throws JSONException
	{
		JSONObject expectedResponse = new JSONObject("{\"restaurantId\":\"bbqgrill\",\"mealId\":5,\"timesOrdered\":1}");

		assertEquals(expectedResponse.toString(), orders.mostFrequentMeal().toString());
	}
}
