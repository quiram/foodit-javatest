package com.foodit.test.sample.controller;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.googlecode.objectify.Key;
import com.threewks.thundr.http.ContentType;
import com.threewks.thundr.http.HttpSupport;

public class ControllerHelper
{
	public static void writeJsonData(HttpServletResponse response, String jsonData) throws IOException
	{
		response.addHeader(HttpSupport.Header.ContentType, ContentType.ApplicationJson.value());
		response.getWriter().write(jsonData);
		response.setContentLength(jsonData.getBytes().length);
	}

	public static RestaurantData getRestaurantData(String restaurantName)
	{
		return ofy().load().key(Key.create(RestaurantData.class, restaurantName)).now();
	}

	public static ArrayList<String> getAllRestaurants()
	{
		return Lists.newArrayList("bbqgrill", "butlersthaicafe", "jashanexquisiteindianfood", "newchinaexpress");
	}

	public static List<RestaurantData> getRestaurantData()
	{
		List<RestaurantData> resturantDataList = new ArrayList<RestaurantData>();

		for (String restaurantName : getAllRestaurants())
		{
			resturantDataList.add(getRestaurantData(restaurantName));
		}

		return resturantDataList;
	}

}
