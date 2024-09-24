package dev.flamenbaum.httpserver;

import dev.flamenbaum.httpserver.config.Configuration;
import dev.flamenbaum.httpserver.config.ConfigurationManager;

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
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            String html = "<html><head><title>Simple Java HTTP SERVER</title></head><body><h1>This page was served using my Simple Java HTTP Server</h1></body></html>";

            final String CRLF = "\n\r"; // bytes 13 , 10
            String response =
                    "HTTP/1.1 200 OK" + CRLF + // Status Line: HTTP VERSION RESPONSE_CODE RESPONSE_MESSAGE
                             "Content-Length: " + html.getBytes().length + CRLF + // HEADER
                            CRLF +
                            html +
                            CRLF + CRLF;

            out.write(response.getBytes());

            in.close();
            out.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
