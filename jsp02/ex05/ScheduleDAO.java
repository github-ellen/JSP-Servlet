package ex05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//db 접속 클래스
public class ScheduleDAO {
	//추가
	int insert(ScheduleDTO sdto) {
		int cnt=0; //저장 건수
		Connection conn =  DBConn.getConn();
		PreparedStatement pstmt = null;
		String sql = "insert into schedule (sdate,stime,subject,content) values (?,?,?,?)";
		try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, sdto.getSdate());
			 pstmt.setInt(2, sdto.getStime());
			 pstmt.setString(3, sdto.getSubject());
			 pstmt.setString(4, sdto.getContent());
			 cnt = pstmt.executeUpdate();
			 System.out.println(cnt +"건 추가");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt !=null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return cnt;
	}
	
	//리스트 조회
	List<ScheduleDTO> selectList_back(){
		List<ScheduleDTO> slist = new ArrayList<>();
		Connection conn =  DBConn.getConn();
		PreparedStatement pstmt=null;		
		ResultSet rs = null;
		String sql="select * from schedule\r\n" + 
					"order by sdate, stime ";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ScheduleDTO sdto = new ScheduleDTO();
				sdto.setSdate(rs.getString("sdate"));
				sdto.setStime(rs.getInt("stime"));
				sdto.setSubject(rs.getString("subject")); 
				sdto.setContent(rs.getString("content"));
				sdto.setRegdate(rs.getString("regdate"));
				sdto.setModifydate(rs.getString("modifydate"));
				slist.add(sdto);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs !=null) rs.close();
				if (pstmt !=null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("selectList() : " + slist);		
		return slist;
		
	}
	
	//리스트 조회
	List<TimeDTO> selectList(String startDate, String endDate){
		
//		Map<String, String> map = new HashMap<>();
//		List<Map<String, String>> slist = new ArrayList<>();

		List<TimeDTO> slist = new ArrayList<>();
		Connection conn =  DBConn.getConn();
		PreparedStatement pstmt=null;		
		ResultSet rs = null;
		String sql="select sdate,\r\n" + 
				"    nvl(max(decode(stime,1,subject)),' ') t1,\r\n" + 
				"    nvl(max(decode(stime,2,subject)),' ') t2,\r\n" + 
				"    nvl(max(decode(stime,3,subject)),' ') t3,\r\n" + 
				"    nvl(max(decode(stime,4,subject)),' ') t4,\r\n" + 
				"    nvl(max(decode(stime,5,subject)),' ') t5,\r\n" + 
				"    nvl(max(decode(stime,6,subject)),' ') t6,\r\n" + 
				"    nvl(max(decode(stime,7,subject)),' ') t7,\r\n" + 
				"    nvl(max(decode(stime,8,subject)),' ') t8\r\n" + 
				"from schedule\r\n"; 
				if (!startDate.equals("")) {
					sql += "where sdate between ? and ?\r\n";
				}
				sql += "group by sdate\r\n" + 
						"order by sdate";
		try {
			pstmt = conn.prepareStatement(sql);
			if (!startDate.equals("")) { //공백과 같지 않다면
				pstmt.setString(1, startDate);
				pstmt.setString(2, endDate);
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				TimeDTO sdto = new TimeDTO();
				sdto.setSdate(rs.getString("sdate"));
				sdto.setT1(rs.getString("t1"));
				sdto.setT2(rs.getString("t2"));
				sdto.setT3(rs.getString("t3"));
				sdto.setT4(rs.getString("t4"));
				sdto.setT5(rs.getString("t5"));
				sdto.setT6(rs.getString("t6"));
				sdto.setT7(rs.getString("t7"));
				sdto.setT8(rs.getString("t8"));
				slist.add(sdto);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs !=null) rs.close();
				if (pstmt !=null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("selectList() : " + slist);		
		return slist;
		
	}
	//한건조회
	ScheduleDTO selectOne(String sdate, int stime){
		ScheduleDTO sdto = null;
		String sql = "select * from schedule\r\n" + 
					"where sdate = ?\r\n" + 
					"and stime = ?"; 
		Connection conn =  DBConn.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sdate);
			pstmt.setInt(2, stime);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sdto = new ScheduleDTO();
				sdto.setSdate(sdate);
				sdto.setStime(stime);
				sdto.setSubject(rs.getString("subject"));
				sdto.setContent(rs.getString("content"));
				sdto.setRegdate(rs.getString("regdate"));
				sdto.setModifydate(rs.getString("modifydate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("selectOne():" + sdto);
		
		return sdto;
		
	}
	//수정
	int update(ScheduleDTO sdto) {
		int cnt = 0;
		//속도면에서 유리
		StringBuffer sb = new StringBuffer();
		sb.append("update schedule ");
		sb.append("set subject = ?, ");
		sb.append("content = ?, ");
		sb.append("modifydate = sysdate ");
		sb.append("where sdate = ? ");
		sb.append("and stime  = ? ");
		
		Connection conn =  DBConn.getConn();
		PreparedStatement pstmt = null;
		try {
			 pstmt = conn.prepareStatement(sb.toString());
			 pstmt.setString(1, sdto.getSubject());
			 pstmt.setString(2, sdto.getContent());
			 pstmt.setString(3, sdto.getSdate());
			 pstmt.setInt(4, sdto.getStime());			 
			 cnt = pstmt.executeUpdate();
			 System.out.println(cnt +"건 수정");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt !=null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return cnt;
		
		
	}
	
	//삭제
	int delete(String sdate, int stime) {
		int cnt = 0;
		
		String sql = "delete from schedule\r\n" + 
				"where sdate = ?\r\n" + 
				"and stime  = ?";
		Connection conn =  DBConn.getConn();
		PreparedStatement pstmt = null;
		try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, sdate);
			 pstmt.setInt(2, stime);			 
			 cnt = pstmt.executeUpdate();
			 System.out.println(cnt +"건 삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt !=null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return cnt;
		
		
	}
	
	
	
}
