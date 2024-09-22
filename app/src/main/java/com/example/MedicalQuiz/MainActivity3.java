package com.example.MedicalQuiz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EdgeToEdge.enable(this);

        ImageView imageView = findViewById(R.id.expanded_image);
        imageView.setImageResource(R.drawable.welcome);
        LottieAnimationView animationView = findViewById(R.id.test_lottie);
        LinearProgressIndicator progressIndicator = findViewById(R.id.progress_indicator);
        TextView resultDateText = findViewById(R.id.resultDateText);
        TextView resultTimetext = findViewById(R.id.resultTimeText);

        imageView.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Выберите дату")
                    .build();

            datePicker.addOnPositiveButtonClickListener(selection -> {
                long selectedTimeInMillis = selection;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String formattedDate = sdf.format(new Date(selectedTimeInMillis));

//                TimeZone timeZone = TimeZone.getDefault();
//                String timeZoneDisplayName = timeZone.getDisplayName(false, TimeZone.SHORT);

                Log.d("DATE_PICKER", "Selected time in millis: " + selectedTimeInMillis);
                Log.d("DATE_PICKER", "Formatted date: " + formattedDate);
//                Log.d("DATE_PICKER", "Time zone: " + timeZoneDisplayName);

                resultDateText.setVisibility(View.VISIBLE);
                resultDateText.setText("Выбрана дата: " + formattedDate);
            });
//            Snackbar.make(v, "Вы выбрали дату: " + formattedDate, Snackbar.LENGTH_LONG).show();
//        });
            datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
        });


        animationView.setOnClickListener(v -> {
                    MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                            .setTimeFormat(TimeFormat.CLOCK_24H)
                            .setHour(12)
                            .setMinute(0)
                            .setTitleText("Выберите время")
                            .build();

            timePicker.addOnPositiveButtonClickListener(selection -> {
                int selectedHour = timePicker.getHour();
                int selectedMinute = timePicker.getMinute();
                String formattedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                Log.d("TIME_PICKER", "Selected time: " + formattedTime);
                String timeZoneDisplayName = null;
                Log.d("TIME_PICKER", "Time zone: " + timeZoneDisplayName);

                TimeZone timeZone = TimeZone.getDefault();
                timeZoneDisplayName = timeZone.getDisplayName(false, TimeZone.SHORT);

//                Snackbar.make(v, "Вы выбрали время: " + formattedTime, Snackbar.LENGTH_LONG).show();
//            });
                resultTimetext.setVisibility(View.VISIBLE);
                resultTimetext.setText("Выбрано время: " + formattedTime + " " + timeZoneDisplayName);
            });

            timePicker.show(getSupportFragmentManager(), "TIME_PICKER");
        });

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
}
//    private void showCustomDialog() {
//        Dialog dialog = new Dialog(this, R.style.CustomDialog);
//        dialog.setContentView(R.layout.dialog_layout);
//        dialog.setCancelable(true);
//
//        Button closeButton = dialog.findViewById(R.id.close_button);
//        closeButton.setOnClickListener(v -> dialog.dismiss());
//
//        dialog.show();
//    }
//}