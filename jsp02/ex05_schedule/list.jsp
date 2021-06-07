<%@page import="ex05.TimeDTO"%>
<%@page import="ex05.ScheduleDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체조회</title>
<script type="text/javascript">
	//msg출력
	if ('<%=request.getParameter("msg")%>' != 'null'){
		alert('<%=request.getParameter("msg")%>');
		//getParameter 제거
		history.replaceState({}, null, location.pathname);
	}
</script>
</head>
<body>
	<%@include file="header.jsp"  %>
	<h2>시간표 조회</h2>
	<fieldset>
		<%
			String startDate = (String)request.getAttribute("startDate");
			String endDate = (String)request.getAttribute("endDate");
			System.out.println(startDate);
			System.out.println(endDate);
		%>
	
		<form action="/jsp01/list.sche">
			날짜 <input type="date" name="startDate" value='<%=startDate%>'>
			~ <input type="date" name="endDate" value = '<%=endDate%>'> 
			<button>조회</button>
		</form>
	</fieldset>
	
	<div>
		<table border="1">
			<tr>
				<th>날짜</th>
				<th>1교시</th>
				<th>2교시</th>
				<th>3교시</th>
				<th>4교시</th>
				<th>5교시</th>
				<th>6교시</th>
				<th>7교시</th>
				<th>8교시</th>
			</tr>
		
		<%
			List<TimeDTO> slist = (List<TimeDTO>)request.getAttribute("slist");
			if (slist != null){
				for(int i=0; i < slist.size(); i++){
		%>
				<tr>
					<td><%=slist.get(i).getSdate()%></td>					
					<td><a href="/jsp01/selectOne.sche?sdate=<%=slist.get(i).getSdate()%>&stime=1"> 
						<%=slist.get(i).getT1()%></a>
					</td>					
					<td><a href="/jsp01/selectOne.sche?sdate=<%=slist.get(i).getSdate()%>&stime=2"> 
						<%=slist.get(i).getT2()%></a>
					</td>					
					<td><a href="/jsp01/selectOne.sche?sdate=<%=slist.get(i).getSdate()%>&stime=3"> 
						<%=slist.get(i).getT3()%></a>
					</td>					
					<td><a href="/jsp01/selectOne.sche?sdate=<%=slist.get(i).getSdate()%>&stime=4"> 
						<%=slist.get(i).getT4()%></a>
					</td>					
					<td><a href="/jsp01/selectOne.sche?sdate=<%=slist.get(i).getSdate()%>&stime=5"> 
						<%=slist.get(i).getT5()%></a>
					</td>					
					<td><a href="/jsp01/selectOne.sche?sdate=<%=slist.get(i).getSdate()%>&stime=6"> 
						<%=slist.get(i).getT6()%></a>
					</td>					
					<td><a href="/jsp01/selectOne.sche?sdate=<%=slist.get(i).getSdate()%>&stime=7"> 
						<%=slist.get(i).getT7()%></a>
					</td>					
					<td><a href="/jsp01/selectOne.sche?sdate=<%=slist.get(i).getSdate()%>&stime=8"> 
						<%=slist.get(i).getT8()%></a>
					</td>					
				</tr>					
		<%
				}
			}
		%>
		</table>
		
	
	</div>
	
	<%@ include file="footer.jsp" %>
</body>
</html>