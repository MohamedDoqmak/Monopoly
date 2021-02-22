/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.io.Serializable;
import java.util.HashSet;

/**
 *
 * @author HP
 */
//<editor-fold defaultstate="collapsed" desc="group">
public class Group implements Serializable{
    private final String colour;
    private HashSet<Property> map=new HashSet<>();
    private final int noOfProp;
    
    public Group(){
        colour="";
        noOfProp=0;
    }
    public Group(int num,String col){
        colour=col;
        noOfProp=num;
    }
    public String getColour(){ return colour; }
    public HashSet getSet(){ return map; }
    public int getNumofProp(){ return noOfProp; }
    public void addProperty(Property p){ map.add(p); }
}
//</editor-fold>