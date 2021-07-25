package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Grid extends JPanel implements MouseListener, KeyListener {

    public Point prevPressedBox = null;
    public static Cell[][] boxes = new Cell[9][9];
    public static int emptyBoxes = 81;

    private final int[][] numbers =  {{0,0,0,0,0,0,0,0,0},
                                        {0,0,9,6,0,0,5,0,0},
                                        {0,0,3,0,0,1,0,2,0},
                                        {3,0,0,1,0,0,0,0,4},
                                        {7,4,0,0,0,0,0,0,0},
                                        {0,0,5,8,0,0,3,0,0},
                                        {0,0,0,0,0,7,4,0,0},
                                        {0,9,7,0,0,2,0,0,5},
                                        {0,0,1,0,0,9,6,0,0}};

    public Grid(){
        this.setPreferredSize(new Dimension(648,648));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);

        int x = 3, y = 3;

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                Cell cell = new Cell(j,i,numbers[i][j]);
                cell.setLocation(x,y);
                x += 70;
                if(j%3 == 2)
                    x += 3;
                else
                    x++;
                boxes[i][j] = cell;
                this.add(cell);
            }
            x = 3;
            y+=70;
            if(i%3 == 2)
                y += 3;
            else
                y++;
        }
        this.setVisible(true);
        Solve.run(boxes);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        TimeBar.start();

        int x = e.getY();
        int y = e.getX();

        int fixX = x/70+1;
        fixX += ((x/70)/3+1)*2;
        x -= fixX;
        x /= 70;

        int fixY = y/70+1;
        fixY += ((y/70)/3+1)*2;
        y -= fixY;
        y /= 70;

        if(prevPressedBox != null){
            boxes[(int)prevPressedBox.getX()][(int)prevPressedBox.getY()].setBorder(BorderFactory.createEmptyBorder());
        }
        boxes[x][y].setBorder(new LineBorder(Color.RED,3));
        prevPressedBox = new Point(x, y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == 32){ //press space bar solve sudoku
            Solve.show(boxes);
        }
        if(prevPressedBox == null)
            return;
        if(e.getKeyCode() > 48 && e.getKeyCode() < 58)
            draw(e.getKeyChar()); //press any number 1-9 to draw the number
        else if(e.getKeyCode() == 8) //press Delete to delete chosen number
            delete();
        else if(e.getKeyCode() == 10) //press Enter to confirm the number
            confirm();

    }

    public void draw(char n){
        int x = (int)prevPressedBox.getX();
        int y = (int)prevPressedBox.getY();

        if(boxes[x][y].getText().equals("")){
            boxes[x][y].draw(Character.toString(n));
        }
    }

    public void delete(){
        int x = (int)prevPressedBox.getX();
        int y = (int)prevPressedBox.getY();

        if(!boxes[x][y].unchangeable){
            boxes[x][y].setText("");
            boxes[x][y].setVerticalAlignment(JLabel.CENTER);
            boxes[x][y].setHorizontalAlignment(JLabel.CENTER);
            boxes[x][y].setForeground(Color.BLACK);
        }
    }

    public void confirm(){
        int x = (int)prevPressedBox.getX();
        int y = (int)prevPressedBox.getY();
        if(!boxes[x][y].getText().equals(""))
            boxes[x][y].confirm();
    }
}