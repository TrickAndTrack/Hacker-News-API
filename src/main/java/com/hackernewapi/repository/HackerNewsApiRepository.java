package com.hackernewapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.hackernewapi.entity.HackerNewsObject;
import com.hackernewapi.entity.User;

@Repository
public class HackerNewsApiRepository {

	private static final String FETCH_STORY_BY_ID_URL = "https://hacker-news.firebaseio.com/v0/item/";
	private static final String JSON_SUFFIX_URL = ".json?print=pretty";
	private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
	private static final String USER_INFO_URL = "https://hacker-news.firebaseio.com/v0/user/";

	public HackerNewsObject fetchHackerNewsStory(int storyId) {
		String uri = FETCH_STORY_BY_ID_URL + storyId + JSON_SUFFIX_URL;
		return new RestTemplate().getForObject(uri, HackerNewsObject.class);
	}

	public int[] getTopStoriesIds() {
		return new RestTemplate().getForObject(TOP_STORIES_URL, int[].class);
	}

	public User getHackerNewsUserInfo(String userHandle) {
		String uri = USER_INFO_URL + userHandle + JSON_SUFFIX_URL;
		return new RestTemplate().getForObject(uri, User.class);
	}

}
