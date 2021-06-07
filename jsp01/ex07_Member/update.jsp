<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>
<script>
	function fix(){
		var pw = updateF.pw.value;
		var nickName = updateF.nickName.value;
		var phone = updateF.phone.value;
		var address = updateF.address.value;
		//alert("성공");
		if(pw != ''){
			if(nickName != ''){
				updateF.submit();
			} else {
				alert("닉네임을 입력해주세요.");
				updateF.nickName.focus();
			}
		} else {
			alert("비밀번호를 입력해주세요.");
			updateF.pw.focus();
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
	<h2>정보 수정</h2>
	<form name = "updateF" method = "post" action="${path}/update.member">
		<table>
			<tr>
				<th>이메일</th>
				<td><input type = "text" name = "email" value="${mdto.email}" readonly></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type = "password" name = "pw"></td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td><input type = "text" name = "nickName"></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td><input type = "tel" name = "phone"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type = "text" name = "address"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type = "button" onclick = "fix()">완료</button>
					<button>취소</button>
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>