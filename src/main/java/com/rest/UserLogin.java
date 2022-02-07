/**
 * 
 */
package com.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;



/**
 * 
 * 
 * @author Xiasong Chen
 * @date 2022-02-04 23:09:51
 * @version v1.0
 */
@Path("login")
public class UserLogin {
	@GET
	@Produces("text/plain")
	public String get() {
		System.out.print("get post");
		return "Hello Word"+selectUser("23@ait.ie");
	}
	
	
	public User selectUser(String eamil) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = MysqlUtil.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("select * from tb_user where user_email =?");) {
			preparedStatement.setString(1, eamil);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String email = rs.getString("user_email");
				String pwd = rs.getString("user_password");
				String name = rs.getString("user_name");
				String phone = rs.getString("user_phone");
				String status = rs.getString("user_status");
				String role = rs.getString("user_role");
				user = new User( email,pwd, name,phone,status,role);
			}
		} catch (SQLException e) {
			MysqlUtil.printSQLException(e);
		}
		return user;
	}
}
