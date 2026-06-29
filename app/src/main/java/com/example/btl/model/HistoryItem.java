package com.example.btl.model;

public class HistoryItem {
    private String questionText;
    private String selectedAnswer;
    private String correctAnswer;
    private boolean isCorrect;
    private String topic;

    public HistoryItem(String questionText, String selectedAnswer,
                       String correctAnswer, boolean isCorrect, String topic) {
        this.questionText = questionText;
        this.selectedAnswer = selectedAnswer;
        this.correctAnswer = correctAnswer;
        this.isCorrect = isCorrect;
        this.topic = topic;
    }

    public String getQuestionText() { return questionText; }
    public String getSelectedAnswer() { return selectedAnswer; }
    public String getCorrectAnswer() { return correctAnswer; }
    public boolean isCorrect() { return isCorrect; }
    public String getTopic() { return topic; }

    // Serialize to string for SharedPreferences
    public String serialize() {
        return questionText + "||" + selectedAnswer + "||" + correctAnswer
                + "||" + (isCorrect ? "1" : "0") + "||" + topic;
    }

    public static HistoryItem deserialize(String data) {
        String[] parts = data.split("\\|\\|");
        if (parts.length < 5) return null;
        return new HistoryItem(parts[0], parts[1], parts[2],
                "1".equals(parts[3]), parts[4]);
    }
}
