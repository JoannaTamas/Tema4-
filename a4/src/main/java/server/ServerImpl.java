package server;

import javax.jws.WebService;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


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
        String sql = "SELECT taken FROM medication_taken WHERE patient_id = " + patient_id +" AND start_day= '" + given_day + "'";
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

    private Double getAverage(String activity) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/medication";
        String userName = "root";
        String password = "rihanna";
        //int i=0;
        String[] array = new String[14];
        String start_time=null;
        String end_time=null;
        int k=0;
        Long result;
        Double average;

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
        String sql = "SELECT start_time, end_time FROM daily_activities WHERE activity= '" + activity + "'";
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
            // System.out.println("START " +start_time);
            end_time=rs.getString("end_time");
            //  System.out.println("END "+ end_time);

            long s = Long.parseLong(start_time);
            long e=Long.parseLong(end_time);


            list_start.add(Long.toString(s).substring(Long.toString(s).length() - 6));
            // System.out.println(list_start.size());
            list_end.add(Long.toString(e).substring(Long.toString(e).length() - 6));

            Date date1=null;
            Date date2=null;


            String str1= Arrays.toString(splitToNChar(list_start.get(k), 2));
            String str2=Arrays.toString(splitToNChar(list_end.get(k), 2));
            try {
                date1=new SimpleDateFormat("[hh,mm,ss]").parse(str1);
                // System.out.println("START "+ date1);
                date2=new SimpleDateFormat("[hh,mm,ss]").parse(str2);
                // System.out.println("END "+ date2);

            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            result=toMinutes(date1,date2);
            // System.out.println(result);

            array[k]= Long.toString(result);
            //System.out.println(array[k]);
            k++;


        }
        rs.close();

        average=getAverage(array);


        return average;

    }



    public HashMap<String,Double> getActivityData() throws SQLException {
        Double average;
      ArrayList<String> list=new ArrayList<String>();
      list.add(0,"Sleeping");
      list.add(1,"Snack");
      HashMap<String,Double> hash=new HashMap<String, Double>();
      for(int i=0;i<list.size();i++){
          average=getAverage(list.get(i));
          hash.put(list.get(i),average);


      }


    return hash;

    }

    private static String[] splitToNChar(String text, int size) {
        List<String> parts = new ArrayList<String>();

        int length = text.length();
        for (int i = 0; i < length; i += size) {
            parts.add(text.substring(i, Math.min(length, i + size)));
        }
        return parts.toArray(new String[0]);
    }

//    private Long toMinutes(Date stringRepresentingDate){
//        SimpleDateFormat yourStandardDateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy");
//        Date date = null;
//        try {
//            date = yourStandardDateFormat.parse( stringRepresentingDate.toString() );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//       long minutes1 = date.getTime() / 60000;
//        return minutes1;
//    }

    private Long toMinutes(Date d1, Date d2){

        long diffMinutes = 0;
        long diffHours=0;
        long diffSeconds;
        long total=0;

        try {

            long diff = d2.getTime() - d1.getTime();
//            long diffMinutes = diff / (60 * 1000) % 60;
//            long diffHours = diff / (60 * 60 * 1000) % 24;

             diffSeconds = diff / 1000 % 60;
             diffMinutes = diff / (60 * 1000) % 60;
             diffHours = diff / (60 * 60 * 1000) % 24;

             total=(diffHours*60 )+ diffMinutes + (diffSeconds/60);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;

    }

    private Double getAverage(String[] array){
        double total=0;
        for(int i=0;i<array.length;i++){
            total = total + Integer.parseInt(array[i]);
        }
        double average= total/array.length;
        return average;
    }











}
