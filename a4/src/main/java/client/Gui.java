package client;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import org.jfree.data.general.DefaultPieDataset;
import server.ServerIF;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;


public class Gui  implements ActionListener  {
    JFrame frame, frame1;
    JButton button,button1;
    static JTable table;
    Stage stage;

    String[] columnNames;
    String[] array;
    DefaultTableModel model = new DefaultTableModel();
    Object[] o = new Object[6];
    JTextField textfield1 ,textfield;

    URL url;

    {
        try {
            url = new URL("http://localhost:7779/ws/hello?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //1st argument service URI, refer to wsdl document above
    //2nd argument is service name, refer to wsdl document above
    QName qname = new QName("http://server/", "ServerImplService");
    Service service = Service.create(url, qname);
    ServerIF hello = service.getPort(ServerIF.class);



    public Gui( String[] columnNames) {

        this.columnNames = columnNames;
        //this.array = array;
    }

    public void createUI() throws Exception {

        frame = new JFrame("Database Search Result");
        button = new JButton("Display");
        button.setBounds(120, 130, 400, 20);
        button.addActionListener(this);

        button1 = new JButton("Display chart");
        button1.setBounds(120, 230, 400, 20);
        button1.addActionListener(this);


        JLabel label = new JLabel();
        label.setText("Enter Id and date :");
        label.setBounds(10, 10, 1000, 100);

        textfield= new JTextField();
        textfield.setBounds(130, 50, 130, 30);


//
        textfield1= new JTextField();
        textfield1.setBounds(130, 90, 130, 30);



        frame.add(label);
        frame.add(textfield);
        frame.add(textfield1);


        frame.add(button);
        frame.add(button1);
        frame.setSize(300,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }



    public void actionPerformed(ActionEvent ae) {
       // button = (JButton) ae.getSource();
        if (ae.getSource() == button) {

        System.out.println("Showing Table Data.......");


        String str = textfield.getText();
        System.out.println(str);
        String str1 = textfield1.getText();
        System.out.println(str1);

        try {
            array = hello.getTakenByPatientID(str, str1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        showTableData();

    }
        else if (ae.getSource() == button1){
           PieChartEx ex= new PieChartEx();
            ex.setVisible(true);



        }
}

    public void showTableData()  {

        frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());


        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        for (String s: array) {
            if(s.equals("1")){
                o[0] = "medication taken";
            }else{
                o[0]="medication not taken";
            }

        }
            model.addRow(o);



        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400, 300);
    }



}


