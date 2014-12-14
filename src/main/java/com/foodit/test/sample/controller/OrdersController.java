package com.foodit.test.sample.controller;

import static com.foodit.test.sample.controller.ControllerHelper.getRestaurantData;
import static com.foodit.test.sample.controller.ControllerHelper.writeJsonData;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.foodit.test.sample.entities.Orders;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;

public class OrdersController
{
	public void getTotalOrders(String restaurant, HttpServletResponse response) throws IOException, JSONException
	{
		RestaurantData restaurantData = getRestaurantData(restaurant);
		JSONArray ordersRawData = new JSONArray(restaurantData.getOrdersJson().getValue());
		Orders orders = new Orders(ordersRawData);
		
		writeJsonData(response, orders.totalOrders().toString());
	}
	
	public void getTotalSales(String restaurant, HttpServletResponse response) throws IOException, JSONException
	{
		RestaurantData restaurantData = getRestaurantData(restaurant);
		JSONArray ordersRawData = new JSONArray(restaurantData.getOrdersJson().getValue());
		Orders orders = new Orders(ordersRawData);
		
		writeJsonData(response, orders.totalSales().toString());
	}
}
