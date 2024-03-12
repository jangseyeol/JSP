package com.magic.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import oracle.sql.DATE;

//create table employees(
//id varchar2(10) not null,
//pass varchar2(10) not null,
//name varchar2(24),
//lev char(1) DEFAULT 'A',
//enter DATE DEFAULT SYSDATE,
//gender CHAR(1) DEFAULT '1',
//phone VARCHAR2(30),
//PRIMARY KEY(id)
//);
//

@Setter
@Getter
@ToString
public class EmployeesVO {
	private String id;
	private String pass;
	private String name;
	private String lev;
	private Date enter;
	private String gender;
	private String phone;
}