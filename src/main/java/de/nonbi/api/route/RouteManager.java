package de.nonbi.api.route;

import de.nonbi.api.controller.CryptoController;
import io.javalin.Javalin;

public class RouteManager {
    public static void registerAll(Javalin app) {
        app.get("/api/text/encode", CryptoController::encode);
        app.get("/api/text/decode", CryptoController::decode);
    }
}
