package monopoly;

import java.io.Serializable;
import javax.swing.*;

public class Asker implements Serializable{

    private int n;
    public String num, player1 = null, player2 = null, player3 = null, player4 = null;

    public Asker() {
    }

    public void starter() {
        JOptionPane.showMessageDialog(null, "Welcome to Monopoly");

        //start of asking
        JOptionPane.showMessageDialog(null, "Please enter the number of players(2~4)");
        num = JOptionPane.showInputDialog("Enter the number of players");
        n = Integer.parseInt(num);

        while (n < 2 || n > 4) {
            JOptionPane.showMessageDialog(null, "Please enter a number between 2 and 4");
            num = JOptionPane.showInputDialog("Enter the number of players");
            n = Integer.parseInt(num);
        }
        if (n == 2) {
            player1 = JOptionPane.showInputDialog("Enter the name of player 1");
            player2 = JOptionPane.showInputDialog("Enter the name of player 2");
        } else if (n == 3) {
            player1 = JOptionPane.showInputDialog("Enter the name of player 1");
            player2 = JOptionPane.showInputDialog("Enter the name of player 2");
            player3 = JOptionPane.showInputDialog("Enter the name of player 3");
        } else {
            player1 = JOptionPane.showInputDialog("Enter the name of player 1");
            player2 = JOptionPane.showInputDialog("Enter the name of player 2");
            player3 = JOptionPane.showInputDialog("Enter the name of player 3");
            player4 = JOptionPane.showInputDialog("Enter the name of player 4");
        }
        //end of asking

    }

    public void disp(String str) {
        JOptionPane.showMessageDialog(null, str);
    }

    public void disp(int x) {
        JOptionPane.showMessageDialog(null, x);
    }

    public void disp(boolean x) {
        JOptionPane.showMessageDialog(null, x);
    }

    public String p1getter() {
        return player1;
    }

    public String p2getter() {
        return player2;
    }

    public String p3getter() {
        return player3;
    }

    public String p4getter() {
        return player4;
    }

    public int numofplayersgetter() {
        return n;
    }
}
