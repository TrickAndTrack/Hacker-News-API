package com.hackernewapi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hackernewapi.entity.Comment;
import com.hackernewapi.entity.Story;
import com.hackernewapi.repository.HackerNewsApiRepository;
import com.hackernewapi.util.CommonUtil;

@Service
public interface HackerApiService {
	
	public List<Story> getTopStories() throws IOException;
	public List<Comment> getTopCommentsForStory(Integer storyId) throws IOException;
	public int countComments(int parentCommentID);
	public List<Comment> populateHackerNewsCommentList(List<Integer> topTenCommentIds);
	

	
}
