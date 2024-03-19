function loginCheck(){
	
	console.log("loginCheck");
	
	if(document.frm.id.value.length == 0){
		alert("아이디를 써주세요.");
		frm.id.focus();
		return false;
	}

	if(document.frm.pwd.value == ""){
		alert("암호는 반드시 입력해야 합니다.");
		frm.pwd.focus();
		return false;
	}
	
	return true;
}


function idCheck(){
	
	if(document.frm.id.value == ""){
		alert("아이디 입력하여 주십시오.");
		document.frm.id.focus();
		return false;
	}
	
	//idCheck.do?userid=user
	let url = "LoginServlet?command=id_check_form";
	window.open(url, "_blank_1", "width=450, height=200");
	
}

function idok(){
	opener.frm.id.value = document.frm.id.value;
	opener.frm.reid.value = document.frm.id.value;
	self.close();
	
}

function joinCheck(){
	
	console.log("------------------");
	if(document.frm.name.value.length==0){
		alert("이름을 써주세요.");
		document.frm.name.focus();
		return false;
	}

	if(document.frm.userid.value.length==0){
		alert("아이디를 써주세요.");
		document.frm.userid.focus();
		return false;
	}

	if(document.frm.userid.value.length < 0){
		alert("아이디는 4글자이상이여야 합니다.");
		document.frm.userid.focus();
		return false;
	}
	
	if(document.frm.pwd.value == ""){
		alert("암호는 반드시  입력하여 주십시오.");
		document.frm.pwd.focus();
		return false;
	}

	//비밀번호 재확인
	if(document.frm.pwd.value != document.frm.pwd_check.value){
		alert("암호가 일치하지 않습니다..");
		document.frm.pwd.focus();
		return false;
	}
	
	if(document.frm.reid.value.length==0){
		alert("중복 체크를 하지 않았습니다.");
		frm.userid.focus();
		return false;	
	}
	
	return true;
}