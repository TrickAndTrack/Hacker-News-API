package com.hackernewapi.helper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hackernewapi.entity.Comment;
import com.hackernewapi.entity.HackerNewsObject;
import com.hackernewapi.entity.Story;
import com.hackernewapi.entity.User;
import com.hackernewapi.util.CommonUtil;



public class TestArtifacts {


	  public static HackerNewsObject getHackerNewsEntity() throws URISyntaxException, IOException {
	    ClassLoader loader = ClassLoader.getSystemClassLoader();
	    String json = Files.lines(Paths.get(Objects.requireNonNull(loader.getResource("hackerNewsObject.json")).toURI())).parallel()
	        .collect(Collectors.joining());
	    return new CommonUtil().parseJsonStringToobject(json);
	  }

	  public static List<Story> getOldStories() throws IOException, URISyntaxException {
	    ClassLoader loader = ClassLoader.getSystemClassLoader();
	    String carInfoJson = new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(loader.getResource("hackerNewsOldStories.json")).toURI())));
	    Gson gson = new Gson();
	    return gson.fromJson(carInfoJson, new TypeToken<List<Story>>() {
	    }.getType());
	  }

	  public static List<Comment> getComments() throws IOException, URISyntaxException {
	    ClassLoader loader = ClassLoader.getSystemClassLoader();
	    String carInfoJson = new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(loader.getResource("hackerNewsComments.json")).toURI())));
	    Gson gson = new Gson();
	    return gson.fromJson(carInfoJson, new TypeToken<List<Comment>>() {
	    }.getType());
	  }

	  public static User getUser() throws IOException, URISyntaxException {
	    ClassLoader loader = ClassLoader.getSystemClassLoader();
	    String carInfoJson = new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(loader.getResource("hackerNewsUser.json")).toURI())));
	    Gson gson = new Gson();
	    return gson.fromJson(carInfoJson, User.class);
	  }

	  public static List<Comment> getTopComments() throws IOException, URISyntaxException {
	    ClassLoader loader = ClassLoader.getSystemClassLoader();
	    String carInfoJson = new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(loader.getResource("hackerNewsTopComments.json")).toURI())));
	    Gson gson = new Gson();
	    return gson.fromJson(carInfoJson, new TypeToken<List<Comment>>() {
	    }.getType());
	  }

	  public static int[] getTopStories() throws IOException, URISyntaxException {
	    ClassLoader loader = ClassLoader.getSystemClassLoader();
	    String carInfoJson = new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(loader.getResource("hackerNewsTopStories.json")).toURI())));
	    return new ObjectMapper().readValue(carInfoJson, int[].class);
	  }
}
