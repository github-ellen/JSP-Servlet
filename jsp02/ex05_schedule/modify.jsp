<%@page import="ex05.ScheduleDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="header.jsp"  %>
	<h2>수정</h2>
	<% 
		ScheduleDTO sdto = (ScheduleDTO)request.getAttribute("sdto");
	%>
	<form action="/jsp01/update.sche">
		<table>
			<tr>
				<th>날짜</th>
				<td><input type="date" name="sdate" value="<%=sdto.getSdate()%>" readonly></td>
			</tr>
			<tr>
				<th>교시</th>
				<td><input type="number" name="stime" min="1" max="8" value="<%=sdto.getStime()%>" readonly></td>
			</tr>
			<tr>
				<th>과목</th>
				<td><input type="text" name="subject" value="<%=sdto.getSubject()%>"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="5" cols="23" name="content"><%=sdto.getContent()%></textarea></td>
			</tr>
			<tr>
				<td colspan = "2"  align="center">
					<input type="submit" value="저장">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>