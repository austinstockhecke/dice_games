
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
public class yahtzeeGame {

    private Dice[] dice;
    private Person[] players;
    private int roundsPlayed;
    private int[][] categoryPoints;

    public yahtzeeGame(int numPlayers) {
        players = new Person[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Person(i + 1);
        }
        dice = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = new Dice(5);
        }
        categoryPoints = new int[numPlayers][14];
        roundsPlayed = 0;
    }

    public void play(Scanner sc) {
        while (roundsPlayed < 1) {
            for (Person player : players) {
                System.out.println("Player " + player + "'s turn (type roll)");
                playRound(sc);
                System.out.println("What category would you like to choose? Note that if you choose a category in which you don't meet criteria, you will get a 0 in that category.");
                System.out.println("Also, for now: keep track of the categories you've already used.");
                System.out.println("UPPER: aces | twos | threes | fours | fives | sixes \nLOWER: 3-kind | 4-kind | full house | small straight | large straight | yahtzee | chance ");
                String command = sc.nextLine();
                scoreRound(player, command);
                System.out.println(player.score());
               

            }

            roundsPlayed++;
        }
        printResults();
    }

    private void rollSome(String[] whichDice) {
        for (String whichDice1 : whichDice) {
            int di = Integer.parseInt(whichDice1) - 1;
            dice[di].roll();
        }
    }

    private void playRound(Scanner sc) {

        int rerolls = 0;
        String command = sc.nextLine();
        System.out.println();
        while (true) {
            if (command.equals("roll")) {
                rollAll();
                printDice();
                rerolls++;
            }

            if (command.equals("some")) {
                System.out.println("which dice? (input dice 1-5 separated by a space");
                command = sc.nextLine();
                String[] whichDice = command.split("\\s+");
                rollSome(whichDice);
                printDice();
                rerolls++;
            }

            if (command.equals("all")) {
                rollAll();
                printDice();

            }
            if (rerolls > 2) {
                return;
            }
            if (command.equals("score") && rerolls > 0) {
                printDice();
                return;
            }
            System.out.println("What would you like to do? (Enter 'all' to roll all, 'some' to specify which dice, or 'score' to see score)");
            command = sc.nextLine();
            System.out.println();

        }

    }

    private void printDice() {
        for (Dice i : dice) {
            System.out.print(i);
        }
        System.out.println("\n");
    }

    private void rollAll() {
        for (int i = 0; i < 5; i++) {
            dice[i].roll();
        }
    }

    private void scoreRound(Person player, String command) {
        System.out.println();
        int score;
        boolean isValid1; boolean isValid2;
        switch (command) {
            case "aces":
                score = addScoreForUpper(1);
                player.addPoint(score);
                categoryPoints[player.id()-1][1] = score;
                break;
            case "twos":
                score = addScoreForUpper(2);
                player.addPoint(score);
                categoryPoints[player.id()-1][2] = score;
                break;
            case "threes":
                score = addScoreForUpper(3);
                player.addPoint(score);
                categoryPoints[player.id()-1][3] = score;
                break;
            case "fours":
                score = addScoreForUpper(4);
                player.addPoint(score);
                categoryPoints[player.id()-1][4] = score;
                break;
            case "fives":
                score = addScoreForUpper(5);
                player.addPoint(score);
                categoryPoints[player.id()-1][5] = score;
                break;
            case "sixes":
                score = addScoreForUpper(6);
                player.addPoint(score);
                categoryPoints[player.id()-1][6] = score;
                break;

            case "3-kind":
                player.addPoint(sumDice(isCategory(3, true)));
                break;
            case "4-kind":
                player.addPoint(sumDice(isCategory(4, true)));
                break;
            case "full house": 
            case "large straight":
                player.addPoint(sumDice(isCategory(1, false)));
                break;
            case "small straight":
                player.addPoint(sumDice(isCategory(2, false)));
                break;
            case "yahtzee":
                player.addPoint(yahtzee());
                break;
            case "chance":
                player.addPoint(sumDice(true));
                break;
                
        }

    }

    private int yahtzee() {
        int first = dice[0].lastRoll();
        int sum = first;

        for (int i = 1; i < 5; i++) {
            if (first != dice[i].lastRoll()) {
                return 0;
            }
            sum += first;
        }
        return sum;
    }

    private int addScoreForUpper(int category) {
        int myScore = 0;
        for (int i = 0; i < 5; i++) {
            if (dice[i].lastRoll() == category) {
                myScore += category;
            }
        }
        return myScore;
    }

    private int[] diceFreq() {
        int[] freq = new int[6];
        for (int i = 0; i < 5; i++) {
            freq[dice[i].lastRoll()]++;
        }
        return freq;

    }

    private boolean isCategory(int n, boolean kind) {
        int[] freq = diceFreq();

        for (int i = 0; i < 6; i++) {
            if (kind) {
                if (freq[i] == n) {
                    return true;
                }
            } else {
                if (freq[i] < n) {
                    return true;
                }
            }
        }

        return false;
    }

    private int sumDice(boolean isValid) {

        int result = 0;
        if (isValid) {
            for (Dice di : dice) {
                result += di.lastRoll();
            }
        }
        return result;
    }
    
    private void printResults(){
        int sum = 0;

        System.out.println("Final results:");
        for (Person player: players){
            for (int i = 1; i < 7; i++){
            sum += categoryPoints[player.id()-1][i];
        }
            if(sum > 62){
                player.addPoint(35);
            }
            System.out.println(player.score());
        }
    }
            

    /* private void setCategories(ArrayList<YahtzeeCategory> categories){
        String test = "aces twos threes fours fives sixes 3-kind 4-kind full-house small-straight large-straight yahtzee chance";
        String[] array = test.split("\\s+");
        for(int i = 0; i < array.length; i++){
            categories.add(new YahtzeeCategory(array[i]));
        }
    }
     */
}
