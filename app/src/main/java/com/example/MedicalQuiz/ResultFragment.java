package com.example.MedicalQuiz;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;


public class ResultFragment extends Fragment {

    private MediaPlayer applause;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        TextView resultText = view.findViewById(R.id.result_text);
        Button restartButton = view.findViewById(R.id.button_restart);

        int score = getArguments().getInt("score", 0);
        String correctAnswersText;

        if (score == 0) {
            correctAnswersText = getString(R.string.correct_answers_count_plurals_zero);
        } else {
            correctAnswersText = getResources().getQuantityString(R.plurals.correct_answers_count_plurals, score, score);
        }
        resultText.setText(correctAnswersText);

        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(requireContext(), R.animator.fade_move);
        animatorSet.setTarget(resultText);
        animatorSet.start();

        AnimatorSet buttonAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(requireContext(), R.animator.fade_move);
        buttonAnimatorSet.setTarget(restartButton);
        buttonAnimatorSet.start();

        applause = MediaPlayer.create(requireContext(), R.raw.applause_y);
        applause.setVolume(0.1f, 0.1f);
        applause.start();

        restartButton.setOnClickListener(v -> {
            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.welcomeFragment, false)
                    .setEnterAnim(R.anim.fade_in)
                    .setExitAnim(R.anim.fade_out)
                    .setPopEnterAnim(R.anim.fade_in)
                    .setPopExitAnim(R.anim.fade_out)
                    .build();
            Navigation.findNavController(view).navigate(R.id.quizFragment, null, navOptions);
        });
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (applause != null) {
            applause.release();
            applause = null;
        }
    }
}