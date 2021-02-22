/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author HP
 */
//<editor-fold defaultstate="collapsed" desc="player">
 public class Player {
    //Declairing the variables that will be used in the class
    private HashMap<String,Set<Property>> list= new HashMap<>();
    private final int id; 
    private final String name, shape;
    private int money, x, y, location;
    private boolean inJail;
    private static int numOfPlayers=0;
    private int doublesRolled; //number of doubles rolled by player in the same turn
    private static int turns=0; 
    // Setters and getters for the money
    public void gainMoney(int money) { this.money += money; }
    public void payMoney(int money) { this.money -= money; }
    public int showMoney() { return money; }
    //Constructors
    public Player() {
        this("Player",1500,0,"Ball");
    }
    
    public Player(String name, int money, int location, String shape) {
        this.name = name;
        this.money = money;
        this.location = location;
        this.shape = shape;
        numOfPlayers++;
        //turns++;
        this.id=numOfPlayers;
        doublesRolled=0;
        x=0;
        y=0;
        inJail=false;
        list.put("Black", new HashSet<>());
        list.put("Cyan", new HashSet<>());
        list.put("Red", new HashSet<>());
        list.put("Blue", new HashSet<>());
        list.put("Green", new HashSet<>());
        list.put("Brown", new HashSet<>());
        list.put("Yellow", new HashSet<>());
        list.put("Orange", new HashSet<>());
        list.put("Pink", new HashSet<>());
        list.put("White", new HashSet<>());
    }
    //Functions regarding the changes in the location
    // Using the dice in the main function to get the number used in the function.
    public void changelocation(int dice) {
        int LastSquare=40; //assuming the last square before GO is 39
        //if(squareNum==30) //assuming 30 is the go to jail coordinate
        int squareNum=location+dice;
        if(location>squareNum && squareNum!=10){//increment player's money if player passes by GO and is not going to jail 
            money+=200;
        }
        location = squareNum;
        if(location>LastSquare)
            location=location-LastSquare;
        /*if(location==1) //if player ends up at GO
            money+=200;*/
        Block b=new Block();
        File Board=new File("C:\\Users\\HP\\Documents\\Fall20\\Object Oriented Programming\\Monopoly\\Board.dat");
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(Board));
            for(int i=1;i<=location+1;i++){
                b=(Block)in.readObject();
            }
            x=b.getXCoordinate();
            y=b.getYCoordinate();
            //System.out.println(b.getName());
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("Error: "+e);
        }
        /* a "board" bin file consisting of all the squares on the board will hold all block objects
        including ones from subclasses sorted according to their number, which will make things easier
        now the number of moves will be added to the player's location however it is important to note that
        "location" here is not a coordinate but in fact the number of the square the player is standing on, to
        effectively change the location, we will add the squareNum to the location as i mentioned then
        read the object with that squareNum from the bin  and make the player's coordinates equal the object's
        */
    } 
    public int getlocation() { return location; }
    public String getShape(){ return shape; }
    public String getName(){ return name; }
    public HashMap<String,Set<Property>> getProperties(){ return list; }
    public void buyProperty(Property p){ 
        list.get(p.getColor()).add(p);
        money -= p.getPrice();
        //p.setOwner(this);
        p.setStatus(true);
        /*should check if player has full group to edit rent??
        or this function could just be for adding the property to the list and changing the properties data
        and decreasing the money should be in a seperate function, perhaps not even a member function
        */
    }
    public int getID(){ return id; }
    public int getDoubles(){ return doublesRolled; }
    public void setDoubles(int d){ doublesRolled=d; }
    public static int getNumberOfPlayers(){ return numOfPlayers; }
    public int getX(){ return x; }
    public int getY(){ return y; }
    public boolean getIfInJail(){ return inJail; }
    public void setInJail(boolean j){ inJail=j; }
    public void editTurns(){
        if(turns==numOfPlayers-1)
            turns=0;
        turns++;
        switch (doublesRolled) {
            case 0 -> {
                break;
            }
            case 3 -> {
                int jail=10;
                this.changelocation(jail);
                this.setInJail(true);
                break;
            }
            default -> {
                turns--;
                break;
            }
        }
    }
    public static int getTurns(){ return turns; }
    //to access static functions use Player.getNumberOfPlayers()
    public boolean fullGroup(String color){
        boolean flag =false;
        try
        {
           
            File objects_file=new File("file.bin");
            ObjectInputStream o= new ObjectInputStream(new FileInputStream(objects_file));
            
            Group []g=new Group[10];
            
            int i=0;
            while(o.available()!=0)
            //for(int i=0;i<=10;i++)
            {    
                g[i]=(Group)(o.readObject());
                if(g[i].getColour()==color)
                   break; 
                i++;     
            }
               // the number of properties of the same color that are owned by the player
              int number_of_colors= list.get(g[i].getColour()).size();

                if(g[i].getNumofProp()==number_of_colors)
                    {
                        flag=true;
                    }
                else 
                    {
                        flag=false;
                    }
        }
        catch(IOException|ClassNotFoundException e)
        {
            System.err.println(e);
        }
        return flag;
    }
    //this function counts the number of properties of the same color owned by the player 
    //Ex: black =2
    public int Black_color_counter(String color){
        int number = 0;
         try
        {
            File objects_file=new File("file.bin");
            ObjectInputStream o= new ObjectInputStream(new FileInputStream(objects_file));
            
            Group []g=new Group[10];
            
            int i=0;
            while(o.available()!=0)
            {    
                g[i]=(Group)(o.readObject());
                if(g[i].getColour()==color)
                   break; 
                i++;     
            }
            
            // the number of black properties of the same color that are owned by the player
            
              int number_of_Blacks= list.get(g[i].getColour()).size();

                switch(number_of_Blacks)
                {
                    case 1 -> number= 1;
                       
                    case 2 -> number= 2;
                       
                    case 3 -> number= 3;
                       
                    case 4 -> number= 4;
                }
              
        }
        catch(IOException|ClassNotFoundException e)
        {
            System.err.println(e);
        }
         return number;
    } 
    public boolean ownedByCurrentPlayer(Property currentprop){
       
        Property temp=null;
        Iterator<Property> it = (list.get(currentprop.getColor())).iterator();
        for(int i=0;i<list.get(currentprop.getColor()).size();i++){
            temp=it.next();
            if(temp.getName().equals(currentprop.getName()))
                return true;
        }
        return false;
    }
 }
//</editor-fold> 
