<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시간표</title>
<script>
	function addCheck(){
		var sdate = addFrm.sdate.value;
		var stime = addFrm.stime.value;
		var subject = addFrm.subject.value;
		//alert(sdate);
		//sdate 공백 체크
		if (sdate == ''){
			alert("날짜를 입력해주세요.")
			addFrm.sdate.focus();
		} else if (stime ==''){
			alert("교시를 선택해주세요.")
			addFrm.stime.focus();
		} else if (subject ==''){
			alert ("과목을 입력해주세요.")
			addFrm.subject.focus();
		} else {
			addFrm.submit();
		}
	}
</script>
</head>
<body>
<%@include file="header.jsp"  %>
		<h2>시간표 추가</h2>
		<form action="/jsp01/insert.sche" name = "addFrm">
			<table>
				<tr>
					<th>날짜</th>
					<td><input type="date" name="sdate"></td>
				</tr>
				<tr>
					<th>교시</th>
					<td><input type="number" name="stime" min="1" max="8"></td>
				</tr>
				<tr>
					<th>과목</th>
					<td><input type="text" name="subject"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="5" cols="23" name="content"></textarea></td>
				</tr>
				<tr>
					<td colspan = "2"  align="center">
						<input type="submit" value="저장" onclick = "addCheck()">
						<input type="reset" value="취소">
						<button type="button" onclick="location.href='/jsp01/list.sche'">조회폼</button>
					</td>
				</tr>
			</table>
		</form>
		<div>
			<%=request.getAttribute("msg") %>
		</div>
		
		
</body>
</html>