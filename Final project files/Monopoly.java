
package monopoly;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class Monopoly {
    
    public static volatile String colour;
    public static volatile int x1,y1,x2,y2,x3,y3,x4,y4;
    
    public static void main(String[] args) {
         //if num of turns == id of player then that player gets to play
        //<editor-fold defaultstate="collapsed" desc="Initializing objects">
        
        colour= "null";
        x1=1030;
        y1=763;
        x2=1050;
        y2=743;
        x3=1050;
        y3=763;
        x4=1030;
        y4=743;
        Asker asker=new Asker();
        asker.starter();
        
        int w=1500,h=850;//approx my whole screen
        String name="MONOPOLY BOARD";
        Board board=new Board(name,w,h);
        board.start();
        Dice dice=new Dice();
        
        //initializing players
        int playernum=3; //placeholder
        ArrayList<Player> players=new ArrayList();
        String []ar=new String[4];
        String []names=new String[4];
        ar[0]="red";
        ar[1]="blue";
        ar[2]="green";
        ar[3]="orange";
        names[0]=asker.p1getter();
        names[1]=asker.p2getter();
        names[2]=asker.p3getter();
        names[3]=asker.p4getter();
        for(int i=0; i < asker.numofplayersgetter(); i++){ //make array of string colors and give each player one index
            players.add( new Player(names[i],1500,0,ar[i]) );
        }
        Player currentplayer = players.get(0);
        asker.disp("Click to roll");
        while(!gameover(Player.getNumberOfPlayers())){
            bankrupt(players,currentplayer);
            //when player clicks on button roll dice
            
            while(!Display.clicked){

            }
//            if(currentplayer.getIfInJail()){
//                //offer to have him pay to get out of jail
//            }
            dice.rolldice(currentplayer);
            currentplayer.editTurns();
            currentplayer.changelocation(dice.getDiceSum());
            moveshape(currentplayer);

            String type=getType(currentplayer.getlocation()); //read from string file
            try{
                ObjectInputStream in=new ObjectInputStream(new FileInputStream("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming\\Monopoly\\Board.dat"));
                for(int i=0; i < currentplayer.getlocation(); i++){
                    Block b= (Block)in.readObject();
            }

            switch(type){
                case "Property" -> {
                    Property prop=(Property)in.readObject();
                    
                    boolean owned=ifPropertyIsOwned(players,prop);
                    System.out.println(owned);
                    if(owned){
                        if(!currentplayer.ownedByCurrentPlayer(prop)){
                            currentplayer.payMoney(prop.getRent());
                            Player ownerOfProperty = ownerOfProperty(players,prop);
                            ownerOfProperty.gainMoney(prop.getRent());

                            asker.disp("you paid a rent of : " + prop.getRent()+" to palyer : " + ownerOfProperty.getName() );
                            asker.disp("your current money is "+currentplayer.showMoney());
                        }
                    }
                    else{
                        //offer to buy
//                       
                        String i=JOptionPane.showInputDialog("Do you want to buy this property ? yes/no ");
                        if(i.equals("yes")){
                            currentplayer.buyProperty(prop);
                            asker.disp("you now own the proprty : "+prop.getName() + "  Color :"+prop.getColor());
                            asker.disp("your current money is : "+currentplayer.showMoney());
                           }
                    }
                    break;
                }
                case "Chance" -> {
                    Chance c=(Chance)in.readObject();
                    String s;
                    c.getCard(currentplayer);

                    bankrupt(players,currentplayer);

                    break;
                }
                case "CommunityChest" -> {
                    CommunityChest c=(CommunityChest)in.readObject();
                    c.getCard(currentplayer);
                    //display card to player
                    bankrupt(players,currentplayer);    

                    break;
                }
                case "GO" -> {
                    GO g=(GO)in.readObject();
                    g.IncPlayerMoney(currentplayer);
                    moveshape(currentplayer);
                    break;
                }
                case "GoToJail" -> {
                    GoToJail p=(GoToJail)in.readObject();
                    p.sendPlayerToJail(currentplayer);
                    moveshape(currentplayer);
                    break;
                }
                case "Tax" -> {
                    Tax p=(Tax)in.readObject();
                    p.DecPlayerMoney(currentplayer);
                    asker.disp("you have paid taxes,your money decremented by sth and your current balance is now  "+currentplayer.showMoney());
                    break;
                }
                default -> {
                    Block p=(Block)in.readObject();
                    break;
                }
            }
                if(gameover(Player.getNumberOfPlayers())){
                    break;
                }
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println("Error: "+e);
            }

            System.out.println(Player.getTurns());
            currentplayer = players.get(Player.getTurns());//should be last line in loop probably
            Display.clicked=false;
        }
        System.out.println("Game over");
        
    }
    
    static void moveshape(Player p){
        switch(p.getShape()){
            case "red" -> { //assuming red is player 1
                x1=p.getX();
                y1=p.getY();
            }
            case "blue" -> { //assuming blue is player 2
                x2=p.getX();
                y2=p.getY();
            }
            case "green" -> { //assuming green is player 3
                x3=p.getX();
                y3=p.getY();
            }
            case "orange" -> { //assuming orange is player 4
                x4=p.getX();
                y4=p.getY();
            }
            default -> {
            }
        }
    }
    
    static boolean gameover(int numberOfPlayers){
        return (numberOfPlayers==1);
        
    }
    
    static String getType(int blocknum){
        
        File f=new File("types.txt");
        String temp=null;
        try{
        Scanner in=new Scanner(f);
        
        for(int i=0;i<=blocknum;i++){
            if(in.hasNextLine())
                temp=in.nextLine();
            else
                break;
        }
        in.close();
        }
        catch(IOException e){
            
        }
        return temp;
    }
    
    static boolean ifPropertyIsOwned(ArrayList<Player> listOfPlayers,Property prop){
        Player playerInList = null;
        boolean owned=false;
        for(int i=0;i<listOfPlayers.size();i++){
            playerInList = listOfPlayers.get(i);
            
            if(playerInList.ownedByCurrentPlayer(prop)){
                owned =true;
                break;
            }
        }
        return owned;
    }
    
    static Player ownerOfProperty(ArrayList<Player> listOfPlayers,Property prop){
        Player playerInList = null;
        for(int i=0;i<listOfPlayers.size();i++){
            playerInList=listOfPlayers.get(i);
            if(playerInList.ownedByCurrentPlayer(prop))
                break;
        }
        return playerInList;
    }
    
    static void bankrupt(ArrayList<Player> playerlist, Player currentplayer){
        if(currentplayer.showMoney()<=0){
            playerlist.remove(currentplayer);
            System.out.println(" you have gone bankrupt and will be removed from the game !"); //you didn't insult them, i am genuinely surprised 
        }
        
    }
    
    static void PayToGetOutOfJail(Player currentplayer){
        //offer to pay
        boolean pay=false;
        if(pay){
            currentplayer.payMoney(50);
            currentplayer.setInJail(false);
        }
    }
    
    
}
