
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author austinstockhecke
 */
public class Dice {

    private final int numberSides;
    private int lastRoll;
    private final Random r;

    public Dice(int numberSides) {
        this.numberSides = numberSides;
        r = new Random();
    }

    public void roll() {
        lastRoll = r.nextInt(numberSides) + 1;
    }
    
    public int lastRoll(){
        return lastRoll;
    }
    
    @Override
    public String toString(){
        return "" + lastRoll + " ";
    }

}
