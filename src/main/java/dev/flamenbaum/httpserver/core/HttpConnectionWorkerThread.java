package dev.flamenbaum.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);

    private Socket socket;

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        InputStream in = null;
        OutputStream out = null;
        try {

            in = socket.getInputStream();
            out = socket.getOutputStream();

            String html = "<html><head><title>Simple Java HTTP SERVER</title></head><body><h1>This page was served using my Simple Java HTTP Server</h1></body></html>";

            final String CRLF = "\n\r"; // bytes 13 , 10
            String response =
                    "HTTP/1.1 200 OK" + CRLF + // Status Line: HTTP VERSION RESPONSE_CODE RESPONSE_MESSAGE
                            "Content-Length: " + html.getBytes().length + CRLF + // HEADER
                            CRLF +
                            html +
                            CRLF + CRLF;

            out.write(response.getBytes());

            LOGGER.info("Connection Processing finish");

        } catch (IOException e) {
            LOGGER.error("Problem with communication", e);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {}
            }
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {}
            }
            if(socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {}
            }
        }
    }
}
