package ex07.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JunitTest {

	@Test
	void test() {
		//MemberDAO의 메소드를 실행해야하기 때문에
		//우선 객체 생성하기
		MemberDAO mdao = new MemberDAO();
		int cnt = mdao.loginCheck("one11@gmail.com", "one");
		
		//cnt 값이 1이면 성공이라는 의미
		assertEquals(1, cnt);
	}

}
