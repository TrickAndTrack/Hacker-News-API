package com.hackernewapi.util;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackernewapi.entity.Story;
import com.hackernewapi.entity.HackerNewsObject;

@Component
public class CommonUtil {

	ObjectMapper objectMapper = new ObjectMapper();

	public Map<Integer, Integer> returnMapSortedByValues(Map<Integer, Integer> unsortedMap) {
		List<Map.Entry<Integer, Integer>> list = new LinkedList<>(unsortedMap.entrySet());
		list.sort(Map.Entry.comparingByValue());
		Collections.reverse(list);
		Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
		for (Map.Entry<Integer, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public int getDifferenceInYearsFromCurrentTime(long givenTime) {
		long currentTime = Instant.now().getEpochSecond();
		return getDifferenceInYears(givenTime, currentTime);
	}

	public int getDifferenceInYears(long Date1, long Date2) {
		Date date = new Date();
		date.setTime(Date1 * 1000);
		Date currentDate = new Date();
		currentDate.setTime((long) Date2 * 1000);
		return (int) (((currentDate.getTime() - date.getTime()) / (1000 * 60 * 60 * 24)) / 365);
	}

	public HackerNewsObject parseJsonStringToobject(String jsonString)
			throws JsonMappingException, JsonProcessingException {
		return objectMapper.readValue(jsonString, HackerNewsObject.class);
	}

	public List<Story> hackerNewsObjectsToHackerNewsStories(List<HackerNewsObject> topTenStories) {
		List<Story> Stories = new ArrayList<>();
		for (HackerNewsObject hackerNewsObject : topTenStories) {
			Story hackerNewsStory = new Story(hackerNewsObject.getBy(), hackerNewsObject.getTitle(),
					hackerNewsObject.getUrl(), hackerNewsObject.getTime(), hackerNewsObject.getScore());
			Stories.add(hackerNewsStory);
		}
		return Stories;
	}

}
