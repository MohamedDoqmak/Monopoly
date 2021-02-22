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
//<editor-fold defaultstate="collapsed" desc="tax">
public class Tax extends Block{
    private final int amount;
    public Tax(){ super(0,0,0,""); amount=0; }
    public Tax(int n, int x, int y, int a,String name){ super(n,x,y,name); amount=a; }
    public void DecPlayerMoney(Player pl){ pl.payMoney(amount); }
    //placeholder for the overriden function
}
//</editor-fold>
