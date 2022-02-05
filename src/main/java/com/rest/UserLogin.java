/**
 * 
 */
package com.rest;

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
		return "Hello Work111";
	}
}
