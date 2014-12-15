package com.foodit.test.sample.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class OrdersWithRepeatedMeals
{
	private Orders orders;

	@Before
	public void setup() throws JSONException
	{
		JSONArray arrayOfOrders = new JSONArray("[{\"totalValue\":7.30,\"lineItems\":[{\"id\":5,\"quantity\":3},{\"id\":1,\"quantity\":6},{\"id\":5,\"quantity\":4}]}]");
		JSONObject menu = new JSONObject("{\"menu\":{\"Kebabs (Kebabs)\":[{\"id\":5,\"name\":\"Mixed Kebab\"},{\"id\":1,\"name\":\"Chicken Kebab\"}]},\"restaurantId\":\"bbqgrill\"}");
		
		orders = new Orders(arrayOfOrders, menu);
	}

	@Test
	public void returnsTheMostFrequentOnAggregate() throws JSONException
	{
		JSONObject expectedResponse = new JSONObject("{\"restaurantId\":\"bbqgrill\",\"mealId\":5,\"timesOrdered\":7}");

		assertEquals(expectedResponse.toString(), orders.mostFrequentMeal().toString());
	}

}
