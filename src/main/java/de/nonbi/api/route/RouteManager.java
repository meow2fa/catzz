package de.nonbi.api.route;

import de.nonbi.api.annotations.Route;
import de.nonbi.api.annotations.RouteHandler;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Set;

public class RouteManager {

    public static void registerRoutes(Javalin app, String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<? extends RouteHandler>> routeClasses = reflections.getSubTypesOf(RouteHandler.class);

        for (Class<?> clazz : routeClasses) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (!method.isAnnotationPresent(Route.class)) continue;

                Route route = method.getAnnotation(Route.class);

                Handler handler;
                try {
                    handler = ctx -> method.invoke(null, ctx); // assumes static methods
                } catch (Exception e) {
                    throw new RuntimeException("Fehler beim Registrieren von " + method.getName(), e);
                }

                switch (route.method().toUpperCase()) {
                    case "GET" -> app.get(route.r(), handler);
                    case "POST" -> app.post(route.r(), handler);
                    case "PUT" -> app.put(route.r(), handler);
                    case "DELETE" -> app.delete(route.r(), handler);
                    default -> throw new IllegalArgumentException("Unsupported method: " + route.method());
                }

                System.out.println("Registered route: [" + route.method() + "] " + route.r());
            }
        }
    }
}

