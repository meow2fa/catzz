package de.nonbi.api.controller;

import de.nonbi.api.annotations.Comment;
import de.nonbi.api.annotations.Route;
import de.nonbi.api.annotations.RouteHandler;
import de.nonbi.api.utils.Crypto;
import io.javalin.http.Context;

import java.util.Map;

@Comment(author = "Niko", msg = "Simple De/-Encrypt Controller")
public class CryptoController implements RouteHandler {

    @Route(r = "/api/text/encode", method = "GET")
    public static void encode(Context ctx) {
        String text = ctx.queryParam("text");
        String key = ctx.queryParam("key");

        if (text == null || key == null) {
            ctx.status(400).json(Map.of("error", "Missing Key or Text"));
            return;
        }

        String result = Crypto.encrypt(text, key);
        ctx.json(Map.of("result", result));
    }

    @Route(r = "/api/text/decode", method = "GET")
    public static void decode(Context ctx) {
        String text = ctx.queryParam("text");
        String key = ctx.queryParam("key");

        if (text == null || key == null) {
            ctx.status(400).json(Map.of("error", "Missing Key or Text"));
            return;
        }

        String result = Crypto.decrypt(text, key);
        ctx.json(Map.of("result", result));
    }
}
