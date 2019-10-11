package com.koreatehc.a_plus_maker;

public class FandQItem {
    private String question;
    private String answer;

    public String getQuestion() {
        return question;
    }
    public String getAnswer() { return answer;}
    public FandQItem(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
