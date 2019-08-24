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
    private int id;
    
    public Person(int id){
        score = 0;
        this.id = id;
    }
    
    public void addPoint(){
        addPoint(1);
    }
    
    public void addPoint(int numAdd){
        score += numAdd;
    }
    
    public String score(){
        return "Player " + id + "'s  score: " + score;
    }
    
    @Override
    public String toString(){
        return Integer.toString(id);
    }
}
