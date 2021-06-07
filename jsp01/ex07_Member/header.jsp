<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<script>
	function sessionCheck(){
		if ('${sessionScope.email}' != ''){
			//alert("${sessionScope.email}");
			document.getElementById('signIn').style.visibility = 'hidden';
		} else {
			document.getElementById('signOut').style.visibility = 'hidden';
		}
	}
</script>
</head>
<body onload = "sessionCheck()">	
	<header>
		<h4>회원 관리 프로그램 v1.0</h4>
		<nav>
			<div>
				<button onclick ="location.href='${path}/selectAll.member'">회원 조회</button>
				<a href = "${path}/ex07_Member/login.jsp" id = "signIn">Sign in</a>
				<a href = "${path}/detail.member?email=${sessionScope.email}">${sessionScope.email}님</a>
				<a href = "${path}/logout.log" id = "signOut">Sign out</a><br>
			</div>
		</nav>
	</header>
	<hr>
</body>
</html>