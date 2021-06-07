<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 상세 조회</title>
<script>
	function deleteCheck(){
		//로그인한 정보와 탈퇴하려는 정보가 동일한지 체크하기
		
		//조회한 아이디 가져오기
		const selectOneEmail = document.getElementById("email").value;
		
		//로그인 아이디와 비교(session)
		if ('${sessionScope.email}' != selectOneEmail){
			alert("삭제 권한이 없습니다.");
			return ;
		}
		var result = confirm("정말 탈퇴하시겠습니까 ?");
		if (result) {
			location.href = "${path}/delete.member";
		}
	}
	
	function modiCheck(){
		const selectOneEmail = document.getElementById("email").value;
		
		if ('${sessionScope.email}' != selectOneEmail){
			alert("수정 권한이 없습니다.");
			return ;
		} else {
			location.href='/jsp01/modifyForm.member';
		}
	}
</script>
<style>
	table th {
		text-align: right;
	}
</style>
</head>
<body>
	<%@ include file = "header.jsp" %>
	<!-- ${mdto} -->
	<h2>회원 정보 조회</h2>
	<input type = "hidden" id = "email" value = "${mdto.email}">
	<table>
		<tr>
			<th>이메일</th>
			<td>${mdto.email}</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td>${mdto.nickName}</td>
		</tr>
		<tr>
			<th>연락처</th>
			<td>${mdto.phone}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${mdto.address}</td>
		</tr>
		<tr>
			<th>등록 일자</th>
			<td>${mdto.regDate}</td>
		</tr>
		<tr>
			<th>수정 일자</th>
			<td>${mdto.modifyDate}</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button onclick = "modiCheck()">수정</button>
				<!-- modify 서버에 어떻게 정보를 넘길 것인가 ? -->
				<!-- href 뒤에 ? 로 데이터 연결시켜도 되지만, Controller 들렸다가 정보 가져오기 -->
				<button onclick = "deleteCheck()">회원 탈퇴</button>
			</td>
		</tr>
	</table>
</body>
</html>