package com.example.MedicalQuiz;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.appcompat.widget.AppCompatTextView;

public class GradientTextView extends AppCompatTextView {

    private int[] colors;
    private float animatedFraction;
    private Paint textPaint;

    public GradientTextView(Context context) {
        super(context);
        init();
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        colors = new int[]{
                0xFFE91E63,
                0xFFFFC107,
                0xFF8BC34A,
                0xFF00BCD4,
                0xFF3F51B5,
                0xFF9C27B0,
                0xFFFF5722
        };

        textPaint = getPaint();
        textPaint.setAntiAlias(true);

        ValueAnimator colorAnimator = ValueAnimator.ofFloat(0f, 1f);
        colorAnimator.setDuration(3000);
        colorAnimator.setRepeatCount(ValueAnimator.INFINITE);
        colorAnimator.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        colorAnimator.addUpdateListener(animation -> {
            animatedFraction = (float) animation.getAnimatedValue();
            invalidate();
        });

        colorAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Shader textShader = new LinearGradient(
                0, 0, getWidth(), 0,
                shiftColors(animatedFraction),
                null, Shader.TileMode.CLAMP
        );

        textPaint.setShader(textShader);
        super.onDraw(canvas);
    }

    private int[] shiftColors(float fraction) {
        int[] shiftedColors = new int[colors.length];
        int shift = (int) (fraction * (colors.length - 1));
        for (int i = 0; i < colors.length; i++) {
            int nextColorIndex = (i + shift) % colors.length;
            shiftedColors[i] = colors[nextColorIndex];
        }
        return shiftedColors;
    }
}
