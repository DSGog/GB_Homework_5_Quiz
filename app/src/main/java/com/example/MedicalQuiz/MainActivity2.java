package com.example.MedicalQuiz;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("rotationX", 0f, 30f);
    PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("rotationY", 0f, 30f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView transitionText = findViewById(R.id.transition_text);

//        PropertyValuesHolder pvhTranslationX = PropertyValuesHolder.ofFloat("rotationX", 0f, 360f);
//        PropertyValuesHolder pvhTranslationY = PropertyValuesHolder.ofFloat("rotationY", 0f, 360f);
//        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 0.1f, 1f);
//        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 0.1f, 1f);
//
//        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(transitionText, pvhTranslationX, pvhScaleX, pvhScaleY);
//        animator.setDuration(2000);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        animator.start();

//        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.color_x_y);
//        animatorSet.setTarget(transitionText);
//        animatorSet.start();

        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(transitionText, View.TRANSLATION_Y.getName(), 0f, 180f);
        translationAnimator.setDuration(5000);
        translationAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        translationAnimator.setRepeatCount(ObjectAnimator.REVERSE);
        translationAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        translationAnimator.start();

        ValueAnimator colorAnimator = ValueAnimator.ofArgb(Color.BLUE, Color.RED, Color.GREEN);
        colorAnimator.setDuration(1000);
        colorAnimator.setRepeatCount(ValueAnimator.INFINITE);
        colorAnimator.setRepeatMode(ValueAnimator.REVERSE);

        colorAnimator.addUpdateListener(anim -> {
            int color = (int) anim.getAnimatedValue();
            transitionText.setTextColor(color);
        });
        colorAnimator.start();

//        transitionText.animate()
//                .translationY(550)
//                .alpha(1f)
//                .setDuration(800)
//                .setStartDelay(1000)
//                .rotation(360f)
//                .setInterpolator(new CycleInterpolator(1f))
//                .scaleX(1.3f)
//                .scaleY(1.3f)
//                .start();

        GradientTextView secondTextTransition = findViewById(R.id.second_text_transition);
        transitionText.setOnClickListener(v -> {
            secondTextTransition.setVisibility(View.VISIBLE);
        });

        ImageView imageWelcome = findViewById(R.id.image_welcome);
        imageWelcome.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                    (MainActivity2.this, imageWelcome, "welcome_transition");
            startActivity(intent, options.toBundle());
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                });

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}