// 회원 정보를 저장하는 클래스
/*
 * VO(value Object) 는 회원 테이블의 정보를 자바에서 얻어오기 전에 회원 정보를 저장할 공간을 위한 준비 과정입니다.
 * 회원 정보를 저장하는 테이블의 내용은 그대로 자바에서 가져다 사용하기 위해서 변수에 저장되어야 한다.
 * 이름, 아이디, 비밀번호, 이메일, 전화번호, 구분 번호를 개별적으로 변수에 저장하는 것보다는 회원 정보를 하나로 묶어서 저장하는 것이 훨씬 효율적이다. 
 * 속성(attribute) : VO 클래스에 입력되는 정보를 저장한다.
 * setter 메소드 : 정보를 VO 클래스에 저장할 때 사용한다.
 * getter 메소드 : VO클래스의 정보를 조회할 때 사용한다.
 * VO 클래스는 데이터를 담는 일종의 컨테이너 역할을 하는 클래스로 데이터의 전달을 목적으로 만들어진 클래스이다.
 */

package com.saeyan.dto;


public class MemberVO{
	private String name;
	private String userid;
	private String pwd;
	private String email;
	private String phone;
	private int admin;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
	@Override
	public String toString() {
		return "MemberVO [name=" + name + ", userid=" + userid + ", pwd=" + pwd + ", email=" + email + ", phone="
				+ phone + ", admin=" + admin + "]";
	}
	
}

/*
 * 외부에서는 필드로 접근하지 않고 getter, setter 로 접근하도록 해야한다.
 * 그래서 필드 앞에 private 접근 제한자를 붙여서 정의한다.
 * 
 */
