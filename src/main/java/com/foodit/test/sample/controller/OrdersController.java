package com.foodit.test.sample.controller;

import static com.foodit.test.sample.controller.ControllerHelper.getRestaurantData;
import static com.foodit.test.sample.controller.ControllerHelper.writeJsonData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.foodit.test.sample.entities.MostFrequentMeal;
import com.foodit.test.sample.entities.Orders;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class OrdersController
{
	public void getTotalOrders(String restaurant, HttpServletResponse response) throws IOException, JSONException
	{
		RestaurantData restaurantData = getRestaurantData(restaurant);
		JSONArray ordersRawData = getJsonOrders(restaurantData);
		Orders orders = new Orders(ordersRawData);

		writeJsonData(response, orders.totalOrders().toString());
	}

	public void getTotalSales(String restaurant, HttpServletResponse response) throws IOException, JSONException
	{
		RestaurantData restaurantData = getRestaurantData(restaurant);
		JSONArray ordersRawData = getJsonOrders(restaurantData);
		Orders orders = new Orders(ordersRawData);

		writeJsonData(response, orders.totalSales().toString());
	}

	public void getMostFrequentMeal(HttpServletResponse response) throws JSONException, IOException
	{
		List<RestaurantData> restaurantDataList = getRestaurantData();
		List<Orders> ordersList = new ArrayList<Orders>();

		for (RestaurantData restaurantData : restaurantDataList)
		{
			ordersList.add(new Orders(getJsonOrders(restaurantData), getJsonMenu(restaurantData)));
		}

		MostFrequentMeal mostFrequentMeal = new MostFrequentMeal(ordersList);

		writeJsonData(response, mostFrequentMeal.obtain().toString());
	}

	private JSONArray getJsonOrders(RestaurantData restaurantData) throws JSONException
	{
		return new JSONArray(restaurantData.getOrdersJson().getValue());
	}

	private JSONObject getJsonMenu(RestaurantData restaurantData) throws JSONException
	{
		return new JSONObject(restaurantData.getMenuJson().getValue());
	}

}
