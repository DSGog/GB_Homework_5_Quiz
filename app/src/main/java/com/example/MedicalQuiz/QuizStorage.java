package com.example.MedicalQuiz;

public class QuizStorage {
    private static final String[] questions = {
            "Самый большой орган в организме человека?",
            "Чему равен дыхательный объём лёгких?",
            "Эта часть мозга отвечает за то, чтобы предупреждать об опасности и заботиться о том, чтобы с вами ничего не случилось"
    };

    private static final String[][] answers = {
            {"Лёгкие", "Мозг", "Кожа"},
            {"500 мл", "1500мл", "3500мл"},
            {"Кора мозга", "Амигдала", "Мозжечок"}
    };

    private static final int[] correctAnswers = {2, 0, 1};

    public static String getQuestion(int index) {
        if (index < 0 || index >= questions.length) {
            throw new ArrayIndexOutOfBoundsException("Invalid question index: " + index);
        }
        return questions[index];
    }

    public static String[] getAnswers(int index) {
        if (index < 0 || index >= answers.length) {
            throw new ArrayIndexOutOfBoundsException("Invalid question index: " + index);
        }
        return answers[index];
    }

    public static int getCorrectAnswer(int index) {
        if (index < 0 || index >= correctAnswers.length) {
            throw new ArrayIndexOutOfBoundsException("Invalid question index: " + index);
        }
        return correctAnswers[index];
    }

    public static int getQuestionCount() {
        return questions.length;
    }
}
