package de.nonbi.api;

import de.nonbi.api.route.RouteManager;
import io.javalin.Javalin;

import java.util.Scanner;

public class Main {

    private static Javalin app;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPort: ");
        int port;
        if (scanner.nextLine().isEmpty()) {
            port = 7070;
        }else {
            port = Integer.valueOf(scanner.nextLine());
        }
        app = Javalin.create().start(port);
        RouteManager.registerAll(app);
    }

    public static Javalin getApp() {
        return app;
    }
}