package com.example.btl.model;

public class Question {
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private int correctAnswer; // 0=A, 1=B, 2=C, 3=D
    private String topic;

    public Question(String questionText, String optionA, String optionB,
                    String optionC, String optionD, int correctAnswer, String topic) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
        this.topic = topic;
    }

    public String getQuestionText() { return questionText; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }
    public String getOptionD() { return optionD; }
    public int getCorrectAnswer() { return correctAnswer; }
    public String getTopic() { return topic; }

    public String getOptionByIndex(int index) {
        switch (index) {
            case 0: return optionA;
            case 1: return optionB;
            case 2: return optionC;
            case 3: return optionD;
            default: return "";
        }
    }

    public String getCorrectAnswerLabel() {
        switch (correctAnswer) {
            case 0: return "A";
            case 1: return "B";
            case 2: return "C";
            case 3: return "D";
            default: return "";
        }
    }

    public String getCorrectAnswerText() {
        return getCorrectAnswerLabel() + ". " + getOptionByIndex(correctAnswer);
    }
}
