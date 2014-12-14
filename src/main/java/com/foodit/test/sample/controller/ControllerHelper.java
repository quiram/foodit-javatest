package com.foodit.test.sample.controller;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

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

}
