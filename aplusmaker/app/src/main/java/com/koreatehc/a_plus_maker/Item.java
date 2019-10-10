package com.koreatehc.a_plus_maker;

public class Item {
    String question;
    int num;

    public String getIndex() {
        return question;
    }
    public int getNum() { return num;}
    public Item(String index, int num) {
        this.question = index;
        this.num = num;
    }
}
