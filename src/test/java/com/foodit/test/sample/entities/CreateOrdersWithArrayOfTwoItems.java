package com.foodit.test.sample.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class CreateOrdersWithArrayOfTwoItems
{
	private Orders orders;
	
	@Before
	public void setup() throws JSONException
	{
		JSONArray arrayOfOrders = new JSONArray("[{\"totalValue\":7.30,\"lineItems\":[{\"id\":5,\"quantity\":1}]}, {\"totalValue\":5.30,\"lineItems\":[{\"id\":1,\"quantity\":3}]}]}]");
		JSONObject menu = new JSONObject("{\"menu\":{\"Kebabs (Kebabs)\":[{\"id\":5,\"name\":\"Mixed Kebab\"},{\"id\":1,\"name\":\"Chicken Kebab\"}]},\"restaurantId\":\"bbqgrill\"}");

		orders = new Orders(arrayOfOrders, menu);		
	}

	@Test
	public void returnsSumOfValuesInArray() throws JSONException
	{
		JSONObject expectedResponse = new JSONObject("{\"total-sales\":12.60}");

		assertEquals(expectedResponse.toString(), orders.totalSales().toString());
	}

	@Test
	public void returnsTheMealWithHigherQuantityInOrder() throws JSONException
	{
		JSONObject expectedResponse = new JSONObject("{\"restaurantId\":\"bbqgrill\",\"mealId\":1,\"timesOrdered\":3}");

		assertEquals(expectedResponse.toString(), orders.mostFrequentMeal().toString());
	}
}
