/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class Dice extends JPanel {
    private BufferedImage i1,i2;
    private static int die1, die2; 
    public Dice(){
        i1=new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB); i2=new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
        die1=0; die2=0;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try{
            switch(die1){
                case 1 -> {
                    i1=ImageIO.read(new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming","1.png"));
                }
                case 2 -> {
                    i1=ImageIO.read(new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming\\2.png"));
                }
                case 3 -> {
                    i1=ImageIO.read(new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming\\3.png"));
                }
                case 4 -> {
                    i1=ImageIO.read(new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming\\4.png"));
                }
                case 5 -> {
                    i1=ImageIO.read(new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming\\5.png"));
                }
                case 6 -> {
                    i1=ImageIO.read(new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming\\6.png"));
                }
            }
            
            if (i1 != null) {
            g.drawImage(i1, 9, 5,100,100,this);
            }
            switch(die2){
                case 1 -> {
                    i2=ImageIO.read(new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming","1.png"));
                }
                case 2 -> {
                    i2=ImageIO.read(new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming\\2.png"));
                }
                case 3 -> {
                    i2=ImageIO.read(new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming\\3.png"));
                }
                case 4 -> {
                    i2=ImageIO.read(new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming","4.png"));
                }
                case 5 -> {
                    i2=ImageIO.read(new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming\\5.png"));
                }
                case 6 -> {
                    i2=ImageIO.read(new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming\\6.png"));
                }
            }
            if (i2 != null) {
                g.drawImage(i2, 113, 5,100,100, this);
            }
            
        }
        catch(IOException e){
            System.out.println("Error: "+e);
        }
    }
    public void showdice(){
        popup i=new popup();
        setVisible(true);
        setSize(250,150);
        Graphics g=i1.getGraphics();
        g=i2.getGraphics();
        i.c.add(this);
        
    }
    
    public int die1(){
        return ((int)(Math.random()*6+1));
    }
    public int die2(){
        return ((int)(Math.random()*6+1));
    }
    public void rolldice(Player pl){
        die1=die1(); die2=die2();
        if(die1==die2){
            pl.setDoubles(pl.getDoubles()+1);
            if(pl.getIfInJail() && pl.getDoubles()==1)
                pl.setInJail(false);
            else if(pl.getIfInJail())
                pl.setDoubles(0);
        }
        
    }
    public int getDiceSum(){
        return die1+die2;
    }
    
}

class popup extends JFrame{
    Container c;
    popup(){
        super("Dice");
        setVisible(true);
        setSize(240,150);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        c=getContentPane();
        c.setLayout(null);
    }
}