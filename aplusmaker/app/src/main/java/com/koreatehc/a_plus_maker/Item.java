package com.koreatehc.a_plus_maker;

public class Item {
    String question;
    int num;

    public String getQusetion() {
        return question;
    }
    public int getNum() { return num;}
    public Item(String question, int num) {
        this.question = question;
        this.num = num;
    }
}
