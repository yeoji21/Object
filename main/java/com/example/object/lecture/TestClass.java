package com.example.object.lecture;

public class TestClass {
    public static void main(String[] args) {
        Digimon a = new Digimon();
        a.attack();
    }
}

class Monster{
    public void attack(){
        System.out.println(skill()+"!!");
    }

    public String skill(){
        return "BASIC";
    }
}

class Digimon extends Monster{
    @Override
    public String skill() {
        return "FIRE";
    }

}