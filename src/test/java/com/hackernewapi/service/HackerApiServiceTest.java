package com.hackernewapi.service;


import com.hackernewapi.HackerNewsApiApplication;
import com.hackernewapi.entity.Comment;
import com.hackernewapi.entity.HackerNewsObject;
import com.hackernewapi.entity.Story;
import com.hackernewapi.helper.TestArtifacts;
import com.hackernewapi.repository.HackerNewsApiRepository;
import com.hackernewapi.util.CommonUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;


@RunWith(MockitoJUnitRunner.class)
public class HackerApiServiceTest {


	
	@InjectMocks
	@Autowired
	HackerApiServiceImpl hackerNewsService;

	@Mock
	@Autowired
	HackerNewsApiRepository hackerNewsRepo;

	@Mock
  CommonUtil commonUtil;
	
	@Mock
  HackerNewsApiApplication newsApp;
	
	@Before
	public void setRepo() {
		hackerNewsService.setHackerNewsApiRepository(hackerNewsRepo);
		hackerNewsService.setCommonUtil(commonUtil);
	}
	
	@Test
	public void testGetTopStoriesError() throws IOException, URISyntaxException{
    int[] ids = TestArtifacts.getTopStories();
    HackerNewsObject hackerNewsObject = TestArtifacts.getHackerNewsEntity();
    List<HackerNewsObject> response = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      response.add(hackerNewsObject);
    }
    List<Story> hackerNewsStory = commonUtil.hackerNewsObjectsToHackerNewsStories(response);
    doReturn(ids).when(hackerNewsRepo).getTopStoriesIds();
    doReturn(hackerNewsObject).when(hackerNewsRepo).fetchHackerNewsStory(anyInt());
    List<Story> actualResponse = hackerNewsService.getTopStories();
    for (int j = 0; j < actualResponse.size(); j++) {
      assertTrue(testEqualityForHackerNewsResponseObject(actualResponse.get(j), hackerNewsStory.get(j)));
    }
  }



  private boolean testEqualityForHackerNewsResponseObject(Story actual, Story response) {
    return actual.getScore() == response.getScore() && actual.getSubmissionDate().equals(response.getSubmissionDate()) && actual.getTitle()
        .equals(response.getTitle()) && actual.getUrl().equals(response.getUrl()) && actual.getUser().equals(response.getUser());
  }

  private boolean testEqualityForHackerNewsComment(Comment actual, Comment response) {
    return actual.getComment().equals(response.getComment()) && actual.getUserAge() == response.getUserAge() && actual.getUserName()
        .equals(response.getUserName());
  }

}
