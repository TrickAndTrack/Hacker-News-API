package com.hackernewapi.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackernewapi.entity.HackerNewsObject;
import com.hackernewapi.entity.Story;


@RunWith(MockitoJUnitRunner.class)
public class CommonUtilTest {

	@InjectMocks
	@Autowired
	  CommonUtil commonUtil;

	  @Test
	  public void testGetDifferenceInYears() {
	    long age1 = 1175714200;
	    long age2 = 1314211127;
	    int hackerNewsEntityActual = commonUtil.getDifferenceInYears(age1, age2);
	    assertEquals(4, hackerNewsEntityActual);
	  }

	  @Test
	  public void testReturnMapSortedByValues() {
	    Map<Integer, Integer> unsortedMap = new HashMap<>();
	    unsortedMap.put(10, 100);
	    unsortedMap.put(1, 10);
	    Map<Integer, Integer> sortedMap = commonUtil.returnMapSortedByValues(unsortedMap);
	    List<Integer> keys = sortedMap.keySet().stream().limit(10).collect(Collectors.toList());
	    assertEquals(new Integer(10), keys.get(0));
	  }

	  @Test
	  public void hackerNewsObjectsToHackerNewsStories() {
	    List<HackerNewsObject> hackerNewsObjects = new ArrayList<>();
	    HackerNewsObject hackerNewsObject = new HackerNewsObject();
	    hackerNewsObject.setTitle("ABC");
	    hackerNewsObject.setUrl("https://www.google.co.in");
	    hackerNewsObject.setTime(1233334567L);
	    hackerNewsObject.setScore(1);
	    hackerNewsObject.setBy("pritesh");
	    hackerNewsObjects.add(hackerNewsObject);
	    List<Story> hackerNewsStories = commonUtil.hackerNewsObjectsToHackerNewsStories(hackerNewsObjects);
	    Story hackerNewsStory = hackerNewsStories.get(0);
	    Assertions.assertEquals(hackerNewsObject.getTitle(), hackerNewsStory.getTitle());
	    Assertions.assertEquals(hackerNewsObject.getUrl(), hackerNewsStory.getUrl());
	    Assertions.assertEquals(hackerNewsObject.getTime(), hackerNewsStory.getSubmissionDate());
	    Assertions.assertEquals(hackerNewsObject.getScore(), hackerNewsStory.getScore());
	    Assertions.assertEquals(hackerNewsObject.getBy(), hackerNewsStory.getUser());
	  }

}
