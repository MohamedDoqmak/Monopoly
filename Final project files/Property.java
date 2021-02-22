/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.Iterator;

/**
 *
 * @author HP
 */
//<editor-fold defaultstate="collapsed" desc="property">
public class Property extends Block{
    private final int price ;
    private final String color;
    private boolean owned;
    private int rent;
    
    public Property(){
        super(0,0,0,"");
        color="Nothing";
        price=0;
        rent=0;
    }
    public Property (int price, String name, String color, int rent, int num, int x, int y){
        super(num,x,y,name);
        this.price=price;
        this.color=color;
        this.rent=rent;
        
    }
    public String getColor(){ return color; }
    public boolean isOwned(){ return owned; }
    public int getRent(){ return rent; }
    public void setStatus(boolean owned){
        this.owned=owned;
    }
    public int getPrice(){ return price; }
    public void setRent(int rent) {
        this.rent=rent;
    }
    public void updateRent(int dice,String color,Property obj,Player p) 
     {
        switch(color)
        {
            case"black":
                Iterator<Property> itr= p.getProperties().get(color).iterator();
                
               while(itr.hasNext())
               {
                      int n = p.Black_color_counter(color);
                 switch(n)
                    {
                                                                
                       case 2  ->itr.next().setRent(50);
                       case 3  ->itr.next().setRent(100);
                       case 4  ->itr.next().setRent(200);
                       }
               }
                
            
                case"white":
                {
                    Iterator<Property> itr2= p.getProperties().get(color).iterator();
                    while (itr2.hasNext()){
                    if(p.fullGroup(color)==true)
                    {
                        itr2.next().setRent(10);
                    }
                    else
                        itr2.next().setRent(4);
                }
                }
                default:
                    if(p.fullGroup(color)==true)
                    {
                        
                        Iterator<Property> itr3= p.getProperties().get(color).iterator();
                        while (itr3.hasNext())
                        {
                            itr3.next().setRent(itr3.next().getRent()*2);
                        }

                    }
                    
        }
        
    }
}
//</editor-fold>
