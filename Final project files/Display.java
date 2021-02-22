/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Display implements MouseListener {
    private JFrame frame;//for displaying window//jframe needs 3 inputs name or title,width & height
    private Canvas canvas;//for drawing graphics
    private String name;
    private int width,height;//in pixels
    public volatile static boolean clicked=false;
    
    public Display(String name,int width ,int height){
        this.name=name;
        this.width=width;
        this.height=height; 
        crdisplay();
    }
    private void crdisplay(){
        frame=new JFrame(name);    
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//to make sure when you press close the game/window will close properly and not run in the background
        frame.setResizable(false);//the ability of the user to resize the window like making it smaller
        frame.setLocationRelativeTo(null);//the window will be displayed in the middle of your screen
        frame.setVisible(true);//to make sure the window is displayed if not the window will not pop up 
        canvas=new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));//to make sure the canvas will always stay to the size
        canvas.addMouseListener(this);
        frame.add(canvas);
        frame.pack();

    }//the function that will display the frame or window with the size sent
    public Canvas getcanvas(){
        return canvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        
        if(x>=665&&x<=735){
            if(y<=630&&y>=600){
                Dice d=new Dice();
                clicked = true;
                d.showdice();
                
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

 
}
