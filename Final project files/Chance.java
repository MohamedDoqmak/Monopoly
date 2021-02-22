
package monopoly;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author HP
 */
//<editor-fold defaultstate="collapsed" desc="chance">
public class Chance extends Block{
    private static int card=0;
    private Asker asker = new Asker();
    public Chance(){ super(0,0,0,""); }
    public Chance(int n, int x, int y){
        super(n,x,y,"Chance");
    }
    public void getCard(Player pl){
        File f=new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming\\Monopoly\\chance.txt");
        try{
            Scanner input = new Scanner(f);
            input.useDelimiter("\n");
            String st=null;
            if(card<1||card>10) //since there are only 10 cards
                card=1;
            for(int i=1;i<=card;i++)
                st=input.next();
            asker.disp(st);
            switch(card){
                case 1 -> {
                    //call function that shifts player location, send player to Mayfair
                    int mayfair=39-pl.getlocation(); 
                    
                    pl.changelocation(mayfair);
                    break;
                }
                case 2 -> {
                    //call function that shifts player location, send player to Pall Wall
                    int pallmall=11;
                    if(pallmall<pl.getlocation())
                        pallmall=39-pallmall;
                    else
                        pallmall-=pl.getlocation();
                    pl.changelocation(pallmall);
                    break;
                }
                case 3 -> {
                    //call function that shifts player location, send player to GO
                    pl.changelocation(40-pl.getlocation());
                    break;
                }
                case 4 -> {
                    //call function that shifts player location, shift player 3 spaces backwards
                    pl.changelocation(-3);
                    break;
                }
                case 5 -> {
                    //decrement player's money by 150
                    pl.payMoney(150);                                              
                    break;
                }
                case 6 -> {
                    //decrement player's money by 15
                    pl.payMoney(15);
                    break;
                }
                case 7 -> {
                    //increment player's money by 100
                    pl.gainMoney(100);
                    break;
                }
                case 8 -> {
                    //call function that shifts player location, send player to jail, do not add 200 if player passes by GO
                    
                    int jail=10;
                    if(jail<pl.getlocation())
                        jail=39-jail;
                    else
                        jail-=pl.getlocation();
                    pl.changelocation(jail);
                    pl.setInJail(true);
                    break;
                }
                case 9 -> {
                    //increment player's money by 50
                    pl.gainMoney(50);
                    break;
                }
                case 10 -> {
                    //call function that shifts player location, send player to Marylebone station
                    int mary=15;
                    if(mary<pl.getlocation())
                        mary=39-mary;
                    else
                        mary-=pl.getlocation();
                    pl.changelocation(mary);
                    break;
                }
            }
            Monopoly.moveshape(pl);
            card++;
            input.close();
        }
        catch(IOException r){
            
        }
    }
}
//</editor-fold>
