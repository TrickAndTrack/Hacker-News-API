package com.hackernewapi.controller;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackernewapi.entity.Comment;
import com.hackernewapi.entity.Story;
import com.hackernewapi.repository.HackerNewsApiRepository;
import com.hackernewapi.service.HackerApiService;

import lombok.Getter;
import lombok.Setter;

@RestController
@EnableCaching
@RequestMapping("/api")
public class HackerApiServiceController {

	private Logger logger = Logger.getLogger(HackerApiServiceController.class);

	@Autowired(required = true)
	@Getter
	@Setter
	private HackerApiService hackerApiService;

	List<Story> pastStories;

	@CacheEvict(value = "topStories", allEntries = true)
	public void evictAllCacheValues() {
	}

	@GetMapping("/comments")
	public ResponseEntity<Map<String, Object>> getComments(@RequestParam(value = "storyId") Integer storyId)
			throws IOException {
		ResponseEntity<Map<String, Object>> response;
		if (storyId == null) {
			logger.log(Level.INFO, "Story ID is mandatory");
			response = formatResponse(FALSE, "Story ID is mandatory", null);
			return response;
		} else {
			try {
				List<Comment> returnComments = hackerApiService.getTopCommentsForStory(storyId);
				if (returnComments == null) {
					logger.log(Level.INFO, "Story ID not provided!");
					response = formatResponse(FALSE, "Story ID not provided!", returnComments);
				} else {
					response = formatResponse(TRUE, "OK", returnComments);
				}
			} catch (Exception e) {
				logger.log(Level.ERROR, e.getMessage());
				response = formatResponse(FALSE, "Internal Server Error", null);
			}
			return response;
		}
	}

	@GetMapping("/past-stories")
	public ResponseEntity<Map<String, Object>> oldStories() {
		ResponseEntity<Map<String, Object>> response;
		if (pastStories == null) {
			logger.log(Level.INFO, "No past stories found");
			response = formatResponse(FALSE, "No past stories found", null);
		} else {
			response = formatResponse(TRUE, "OK", pastStories);
		}
		return response;
	}

	@GetMapping("/top-stories")
	public ResponseEntity<Map<String, Object>> topStories() {
		ResponseEntity<Map<String, Object>> response;
		try {
			pastStories = hackerApiService.getTopStories();
			response = formatResponse(TRUE, "OK", pastStories);
		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage());
			response = formatResponse(FALSE, "Internal Server Error", null);
		}
		return response;
	}

	private ResponseEntity<Map<String, Object>> formatResponse(Boolean success, String message, Object dataObject) {
		Map<String, Object> object = new HashMap<>();
		object.put("success", success);
		object.put("message", message);
		object.put("data", dataObject);
		return ResponseEntity.status(HttpStatus.OK).body(object);
	}

	

}
