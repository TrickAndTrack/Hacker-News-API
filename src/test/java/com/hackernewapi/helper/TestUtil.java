package com.hackernewapi.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class TestUtil {

  public static ResponseEntity<Map<String, Object>> formatResponse(Boolean success, String message, Object ResponseObject) {
    Map<String, Object> map = new LinkedHashMap<>();
    map.put("success", success);
    map.put("message", message);
    map.put("data", ResponseObject);
    return ResponseEntity.status(HttpStatus.OK).body(map);
  }

}
