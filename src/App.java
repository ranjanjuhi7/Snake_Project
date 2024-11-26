import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth=500;
        int boardHeight=boardWidth;
        JFrame frame=new JFrame("snack");
        frame.setVisible(true);
        frame.setSize(boardHeight,boardWidth);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        SnackeGame snackeGame=new  SnackeGame(boardWidth,boardHeight);
        frame.add(snackeGame);
        frame.pack();

       snackeGame.requestFocus();
    }
}
