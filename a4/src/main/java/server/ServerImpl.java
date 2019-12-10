package server;

import javax.jws.WebService;
import java.sql.*;
import java.util.ArrayList;


@WebService (endpointInterface = "server.ServerIF")
public class ServerImpl implements ServerIF {


    public String[] getTakenByPatientID(String patient_id, String given_day) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/medication";
        String userName = "root";
        String password = "rihanna";
        int taken = 0;
        int i=0;
        String[] array = new String[2];


        Connection conn = null;
        Statement stmt = null;


        //Register JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        //Open a connection
        System.out.println("Connecting to a selected database...");
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MedicationPlan medicationPlan = new MedicationPlan();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "SELECT taken FROM medication_taken WHERE patient_id= " + patient_id +" AND start_day= " +  given_day;
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Extract data from result set
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            taken = rs.getInt("taken");

            array[i]= Integer.toString(taken);
            ++i;



        }
        rs.close();
        return array;
    }



    public String[] getActivityData(String activity) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/medication";
        String userName = "root";
        String password = "rihanna";
        //int i=0;
        String[] array = new String[2];
        String start_time=null;
        String end_time=null;

        ArrayList<String> list_start=new ArrayList<String>();
        ArrayList<String> list_end=new ArrayList<String>();

        Connection conn = null;
        Statement stmt = null;


        //Register JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        //Open a connection
        System.out.println("Connecting to a selected database...");
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MedicationPlan medicationPlan = new MedicationPlan();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "SELECT start_time, end_time FROM patient_sensor WHERE activity= '" + activity + "'";
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Extract data from result set
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //taken = rs.getInt("taken");
            start_time=rs.getString("start_time");
            end_time=rs.getString("end_time");

            long s = Long.parseLong(start_time);
            long e=Long.parseLong(end_time);


            list_start.add(Long.toString(s).substring(Long.toString(s).length() - 6));
            list_end.add(Long.toString(e).substring(Long.toString(e).length() - 6));


           // list_end.add(end_time);




           // array[i]= Integer.toString(taken);
           // ++i;



        }
        System.out.println(list_start);
        System.out.println(list_end);
        rs.close();
        return array;
    }







}
