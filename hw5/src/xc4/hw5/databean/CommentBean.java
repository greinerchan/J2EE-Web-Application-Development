package xc4.hw5.databean;


import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class CommentBean {
	private int id;
	private String email;
	private String comment;
	private String date;
	private int commentId;
	private String userName;
	private Date realDate;
	private String visitorEmail;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVisitorEmail() {
		return visitorEmail;
	}
	public void setVisitorEmail(String visitorEmail) {
		this.visitorEmail = visitorEmail;
	}
	
	public String getComments() {
		return comment;
	}
	
	public void setComments(String comment) {
		this.comment = comment;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Date getRealDate() {
		return realDate;
	}
	public void setRealDate(Date realDate) {
		this.realDate = realDate;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
