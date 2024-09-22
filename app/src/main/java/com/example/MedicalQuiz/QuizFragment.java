package com.example.MedicalQuiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class QuizFragment extends Fragment {

    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        TextView questionText = view.findViewById(R.id.question_text);
        RadioGroup answersGroup = view.findViewById(R.id.answers_group);
        Button backButton = view.findViewById(R.id.back_button);
        Button submitButton = view.findViewById(R.id.submit_button);

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        updateQuestion(questionText, answersGroup);

        backButton.setOnClickListener(v -> navController.navigate(R.id.welcomeFragment));

        submitButton.setOnClickListener(v -> {
            int selectedAnswerId = answersGroup.getCheckedRadioButtonId();
            if (selectedAnswerId == -1) {
                Toast.makeText(getContext(), R.string.choose_answer, Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedAnswerIndex = answersGroup.indexOfChild(view.findViewById(selectedAnswerId));

            if (selectedAnswerIndex == QuizStorage.getCorrectAnswer(currentQuestionIndex)) {
                Toast.makeText(getContext(), R.string.correct_answer, Toast.LENGTH_SHORT).show();
                score++;
            } else {
                Toast.makeText(getContext(), R.string.false_answer, Toast.LENGTH_SHORT).show();
            }

            currentQuestionIndex++;

            if (currentQuestionIndex < QuizStorage.getQuestionCount()) {
                updateQuestion(questionText, answersGroup);
            } else {
                Bundle bundle = new Bundle();
                bundle.putInt("score", score);
                navController.navigate(R.id.action_quizFragment_to_resultFragment, bundle);
            }
        });

        return view;
    }

    private void updateQuestion(TextView questionText, RadioGroup answersGroup) {
        if (currentQuestionIndex >= QuizStorage.getQuestionCount()) {
            Bundle bundle = new Bundle();
            bundle.putInt("score", score);
            Navigation.findNavController(requireView()).navigate(R.id.action_quizFragment_to_resultFragment, bundle);
            return;
        }

        Animation fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in);
        questionText.setText(QuizStorage.getQuestion(requireContext(), currentQuestionIndex));
        questionText.startAnimation(fadeIn);

        answersGroup.clearCheck();
        answersGroup.removeAllViews();

        String[] answers = QuizStorage.getAnswers(requireContext(), currentQuestionIndex);
        for (String answer : answers) {
            RadioButton radioButton = new RadioButton(requireContext());
            radioButton.setText(answer);
            answersGroup.addView(radioButton);
            radioButton.startAnimation(fadeIn);
        }
    }
}
