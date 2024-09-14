package com.example.MedicalQuiz;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EdgeToEdge.enable(this);

        ImageView imageView = findViewById(R.id.expanded_image);
        imageView.setImageResource(R.drawable.welcome);
        imageView.setOnClickListener(v -> showCustomDialog());
        LottieAnimationView animationView = findViewById(R.id.test_lottie);
        LinearProgressIndicator progressIndicator = findViewById(R.id.progress_indicator);

        animationView.addAnimatorUpdateListener(animation -> {
            float progress = animation.getAnimatedFraction();
            progressIndicator.setProgress((int) (progress * 100));
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showCustomDialog() {
        Dialog dialog = new Dialog(this, R.style.CustomDialog);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setCancelable(true);

        Button closeButton = dialog.findViewById(R.id.close_button);
        closeButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}