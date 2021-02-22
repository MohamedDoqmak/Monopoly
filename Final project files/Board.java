/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Board implements Runnable /*runnable is a thread can make a class run without the whole program*/ {
    private Display display;
    public int width,height;
    public String name;
    private Thread thread;
    private boolean running=false;
    private BufferStrategy bs;//a way to draw things to the screen
    private BufferedImage bi;//for buffered image
    private Graphics g;
    
    public Board(String name,int width,int height){
        this.width=width;
        this.height=height;
        this.name=name;
    }
    
    
    private void initialize(){
        display=new Display(name,width,height);
        bi=ImageLoader.loadimage("/textures/board8.png");
        display.clicked=false;
    }
    
   
    private void render(){
        bs=display.getcanvas().getBufferStrategy();
        if(bs==null){
            display.getcanvas().createBufferStrategy(3);
            return;
        }
        g=bs.getDrawGraphics();
        //clearing the window before drawing is better
        g.clearRect(0,0, width, height);
        //draw here
        g.drawImage(bi, 300, 1, null);
        //end of drawing
        
        //dice
        g.setColor(new Color(207, 255, 239));
        g.fillRect(665, 600, 70, 30);
        g.setColor(Color.BLACK);
        g.drawRect(665, 600, 70, 30);
        g.drawString("Roll Dice", 676, 619);
        
        //players
        switch(Player.getNumberOfPlayers()){
            case 2 -> {
                g.setColor(Color.RED);
                g.fillOval(Monopoly.x1 , Monopoly.y1, 20, 20);
                g.setColor(Color.BLUE);
                g.fillOval(Monopoly.x2,Monopoly.y2, 20, 20);
            }
            case 3 -> {
                g.setColor(Color.RED);
                g.fillOval(Monopoly.x1 , Monopoly.y1, 20, 20);
                g.setColor(Color.BLUE);
                g.fillOval(Monopoly.x2,Monopoly.y2, 20, 20);
                g.setColor(Color.GREEN);
                g.fillOval(Monopoly.x3 , Monopoly.y3, 20, 20);
            }
            default -> {
                g.setColor(Color.RED);
                g.fillOval(Monopoly.x1 , Monopoly.y1, 20, 20);
                g.setColor(Color.BLUE);
                g.fillOval(Monopoly.x2,Monopoly.y2, 20, 20);
                g.setColor(Color.GREEN);
                g.fillOval(Monopoly.x3 , Monopoly.y3, 20, 20);
                g.setColor(Color.ORANGE);
                g.fillOval(Monopoly.x4 , Monopoly.y4, 20, 20);
            }
    }
        
        
        
        
        bs.show();
        g.dispose();
    }
    @Override
    public void run() {
        initialize();
        while(running){
            render();
        }
        stop();
    }
    public synchronized void start()/*when working on threads(like starting and stoping)you need to put synchronized*/{
        if(running==true)
            return;
    
        running=true;
        thread =new Thread(this);
        thread.start();
    }
    public synchronized void stop(){
        if(running==false)
            return;
        running=false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
           System.out.println(ex);
        }
    }
   
}
