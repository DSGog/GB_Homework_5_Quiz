package com.example.MedicalQuiz;

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
        resultText.setText(getString(R.string.correct_answers_count) + score);

        applause = MediaPlayer.create(requireContext(), R.raw.applause_y);
        applause.setVolume(0.1f, 0.1f);
        applause.start();

        restartButton.setOnClickListener(v -> {
            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.welcomeFragment, false)
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