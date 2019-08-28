
import java.util.ArrayList;
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        YahtzeeGame yz = new YahtzeeGame();
        ArrayList<String> categoryUsed = new ArrayList<>();
        Person player = new Person(1);
        System.out.println("Welcome to Yahtzee!");
        int roundsPlayed = 0;
        while (roundsPlayed < 2) {
            int rerolls = 0;
            boolean roundOver = false;
            System.out.println("");

            while (!roundOver) {
                System.out.print("Type 'roll', 'roll some', or 'score': ");
                String command = sc.nextLine();

                if (command.equals("roll some") && rerolls > 0) {
                    System.out.println("Enter which dice (1-5) separated by a space");
                    String[] diceToRoll = sc.nextLine().split("\\s+");
                    yz.rollSome(diceToRoll);
                    ++rerolls;
                }

                if (command.equals("roll")) {
                    yz.roll();
                    ++rerolls;
                }

                if (rerolls > 2 || command.equals("score")) {
                    roundOver = true;
                }

            }
           
            System.out.println("\nWhat category would you like to choose? Note that if you choose a category in which you don't meet criteria, you will get a 0 in that category.");
            System.out.println("Also, for now: keep track of the categories you've already used.");
            System.out.println("UPPER: aces | twos | threes | fours | fives | sixes \nLOWER: 3-kind | 4-kind | full house | small straight | large straight | yahtzee | chance ");
            String command = sc.nextLine();
            player.addPoint(yz.scoreRound(command));
            System.out.println(player.score());
            ++roundsPlayed;
        }
    }
}
