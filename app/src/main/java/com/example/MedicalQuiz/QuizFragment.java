package com.example.MedicalQuiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class QuizFragment extends Fragment {

    private int score = 0;
    private int currentQuestion = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        TextView questionText = view.findViewById(R.id.question_text);
        RadioGroup answersGroup = view.findViewById(R.id.answers_group);
        Button backButton = view.findViewById(R.id.back_button);
        Button submitButton = view.findViewById(R.id.submit_button);

        questionText.setText(QuizStorage.getQuestion(currentQuestion));

        for (String answer : QuizStorage.getAnswers(currentQuestion)) {
            RadioButton radioButton = new RadioButton(requireContext());
            radioButton.setText(answer);
            answersGroup.addView(radioButton);
        }

        backButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.welcomeFragment));

        submitButton.setOnClickListener(v -> {
            int selectedAnswerId = answersGroup.getCheckedRadioButtonId();
            if (selectedAnswerId == -1) {
                Toast.makeText(getContext(), "Выберите ответ?", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedAnswer = answersGroup.indexOfChild(view.findViewById(answersGroup.getCheckedRadioButtonId()));
            if (selectedAnswer == QuizStorage.getCorrectAnswer(currentQuestion)) {
                Toast.makeText(getContext(), "Правильно!", Toast.LENGTH_SHORT).show();
                score++;
            } else {
                Toast.makeText(getContext(), "Неправильно!", Toast.LENGTH_SHORT).show();
            }
            currentQuestion++;

            if (currentQuestion < QuizStorage.getQuestionCount()) {
                questionText.setText(QuizStorage.getQuestion(currentQuestion));
                answersGroup.removeAllViews();
                for (String answer : QuizStorage.getAnswers(currentQuestion)) {
                    RadioButton radioButton = new RadioButton(requireContext());
                    radioButton.setText(answer);
                    answersGroup.addView(radioButton);
                }
            } else {
                Bundle bundle = new Bundle();
                bundle.putInt("score", score);
                Navigation.findNavController(view).navigate(R.id.resultFragment, bundle);
            }
        });

        return view;
    }

}