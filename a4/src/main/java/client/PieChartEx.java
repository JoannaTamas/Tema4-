package client;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class PieChartEx extends JFrame {

    Double avg;
   // Double getAvg;
    String activity;


    public PieChartEx(String activity,Double avg) {
        this.avg=avg;
        this.activity=activity;
        initUI();
    }

    private void initUI() {

        DefaultPieDataset dataset = createDataset();

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Pie chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private DefaultPieDataset createDataset() {

        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue(activity, avg);
       // dataset.setValue("Showering", avg2);
        //dataset.setValue("Sleeping", 12);
        dataset.setValue("Breakfast", 2);
       // dataset.setValue("Lunch", 1);
        //dataset.setValue("Others", 2);

        return dataset;
    }

    private JFreeChart createChart(DefaultPieDataset dataset) {

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Patient history daily activities",
                dataset,
                false, true, false);

        return pieChart;
    }
}

