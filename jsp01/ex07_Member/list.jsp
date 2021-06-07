<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "includeFile.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 회원 조회</title>
<script>
	function memberList(){
		//조회 권한 체크
		const loginEmail = '${sessionScope.email}';
		//alert(loginEmail);
		if (loginEmail == ''){
			alert("로그인해주세요.");
		} else {
			//alert("function 성공");
			var findKey = document.getElementById("findKey").value;
			var findValue = document.getElementById("findValue").value;
			//alert(findKey);
			//alert(findValue);
			location.href="${path}/selectAll.member?findKey="+findKey+"&findValue="+findValue;
		}
	}

</script>
</head>
<body>
	<%@ include file = "header.jsp" %>
	<h2>전체 회원 조회</h2>
	
	<fieldset>
		<select name= "findKey" id = "findKey">
				<!-- 삼항 연산자 이용 -->
				<option value = "email" ${findKey eq 'email' ? 'selected':''}> 이메일</option>
				<option value = "nickName" ${findKey eq 'nickName' ? 'selected':''}>닉네임</option>
				<option value = "address" ${findKey eq 'address' ? 'selected':''}>주소</option>
		</select>
		<input type = "text" name = "findValue" id = "findValue" value = "${findValue}">
		<button onclick = "memberList()">조회</button>
	</fieldset>
	<p>
		<table border="1">
			<tr>
				<th>이메일</th>
				<th>닉네임</th>
				<th>주소</th>
				<th>등록 일자</th>
				<th>수정 일자</th>
			</tr>
			<c:forEach var = "member" items="${mList}">
			<tr>
				<td><a href = '${path}/detail.member?email=${member.email}'>${member.email}</a></td>
				<td>${member.nickName}</td>
				<td>${member.address}</td>
				<td>${member.regDate}</td>
				<td>${member.modifyDate}</td>
			</tr>
			</c:forEach>
		</table>
	</p>
	
	
</body>
</html>