package com.company;

import javax.swing.*;
import java.awt.*;

public class Cell extends JLabel{
    public int x, y;
    public boolean unchangeable = false;
    public String solved = "";
    public Cell(int xc, int yc, int number){
        x = xc;
        y = yc;

        if(number != 0){
            this.setText(Integer.toString(number));
            this.solved = Integer.toString(number);
            TimeBar.emptyBoxes--;
            unchangeable = true;
        }
        this.setSize(70,70);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(new Font(null, Font.BOLD, 40));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
    }

    public void draw(String s){
        this.setText(s);
        this.setHorizontalAlignment(JLabel.LEFT);
        this.setVerticalAlignment(JLabel.TOP);
        this.setForeground(Color.GRAY);
        this.setFont(new Font(null, Font.BOLD, 30));
    }

    public void confirm(){

        if(this.getText().equals(this.solved)){
            this.setForeground(Color.BLACK);
            this.setFont(new Font(null, Font.BOLD, 40));
            this.unchangeable = true;
            TimeBar.emptyBoxes--;
            if(TimeBar.emptyBoxes == 0)
                TimeBar.winMessage();
        }
        else{
            TimeBar.wrong();
            this.setText("");
        }
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
    }
}
