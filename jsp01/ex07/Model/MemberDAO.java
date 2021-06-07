package ex07.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int cnt = 0;

	// 로그인 체크
	public int loginCheck(String email, String pw) {
		// 이메일과 비밀번호가 일치하는 DB가 있는지 체크
		// 매개변수로 이메일과 비밀번호 받아오기

		// 오라클 연결
		conn = DBConn.getConn();
		String sql = "SELECT * FROM MEMBER\r\n" + "WHERE EMAIL = ?\r\n" + "AND PW = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = 1;
				System.out.println("정보 존재");
			} else {
				System.out.println("정보 부재");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	public int insert(MemberDTO mdto) {
		conn = DBConn.getConn();
		String sql = "INSERT INTO MEMBER(EMAIL, PW, NICKNAME, " + "PHONE, ADDRESS) VALUES(?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mdto.getEmail());
			pstmt.setString(2, mdto.getPw());
			pstmt.setString(3, mdto.getNickName());
			pstmt.setString(4, mdto.getPhone());
			pstmt.setString(5, mdto.getAddress());
			cnt = pstmt.executeUpdate();
			System.out.println(cnt + "건 추가 완료");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("이미 존재하는 이메일입니다.");
			cnt = -1;
		} catch (SQLException e) {
			// 1과 -1이 아닌 다른 수가 들어오면 이 예외가 실행된 것.
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	public MemberDTO selectOne(String email) {
		MemberDTO mdto = null;
		conn = DBConn.getConn();
		String sql = "SELECT * FROM MEMBER\r\n" + "WHERE EMAIL = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// MemberDTO에 넣어 DAO로 호출
				mdto = new MemberDTO();
				mdto.setEmail(email);
				mdto.setPw(rs.getString("pw"));
				mdto.setNickName(rs.getString("nickName"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setAddress(rs.getString("address"));
				mdto.setRegDate(rs.getString("regDate"));
				mdto.setModifyDate(rs.getString("modifyDate"));
				System.out.println(mdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mdto;
	}
	public int update(MemberDTO mdto) {
		conn = DBConn.getConn();
		String sql = "UPDATE MEMBER SET PW = ?, "
				+ "NICKNAME = ?, PHONE=?, "
				+ "ADDRESS = ? WHERE EMAIL = ?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mdto.getPw());
				pstmt.setString(2, mdto.getNickName());
				pstmt.setString(3, mdto.getPhone());
				pstmt.setString(4, mdto.getAddress());
				pstmt.setString(5, mdto.getEmail());
				cnt = pstmt.executeUpdate();
				System.out.println(cnt);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return cnt;
	}
	public int delete(String email) {
		conn = DBConn.getConn();
		String sql = "DELETE MEMBER\r\n" + 
					"WHERE EMAIL = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	public List<MemberDTO> selectAll(String findKey, String findValue) {
		//전체 조회이기 때문에(여러 건) 배열 필요!!!
		List<MemberDTO> mList = new ArrayList<>();
		
		conn = DBConn.getConn();
		String sql = "SELECT * FROM MEMBER "
					+ "WHERE "+findKey+" LIKE '%' || ? || '%'";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, findValue);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO mdto = new MemberDTO();
				mdto.setEmail(rs.getString("email"));
				mdto.setNickName(rs.getString("nickName"));
				mdto.setAddress(rs.getString("address"));
				mdto.setRegDate(rs.getString("regDate"));
				mdto.setModifyDate(rs.getString("modifyDate"));
				mList.add(mdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(mList);
		return mList;
	}
}
