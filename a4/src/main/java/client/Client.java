package client;

import server.ServerIF;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;


public class Client {
    public static void main(String[] args) throws Exception {
//        URL url = new URL("http://localhost:7779/ws/hello?wsdl");
           String[] columnNames = {"TAKEN"};
//
//        //1st argument service URI, refer to wsdl document above
//        //2nd argument is service name, refer to wsdl document above
//        QName qname = new QName("http://server/", "ServerImplService");
//        Service service = Service.create(url, qname);
//        ServerIF hello = service.getPort(ServerIF.class);
//        Gui gui=new Gui(columnNames);
       // Gui gui=new Gui(columnNames,hello.getTakenByPatientID("1","11"));
      //  gui.createUI();



        URL url = new URL("http://localhost:7779/ws/hello?wsdl");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://server/", "ServerImplService");
        Service service = Service.create(url, qname);
        ServerIF hello = service.getPort(ServerIF.class);
        hello.getActivityData("Sleeping");




       // System.out.println(Arrays.toString(hello.getTakenByPatientID("1","11")));
    }

}

