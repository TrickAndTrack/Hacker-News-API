package com.hackernewapi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hackernewapi.entity.Comment;
import com.hackernewapi.entity.HackerNewsObject;
import com.hackernewapi.entity.Story;
import com.hackernewapi.entity.User;
import com.hackernewapi.repository.HackerNewsApiRepository;
import com.hackernewapi.util.CommonUtil;

import lombok.Getter;
import lombok.Setter;

@Service
public class HackerApiServiceImpl implements HackerApiService {

	@Autowired
	@Getter
	@Setter
	private HackerNewsApiRepository hackerNewsApiRepository;
	
	@Autowired
	@Getter
	@Setter
	private CommonUtil commonUtil;

	@Cacheable(value = "topStories")
	@Override
	public List<Story> getTopStories() throws IOException {
		HackerNewsObject response;
		List<HackerNewsObject> hackerNewsObjects = new ArrayList<>();
		int[] topStoriesIds = hackerNewsApiRepository.getTopStoriesIds();
		for (int topStoryId : topStoriesIds) {
			response = hackerNewsApiRepository.fetchHackerNewsStory(topStoryId);
			hackerNewsObjects.add(response);
		}
		Collections.sort(hackerNewsObjects);
		List<HackerNewsObject> topTenStories = hackerNewsObjects.subList(hackerNewsObjects.size() - 10,
				hackerNewsObjects.size());
		Collections.reverse(topTenStories);
		return commonUtil.hackerNewsObjectsToHackerNewsStories(topTenStories);
	}

	@Cacheable(value = "topComments", key = "#storyId")
	@Override
	public List<Comment> getTopCommentsForStory(Integer storyId) throws IOException {
		HackerNewsObject hackerNewsParentObject = hackerNewsApiRepository.fetchHackerNewsStory(storyId);
		if (StringUtils.isNotEmpty(hackerNewsParentObject.getType())
				&& StringUtils.equals("story", hackerNewsParentObject.getType())) {
			int commentCount;
			Map<Integer, Integer> commentCountMap = new HashMap<>();
			for (int kid : hackerNewsParentObject.getKids()) {
				commentCount = countComments(kid);
				commentCountMap.put(kid, commentCount);
			}
			Map<Integer, Integer> sortedCommentIDCountMap = commonUtil.returnMapSortedByValues(commentCountMap);
			List<Integer> topTenCommentIds = sortedCommentIDCountMap.keySet().stream().limit(10)
					.collect(Collectors.toList());
			return populateHackerNewsCommentList(topTenCommentIds);
		}
		return null;
	}

	@Override
	public int countComments(int parentCommentID) {
		HackerNewsObject commentParent = hackerNewsApiRepository.fetchHackerNewsStory(parentCommentID);
		if (commentParent.getKids() == null) {
			return 1;
		} else {
			int count = 0;
			for (int kidId : commentParent.getKids()) {
				count += countComments(kidId);
			}
			return count + 1;
		}
	}

	@Override
	public List<Comment> populateHackerNewsCommentList(List<Integer> topTenCommentIds) {
		List<Comment> topTenHackerNewsComments = new ArrayList<>();
		for (Integer commentId : topTenCommentIds) {
			HackerNewsObject commentObject = hackerNewsApiRepository.fetchHackerNewsStory(commentId);
			User user = hackerNewsApiRepository.getHackerNewsUserInfo(commentObject.getBy());
			Comment hackerNewsComment = new Comment(user.getId(),
					commonUtil.getDifferenceInYearsFromCurrentTime(user.getCreated()), commentObject.getText());
			topTenHackerNewsComments.add(hackerNewsComment);
		}
		return topTenHackerNewsComments;
	}

	

}
