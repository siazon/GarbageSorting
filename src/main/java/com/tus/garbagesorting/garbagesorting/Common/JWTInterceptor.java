package com.tus.garbagesorting.garbagesorting.Common;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT Interceptor
 */
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        Map<String, Object> map = new HashMap<>();
        // Get the token from header
        String token = request.getHeader("token");
        // If there is no mapping to a method directly skip
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        try {
            JwtTokenUtil.verifier(token);
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "Invalid signature, please log in again!");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "Token has expired, please log in again!");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "Token algorithm inconsistent, please log in again!");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "Token invalid, please login first!");
        }
        map.put("state", false); // set state
        // Map To JSON jaskson
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
