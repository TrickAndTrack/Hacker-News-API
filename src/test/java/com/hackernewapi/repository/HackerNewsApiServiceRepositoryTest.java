package com.hackernewapi.repository;

import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;
import java.net.URISyntaxException;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;


import com.hackernewapi.entity.User;
import com.hackernewapi.helper.TestArtifacts;



@RunWith(MockitoJUnitRunner.class)
public class HackerNewsApiServiceRepositoryTest {
	
	@InjectMocks
	HackerNewsApiRepository hackerNewsRepo;
	
	@Test
	  public void testGetHackerNewsUserInfo() throws IOException, URISyntaxException {
	    User user = TestArtifacts.getUser();
	    User actualUserResponse = hackerNewsRepo.getHackerNewsUserInfo("ab");
	    assertArrayEquals(user.getSubmitted(), actualUserResponse.getSubmitted());
	  }
	

}
