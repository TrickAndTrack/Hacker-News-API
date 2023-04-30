package com.hackernewapi;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RestController;
import com.hackernewapi.controller.HackerApiServiceController;

@RestController
@EnableCaching
@SpringBootApplication
public class HackerNewsApiApplication {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(HackerNewsApiApplication.class);
		SpringApplication.run(HackerNewsApiApplication.class, args);
		new HackerApiServiceController().evictAllCacheValues();
		logger.info("Cache cleared!!!");
	}

}
