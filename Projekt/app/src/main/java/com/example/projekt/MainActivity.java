package com.example.projekt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String CURRENT_VIEW ="CURRENT_VIEW_ID";
    public static final int NEW_DOUGH_RECIPE_ACTIVITY_REQUEST_CODE = 0;
    private int currentViewID = 0;

    private RecyclerView currentView;
    private DoughRecipeAdapter doughRecipeAdapter;
    private DoughTaskAdapter doughTaskAdapter;
    private FloatingActionButton floatingActionButton;
    private DoughRecipeViewModel doughRecipeViewModel;
    private DoughTaskViewModel doughTaskViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doughRecipeAdapter = new DoughRecipeAdapter();
        doughTaskAdapter = new DoughTaskAdapter();
        currentView = findViewById(R.id.recycler_view);
        currentView.setLayoutManager(new LinearLayoutManager(this));
        floatingActionButton = findViewById(R.id.floating_button);

        doughRecipeViewModel = ViewModelProviders.of(this).get(DoughRecipeViewModel.class);
        doughTaskViewModel = ViewModelProviders.of(this).get(DoughTaskViewModel.class);

        doughRecipeViewModel.getDoughRecipes().observe(this, recipes -> doughRecipeAdapter.setRecipies(recipes));

        List<DoughRecipe> currentRecipe = doughRecipeViewModel.getActiveDoughRecipe();
        if(!currentRecipe.isEmpty())
        {
            doughTaskViewModel.getDoughTasksByRecipeId(currentRecipe.get(0).getId()).observe(this, tasks -> doughTaskAdapter.setTasks(tasks));
            floatingActionButton.setVisibility(View.INVISIBLE);
        }
        else
        {
            doughTaskViewModel.getDoughTasksByRecipeId(-1).observe(this, tasks -> doughTaskAdapter.setTasks(tasks));
            floatingActionButton.setVisibility(View.VISIBLE);
        }


        Button tasksViewButton = findViewById(R.id.task_view_button);
        tasksViewButton.setOnClickListener(v->
        {
            currentView.setAdapter(doughTaskAdapter);
            currentViewID = 0;
            if(!currentRecipe.isEmpty())
            {
                floatingActionButton.setVisibility(View.INVISIBLE);
            }
            else
            {
                floatingActionButton.setVisibility(View.VISIBLE);
            }
            floatingActionButton.setOnClickListener(view ->
            {
                Intent intent = new Intent(MainActivity.this, EditDoughRecipeActivity.class);
                startActivityForResult(intent,NEW_DOUGH_RECIPE_ACTIVITY_REQUEST_CODE);
            });
        });

        Button historyViewButton = findViewById(R.id.history_view_button);
        historyViewButton.setOnClickListener(v->
        {
            currentView.setAdapter(doughRecipeAdapter);
            currentViewID = 1;

            floatingActionButton.setVisibility(View.INVISIBLE);

        });

        if(savedInstanceState != null)
        {
            currentViewID = savedInstanceState.getInt(CURRENT_VIEW);
        }
        switch(currentViewID) {
            case 0: {
                tasksViewButton.performClick();
                break;
            }
            case 1: {
                historyViewButton.performClick();
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            if(requestCode == NEW_DOUGH_RECIPE_ACTIVITY_REQUEST_CODE)
            {

            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_VIEW, currentViewID);
    }
    private class DoughRecipeHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private DoughRecipe doughRecipe;
        private NumberPicker numberOfDoughballsNumberPickerView;
        private NumberPicker doughballWeightNumberPickerView;
        private NumberPicker hydrationNumberPickerView;


        public DoughRecipeHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(R.layout.dough_recipe_list_item,parent,false));
            itemView.setOnClickListener(this);

            numberOfDoughballsNumberPickerView = itemView.findViewById(R.id.number_of_doughballs);
            doughballWeightNumberPickerView = itemView.findViewById(R.id.doughball_weight);
            hydrationNumberPickerView = itemView.findViewById(R.id.hydration);
        }

        public void bind(DoughRecipe doughRecipe)
        {
            this.doughRecipe = doughRecipe;
            numberOfDoughballsNumberPickerView.setValue(doughRecipe.getNumberOfDoughballs());
            doughballWeightNumberPickerView.setValue(doughRecipe.getDoughballWeight());
            hydrationNumberPickerView.setValue(doughRecipe.getHydration());
        }

        @Override
        public void onClick(View v)
        {

        }


    }

    private class DoughRecipeAdapter extends RecyclerView.Adapter<DoughRecipeHolder> {
        private List<DoughRecipe> recipes;

        @NonNull
        @Override
        public DoughRecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DoughRecipeHolder(getLayoutInflater(), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull DoughRecipeHolder holder, int position) {
            if (recipes != null) {
                DoughRecipe doughRecipe = recipes.get(position);
                holder.bind(doughRecipe);
            }
            else
                Log.d("MainActivity", "No recipes");
        }

        @Override
        public int getItemCount() {
            if (recipes != null)
                return recipes.size();
            return 0;
        }

        void setRecipies(List<DoughRecipe> recipes) {
            this.recipes = recipes;
            notifyDataSetChanged();
        }
    }
    private class DoughTaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {

        private DoughTask doughTask;

        public DoughTaskHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(R.layout.dough_task_list_item, parent, false));
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);


        }

        public void bind(DoughTask doughTask)
        {
            this.doughTask = doughTask;
        }

        @Override
        public void onClick(View v)
        {
        }

        @Override
        public boolean onLongClick(View v)
        {
            return true;
        }
    }

    private class DoughTaskAdapter extends RecyclerView.Adapter<DoughTaskHolder> {
        private List<DoughTask> tasks;

        @NonNull
        @Override
        public DoughTaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DoughTaskHolder(getLayoutInflater(), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull DoughTaskHolder holder, int position) {
            if (tasks != null) {
                DoughTask doughTask = tasks.get(position);
                holder.bind(doughTask);
            }
            else
                Log.d("MainActivity", "No books");
        }

        @Override
        public int getItemCount() {
            if (tasks != null)
                return tasks.size();
            return 0;
        }

        void setTasks(List<DoughTask> tasks) {
            this.tasks = tasks;
            notifyDataSetChanged();
        }
    }
}
