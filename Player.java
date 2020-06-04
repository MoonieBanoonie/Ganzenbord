package com.ganzenbord;

public class Player {
    private final String name;
    private int position;
    private final int number;
    private int prisonCount;
    private boolean inn;
    private boolean potHole;
    private boolean prison;

    public Player(String name, int number, boolean inn, boolean potHole, boolean prison){
        this.name = name;
        this.position = 0;
        this.number = number;
        this.inn = inn;
        this.potHole = potHole;
        this.prison = prison;
        this.prisonCount = 0;
    }

    public boolean isNotInn(){
        return this.inn;
    }
    public boolean isInn(){
        return this.inn = false;
    }

    public boolean outOfInn(){
        return this.inn = true;
    }

    public boolean isNotPotHole(){
        return this.potHole;
    }
    public boolean isInPothole(){
        return this.potHole = false;
    }

    public boolean outOfPothole(){
        return this.potHole = true;
    }

    public boolean isNotInPrison(){
        return this.prison;
    }
    public boolean isInPrison(){
        return this.prison = false;
    }

    public boolean outOfPrison(){
        return this.prison = true;
    }

    public int getPosition(){
        return this.position;
    }

    public void setPosition(int number){
        this.position = number;
    }

    public void increasePosition(int number){
         this.position+=number;
    }

    public void decreasePosition(int number){
         this.position-=number;
    }

    public String getName(){
        return this.name;
    }

    public int getNumber(){
        return this.number;
    }

    public String toString() {
        return this.name ;
    }

    public int countPrisonCount(){
        return this.prisonCount++;
    }



}



