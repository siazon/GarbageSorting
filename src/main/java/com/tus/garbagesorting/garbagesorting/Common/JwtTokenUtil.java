package com.tus.garbagesorting.garbagesorting.Common;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;


public class JwtTokenUtil {
    private static final String SIGN = "!Q@W#E$R%T1,./"; //签名

    /**
     * 生成token令牌 header.payload.sign
     *
     * @param map
     * @return
     */
    public static String getToken(Map<String, String> map) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, 7); // 生成token过期时间,默认7天过期
        // 创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        map.forEach((key, value) -> {
            builder.withClaim(key, value); //循环token的用户信息
        });
        String token = builder.withExpiresAt(calendar.getTime()) // 指定token过期时间
                .sign(Algorithm.HMAC256(SIGN)); // token的密钥
        return token;
    }

    /**
     * 验证token的合法性
     *
     * @param token
     */
    public static void verifier(String token) {
        JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    /**
     * 获取token信息，可以不用这个方法，如果需要获取信息可以将verifier设置一个返回值跟这个方法一致即可
     *
     * @param token
     * @return
     */
    public static DecodedJWT getTokenInfo(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }
    

}
