package com.example.MedicalQuiz;

import android.content.Context;

public class QuizStorage {
    public static String getQuestion(Context context, int index) {
        switch (index) {
            case 0:
                return context.getString(R.string.question_1);
            case 1:
                return context.getString(R.string.question_2);
            case 2:
                return context.getString(R.string.question_3);
            default:
                throw new IllegalArgumentException("Invalid question index: " + index);
        }
    }

    public static String[] getAnswers(Context context, int index) {
        switch (index) {
            case 0:
                return new String[]{
                        context.getString(R.string.answer_1_1),
                        context.getString(R.string.answer_1_2),
                        context.getString(R.string.answer_1_3)
                };
            case 1:
                return new String[]{
                        context.getString(R.string.answer_2_1),
                        context.getString(R.string.answer_2_2),
                        context.getString(R.string.answer_2_3)
                };
            case 2:
                return new String[]{
                        context.getString(R.string.answer_3_1),
                        context.getString(R.string.answer_3_2),
                        context.getString(R.string.answer_3_3)
                };
            default:
                throw new IllegalArgumentException("Invalid question index: " + index);
        }
    }

    private static final int[] correctAnswers = {2, 0, 1};

//    public static String getQuestion(int index) {
//        if (index < 0 || index >= questions.length) {
//            throw new ArrayIndexOutOfBoundsException("Invalid question index: " + index);
//        }
//        return questions[index];
//    }
//
//    public static String[] getAnswers(int index) {
//        if (index < 0 || index >= answers.length) {
//            throw new ArrayIndexOutOfBoundsException("Invalid question index: " + index);
//        }
//        return answers[index];
//    }

    public static int getCorrectAnswer(int index) {
        if (index < 0 || index >= correctAnswers.length) {
            throw new ArrayIndexOutOfBoundsException("Invalid question index: " + index);
        }
        return correctAnswers[index];
    }

    public static int getQuestionCount() {
        return correctAnswers.length;
    }
}
