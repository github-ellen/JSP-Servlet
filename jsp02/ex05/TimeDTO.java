package ex05;

public class TimeDTO {
	private String sdate;
	private String t1;
	private String t2;
	private String t3;
	private String t4;
	private String t5;
	private String t6;
	private String t7;
	private String t8;
	public TimeDTO() {
		super();
	}
	public TimeDTO(String sdate, String t1, String t2, String t3, String t4, String t5, String t6, String t7,
			String t8) {
		super();
		this.sdate = sdate;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.t4 = t4;
		this.t5 = t5;
		this.t6 = t6;
		this.t7 = t7;
		this.t8 = t8;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getT1() {
		return t1;
	}
	public void setT1(String t1) {
		this.t1 = t1;
	}
	public String getT2() {
		return t2;
	}
	public void setT2(String t2) {
		this.t2 = t2;
	}
	public String getT3() {
		return t3;
	}
	public void setT3(String t3) {
		this.t3 = t3;
	}
	public String getT4() {
		return t4;
	}
	public void setT4(String t4) {
		this.t4 = t4;
	}
	public String getT5() {
		return t5;
	}
	public void setT5(String t5) {
		this.t5 = t5;
	}
	public String getT6() {
		return t6;
	}
	public void setT6(String t6) {
		this.t6 = t6;
	}
	public String getT7() {
		return t7;
	}
	public void setT7(String t7) {
		this.t7 = t7;
	}
	public String getT8() {
		return t8;
	}
	public void setT8(String t8) {
		this.t8 = t8;
	}
	@Override
	public String toString() {
		return "TimeDTO [sdate=" + sdate + ", t1=" + t1 + ", t2=" + t2 + ", t3=" + t3 + ", t4=" + t4 + ", t5=" + t5
				+ ", t6=" + t6 + ", t7=" + t7 + ", t8=" + t8 + "]";
	}
	
	
	
}
