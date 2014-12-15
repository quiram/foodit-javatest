package com.foodit.test.sample.entities;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;

public class CreateOrdersWithoutMenu
{
	private Orders orders;

	@Before
	public void setup() throws JSONException
	{
		JSONArray arrayOfOrders = new JSONArray("[{\"totalValue\":7.30,\"lineItems\":[{\"id\":5,\"quantity\":1}]}]");
		orders = new Orders(arrayOfOrders);
	}

	@Test(expected=IllegalStateException.class)
	public void throwsException() throws JSONException
	{
		orders.mostFrequentMeal();
	}
}
