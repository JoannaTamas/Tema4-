package server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ServerIF {
    @WebMethod
    String[] getTakenByPatientID(String patient_id, String given_day) throws SQLException;
    @WebMethod
     String[] getActivityData(String activity) throws SQLException;


}
