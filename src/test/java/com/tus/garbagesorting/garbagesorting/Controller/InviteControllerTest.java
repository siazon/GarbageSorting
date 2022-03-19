package com.tus.garbagesorting.garbagesorting.Controller;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class InviteControllerTest {
    @Test
    public void GetInviteCode() throws Exception {
        InviteController inviteController = new InviteController();
        Map<String, String> map = new HashMap<>();
        map.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjE5IiwiZXhwIjoxNjQ4MzA1NDY1LCJ1c2VybmFtZSI6InNpYXpvbiJ9.Phx7c86uhN9TRUTskLfZ5ddOhKEPm5EgIqVHvS_Mc4E");
        inviteController.GetInviteCode(map);
    }
}