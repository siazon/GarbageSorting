package com.tus.garbagesorting.garbagesorting.Controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.tus.garbagesorting.garbagesorting.Common.JwtTokenUtil;
import com.tus.garbagesorting.garbagesorting.Mapper.PointStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class PointStoreController {
    @Autowired
    private PointStoreMapper pointStoreMapper;

    @GetMapping("/GetInviteCode")
    public ResponseEntity<Map<String, Object>> GetInviteCode(
            @RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {

        });
        String token = headers.get("token");
        DecodedJWT jwt = JwtTokenUtil.getTokenInfo(token);
        String user_id = jwt.getClaim("id").asString();
        Map<String, Object> map = new HashMap<>();
        String code = pointStoreMapper.GetGiftList(Integer.parseInt(user_id));
        if (code.length() > 0) {
            map.put("state", true);
            map.put("code", code);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } else {
            map.put("state", false);
            map.put("msg", "can not Invite");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
    }
}
