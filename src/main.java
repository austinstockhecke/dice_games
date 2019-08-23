
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author austinstockhecke
 */
public class main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("How many players? ");
        int numPlayers = Integer.parseInt(sc.nextLine());
        yahtzeeGame yz = new yahtzeeGame(numPlayers);
        
        yz.play(sc);

    }
}
