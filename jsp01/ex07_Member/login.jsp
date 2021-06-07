<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "includeFile.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script>
	if ("${param.msg}" != ""){
		alert("${param.msg}");
	}
	function login(){
		//사용자에게 입력받은 값을 잘 가져왔는지 먼저 확인하기
		var email = loginF.email.value;
		var pw = loginF.pw.value;
		//alert(email);
		//alert(pw);
		//값을 입력받지 않았을 경우(공백 처리)
		if (email == ""){
			alert("이메일을 입력해주세요.")
			loginF.email.focus();
		}
		else if (pw == ""){
			alert("비밀번호를 입력해주세요.")
			loginF.email.focus();
		}
		else { //메일과 비번을 입력받았으니 정보가 있는지 DB 체크하기 위해 submit 진행
			loginF.submit();
		}
	}
</script>
<style type="text/css">
	label {
		display : inline-block;
		width: 70px;
	}
	button {
		margin-top: 10px;
	}
</style>
</head>
<body>
	<!-- contextPath 변수 생성(contextPath 변경 대비) -->
	<c:set var = "path" value = "${pageContext.request.contextPath}"/>
	
	<form name = "loginF" action="${path}/login.log" method = "post">
		<label>이메일</label>
		<!-- name 값은 DB의 속성명과 동일하게 -->
		<input type = "email" name = "email"><br>
		<label>비밀번호</label>
		<input type = "password" name = "pw"><br>
		<!-- type="button" 지정하면 클릭 시, 아무 반응 x-->
		<!-- 동적으로 바꾸려면 자바스크립트 작성 -->
		<button type="button" onclick ="login()">로그인</button>
		<button type="button" onclick = "location.href='${path}/ex07_Member/join.jsp'">회원가입</button>
	</form>
	
</body>
</html>