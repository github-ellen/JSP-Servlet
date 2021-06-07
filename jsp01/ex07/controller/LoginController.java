package ex07.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ex07.Model.MemberDAO;


//login.log : 로그인

@WebServlet("*.log")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String msg = null;
		
		if (uri.indexOf("login") != -1) {
			//로그인
			//뷰에서 보낸 메일과 비밀번호 가져오기
			String email = request.getParameter("email");
			String pw = request.getParameter("pw");
			System.out.println(email);
			System.out.println(pw);
			
			//Model에서 메일주소와 비밀번호를 비교하여
			//그 결과를 이 곳으로 가져오기. (접근제한자 체크)
			MemberDAO mdao = new MemberDAO();
			int cnt = mdao.loginCheck(email, pw);
			
			if (cnt > 0) {
				//세션 저장
				//getSession : 추상 메소드
				HttpSession session = request.getSession();
				session.setAttribute("email", email); //session은 WAS(톰캣)에서 관리한다.
				//Session의 유지 시간 : 30분(Default)
				session.setMaxInactiveInterval(60*60*2); //2시간으로 유지하겠다.
				
				msg = "로그인 성공";
				//Main 페이지로 이동
				//request.setAttribute("msg", msg);
				//request.getRequestDispatcher("/ex07_Member/main.jsp").forward(request,response);
				//위와 같이 forward로 작성 시,
				//URL은 /jsp01/login.log 로 남아있기 때문에
				//main으로 변경하려면 redirect(url로 직접 이동) 사용.
				//메세지는 url 마지막에 '?'로 이어붙이기
				msg = URLEncoder.encode(msg, "utf-8");
				
				//contextPath 조회
				String path = request.getContextPath();
				
				response.sendRedirect(path+ "/ex07_Member/list.jsp");
				//한글을 읽어들이지 못하므로 인코딩 필요 -> 주소를 넘기기 전에 생성하기
			}
			else {
				msg = "로그인 실패";
				request.setAttribute("msg", msg);
				
				//로그인 페이지로 이동
				msg = URLEncoder.encode(msg, "utf-8");
				String path = request.getContextPath();
				response.sendRedirect(path+ "/ex07_Member/login.jsp?msg="+msg);
			}
		}
		
		//로그아웃 (session 정보 지우기)
		if (uri.indexOf("logout") != -1) {
			HttpSession session = request.getSession();
			//session은 정보를 가지고 있는 상태이기 때문에
			//로그아웃 시에는 정보를 삭제시켜야함.
			session.invalidate(); //정보 삭제
			//로그인 페이지 이동
			msg = "로그아웃 완료";
			msg = URLEncoder.encode(msg, "utf-8");
			String path = request.getContextPath();
			response.sendRedirect(path+ "/ex07_Member/list.jsp?msg="+msg);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
