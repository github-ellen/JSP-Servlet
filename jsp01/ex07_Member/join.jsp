<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script> /* HTML 태그 타입의 유효성 체크를 자동 해제시킴 */
	
	if ("${param.msg}" != ""){
		alert("${param.msg}");
	}
	
	//폼이 완료된 후에 작업이 실행되어야하므로
	function emailSetting(){
		//사용 가능한 이메일이라면, 이메일 입력창에 이메일 세팅
		if ('${param.flag}' == '1'){
			joinF.email.value = '${param.email}';
		} 
	}
	
	function join() {
		//alert ("성공");
		var email = joinF.email.value;
		var pw = joinF.pw.value;
		var nickName = joinF.nickName.value;
		var phone = joinF.phone.value;
		var address = joinF.address.value;
		
		if (email != ''){
			if(pw != ''){
				if(nickName != ''){
					return true;
				} else {
					alert("닉네임을 입력해주세요.");
					joinF.nickName.focus();
					return false;
				}
			} else {
				alert("비밀번호를 입력해주세요.");
				joinF.pw.focus();
				return false;
			}
		} else {
			alert("이메일을 입력해주세요.");
			joinF.email.focus();
			return false;
		}
	}
	//이메일 중복 체크
	function emailCheck(){
		var email = joinF.email.value;
		//이메일에 @포함 여부 확인
		var pattern = new RegExp('@');
		
		if (email ==''){
			alert("이메일을 입력해주세요.");
			joinF.email.focus();
		} else if (!pattern.test(email)) { //이메일에 @ 미포함 시,
			alert("이메일 형식을 확인해주세요.")
		} else {
			location.href='${path}/emailCheck.member?email='+email;
		}
		
	}
</script>
<style type="text/css">
	th {
		text-align: right;
	}
</style>
</head>
<body onload ="emailSetting()"> <!-- 바디 태그 안의 내용들이 세팅된 후에 emailSetting 펑션을 수행해라.-->
	<h4>회원가입</h4>
	<form name = "joinF" method = "post" action="${path}/join.member"
			onsubmit="return join()"> <!-- return값이 true 시에만 최종 submit(action) 진행 -->
		<table>
			<tr>
				<th>이메일</th>
				<td>
					<input type ="email" name = "email">
					<button type = "button" onclick = "emailCheck()">중복 체크</button>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type ="password" name = "pw"></td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td><input type ="text" name = "nickName"></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td><input type ="tel" name = "phone" placeholder="'-'를 넣어주세요"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type ="text" name = "address"></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<button>완료</button> 
					<button type="reset">취소</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>