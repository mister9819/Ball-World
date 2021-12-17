package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.adapter.DispatchAdapter;

import java.awt.*;

import static spark.Spark.*;


/**
 * The paint world controller creates the adapter(s) that communicate with the view.
 * The controller responds to requests from the view after contacting the adapter(s).
 */
public class BallWorldController {

    /**
     * The main entry point into the program.
     * @param args  The program arguments normally specified on the cmd line
     */
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getHerokuAssignedPort());
        Gson gson = new Gson();
        DispatchAdapter dis = new DispatchAdapter();

        post("/load", (request, response) -> {
            return gson.toJson(dis.loadBall(request.queryParams("strategies"), request.queryParams("type")));
        });

        post("/switch", (request, response) -> {
            dis.switchStrategy(request.queryParams("strategies"));
            return gson.toJson("switch strategies");
        });

        get("/update", (request, response) -> {
            return gson.toJson(dis.updateBallWorld());
        });

        post("/canvas/dims", (request, response) -> {
            dis.setCanvasDims(request.body());
            return gson.toJson("set canvas dimensions");
        });

        get("/remove/:id", (request, response) -> {
            dis.removeFew(request.params("id"));
            return gson.toJson("removed some balls in paint world");
        });

        get("/clear", (request, response) -> {
            dis.removeAll();
            return gson.toJson("removed all balls in paint world");
        });

        // GET request for ballworldstore
        get("/ballworldstore", (request, response) -> {
            response.redirect("/");
            return gson.toJson("Redirected");
        });

        // handle unexpected requests
        get("*", (request, response) -> {
            response.redirect("/");
            return gson.toJson("Redirected");
        });
    }

    /**
     * Get the heroku assigned port number.
     * @return The port number
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
