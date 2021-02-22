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
//<editor-fold defaultstate="collapsed" desc="go">
public class GO extends Block{
    public GO(){ super(0,1048 , 743,""); }
    public GO(int x, int y){ super(0,x,y,"GO"); }
    public void IncPlayerMoney(Player pl){ pl.gainMoney(200); }
    //placeholder for the overriden function
}
//</editor-fold>
