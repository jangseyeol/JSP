package com.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class MovieVO {
	private Integer code;
	private String title;
	private Integer price;
	private String director;
	private String actor;
	private String poster;
	private String sysnopsis;
}
