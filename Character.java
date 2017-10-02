package com.example.kishan.homework2;

/**
 * Created by Kishan on 10/1/2017.
 */

public class Character {
    String name;
    int attackValue,defenseValue,health;
    boolean isAttacking;



    public Character(String name, int attackValue, int defenseValue, int health){
        this.name=name;
        this.attackValue=attackValue;
        this.defenseValue=defenseValue;
        this.health=health;

}
    public int getAttackValue() {
        return attackValue;
    }
    public int getDefenseValue() {
        return defenseValue;
    }
    public String getName(){
        return this.name;
    }
    public int getHealth(){ return this.health;}
    public boolean getIsAttacking(){ return this.isAttacking;}
    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public void getsAttacked(Character attackedBy){
        this.health -= (attackedBy.getAttackValue()-defenseValue);
    }
    public void heals(){
        this.health+=(defenseValue/2);
    }
}
