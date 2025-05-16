package de.nonbi.api.controller;

import io.javalin.http.Context;

import java.util.LinkedHashMap;

public class LoginController {
    public static void handleLogin(Context ctx) {
        ctx.json(new LinkedHashMap<>() {{
            put("status", false);
            put("msg", "Login is currently invaluable");
        }});
    }
}
