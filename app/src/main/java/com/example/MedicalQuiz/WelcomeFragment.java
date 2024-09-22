package com.example.MedicalQuiz;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WelcomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        LottieAnimationView lottieAnimationView = view.findViewById(R.id.lottie_welcome_to_quiz);
        ProgressBar progressBar = view.findViewById(R.id.progress_indicator_welcome_to_quiz);
        Button startButton = view.findViewById(R.id.button_start);
        Button languageButton = view.findViewById(R.id.language_button);

        ValueAnimator colorAnimator = ValueAnimator.ofArgb(Color.WHITE, Color.GREEN);
        colorAnimator.setDuration(1000);
        colorAnimator.setRepeatCount(ValueAnimator.INFINITE);
        colorAnimator.setRepeatMode(ValueAnimator.REVERSE);

        colorAnimator.addUpdateListener(anim -> {
            int color = (int) anim.getAnimatedValue();
            startButton.setTextColor(color);
        });
        colorAnimator.start();

        lottieAnimationView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        languageButton.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Выберите дату")
                    .build();

            datePicker.addOnPositiveButtonClickListener(selection -> {
                long selectedTimeInMillis = selection;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String formattedDate = sdf.format(new Date(selectedTimeInMillis));

                Log.d("DATE_PICKER", "Selected time in millis: " + selectedTimeInMillis);
                Log.d("DATE_PICKER", "Formatted date: " + formattedDate);
                Snackbar.make(v, "Вы выбрали дату: " + formattedDate, Snackbar.LENGTH_LONG).show();
            });

            datePicker.show(getChildFragmentManager(), "DATE_PICKER");
        });

        startButton.setOnClickListener(v -> {

            lottieAnimationView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            lottieAnimationView.playAnimation();

            ValueAnimator progressAnimator = ValueAnimator.ofFloat(0f, 1f);
            progressAnimator.setDuration(lottieAnimationView.getDuration());
            progressAnimator.addUpdateListener(animation -> {
                float progress = animation.getAnimatedFraction();
                progressBar.setProgress((int) (progress * 100));
            });
            progressAnimator.start();

            lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    startButton.setEnabled(false);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    NavOptions navOptions = new NavOptions.Builder()
                            .setPopUpTo(R.id.welcomeFragment, false)
                            .setEnterAnim(R.anim.slide_in_left)
                            .setExitAnim(R.anim.slide_out_right)
                            .setPopEnterAnim(R.anim.slide_in_left)
                            .setPopExitAnim(R.anim.slide_out_right)
                            .build();
                    Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_quizFragment, null, navOptions);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
        });

        return view;
    }
}
