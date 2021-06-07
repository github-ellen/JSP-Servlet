<%@page import="ex05.ScheduleDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 조회</title>
<% 
	ScheduleDTO sdto = (ScheduleDTO)request.getAttribute("sdto"); 
%>
<script type="text/javascript">
	function deleteCheck() {
		var result =confirm('삭제하시겠습니까?');
		if (result){
			location.href='/jsp01/delete.sche?sdate=<%=sdto.getSdate()%>&stime=<%=sdto.getStime()%>'
		}
		
	}

</script>
</head>
<body>
<%@include file="header.jsp"  %>
	<h2>상세조회</h2>


	날짜 : <%=sdto.getSdate() %> <br>
	교시 : <%=sdto.getStime() %> <br>
	제목 : <%=sdto.getSubject() %> <br>
	내용 : <%=sdto.getContent() %> <br>
	등록일자 : <%=sdto.getRegdate() %> <br>
	수정일자 : <%=sdto.getModifydate() %> <br>
	<button onclick="location.href='/jsp01/updateForm.sche?sdate=<%=sdto.getSdate()%>&stime=<%=sdto.getStime()%>'">수정폼</button>
	<button onclick="deleteCheck()">삭제</button>	
		
</body>
</html>