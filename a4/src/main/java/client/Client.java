package client;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.SoapBindingParameterStyle;
import server.ServerIF;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Client {
    public static void main(String[] args) throws Exception {

        String[] columnNames = {"TAKEN"};

        URL url = new URL("http://localhost:7779/ws/hello?wsdl");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://server/", "ServerImplService");
        Service service = Service.create(url, qname);
        ServerIF hello = service.getPort(ServerIF.class);
        String[] array;
        String[] array1;
        double avg1;
        double avg2;

//        array=hello.getActivityData("Sleeping");
//        array1=hello.getActivityData("Snack");
//          for(int i=0;i<array1.length;i++){
//             System.out.println(array1[i]);
//        }


        HashMap<String,Double> hash= new HashMap<String, Double>();
        hash=hello.getActivityData();
        Iterator hmIterator = hash.entrySet().iterator();
       for(Map.Entry<String,Double> entry : hash.entrySet()){
           System.out.println("Activity "+ entry.getKey());
           System.out.println("Average "+ entry.getValue());
       }


       // Gui gui=new Gui(columnNames,);
        //  Gui gui=new Gui(columnNames,hello.getTakenByPatientID("1","11"));
       // gui.createUI();
        //System.out.println(avg2);
       // System.out.println(Arrays.toString(hello.getTakenByPatientID("1","11")));
    }

    public static Double getAverage(String[] array){
        double total=0;
        for(int i=0;i<array.length;i++){
            total = total + Integer.parseInt(array[i]);
        }
        double average= total/array.length;
        return average;
    }

}

