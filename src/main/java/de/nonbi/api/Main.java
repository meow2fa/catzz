package de.nonbi.api;

import de.nonbi.api.route.RouteManager;
import io.javalin.Javalin;

public class Main {

    private static Javalin app;

    public static void main(String[] args) {
        app = Javalin.create().start(7070);
        RouteManager.registerRoutes(app, "de.nonbi.api.controller");
    }

    public static Javalin getApp() {
        return app;
    }
}