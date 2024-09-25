package dev.flamenbaum.httpserver;

import dev.flamenbaum.httpserver.config.Configuration;
import dev.flamenbaum.httpserver.config.ConfigurationManager;
import dev.flamenbaum.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *
 * Driver class for the http server
 *
 */
public class HttpServer {

    private static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {
        LOGGER.info("Server starting...");

        ConfigurationManager.getInstance().lookup("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Using port: " + conf.getPort());
        LOGGER.info("Using webRoot: " + conf.getWebRoot());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebRoot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
