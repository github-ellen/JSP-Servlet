package ex07.Model;

import java.util.Date;

public class MemberDTO {
	private String email;
	private String pw;
	private String nickName ;
	private String phone ;
	private String address;
	private String regDate;
	private String modifyDate;
	
	public MemberDTO() {
		super();
	}

	//Insert, Update 용
	public MemberDTO(String email, String pw, String nickName, String phone, String address) {
		super();
		this.email = email;
		this.pw = pw;
		this.nickName = nickName;
		this.phone = phone;
		this.address = address;
	}

	public MemberDTO(String email, String pw, String nickName, String phone, String address, String regDate,
			String modifyDate) {
		super();
		this.email = email;
		this.pw = pw;
		this.nickName = nickName;
		this.phone = phone;
		this.address = address;
		this.regDate = regDate;
		this.modifyDate = modifyDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "MemberDTO [email=" + email + ", pw=" + pw + ", nickName=" + nickName + ", phone=" + phone + ", address="
				+ address + ", regDate=" + regDate + ", modifyDate=" + modifyDate + "]";
	}
	
}
