package ex07.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ex07.Model.MemberDAO;
import ex07.Model.MemberDTO;


@WebServlet("*.member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post 방식으로 들어오게 되면 인코딩 필수
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		System.out.println("URI : " + uri);
		//context path구하기
		String path = request.getContextPath();
		
		if (uri.indexOf("join") != -1) {
			//회원가입
			String email = request.getParameter("email");
			String pw = request.getParameter("pw");
			String nickName = request.getParameter("nickName");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			
			System.out.println(email+" / "+pw+" / "+nickName);
			
			//위의 정보들을 DTO에 담아서 DAO에 보내기
			MemberDTO mdto = new MemberDTO();
			mdto.setEmail(email);
			mdto.setPw(pw);
			mdto.setNickName(nickName);
			mdto.setPhone(phone);
			mdto.setAddress(address);
			
			System.out.println("From Controller : " + mdto);
			
			MemberDAO mdao = new MemberDAO();
			int cnt = mdao.insert(mdto);
			if (cnt == 1) {
				//로그인 페이지로 이동
				//주소 변경 가능한 redirect로 이동
				String msg = "회원가입 완료";
				msg = URLEncoder.encode(msg, "utf-8");
				response.sendRedirect(path+"/ex07_Member/login.jsp?msg="+msg);
			} else if (cnt == -1) {
				String msg = "중복된 이메일\\n다른 이메일을 입력하세요.";
				msg = URLEncoder.encode(msg, "utf-8");
				response.sendRedirect(path+"/ex07_Member/join.jsp?msg="+msg);
			} else {
				String msg = "회원가입 실패";
				msg = URLEncoder.encode(msg, "utf-8");
				response.sendRedirect(path+"/ex07_Member/join.jsp?msg="+msg);
			}
		} else if (uri.indexOf("detail") != -1) {
			//한 건 조회
			
			//HttpSession session = request.getSession();
			//String email = (String)session.getAttribute("email");
			
			String email = request.getParameter("email");
			
			System.out.println("Controller : "+email);
			
			MemberDAO mdao = new MemberDAO();
			//결과값만 mdto에 넘기면 되므로 mdto 객체 생성 필요없음.
			//MemberDAO의 selectOne 메소드의 반환값과 변수명이 동일해야함.
			MemberDTO mdto = mdao.selectOne(email);
			
			request.setAttribute("mdto", mdto);
			//request.forward 방식 (대량의 데이터(mdto)를 보내야하기 때문에) 
			request.getRequestDispatcher("/ex07_Member/detail.jsp")
					.forward(request, response);
			
		} else if (uri.indexOf("modifyForm") != -1) {
			//한 건을 조회하여 Modify 페이지로 이동
			HttpSession session = request.getSession();
			//session : 로그인했을 때의 정보를 가지고 있는 상태
			String email = (String)session.getAttribute("email");
			MemberDAO mdao = new MemberDAO();
			MemberDTO mdto = mdao.selectOne(email);
			request.setAttribute("mdto", mdto);
			request.getRequestDispatcher("/ex07_Member/update.jsp")
			.forward(request, response);
			
		} else if (uri.indexOf("update") != -1) {
			String email = request.getParameter("email");
			String pw = request.getParameter("pw");
			String nickName = request.getParameter("nickName");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			
			MemberDTO mdto = new MemberDTO();
			mdto.setEmail(email);
			mdto.setPw(pw);
			mdto.setNickName(nickName);
			mdto.setPhone(phone);
			mdto.setAddress(address);
			
			MemberDAO mdao = new MemberDAO();
			int cnt = mdao.update(mdto);
			
			
		} else if (uri.indexOf("emailCheck") != -1) {
			//이메일 중복 체크
			String email = request.getParameter("email");
			String msg ;
			int flag ;
			MemberDAO mdao = new MemberDAO();
			MemberDTO mdto = mdao.selectOne(email);
			if (mdto == null) {
				msg = "사용 가능한 이메일입니다.";
				flag = 1;
			} else {
				msg = "이미 사용 중인 이메일입니다.";
				flag = 0;
			}
			msg = URLEncoder.encode(msg, "utf-8"); 
			response.sendRedirect(path+"/ex07_Member/join.jsp?flag="+flag+"&email="+email+"&msg="+msg);
			
		} else if (uri.indexOf("delete") != -1) {
			HttpSession session = request.getSession();
			String email = (String)session.getAttribute("email");
			MemberDAO mdao = new MemberDAO();
			mdao.delete(email);
			String msg = URLEncoder.encode("회원 탈퇴 완료", "utf-8"); 
			response.sendRedirect(path+"/ex07_Member/login.jsp?msg="+msg);
			
		} else if (uri.indexOf("selectAll") != -1) {
			String findKey = request.getParameter("findKey");
			String findValue = request.getParameter("findValue");
			//System.out.println(findKey +":"+findValue);
			
			MemberDAO mdao = new MemberDAO();
			List<MemberDTO> mList = mdao.selectAll(findKey, findValue);
			//System.out.println(mList);
			request.setAttribute("mList", mList);
			
			//검색 후 검색값을 남아있게 하기 위해서는
			request.setAttribute("findKey", findKey);
			request.setAttribute("findValue", findValue);
			request.getRequestDispatcher("/ex07_Member/list.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
