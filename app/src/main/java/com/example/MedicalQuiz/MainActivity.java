package com.example.MedicalQuiz;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.MedicalQuiz.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.languageButton.setOnClickListener(v -> {
            ShowLanguageDialog();
        });

        binding.buttonActivity2.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity2.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
            startActivity(intent, options.toBundle());
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();

            navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                if (destination.getId() == R.id.quizFragment || destination.getId() == R.id.resultFragment) {
                    binding.buttonActivity2.setVisibility(View.GONE);
                    binding.languageButton.setVisibility(View.GONE);
                } else {
                    binding.buttonActivity2.setVisibility(View.VISIBLE);
                    binding.languageButton.setVisibility(View.VISIBLE);
                }
            });
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
    }

    private void ShowLanguageDialog() {
        String[] languages = {"English", "Русский"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Language")
                .setItems(languages, (dialog, which) -> {
                    if (which == 0) {
                        setLocale("en");
                    } else if (which == 1) {
                        setLocale("ru");
                    }
                });
        builder.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }
}