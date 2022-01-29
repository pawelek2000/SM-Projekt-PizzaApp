package com.example.projekt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class EditDoughRecipeActivity extends AppCompatActivity{
    public static final String EXTRA_EDIT_DOUGH_RECIPE_NUMBER_OF_DOUGHBALLS = "com.example.EXTRA_EDIT_DOUGH_RECIPE_NUMBER_OF_DOUGHBALLS";
    public static final String EXTRA_EDIT_DOUGH_RECIPE_DOUGHBALL_WEIGHT = "com.example.EXTRA_EDIT_DOUGH_RECIPE_DOUGHBALL_WEIGHT";
    public static final String EXTRA_EDIT_DOUGH_RECIPE_HYDRATION = "com.example.EXTRA_EDIT_DOUGH_RECIPE_HYDRATION";

    public static final String EXTRA_EDIT_TPBLOK = "com.example.EXTRA_EDIT_TPBLOK";
    public static final String EXTRA_EDIT_TKBLOK = "com.example.EXTRA_EDIT_TKBLOK";
    public static final String EXTRA_EDIT_TPKULKI = "com.example.EXTRA_EDIT_TPKULKI";

    public static final String EXTRA_EDIT_TKKULKI = "com.example.EXTRA_EDIT_TKKULKI";
    public static final String EXTRA_EDIT_TPKULKI2 = "com.example.EXTRA_EDIT_TPKULKI2";


    private NumberPicker doughballWeightNumberPicker;
    private NumberPicker numberOfDoughballsNumberPicker;
    private NumberPicker hydrationNumberPicker;

    private NumberPicker TPBLOKNumberPicker;
    private NumberPicker TKBLOKNumberPicker;
    private NumberPicker TPKULKINumberPicker;

    private NumberPicker TKKULKINumberPicker;
    private NumberPicker TPKULKI2NumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dough_recipe);
        Intent starter = getIntent();
        numberOfDoughballsNumberPicker = findViewById(R.id.number_of_doughballs);
        doughballWeightNumberPicker = findViewById(R.id.doughball_weight);
        hydrationNumberPicker = findViewById(R.id.hydration);

        TPBLOKNumberPicker = findViewById(R.id.tp_blok);
        TKBLOKNumberPicker = findViewById(R.id.tk_blok);
        TPKULKINumberPicker = findViewById(R.id.tp_kulki);

        TKKULKINumberPicker = findViewById(R.id.tk_kulki);
        TPKULKI2NumberPicker = findViewById(R.id.tp_kulki2);


        numberOfDoughballsNumberPicker.setMinValue(1);
        numberOfDoughballsNumberPicker.setMaxValue(10);
        numberOfDoughballsNumberPicker.setValue(1);

        doughballWeightNumberPicker.setMinValue(240);
        doughballWeightNumberPicker.setMaxValue(320);
        doughballWeightNumberPicker.setValue(265);

        hydrationNumberPicker.setMaxValue(55);
        hydrationNumberPicker.setMaxValue(75);
        hydrationNumberPicker.setValue(60);

        TPBLOKNumberPicker.setMinValue(8);
        TPBLOKNumberPicker.setMaxValue(14);
        TPBLOKNumberPicker.setValue(12);

        TKBLOKNumberPicker.setMinValue(12);
        TKBLOKNumberPicker.setMaxValue(36);
        TKBLOKNumberPicker.setValue(24);

        TPKULKINumberPicker.setMinValue(4);
        TPKULKINumberPicker.setMaxValue(10);
        TPKULKINumberPicker.setValue(6);

        TKKULKINumberPicker.setMinValue(0);
        TKKULKINumberPicker.setMaxValue(120);
        TKKULKINumberPicker.setValue(0);

        TPKULKI2NumberPicker.setMinValue(0);
        TPKULKI2NumberPicker.setMaxValue(10);
        TPKULKI2NumberPicker.setValue(0);

        final  Button button = findViewById(R.id.button_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v)
            {

                if((TKKULKINumberPicker.getValue()==0 && TPKULKI2NumberPicker.getValue()==0 ) || (TKKULKINumberPicker.getValue()>8 && TPKULKI2NumberPicker.getValue()>4 ) )
                {
                    Intent replyIntent = new Intent();

                    replyIntent.putExtra(EXTRA_EDIT_DOUGH_RECIPE_NUMBER_OF_DOUGHBALLS, numberOfDoughballsNumberPicker.getValue());
                    replyIntent.putExtra(EXTRA_EDIT_DOUGH_RECIPE_DOUGHBALL_WEIGHT, doughballWeightNumberPicker.getValue());
                    replyIntent.putExtra(EXTRA_EDIT_DOUGH_RECIPE_HYDRATION, hydrationNumberPicker.getValue());

                    replyIntent.putExtra(EXTRA_EDIT_TPBLOK, TPBLOKNumberPicker.getValue());
                    replyIntent.putExtra(EXTRA_EDIT_TKBLOK, TKBLOKNumberPicker.getValue());
                    replyIntent.putExtra(EXTRA_EDIT_TPKULKI, TPKULKINumberPicker.getValue());

                    replyIntent.putExtra(EXTRA_EDIT_TKKULKI, TKKULKINumberPicker.getValue());
                    replyIntent.putExtra(EXTRA_EDIT_TPKULKI2, TPKULKI2NumberPicker.getValue());

                    setResult(RESULT_OK, replyIntent);
                    finish();
                }
                else
                {
                    Toast.makeText(EditDoughRecipeActivity.this, R.string.add_recipe_failure, Toast.LENGTH_LONG).show();
                }
            }

        });
    }

}
