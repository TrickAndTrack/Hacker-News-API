package com.hackernewapi.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HackerNewsObject implements Serializable, Comparable<HackerNewsObject> {

	private int id;
	private String by;
	private int descendants;
	private int[] kids;
	private String url;
	private int score;
	private Long time;
	private String title;
	private String type;
	private String text;
	private Boolean deleted;
	private Boolean dead;
	private BigDecimal parent;

	@Override
	public int compareTo(HackerNewsObject HackerNewsObject) {
		return Integer.compare(this.getScore(), HackerNewsObject.getScore());
	}

}
