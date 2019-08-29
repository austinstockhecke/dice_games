
/**
 *
 * @author austinstockhecke
 */
public class YahtzeeGame {

    private final Dice[] dice;

    public YahtzeeGame() {

        dice = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = new Dice(5);
        }
    }

    public void rollSome(String[] diceToRoll) {
        for (String thisDice : diceToRoll) {
            dice[Integer.parseInt(thisDice) - 1].roll();
        }
        printDice();
    }

    private void printDice() {
        for (Dice i : dice) {
            System.out.print(i);
        }
        System.out.println("\n");
    }

    public void roll() {
        for (int i = 0; i < 5; i++) {
            dice[i].roll();
        }
        printDice();
    }

    public int scoreRound(int command) {
        switch (command) {
            case 1:
                return addScoreForUpper(1);
            case 2:
                return addScoreForUpper(2);
            case 3:
                return addScoreForUpper(3);
            case 4:
                return addScoreForUpper(4);
            case 5:
                return addScoreForUpper(5);
            case 6:
                return addScoreForUpper(6);

            case 7: // 3 of a kind
                return sumDice(isCategory(3, true));
            case 8: // 4 of a kind
                return sumDice(isCategory(4, true));
            case 9: // full house
            case 10: // large straight
                return sumDice(isCategory(1, false));
            case 11: // small straight
                return sumDice(isCategory(2, false));
            case 12: // yahtzee!
                return yahtzee();
            case 13: // chance
                return sumDice(true);

        }
        return -1;
    }

    private int yahtzee() {
        int first = dice[0].lastRoll();

        for (int i = 1; i < 5; i++) {
            if (first != dice[i].lastRoll()) {
                return 0;
            }
        }
        return 100;
    }

    private int addScoreForUpper(int category) {
        int score = 0;
        for (int i = 0; i < 5; i++) {
            if (dice[i].lastRoll() == category) {
                score += category;
            }
        }
        return score;
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
    
    public int scoreGame(int[] array){
        int sum = 0, total = 0;
        
        for (int i = 1; i < 7; i ++){
            sum += array[i];
        }
        if (sum > 62){
            total += 35;
        }
         return total;
    }
   
}

/*private void playRound(String command) {

        int rerolls = 0;
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

    }*/
 /* private void setCategories(ArrayList<YahtzeeCategory> categories){
        String test = "aces twos threes fours fives sixes 3-kind 4-kind full-house small-straight large-straight yahtzee chance";
        String[] array = test.split("\\s+");
        for(int i = 0; i < array.length; i++){
            categories.add(new YahtzeeCategory(array[i]));
        }
    }
 */


    /*public void play(Scanner sc) {
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
    }*/
