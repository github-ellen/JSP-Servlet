package ex05;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//확장자 패턴
//확장자가 sche인 모든 매핑 처리 
@WebServlet("*.sche")
public class ScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println("uri:" + uri);
		//uri.indexOf("insert"); //문자열이 존재하지 않을때 -1을 리턴
		//uri로 작업 구분
		if (uri.indexOf("insert") != -1) { //추가
			String sdate = request.getParameter("sdate");
			int stime = Integer.parseInt(request.getParameter("stime"));
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			System.out.println(sdate);
			System.out.println(stime);
			System.out.println(subject);
			System.out.println(content);
			
			ScheduleDTO sdto = new ScheduleDTO(sdate,stime,subject,content);
			System.out.println(sdto);
			ScheduleDAO sdao = new ScheduleDAO();
			int cnt = sdao.insert(sdto);
			String msg=null;
			if (cnt > 0) {
				msg = "추가 완료";
			}else {
				msg = "추가 실패";
			}
			
			//이동
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/ex05_schedule/add.jsp")
					.forward(request, response);
			
		}else if (uri.indexOf("list") != -1) {
			//리스트 조회
			//startdate, enddate null처리
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			
			if (startDate==null || endDate==null) {
				Date d = new Date();
				System.out.println(d);
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				if (startDate==null) startDate = sf.format(d);
				if (endDate==null) endDate = sf.format(d);
			}
			
			System.out.println(startDate);
			System.out.println(endDate);
			ScheduleDAO sdao = new ScheduleDAO();
			List<TimeDTO> slist = sdao.selectList(startDate ,endDate);
			request.setAttribute("slist", slist);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			//이동
			request.getRequestDispatcher("/ex05_schedule/list.jsp")
				.forward(request, response);			
		}else if (uri.indexOf("selectOne") !=-1) {
			//한건조회
			String sdate = request.getParameter("sdate");
			int stime = Integer.parseInt(request.getParameter("stime"));
			System.out.println(sdate);
			System.out.println(stime);
			ScheduleDAO sdao = new ScheduleDAO();
			ScheduleDTO sdto = sdao.selectOne(sdate,stime);
			
			request.setAttribute("sdto", sdto);
			//detail.jsp로 이동
			request.getRequestDispatcher("/ex05_schedule/detail.jsp")
					.forward(request, response);
			
		}else if (uri.indexOf("updateForm") !=-1) {
			//수정폼으로 이동
			String sdate = request.getParameter("sdate");
			int stime = Integer.parseInt(request.getParameter("stime"));
			System.out.println(sdate);
			System.out.println(stime);
			ScheduleDAO sdao = new ScheduleDAO();
			ScheduleDTO sdto = sdao.selectOne(sdate,stime);
			request.setAttribute("sdto", sdto);
			//detail.jsp로 이동
			request.getRequestDispatcher("/ex05_schedule/modify.jsp")
					.forward(request, response);			
			
		}else if (uri.indexOf("update") !=-1) {
			//수정
			String sdate = request.getParameter("sdate");
			int stime = Integer.parseInt(request.getParameter("stime"));
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			System.out.println(sdate);
			System.out.println(stime);
			System.out.println(subject);
			System.out.println(content);
			
			ScheduleDTO sdto = new ScheduleDTO(sdate,stime,subject,content);
			System.out.println(sdto);
			ScheduleDAO sdao = new ScheduleDAO();
			int cnt = sdao.update(sdto);
			
			//forward방식:주소변경안됨:대량의 데이터 전송:서블릿 다시 호출
//			request.getRequestDispatcher("selectOne.sche")
//									.forward(request, response);
			//redirect방식:주소변경
			response.sendRedirect("selectOne.sche?sdate=" + sdate + "&stime=" + stime);
			
		}else if (uri.indexOf("delete") !=-1) {
			//삭제
			String sdate = request.getParameter("sdate");
			int stime = Integer.parseInt(request.getParameter("stime"));
			System.out.println(sdate);
			System.out.println(stime);
			ScheduleDAO sdao = new ScheduleDAO();
			int cnt = sdao.delete(sdate, stime);
			String msg ="";
			if (cnt>0) {
				msg = "삭제완료";
			}else {
				msg = "삭제실패";
			}
			//리스트로 이동:서블릿을 다시 호출
			msg = URLEncoder.encode(msg, "utf-8"); //URL의 utf-8인코딩으로 변경 
			response.sendRedirect("list.sche?msg="+msg);
			
			
		}
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
