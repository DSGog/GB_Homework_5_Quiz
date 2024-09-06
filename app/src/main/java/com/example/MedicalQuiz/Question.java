package com.example.MedicalQuiz;

public class Question {
    private String text;
    private String[] answers;
    private int correctAnswerIndex;

    public Question(String text, String answer1, String answer2, String answer3, int correctAnswerIndex) {
        this.text = text;
        this.answers = new String[]{answer1, answer2, answer3};
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getText() {
        return text;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
