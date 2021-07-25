package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TimeBar extends JPanel{
    public static int xs = 0;
    public static JLabel threeXs;
    public static JLabel clock = new JLabel();
    public static int time = 0, min = 0, sec = 0;
    public static boolean started = false;
    public static String secString = String.format("%02d", sec);
    public static String minString = String.format("%02d", min);
    public static int emptyBoxes = 81;

    static Timer timer = new Timer(1000, e -> {
        time += 1000;
        min = time/60000;
        sec = (time/1000)%60;
        secString = String.format("%02d", sec);
        minString = String.format("%02d", min);
        clock.setText(minString + ":" + secString);
    });

    public TimeBar(){
        this.setPreferredSize(new Dimension(648,25));
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(1,2));

        threeXs = new JLabel();
        threeXs.setForeground(Color.RED);
        threeXs.setText("");
        threeXs.setFont(new Font(null, Font.PLAIN, 20));
        threeXs.setHorizontalAlignment(JLabel.LEFT);
        threeXs.setVerticalAlignment(JLabel.CENTER);
        threeXs.setBorder(new EmptyBorder(0,5,0,0));

        clock.setForeground(Color.BLACK);
        clock.setText(minString + ":" + secString);
        clock.setFont(new Font(null, Font.PLAIN, 20));
        clock.setHorizontalAlignment(JLabel.RIGHT);
        clock.setVerticalAlignment(JLabel.CENTER);
        clock.setBorder(new EmptyBorder(0,0,0,5));

        this.add(threeXs);
        this.add(clock);
        this.setVisible(true);
    }

    public static void wrong(){
        if(xs == 0){
            threeXs.setText("X");
            xs++;
        }
        else if(xs == 1){
            threeXs.setText("XX");
            xs++;
        }
        else if(xs == 2){
            threeXs.setText("XXX");
            timer.stop();
            Object[] options = {"Exit"};
            JOptionPane.showOptionDialog(null, "You lost!\nTime: " + minString + ":" + secString,"Maybe next time :(",
                                        JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,options,"Exit");
            System.exit(0);
        }
    }

    public static void start(){
        if(!started){
            timer.start();
            started = true;
        }
    }

    public static void winMessage(){
        timer.stop();
        Object[] options = {"Exit"};
        JOptionPane.showOptionDialog(null, "Good job!\nTime: " + minString + ":" + secString,"You won!",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,"Exit");
        System.exit(0);
    }
}
