package com.saeyan.dto;


import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 	ID     NOT NULL VARCHAR2(10) 
	PASS   NOT NULL VARCHAR2(10) 
	NAME            VARCHAR2(24) 
	LEV             CHAR(1)      
	ENTER           DATE         
	GENDER          CHAR(1)      
	PHONE           VARCHAR2(30) 
*/


@Setter
@Getter
@ToString
public class LoginVO {
	private String id;
	private String pass;
	private String name;
	private String lev;
	private Date enter;
	private int gender;
	private String phone;
}