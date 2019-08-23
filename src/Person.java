/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author austinstockhecke
 */
public class Person {
    private int score;
    
    public Person(){
        score = 0;
    }
    
    public void addPoint(){
        addPoint(1);
    }
    
    public void addPoint(int numAdd){
        score += numAdd;
    }
    
    public int score(){
        return score;
    }
}
