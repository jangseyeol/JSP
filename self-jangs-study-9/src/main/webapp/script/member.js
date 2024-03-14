function loginCheck(){
	if(document.frm.userid.value.length == 0){
		alert("아이디를 써주세요"); //alert 함수를 호출하여 대화상자에 에러 메시지를 출력한다.
		frm.userid.focus(); //포커스 함수를 호출하여 마우스 커서가 아이디 입력 상자에 놓이도록 한다.
		return false;
	}
	if(document.frm.pwd.value == ""){
		alert("비밀번호를 입력해주세요.")
		frm.pwd.focus();
		return false;
	}
	return true;
	}
	
function idCheck(){
	if(document.frm.userid.value == ""){
		alert("아이디를 입력하여 주십시오.");
		document.frm.userid.focus();
		return;
	}
	var url = "idCheck.do?userid=" + document.frm.userid.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}	
	
function idok(){
	opener.frm.userid.value=document.frm.userid.value;
	//자바스크립트에서 opener란 이 창을 열어준 부모 창을 말합니다. 여기에서는 회원 가입 폼이 됩니다. 회원 가입 폼의 아이디를 입력받는 폼에 아이디 중복 체크가 끝난 아이디 값을 넣어줍니다.
	opener.frm.reid.value=document.frm.userid.value;
	//reid는 아이디 중복 체크 과정을 거쳤는지를 확인하기 위해서 회원 가입 폼에 만들어둔 히든 태그입니다.
	//join.jsp에서 <input type ="hidden" name ="reid" size="20">으로 작성했는데
	//이미 사용한 글 상자와 동일하게 <input>태그를 사용하되 type속성 값을 hidden으로 줍니다.
	//이렇게 type속성 값을 hidden으로 하면 이 글 상자는 사용자의 눈에는 보이지 않습니다.
	//하지만 여기에 값을 저장할 수 있습니다. 히든 태그의 이름이 reid 이므로 opener.frm.reid 로 접근하여 
	//value 속성에 아이디 중복 체크가 끝난 아이디 값을 줍니다.
	//이 값은 회원 가입ㅇ르 진행할 때 아이디 중복 체크를 했는지를 확인할 때 사용합니다.
	self.close();
	//아이디 중복 체크하는 창을 닫습니다.
}

	
	
function joinCheck(){
	if(document.frm.name.value.length==0){
		alert("이름을 써주세요");
		frm.name.focus();
		return false;
	}
	
	if(document.frm.userid.value.length==0){
		alert("아이디를 써주세요");
		frm.userid.focus();
		return false;
	}
	
	if(document.frm.userid.value.length<4){
		alert("아이디는 4글자 이상이어야 합니다.");
		frm.userid.focus();
		return false;
	}
	
	if(document.frm.pwd.value== ""){
		alert("암호는 반드시 입력해야 합니다.");
		frm.pwd.focus();
		return false;
	}
	
	if(document.frm.pwd.value != document.frm.pwd_check.value){
		alert("암호가 일치하지 않습니다.");
		frm.pwd.focus();
		return false;
	}
	
	if(document.frm.reid.value.length == 0){
		alert("중복 체크를 하지 않았습니다.");
		frm.userid.focus();
		return false;
	}
	
	return true;
}	

