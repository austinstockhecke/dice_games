
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

    public yahtzeeGame(int numPlayers) {
        players = new Person[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Person(i + 1);
        }
        dice = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = new Dice(5);
        }
        roundsPlayed = 0;
    }

    public void play(Scanner sc) {
        while (roundsPlayed < 3) {
            for (Person player : players) {
                System.out.println("Player " + player + "'s turn (type roll)");
                playRound(sc);
                System.out.println("What category would you like to choose? Note that if you choose a category in which you don't meet criteria, you will get a 0 in that category.");
                System.out.println("UPPER: aces | twos | threes | fours | fives | sixes \nLOWER: 3-kind | 4-kind | full house | small straight | large straight | yahtzee | chance ");
                String command = sc.nextLine();
                scoreRound(player, command);
                System.out.println(player.score());

            }

            roundsPlayed++;
        }
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
            if (command.equals("score")) {
                printDice();
                return;
            }
            System.out.println("What would you like to do? (Enter 'all' to roll all, 'some' to specify which dice, or 'score' to see score)" + rerolls);
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
        System.out.println("Entering scoreRound");
        boolean isValid;
        switch (command) {
            case "aces":
                player.addPoint(addScoreForUpper(1));
            case "twos":
                player.addPoint(addScoreForUpper(2));
            case "threes":
                player.addPoint(addScoreForUpper(3));
            case "fours":
                player.addPoint(addScoreForUpper(4));
            case "fives":
                player.addPoint(addScoreForUpper(5));
            case "sixes":
                player.addPoint(addScoreForUpper(6));

            case "3-kind": player.addPoint(sumDice(isCategory(3)));
            case "4-kind": player.addPoint(sumDice(isCategory(4)));
            case "full house":
            case "large straight":
            case "small straight":
            case "yahtzee":
            case "chance":
                diceFreq();
        }

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

    private boolean isCategory(int n) {
        int[] freq = diceFreq();

        for (int i = 0; i < 6; i++) {
            if (freq[i] == n) {
                return true;
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

}
