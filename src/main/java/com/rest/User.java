package com.rest;
/**
 * 
 * 
 * @author Xiasong Chen
 * @date 2021-11-30 23:56:18
 * @version v1.0
 */
public class User {
	protected String user_password;
	protected String user_email;
	protected String user_name;
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	protected String user_phone;
	protected String user_status;
	protected String user_role;
	

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	public User() {
	}
	
	public User( String email,String password,String name,String phone,String status, String role) {
		super();
		this.user_password = password;
		this.user_email = email;
		this.user_role = role;
		this.user_name=name;
		this.user_phone=phone;
		this.user_status=status;
	}
@Override
public String toString()
{
return "["+this.user_name+","+this.user_email+"]";	
}
	
}
