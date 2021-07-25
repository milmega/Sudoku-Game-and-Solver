package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Solve {
    public static Map<Integer,ArrayList<Cell>> square;

    public static void run(Cell[][] grid){
        square = new HashMap<>();
        updateSquareMap(grid);
        if(solve(grid))
            System.out.println("Done");
    }

    public static int whichSquare(int x, int y){
        if(y < 3){
            if(x < 3) return 1;
            else if(x < 6) return 2;
            else return 3;
        }
        else if(y < 6){
            if(x < 3) return 4;
            else if(x < 6) return 5;
            else return 6;
        }
        else {
            if(x < 3) return 7;
            else if(x < 6) return 8;
            else return 9;
        }
    }

    public static void updateSquareMap(Cell[][] grid){
        ArrayList<Cell> arr;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(!grid[i][j].getText().equals("")) {
                    int s = whichSquare(i, j);
                    arr = square.get(s);
                    if (arr == null)
                        arr = new ArrayList<>();
                    arr.add(grid[i][j]);
                    square.put(s, arr);
                }
            }
        }
    }

    public static int[] findEmpty(Cell[][] grid){
        int[] output = new int[3]; //[0] boolean value if found or not, [1] - x, [2] - y
        output[0] = 0;
        output[1] = -1;
        output[2] = -1;

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(grid[i][j].solved.equals("")){
                    output[0] = 1;
                    output[1] = i;
                    output[2] = j;
                    return output;
                }
            }
        }
        return output;
    }

    public static boolean isSafe(Cell[][] grid, int x, int y, String v){
        for(int i = 0; i < 9; i++){
            if(grid[i][y].solved.equals(v))
                return false;
            if(grid[x][i].solved.equals(v))
                return false;
        }
        int s = whichSquare(x, y);
        for(int i = 0; i < square.get(s).size(); i++){
            if(square.get(s).get(i).solved.equals(v))
                return false;
        }
        return true;
    }

    public static void addNumber(Cell[][] grid, int x, int y, int v, int s){
        grid[x][y].solved = Integer.toString(v);
        Grid.emptyBoxes--;
        ArrayList<Cell> arr = square.get(s);
        arr.add(grid[x][y]);
        square.put(s,  arr);
    }

    public static void deleteNumber(Cell[][] grid, int x, int y, int s){
        grid[x][y].solved = "";
        Grid.emptyBoxes++;
        ArrayList<Cell> arr = square.get(s);
        arr.remove(grid[x][y]);
        square.put(s,  arr);
    }

    public static void show(Cell[][] grid){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j].setText(grid[i][j].solved);
                grid[i][j].setForeground(Color.BLACK);
                grid[i][j].setVerticalAlignment(JLabel.CENTER);
                grid[i][j].setHorizontalAlignment(JLabel.CENTER);
            }
        }
        TimeBar.timer.stop();
    }

    public static boolean solve(Cell[][] grid){
        int[] firstEmpty = findEmpty(grid);
        if(firstEmpty[0] == 0)
            return true;

        int x = firstEmpty[1];
        int y = firstEmpty[2];
        int s = whichSquare(x,y);

        for(int i = 1; i < 10; i++){
            if(isSafe(grid, x, y, Integer.toString(i))){
                addNumber(grid, x, y, i, s);
                if(solve(grid)) return true;
                deleteNumber(grid, x, y, s);
            }
        }
        return false;
    }
}
