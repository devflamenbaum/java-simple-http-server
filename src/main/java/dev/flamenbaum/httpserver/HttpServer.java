package dev.flamenbaum.httpserver;

import dev.flamenbaum.httpserver.config.Configuration;
import dev.flamenbaum.httpserver.config.ConfigurationManager;
import dev.flamenbaum.httpserver.core.ServerListenerThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * Driver class for the http server
 *
 */
public class HttpServer {

    public static void main(String[] args) {
        System.out.println("Server starting...");

        ConfigurationManager.getInstance().lookup("src/main/resources/http.json");

        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebRoot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
