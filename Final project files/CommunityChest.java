/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author HP
 */
//<editor-fold defaultstate="collapsed" desc="cchest">
public class CommunityChest extends Block{
    private static int card=0;
    private Asker asker = new Asker();
    public CommunityChest(){ super(0,0,0,""); }
    public CommunityChest(int n, int x, int y){
        super(n,x,y,"Community Chest");
    }
    public void getCard(Player pl) {
        File f=new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming\\Monopoly\\CommChest.txt");
        try{
            Scanner input =new Scanner(f);
            input.useDelimiter("\n");
            String st=null;
            if(card<1||card>10) //since there are only 10 cards
                card=1;
            for(int i=1;i<=card;i++)
                st=input.next();
            asker.disp(st);
            switch(card){
                case 1 -> {
                    //send player to GO
                    pl.changelocation(40-pl.getlocation());
                    break;
                }
                case 2 -> {
                    //increment player's money by 10
                    pl.gainMoney(10);
                    break;
                }
                case 3 -> {
                    //decrement player's money by 25
                    pl.payMoney(25);
                    break;
                }
                case 4 -> {
                    //send player to jail
                    int jail=10;
                    if(jail<pl.getlocation())
                        jail=39-jail;
                    else
                        jail-=pl.getlocation();
                    pl.changelocation(jail);
                    pl.setInJail(true);
                    break;
                }
                case 5 -> {
                    //increment player's money by 25
                    pl.gainMoney(25);
                    break;
                }
                case 6 -> {
                    //increment player's money by 200
                    pl.gainMoney(200);
                    break;
                }
                case 7 -> {
                    //decrement player's money by 50
                    pl.payMoney(50);
                    break;
                }
                case 8 -> {
                    //increment player's money by 100
                    pl.gainMoney(100);
                    break;
                }
                case 9 -> {
                    //increment player's money by 50
                    pl.gainMoney(50);
                    break;
                }
                case 10 -> {
                    //decrement player's money by 100
                    pl.payMoney(100);
                    break;
                }
            }
            card++;
            Monopoly.moveshape(pl);
            input.close();
        }
        catch(IOException r){
        }
        
    }
}
//</editor-fold>