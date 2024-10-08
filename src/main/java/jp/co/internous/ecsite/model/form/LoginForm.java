package jp.co.internous.ecsite.model.form;

import java.io.Serializable;

public class LoginForm implements Serializable{
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;//return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;//System.out.println(":userName::"+userName);
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
