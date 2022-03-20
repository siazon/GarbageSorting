package com.tus.garbagesorting.garbagesorting.Common;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;


public class JwtTokenUtil {
    private static final String SIGN = "!Q@W#E$R%T1,./"; //sign

    /**
     * Create token header.payload.sign
     *
     * @param map
     * @return
     */
    public static String getToken(Map<String, String> map) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, 7); // Generate token expiry time, default 7 days
        // 创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        map.forEach((key, value) -> {
            builder.withClaim(key, value); //Loop token's user information
        });
        String token = builder.withExpiresAt(calendar.getTime()) // Specify the token expiry time
                .sign(Algorithm.HMAC256(SIGN)); // token key
        return token;
    }

    /**
     * Verify the token
     *
     * @param token
     */
    public static void verifier(String token) {
        JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    /**
     * Get token information, you can not use this method,
     * if you need to get information,
     * you can set the verifier a return value consistent with this method can be
     *
     * @param token
     * @return
     */
    public static DecodedJWT getTokenInfo(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }


}
