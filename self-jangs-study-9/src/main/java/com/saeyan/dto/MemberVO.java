//VO (Value Object)는 회원 테이블의 정보를 자바에서 얻어오기 전에 회원 정보를 저장할 공간을 위한 준비 과정이다.
//회원 정보를 저장하는 테이블의 내용은 그대로 자바에서 가져다 사용하기 위해 변수에 저장되어야 한다.
//이름, 아이디, 비밀번호, 이메일, 전화번호, 구분 번호를 개별적으로 변수에 저장하는 것보다는 회원정보를 하나로 묶어서 저장하는 것이 훨씬 효율적이다.
//회원 테이블의 정보를 저장할 VO 클래스를 설계해보자.
//VO객체에 저장할 내용은 테이블에서 얻어오기 때문에 그 구조가 테이블과 동일해야 한다.
//테이블에 저장된 하나의 행(로우) 정보를 통째로 전송하기 위해서 이 자체가 VO클래스가 되고 여러 개의 컬럼이 모여서 행이 된 것이므로 클래스를 구성하는 각각의 필드가 바로 컬럼 값을 저장하는 공간이 된다.
//즉, VO클래스는 '매핑'을 시켜준다.
//VO클래스는 속성, setter와 getter로 구성된다.
//속성 : VO클래스에 입력되는 정보를 저장한다.
//setter 메소드 : 정보를 VO클래스에 저장할 때 사용한다.
//getter 메소드 : VO클래스의 정보를 조회할 때 사용한다.
//VO클래스는 데이터를 담는 일종의 컨테이너 역할을 하는 클래스로 데이터의 전달을 목적으로 만들어진 클래스이다.
package com.saeyan.dto;

public class MemberVO {

	//숨겨진 데이터
		private String name;
		private String userid;
		private String pwd;
		private String email;
		private String phone;
		private int admin;
		
		//공개된 메소드
		//DAO에서 name 값을 달라고 할 때 전달해주는 메소드
		public String getName() {
			return name;
		}
		
		//DAO에서 name값을 주었을 때 저장하는 메소드
		public void setName(String name) {
			this.name = name;
		}
		
		//DAO에서 Userid값을 달라고 할 때 전달해주는 메소드
		public String getUserid() {
			return userid;
		}
		
		//DAO에서 Userid값을 저장하라고 할 때 저장하는 메소드
		public void setUserid(String userid) {
			this.userid = userid;
		}
		
		//DAO에서 pwd값을 가져오라고 할 때 가져다주는 메소드
		public String getPwd() {
			return pwd;
		}
		
		//DAO에서 pwd값을 가지고 있으라고 할 때 저장하는 메소드
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		
		//DAO에서 Email 값을 달라고 할 때 주는 메소드
		public String getEmail() {
			return email;
		}
		
		//DAO에서 Email 값을 줄 때 저장하는 메소드
		public void setEmail(String email) {
			this.email = email;
		}
		
		//DAO에서 Phone 값을 필요하다고 요청이 왔을 때 주는 메소드
		public String getPhone() {
			return phone;
		}
		
		//DAO에서  Phone 값을 줄 때 저장해 놓는 메소드
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		//DAO에서 Admin 값을 달라고 할 때 전달해주는 메소드
		public int getAdmin() {
			return admin;
		}
		
		//DAO에서 Admin 값을 전달해줄 때 저장하는 메소드
		public void setAdmin(int admin) {
			this.admin = admin;
		}

		//toString() 메소드를 오버라이딩 하는 이유는 객체에 저장된 필드 값들을 출력해서 보기 쉽게 하기 위한 것
		//출력 함수에서 객체는 toString() 메소드를 자동으로 호출하도록 되어 있어 객체의 내용을 모두 출력하기 위해서 toString()메소드를 오버라이딩하여 필드의 값을 출력하는 코드를 기술한다.
		@Override
		public String toString() {
			return "MemberVO [name=" + name + ", userid=" + userid + ", pwd=" + pwd + ", email=" + email + ", phone="
					+ phone + ", admin=" + admin + "]";
		}
		
		
		
		
}
