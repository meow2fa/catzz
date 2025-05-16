package de.nonbi.api.controller;

import de.nonbi.api.utils.Crypto;
import io.javalin.http.Context;

import java.util.Map;

public class CryptoController {
    public static void encode(Context ctx) {
        String text = ctx.queryParam("text");
        String key = ctx.queryParam("key");

        if (text == null || key == null) {
            ctx.status(400).json(Map.of("error", "Fehlender text oder key"));
            return;
        }

        String result = Crypto.encrypt(text, key);
        ctx.json(Map.of("result", result));
    }

    public static void decode(Context ctx) {
        String text = ctx.queryParam("text");
        String key = ctx.queryParam("key");

        if (text == null || key == null) {
            ctx.status(400).json(Map.of("error", "Fehlender text oder key"));
            return;
        }

        String result = Crypto.decrypt(text, key);
        ctx.json(Map.of("result", result));
    }
}
