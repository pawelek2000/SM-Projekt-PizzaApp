package com.example.projekt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

public class EditDoughRecipeActivity extends AppCompatActivity{
    public static final String EXTRA_EDIT_DOUGH_RECIPE_NUMBER_OF_DOUGHBALLS = "com.example.EXTRA_EDIT_DOUGH_RECIPE_NUMBER_OF_DOUGHBALLS";
    public static final String EXTRA_EDIT_DOUGH_RECIPE_DOUGHBALL_WEIGHT = "com.example.EXTRA_EDIT_DOUGH_RECIPE_DOUGHBALL_WEIGHT";
    public static final String EXTRA_EDIT_DOUGH_RECIPE_HYDRATION = "EXTRA_EDIT_DOUGH_RECIPE_HYDRATION";

    private NumberPicker doughballWeightNumberPicker;
    private NumberPicker numberOfDoughballsNumberPicker;
    private NumberPicker hydrationNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dough_recipe);
        Intent starter = getIntent();
        numberOfDoughballsNumberPicker = findViewById(R.id.number_of_doughballs);
        doughballWeightNumberPicker = findViewById(R.id.doughball_weight);
        hydrationNumberPicker = findViewById(R.id.hydration);

        numberOfDoughballsNumberPicker.setMinValue(1);
        numberOfDoughballsNumberPicker.setMaxValue(10);
        numberOfDoughballsNumberPicker.setValue(1);

        doughballWeightNumberPicker.setMinValue(240);
        doughballWeightNumberPicker.setMaxValue(320);
        doughballWeightNumberPicker.setValue(265);

        hydrationNumberPicker.setMaxValue(55);
        hydrationNumberPicker.setMaxValue(75);
        hydrationNumberPicker.setValue(60);

        final  Button button = findViewById(R.id.button_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();

                replyIntent.putExtra(EXTRA_EDIT_DOUGH_RECIPE_NUMBER_OF_DOUGHBALLS, numberOfDoughballsNumberPicker.getValue());
                replyIntent.putExtra(EXTRA_EDIT_DOUGH_RECIPE_DOUGHBALL_WEIGHT, doughballWeightNumberPicker.getValue());
                replyIntent.putExtra(EXTRA_EDIT_DOUGH_RECIPE_HYDRATION, hydrationNumberPicker.getValue());
                setResult(RESULT_OK, replyIntent);
                finish();
            }

        });
    }

}
