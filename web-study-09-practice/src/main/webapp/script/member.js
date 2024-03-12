/* 폼에 입력된 정보가 올바른지 판단하는 자바스크립트
로그인을 위해 아이디와 패스워드를 입력받을 login.jsp의 로그인 버튼이 눌러지면 loginCheck()함수가 호출된다.
<input type = "submit" value="로그인" onclick="return loginCheck()">이다.
로그인을 하려면 아이디는 반드시 입력되어야 한다. 아이디 입력 상자는 이름이 frm인 폼 태그 내부에 존재하기 때문에
"document.frm.userid" 로 접근한다.

입력 상자에 입력된 값은 value 속성을 덧붙이고 입력된 값이 없는지를 살펴보기 위해서는 입력된 문자열의 길이를 알려주는 length 속성을 덧붙여야 한다.
이 값이 0이면 입력한 값이 없으므로 alert()함수를 호출하여 대화상자에 에러 메시지를 출력하고 다시 아이디를 입력받을 수 있도록
focus() 함수를 호출하여 마우스 커서가 아이디 입력 상자에 놓이도록 한다.
마지막으로 로그인 작업이 진행되지 못하도록 함수의 리턴값을 false 로 줍니다.

*/

function loginCheck(){
	if(document.frm.userid.value.length == 0){
		alert("아이디를 써 주세요.");
		frm.userid.focus();
		return false;
	}
	
	if(document.frm.pwd.value ==""){
		//패스워드도 필수 입력란으로 반드시 입력되어야 하는데 이번에는 document.frm.pwd.value 값이 ""인지 비교하여 유효성을 체크한다.
		alert("비밀번호를 입력해주세요.");
		frm.pwd.focus();
		return false;
	}
	
	return true;
}


function idCheck(){
	if(document.frm.userid.value == ""){
//회원가입 폼에서 아이디를 입력 받았는지를 확인한다.
		
		alert("아이디를 입력하여 주십시오");
		document.frm.userid.focus();
		return false;
	}
	
	let url = "idCheck.do?userid=" + document.frm.userid.value;
	//idCheck.jsp 페이지는 idCheck.do로 요청한다.
	//idCheck.do를 요청하면서 입력받은 회원 아이디를 서블릿에 보내 데이터베이스에 이 아이디가 저장되어 있는지 확인한다.
	
	window.open(url, "_blank_1", "width=450, height=200");
}
//아이디 중복 체크를 위해서는 아이디를 입력받아야 한다. 아이디 중복 체크를 위해서 idCheck.jsp 페이지는 현재 페이지가 아닌 새로운 창에서 출력해야한다.
//자바스크립트에서는 새로운 창을 띄우기 위해서 window 객체의 open() 메소드를 제공한다.
//window.open 함수를 사용한다.

function idok(){
	opener.frm.userid.value = document.frm.userid.value;
	//자바스크립트에서 opener란 이 창을 열어준 부모창을 말한다.
	//여기에서는 회원가입 폼이 된다. 
	//회원 가입 폼의 아이디를 입력받는 폼에 아이디 중복체크가 끝난 아이디 값을 준다.
	
	opener.frm.reid.value = document.frm.userid.value;
	//reid는 아이디 중복 체크 과정을 거쳤는지를 확인하기 위해 회원 가입 폼에 만들어준 히든 태그이다.
	
	self.close();
	//아이디 중복 체크하는 창을 닫는다.
}

function joinCheck(){
	
	if(document.frm.name.value.length == 0){
		alert("이름을 써 주세요.");
		document.frm.name.focus();
		return false;
	}
	
	if(document.frm.userid.value.length == 0){
		alert("아이디를 써 주세요.");
		document.frm.userid.focus();
		return false;
	}
	
	if(document.frm.userid.value.length < 4){
		alert("아이디는 4글자 이상이여야 합니다.");
		document.frm.userid.focus();
		return false;
	}
	
	//비밀번호 재확인
	if(document.frm.pwd.value != document.frm.pwd_check.value){
		alert("암호가 일치하지 않습니다.");
		document.frm.pwd.focus();
		return false;
	}
	
	if(document.frm.reid.value.length == 0){
		alert("중복체크를 하지 않았습니다.");
		frm.userid.focus();
		return false;
	}
	
	return true;
}
