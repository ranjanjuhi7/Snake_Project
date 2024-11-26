import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.management.loading.PrivateClassLoader;
import javax.swing.*;

public class SnackeGame extends JPanel implements ActionListener,KeyListener {
    private class Tile{
        int x;
        int y;
        Tile(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    int boardWidth;
    int boardHeight;
    int tilesize =25;

     Tile snackeHead;
     ArrayList<Tile>snakeBody;
     
     Tile food;
     Random random;

     Timer gameloop;
     int valocityY;
     int valocityX;

    SnackeGame(int boardWidth,int boardHeight){
        this.boardWidth=boardWidth;
        this.boardHeight=boardHeight;
        setPreferredSize(new Dimension(this.boardWidth,this.boardHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        snackeHead = new Tile(5,5);
        snakeBody=new ArrayList<Tile>();


        food =new Tile(10,10);
        random=new Random();
        placeFood();

        gameloop=new Timer(1000,this);
        gameloop.start();
        valocityX=0;
        valocityY=1;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){

        for(int i=0; i<boardWidth/tilesize; i++){
            g.drawLine(i*tilesize,0, i*tilesize,boardHeight);
            g.drawLine(0, i*tilesize,boardWidth, i*tilesize);
        }
        g.setColor(Color.red);
        g.fillRect(food.x * tilesize,food.y * tilesize,tilesize,tilesize);

        g.setColor(Color.green);
        g.fillRect(snackeHead.x * tilesize,snackeHead.y * tilesize,tilesize,tilesize);


        for(int i=0; i<snakeBody.size(); i++){
            Tile snackePart=snakeBody.get(i);
            g.fillRect(snackePart.x * tilesize,snackePart.y * tilesize,tilesize,tilesize);
        }
        
    }
    public void placeFood(){
        food.x=random.nextInt(boardWidth/tilesize);
        food.y=random.nextInt(boardHeight/tilesize);
    }
    public boolean collision(Tile tile1,Tile tile2){
        return tile1.x==tile2.x && tile1.y==tile2.y;
    }
    public void move(){
        if (collision(snackeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }
        for(int i = snakeBody.size()-1; i >= 0; i--){
            Tile snakePart = snakeBody.get(i);
            if (i==0) {
                snakePart.x=snackeHead.x;
                snakePart.y=snackeHead.y;
                
            }
            else{
                Tile prevSnakePart=snakeBody.get(i-1);
                snakePart.x=prevSnakePart.x;
                snakePart.y=prevSnakePart.y;
            }
        }
        snackeHead.x+=valocityX;
        snackeHead.y+=valocityY;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
       if (e.getKeyCode()==KeyEvent.VK_UP && valocityY!=1){
        valocityX=0;
        valocityY=-1;
       }
       else if(e.getKeyCode()==KeyEvent.VK_DOWN && valocityY!=-1){
        valocityX=0;
        valocityY=1;
       }
       else if(e.getKeyCode()==KeyEvent.VK_LEFT && valocityX!=1){
        valocityX=-1;
        valocityY=0;
       }
       else if(e.getKeyCode()==KeyEvent.VK_RIGHT && valocityX!=-1){
        valocityX=1;
        valocityY=0;
       }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
   
    @Override
    public void keyTyped(KeyEvent e) {}
}

