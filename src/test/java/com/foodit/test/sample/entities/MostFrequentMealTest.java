package com.foodit.test.sample.entities;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class MostFrequentMealTest
{

	@Test
	public void emptyCollectionReportsEmptyResult() throws JSONException
	{
		MostFrequentMeal mostFrequentMeal = new MostFrequentMeal(new ArrayList<Orders>());

		JSONObject expectedResponse = new JSONObject("{}");

		assertEquals(expectedResponse.toString(), mostFrequentMeal.obtain().toString());
	}

	@Test
	public void collectionOfOneItemReturnsResultFromThatItem() throws JSONException
	{
		Orders orders = mock(Orders.class);

		JSONObject expectedResponse = new JSONObject("{\"restaurantId\":\"bbqgrill\",\"mealId\":5,\"timesOrdered\":7}");
		when(orders.mostFrequentMeal()).thenReturn(expectedResponse);

		MostFrequentMeal mostFrequentMeal = new MostFrequentMeal(Arrays.asList(new Orders[] { orders }));

		assertEquals(expectedResponse.toString(), mostFrequentMeal.obtain().toString());
	}

	@Test
	public void collectionOfMultipleItemsReturnshighestResult() throws JSONException
	{
		List<Orders> listOfOrders = new ArrayList<Orders>();

		Orders orders = mock(Orders.class);
		JSONObject expectedResponse = new JSONObject("{\"restaurantId\":\"bbqgrill\",\"mealId\":5,\"timesOrdered\":7}");
		when(orders.mostFrequentMeal()).thenReturn(expectedResponse);
		listOfOrders.add(orders);
		
		orders = mock(Orders.class);
		expectedResponse = new JSONObject("{\"restaurantId\":\"cantinamariachi\",\"mealId\":2,\"timesOrdered\":9}");
		when(orders.mostFrequentMeal()).thenReturn(expectedResponse);
		listOfOrders.add(orders);
		
		
		MostFrequentMeal mostFrequentMeal = new MostFrequentMeal(listOfOrders);

		assertEquals(expectedResponse.toString(), mostFrequentMeal.obtain().toString());
	}
}
