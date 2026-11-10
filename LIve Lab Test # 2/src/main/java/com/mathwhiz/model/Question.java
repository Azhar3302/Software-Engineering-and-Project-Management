package com.mathwhiz.model;

public class Question {
    private final String text;
    private final String type;
    private final double answer;

    public Question(String text, String type, double answer) {
        this.text = text;
        this.type = type;
        this.answer = answer;
    }
    public String getText() { return text; }
    public String getType() { return type; }
    public double getAnswer() { return answer; }
}
