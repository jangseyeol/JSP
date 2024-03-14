package com.ezen.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
CREATE TABLE movie(
code number(4) PRIMARY KEY,
title VARCHAR2(50),
price NUMBER(10),
director VARCHAR2(20),
actor VARCHAR2(20),
poster VARCHAR2(100),
synopsis VARCHAR2(200));
*/

@Setter
@Getter
@ToString
public class MovieVO {
	private int code;
	private String title;
	private int price;
	private String director;
	private String actor;
	private String poster;
	private String synopsis;
}
