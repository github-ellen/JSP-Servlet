<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
<script type="text/javascript">
	//세션 체크
	//로그인이 안 된 상태에서 홈페이지로 접속할 수 없도록 차단 역할
	//로그인이 되어있지 않으면(=이메일에 세션 값 x) 로그인 창으로 이동
 	if ('${sessionScope.email}' == ''){		
		alert('로그인 필수');
		location.href = "${path}/ex07_Member/login.jsp";
	}
	
	var t = 1000;
	//일정 시간 간격으로 함수 실행
	var showTime = setInterval(() => {
			t -- ;
			document.getElementById('time').innerHTML = t;
			if (t < 1){
				alert("세션 종료");
				clearInterval(showTime);
				//logout.log 실행 -> 자동 로그아웃
				location.href = '${path}/logout.log'
			}
		},1000); // 1000 = 1초 간격
		
	function selectAll(){
			location.href = "${path}/ex07_Member/list.jsp";
		}
	
</script>

</head>
<body>
	<div id = "time"></div>
	<h2>메인 화면</h2>
	<div>
		<!-- 메세지 불러오기 -->
		<!-- requestScope 이용 -->
		<!-- ${msg} -->
		
		<!-- url parameter -->
		<%-- ${param.email}님, 환영합니다. <br> --%>
		
		<!-- 내장 객체 session 이용 -->
		<a href = "${path}/detail.member?email=${sessionScope.email}">${sessionScope.email}</a>님, 환영합니다.<br>
		<a href = "${path}/logout.log">로그아웃</a>
		<!-- requestScope 먼저 실행 후, sessionScope 실행 -->
		<!-- sessionScope 범위 (EL 표현식) / 생략 가능 -->
		<!--${email}님, 환영합니다.<br>
		세션 ID : <%=session.getId() %> -->
	</div>
	<button onclick = "selectAll()">전체 회원 조회</button>

	
</body>
</html>