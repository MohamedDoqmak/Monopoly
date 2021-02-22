/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.io.Serializable;

/**
 *
 * @author HP
 */
//<editor-fold defaultstate="collapsed" desc="Block">
public class Block implements Serializable{
    private final int num; //number on the board
    private final int x,y; //cooardinates
    private final String name;
    public Block(){ num=0; x=0; y=0; name=""; }
    public Block(int n, int x, int y, String name){
        num=n;
        this.x=x;
        this.y=y;
        this.name=name;
    }
    public int getNum(){ return num; }
    public int getXCoordinate(){ return x; }
    public int getYCoordinate(){ return y; }
    public String getName(){ return name; }
}
//</editor-fold>
