package xc4.hw5.databean;


import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class PostBean {
	private int id;
	private String email;
	private String post;
	private String date;
	private String userName;
	private Date realDate;
	
	public int getId() {
		return id;
	}
	public void setId(int postId) {
		id = postId;
	}

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPosts() {
		return post;
	}
	
	public void setPosts(String post) {
		this.post = post;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
