/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

/**
 *
 * @author HP
 */
//<editor-fold defaultstate="collapsed" desc="go to jail">
public class GoToJail extends Block{
    public GoToJail(){ super(0,1015 , 54,""); }
    public GoToJail(int x, int y){ super(30,x,y,"GoToJail"); }
    public void sendPlayerToJail(Player pl){
        int jail=10;
        if(jail<pl.getlocation())
            jail=39-jail;
        else
            jail-=pl.getlocation();
        pl.changelocation(jail);
        pl.setInJail(true);
    }
}
//</editor-fold>
