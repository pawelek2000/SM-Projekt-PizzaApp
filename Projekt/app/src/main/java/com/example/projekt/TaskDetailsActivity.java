package com.example.projekt;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_DETAILS_TASK_TITLE = "com.example.EXTRA_DETAILS_TASK_TITLE";
    public static final String EXTRA_DETAILS_TASK_DATE = "com.example.EXTRA_DETAILS_TASK_DATE";
    public static final String EXTRA_DETAILS_TASK_DESCRIPTION = "com.example.EXTRA_DETAILS_TASK_DESCRIPTION";

    private TextView TitleTextView;
    private TextView DateTextView;
    private TextView DescriptionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_details);


        TitleTextView = findViewById(R.id.title_details);
        DateTextView = findViewById(R.id.date_details);
        DescriptionTextView = findViewById(R.id.description_details);

        Intent starter = getIntent();
        if (starter.hasExtra(EXTRA_DETAILS_TASK_TITLE))
            TitleTextView.setText(starter.getStringExtra(EXTRA_DETAILS_TASK_TITLE));
        if (starter.hasExtra(EXTRA_DETAILS_TASK_DATE))
            DateTextView.setText(starter.getStringExtra(EXTRA_DETAILS_TASK_DATE));
        if (starter.hasExtra(EXTRA_DETAILS_TASK_DESCRIPTION))
            DescriptionTextView.setText(starter.getStringExtra(EXTRA_DETAILS_TASK_DESCRIPTION));

    }
}
