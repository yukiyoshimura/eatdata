package responseBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TRespondentBean {
	
	private String respondentId;

	private String employeeId;
	
	private String employeeNm;
	
	private String comment;

	private String createDate;

	private String updateDate;
	
	public HashMap<String, String> respondentStatus = new HashMap<String, String>();
	
	public String sumAttend;
	
	public String sumAbsent;
	
	public String sumUndecided;

	public String getRespondentId() {
		return respondentId;
	}

	public void setRespondentId(String respondentId) {
		this.respondentId = respondentId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeNm() {
		return employeeNm;
	}

	public void setEmployeeNm(String employeeNm) {
		this.employeeNm = employeeNm;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public HashMap<String, String> getRespondentStatus() {
		return respondentStatus;
	}

	public void setRespondentStatus(HashMap<String, String> respondentStatus) {
		this.respondentStatus = respondentStatus;
	}

	public String getSumAttend() {
		return sumAttend;
	}

	public void setSumAttend(String sumAttend) {
		this.sumAttend = sumAttend;
	}

	public String getSumAbsent() {
		return sumAbsent;
	}

	public void setSumAbsent(String sumAbsent) {
		this.sumAbsent = sumAbsent;
	}

	public String getsumUndecided() {
		return sumUndecided;
	}

	public void setsumUndecided(String sumUndecided) {
		this.sumUndecided = sumUndecided;
	}


	/*	
	public class resStatus {
		public HashMap<String, String> respondentStCode = new HashMap<String, String>();
//		public HashMap<String, String> respondentStText = new HashMap<String, String>();
		
		public HashMap<String, String> getrespondentStCode(HashMap<String, String> respondentStCode) {
			return respondentStCode;
		}

		public void setrespondentStCode(HashMap<String, String> respondentStCode) {
			this.respondentStCode = respondentStCode;
		}

		
	}
*/

}
