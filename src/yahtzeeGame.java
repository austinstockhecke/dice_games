

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
public class yahtzeeGame {
    private Dice[] dice;
    private Person[] player;
    private int roundsPlayed;
    
    public yahtzeeGame(int numPlayers){
        player = new Person[numPlayers];
        dice = new Dice[5];
        for (int i = 0; i< 5; i++){
            dice[i] = new Dice(5);
        }
        roundsPlayed = 0;
    }
    
    public void play(Scanner sc){
        System.out.println("Enter a command");
        String command = sc.nextLine();
        if (command.equals("roll")){
            rollAll();
            printDice();
        }
    }
    
    public void printDice(){
        for (Dice i : dice){
            System.out.print(i);
        }
    }
    
    public void rollAll(){
        for (int i = 0; i < 5; i++){
            dice[i].roll();
        }
    }
    
    
}
