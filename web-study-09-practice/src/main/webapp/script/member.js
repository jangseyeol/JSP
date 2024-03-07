function idCheck(){
	
	if(document.frm.userid.value == ""){
		alert("아이디를 입력하여 주십시오");
		document.frm.userid.focus();
		return false;
	}
	
	let url = "idCheck.do?userid=" + document.frm.userid.value;
	window.open(url, "_blank_1", "width=450, height=200");
}