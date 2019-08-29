
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
        int[] categoryPoints = new int[14];
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
            System.out.println("Also, for now: keep track of the categories you've already used. Enter the number of the category you'd like to be scored in.");
            System.out.println("UPPER: 1. aces | 2. twos | 3. threes | 4. fours | 5. fives | 6. sixes \nLOWER: 7. 3 of a kind | 8. 4 of a kind | 9. full house | 10. small straight | 11. large straight | 12. yahtzee | 13. chance ");
            int command = Integer.parseInt(sc.nextLine());
            while (yz.scoreRound(command) < 0) {
                System.out.println("Not a valid command. Try again: ");
                command = Integer.parseInt(sc.nextLine());
            }
                int score = yz.scoreRound(command);
                categoryPoints[command] = score;
                player.addPoint(score);
                System.out.println(player.score());
                ++roundsPlayed;

            }

            System.out.println("\nCalculating score..\n\n");
            int bonusPoints = yz.scoreGame(categoryPoints);
            player.addPoint(bonusPoints);
            System.out.println("final results!");
            System.out.println(player.score());
        }
    }

