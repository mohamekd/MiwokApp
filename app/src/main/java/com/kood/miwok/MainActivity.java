package com.kood.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView numbersTV, familyTV, colorsTV, phrasesTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        numbersTV = findViewById(R.id.tv_numbers);
        familyTV = findViewById(R.id.tv_family);
        colorsTV = findViewById(R.id.tv_colors);
        phrasesTV = findViewById(R.id.tv_phrases);

        numbersTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openNumbersIntent = new Intent(MainActivity.this, Numbers.class);
                startActivity(openNumbersIntent);
            }
        });

        familyTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFamilyIntent = new Intent(MainActivity.this, FamilyMembers.class);
                startActivity(openFamilyIntent);
            }
        });

        colorsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openColorsIntent = new Intent(MainActivity.this, Colors.class);
                startActivity(openColorsIntent);
            }
        });

        phrasesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPhrasesIntent = new Intent(MainActivity.this, Phrases.class);
                startActivity(openPhrasesIntent);
            }
        });

    }

}