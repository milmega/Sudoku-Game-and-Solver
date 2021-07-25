package com.company;
import javax.swing.*;

public class Frame extends JFrame {

    public Frame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Sudoku Game & Solver");

        JPanel fr = new JPanel();
        fr.setLayout(new BoxLayout(fr, BoxLayout.Y_AXIS));

        Grid tg = new Grid();
        TimeBar tb = new TimeBar();

        fr.add(tg);
        fr.add(tb);
        this.add(fr);
        this.pack();
        this.setVisible(true);
    }

}


