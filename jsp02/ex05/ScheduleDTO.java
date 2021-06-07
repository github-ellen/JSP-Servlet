package ex05;

public class ScheduleDTO {
	private String sdate;
	private int stime;
	private String subject;
	private String content;
	private String regdate;
	private String modifydate;
	public ScheduleDTO() {
		super();
	}
	
	public ScheduleDTO(String sdate, int stime, String subject, String content) {
		super();
		this.sdate = sdate;
		this.stime = stime;
		this.subject = subject;
		this.content = content;
	}

	public ScheduleDTO(String sdate, int stime, String subject, String content, String regdate, String modifydate) {
		super();
		this.sdate = sdate;
		this.stime = stime;
		this.subject = subject;
		this.content = content;
		this.regdate = regdate;
		this.modifydate = modifydate;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public int getStime() {
		return stime;
	}

	public void setStime(int stime) {
		this.stime = stime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getModifydate() {
		return modifydate;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	@Override
	public String toString() {
		return "ScheduleDTO [sdate=" + sdate + ", stime=" + stime + ", subject=" + subject + ", content=" + content
				+ ", regdate=" + regdate + ", modifydate=" + modifydate + "]";
	}
	
}
