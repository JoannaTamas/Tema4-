package server;

import server.ServerImpl;

import javax.xml.ws.Endpoint;
import java.sql.SQLException;

public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:7779/ws/hello", new ServerImpl());
    }


}
