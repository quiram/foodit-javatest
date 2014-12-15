package com.foodit.test.sample.controller;

import static com.foodit.test.sample.controller.ControllerHelper.getAllRestaurants;
import static com.foodit.test.sample.controller.ControllerHelper.getRestaurantData;
import static com.foodit.test.sample.controller.ControllerHelper.writeJsonData;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.threewks.thundr.logger.Logger;
import com.threewks.thundr.view.jsp.JspView;
import com.threewks.thundr.view.string.StringView;

public class DataLoadController {

	public JspView instructions() {
		return new JspView("instructions.jsp");
	}

	public StringView load() {
		Logger.info("Loading data");
		List<String> restaurants = getAllRestaurants();
		List<RestaurantData> restaurantData = Lists.newArrayList();
		for (String restaurant : restaurants) {
			restaurantData.add(loadData(restaurant));
		}
		ofy().save().entities(restaurantData);
		return new StringView("Data loaded.");
	}

	private RestaurantData loadData(String restaurantName) {
		String orders = readFile(String.format("orders-%s.json", restaurantName));
		String menu = readFile(String.format("menu-%s.json", restaurantName));
		RestaurantData restaurantLoadData = new RestaurantData(restaurantName, menu, orders);
		return restaurantLoadData;
	}

	private String readFile(String resourceName) {
		URL url = Resources.getResource(resourceName);
		try {
			return IOUtils.toString(new InputStreamReader(url.openStream()));
		} catch (IOException e) {
			Logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public void viewData(String restaurant, HttpServletResponse response) throws IOException {
		RestaurantData restaurantLoadData = getRestaurantData(restaurant);
		String data = restaurantLoadData.viewData();

		writeJsonData(response, data);
	}
}
