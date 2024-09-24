package dev.flamenbaum.httpserver;

import dev.flamenbaum.httpserver.config.Configuration;
import dev.flamenbaum.httpserver.config.ConfigurationManager;

/**
 *
 * Driver class for the http server
 *
 */
public class HttpServer {

    public static void main(String[] args) {
        System.out.println("Server starting...");

        ConfigurationManager.getInstance().lookup("src/main/resources/http.json");

        Configuration currentConfiguration = ConfigurationManager.getInstance().getCurrentConfiguration();
    }
}
