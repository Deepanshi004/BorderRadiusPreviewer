package com.example.borderradiuspreviewer;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private View box1;
    private SeekBar seekBarblr, seekBartrr, seekBarbrr, seekBartlr;
    private Button button;
    private float toplR = 0f, bottomlR = 0f, toprR = 0f, bottomrR = 0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        box1 = findViewById(R.id.box);
        seekBartlr = findViewById(R.id.seekbarTL);
        seekBartrr = findViewById(R.id.seekbarTR);
        seekBarbrr = findViewById(R.id.seekbarBR);
        seekBarblr = findViewById(R.id.seekbarBL);
        button = findViewById(R.id.btn);

        setupSeekBar(seekBartlr, 0);
        setupSeekBar(seekBartrr, 1);
        setupSeekBar(seekBarbrr, 2);
        setupSeekBar(seekBarblr, 3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint("DefaultLocale") String cssCode = String.format(
                        "border-radius: %dpx %dpx %dpx %dpx",
                        (int) toplR,
                        (int) toprR,
                        (int) bottomrR,
                        (int) bottomlR
                );
                copyToClipboard(cssCode);
            }
        });
    }

    private void copyToClipboard(String cssCode) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("CSS Code", cssCode);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "CSS copied to Clipboard", Toast.LENGTH_SHORT).show();
    }

    private void updateBoxShape() {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.parseColor("#FF6200EE"));
        shape.setCornerRadii(new float[]{
                toplR, toplR,
                toprR, toprR,
                bottomrR, bottomrR,
                bottomlR, bottomlR
        });
        box1.setBackground(shape);
    }


    private void setupSeekBar(SeekBar seekBar, final int cornerIndex) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (cornerIndex) {
                    case 0:
                        toplR = progress;
                        break;
                    case 1:
                        toprR = progress;
                        break;
                    case 2:
                        bottomrR = progress;
                        break;
                    case 3:
                        bottomlR = progress;
                        break;
                }
                updateBoxShape();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


}