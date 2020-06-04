package com.ganzenbord;

import java.util.Random;

public class Dice {
    Random dice;
    int numberOfDice;

    public Dice(){
        this.dice = new Random();
    }

    public int throwDice(){
        this.numberOfDice = this.dice.nextInt(6 - 1 );
        if(this.numberOfDice == 0){
            this.numberOfDice = 1;
        }
        return this.numberOfDice;
    }


}
