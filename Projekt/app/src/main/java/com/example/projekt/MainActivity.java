package com.example.projekt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.youtube.player.YouTubeBaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String CURRENT_VIEW ="CURRENT_VIEW_ID";
    public static final int NEW_DOUGH_RECIPE_ACTIVITY_REQUEST_CODE = 0;
    public static final int YOUTUBE_REQUEST_CODE = 1;
    public static final int DOUGH_TASK_DETAILS_REQUEST_CODE = 2;
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

        doughRecipeViewModel.getDoughRecipes().observe(this, new Observer<List<DoughRecipe>>() {
            @Override
            public void onChanged(@Nullable final List<DoughRecipe> recipes) {
                List<DoughRecipe> temp;
                temp = new ArrayList<>();
                for (int i = 0; i < recipes.size();i++)
                    if(recipes.get(i).isActiveRecipe() == false)
                        temp.add(recipes.get(i));
                doughRecipeAdapter.serRecipes(temp);
            }
        });
        List<DoughRecipe> currentRecipes = doughRecipeViewModel.getActiveDoughRecipe();
        if(!currentRecipes.isEmpty())
        {
            doughTaskViewModel.getDoughTasksByRecipeId(currentRecipes.get(0).getId()).observe(this, new Observer<List<DoughTask>>() {
                        @Override
                        public void onChanged(List<DoughTask> tasks) {

                            doughTaskAdapter.setTasks(tasks);
                        }
                    });
                    floatingActionButton.setVisibility(View.INVISIBLE);
        }
        else
        {
            doughTaskViewModel.getDoughTasksByRecipeId(-1).observe(this, new Observer<List<DoughTask>>() {
                        @Override
                        public void onChanged(List<DoughTask> tasks) {
                            doughTaskAdapter.setTasks(tasks);
                        }
                    });
                    floatingActionButton.setVisibility(View.VISIBLE);
        }


        Button tasksViewButton = findViewById(R.id.task_view_button);
        tasksViewButton.setOnClickListener(v->
        {
            currentView.setAdapter(doughTaskAdapter);
            currentViewID = 0;
            if(!currentRecipes.isEmpty())
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

        Button tutorialsViewButton = findViewById(R.id.tutorials_view_button);
        tutorialsViewButton.setOnClickListener(view ->
        {
            Intent intent = new Intent(MainActivity.this, YouTubeTutorialsActivity.class);
            startActivityForResult(intent,YOUTUBE_REQUEST_CODE);
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

    @lombok.SneakyThrows
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            if(requestCode == NEW_DOUGH_RECIPE_ACTIVITY_REQUEST_CODE)
            {
                DoughRecipe doughRecipe = new DoughRecipe
                        (
                                data.getIntExtra(EditDoughRecipeActivity.EXTRA_EDIT_DOUGH_RECIPE_NUMBER_OF_DOUGHBALLS,13),
                                data.getIntExtra(EditDoughRecipeActivity.EXTRA_EDIT_DOUGH_RECIPE_DOUGHBALL_WEIGHT,13),
                                data.getIntExtra(EditDoughRecipeActivity.EXTRA_EDIT_DOUGH_RECIPE_HYDRATION,13),

                                data.getIntExtra(EditDoughRecipeActivity.EXTRA_EDIT_TPBLOK,13),
                                data.getIntExtra(EditDoughRecipeActivity.EXTRA_EDIT_TKBLOK,13),
                                data.getIntExtra(EditDoughRecipeActivity.EXTRA_EDIT_TPKULKI,13),

                                data.getIntExtra(EditDoughRecipeActivity.EXTRA_EDIT_TKKULKI,13),
                                data.getIntExtra(EditDoughRecipeActivity.EXTRA_EDIT_TPKULKI2,13)
                        );
                doughRecipeViewModel.insert(doughRecipe);
                Thread.sleep(1000);
                int taskNumber = 0;
                DoughRecipe doughRecipe2 = doughRecipeViewModel.getActiveDoughRecipe().get(0);
                DoughTask doughTask1 = new DoughTask
                        (
                                getResources().getString(R.string.mixing_ingredients),
                                getResources().getString(R.string.flour) + doughRecipe.getFlour() + "\n" + getResources().getString(R.string.water) + doughRecipe.getWater() + "\n"
                                        + getResources().getString(R.string.yeast) + doughRecipe.getYeast() + "\n" + getResources().getString(R.string.salt) + doughRecipe.getSalt() + "\n"
                                        + getResources().getString(R.string.oil)+ doughRecipe.getOliveOil(),
                                0,
                                doughRecipe2.getId(),
                                false,
                                taskNumber
                        );

                taskNumber++;
                doughTask1.setActive(true);
                doughTask1.setTaskEndDate(" ");
                doughTaskViewModel.insert(doughTask1);
                for(int i=0;i<3;i++) {
                    DoughTask doughTask2 = new DoughTask(
                            getResources().getString(R.string.dough_kneading),
                            getResources().getString(R.string.dough_kneading_description),
                            10,
                            doughRecipe2.getId(),
                            false,
                            taskNumber

                    );
                    doughTaskViewModel.insert(doughTask2);
                    taskNumber++;
                }
                DoughTask doughTask2 = new DoughTask(
                        getResources().getString(R.string.block_RT_fermentation),
                        getResources().getString(R.string.fermentation_RT),
                        doughRecipe2.getTPBLOK()*60,
                        doughRecipe2.getId(),
                        false,
                        taskNumber

                );
                doughTaskViewModel.insert(doughTask2);
                taskNumber++;

                DoughTask doughTask3 = new DoughTask(
                        getResources().getString(R.string.block_CT_fermentation),
                        getResources().getString(R.string.fermentation_CT),
                        doughRecipe.getTKBLOK()*60,
                        doughRecipe2.getId(),
                        false,
                        taskNumber

                );
                doughTaskViewModel.insert(doughTask3);
                taskNumber++;

                DoughTask doughTask4 = new DoughTask(
                        getResources().getString(R.string.ball_RT_fermentation),
                        getResources().getString(R.string.fermentation_RT),
                        doughRecipe.getTPKULKI()*60,
                        doughRecipe2.getId(),
                        false,
                        taskNumber

                );
                doughTaskViewModel.insert(doughTask4);
                taskNumber++;
                if(doughRecipe.getTKKULKI()>0)
                {
                    DoughTask doughTask5 = new DoughTask(
                            getResources().getString(R.string.ball_CT_fermentation),
                            getResources().getString(R.string.fermentation_CT),
                            doughRecipe.getTKKULKI()*60,
                            doughRecipe2.getId(),
                            false,
                            taskNumber

                    );
                    doughTaskViewModel.insert(doughTask5);
                    taskNumber++;

                    DoughTask doughTask6 = new DoughTask(
                            getResources().getString(R.string.ball_RT_fermentation2),
                            getResources().getString(R.string.fermentation_RT),
                            doughRecipe.getTPKULKI2()*60,
                            doughRecipe2.getId(),
                            false,
                            taskNumber

                    );
                    doughTaskViewModel.insert(doughTask6);
                    taskNumber++;

                }

                DoughTask endDoughTask = new DoughTask(
                        getResources().getString(R.string.endTask),
                        " ",
                        0,
                        doughRecipe2.getId(),
                        true,
                        taskNumber

                );
                doughTaskViewModel.insert(endDoughTask);
                floatingActionButton.setVisibility(View.INVISIBLE);
                finish();
                startActivity(getIntent());
            }
            if(requestCode==YOUTUBE_REQUEST_CODE)
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
        private TextView titleTextView;
        private TextView endTimeTextView;


        public DoughRecipeHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(R.layout.dough_recipe_list_item,parent,false));

            titleTextView = itemView.findViewById(R.id.hydration);
            endTimeTextView = itemView.findViewById(R.id.end_date);

            itemView.setOnClickListener(this);
        }

        public void bind(DoughRecipe doughRecipe)
        {
            this.doughRecipe = doughRecipe;
            titleTextView.setText(getResources().getString(R.string.hydration)+" "+doughRecipe.getHydration());
            endTimeTextView.setText(""+doughRecipe.getEndDate());
        }

        @Override
        public void onClick(View v)
        {

        }

    }

    private class DoughRecipeAdapter extends RecyclerView.Adapter<DoughRecipeHolder>  {
        private List<DoughRecipe> recipes;

        @NonNull
        @Override
        public DoughRecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DoughRecipeHolder(getLayoutInflater(), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull DoughRecipeHolder holder, int position)
        {
                DoughRecipe doughRecipe = recipes.get(position);
                holder.bind(doughRecipe);
        }

        @Override
        public int getItemCount()
        {
                return recipes.size();
        }
        void serRecipes(List<DoughRecipe> recipes) {
            this.recipes = recipes;
            notifyDataSetChanged();
        }
    }
    private class DoughTaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {

        private DoughTask doughTask;
        private TextView titleTextView;
        private TextView endTimeTextView;

        public DoughTaskHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(R.layout.dough_task_list_item, parent, false));
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            titleTextView = itemView.findViewById(R.id.task_title);
            endTimeTextView = itemView.findViewById(R.id.end_time);

        }

        public void bind(DoughTask doughTask)
        {
            this.doughTask = doughTask;
            titleTextView.setText(doughTask.getTitle());
            endTimeTextView.setText(doughTask.getTaskEndDate());
        }

        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(MainActivity.this, TaskDetailsActivity.class);
            intent.putExtra(TaskDetailsActivity.EXTRA_DETAILS_TASK_TITLE,doughTask.getTitle());
            intent.putExtra(TaskDetailsActivity.EXTRA_DETAILS_TASK_DATE,""+doughTask.getTaskEndDate());
            intent.putExtra(TaskDetailsActivity.EXTRA_DETAILS_TASK_DESCRIPTION,doughTask.getDescription());
            startActivityForResult(intent, DOUGH_TASK_DETAILS_REQUEST_CODE);
        }

        @Override
        public boolean onLongClick(View v)
        {
            if (doughTask.isLastTask() && !doughTask.isActive())
            {
                List<DoughTask> tempTasks = doughTaskViewModel.getDoughTasksByRecipeIdButNormalListNotThisWeirdLiveData(doughTask.getDoughRecipeId());

                for (int i = 0; i < tempTasks.size(); i++)
                {
                    doughTaskViewModel.delete(tempTasks.get(i));
                }
                DoughRecipe tempRecipe = doughRecipeViewModel.getActiveDoughRecipe().get(0);
                doughRecipeViewModel.delete(tempRecipe);
                finish();
                startActivity(getIntent());
                return true;
            }
            else if (doughTask.isLastTask() && doughTask.isActive())
            {
                List<DoughTask> tempTasks = doughTaskViewModel.getDoughTasksByRecipeIdButNormalListNotThisWeirdLiveData(doughTask.getDoughRecipeId());

                for (int i = 0; i < tempTasks.size(); i++)
                {
                    doughTaskViewModel.delete(tempTasks.get(i));
                }
                DoughRecipe tempRecipe = doughRecipeViewModel.getActiveDoughRecipe().get(0);
                tempRecipe.setActiveRecipe(false);
                Calendar currentTimeNow = Calendar.getInstance();
                Date currentDate = currentTimeNow.getTime();
                tempRecipe.setEndDate(""+currentDate);
                doughRecipeViewModel.update(tempRecipe);
                finish();
                startActivity(getIntent());
                return true;
            }
            else if(doughTask.isActive())
            {
                DoughTask tempTask;

                List<DoughTask> tempTasks = doughTaskViewModel.getDoughTasksByRecipeIdAndUmber(doughTask.getDoughRecipeId(),doughTask.getTaskNumer() + 1);

                tempTask = tempTasks.get(0);
                tempTask.setActive(true);
                Calendar currentTimeNow = Calendar.getInstance();
                currentTimeNow.add(Calendar.MINUTE, tempTask.getTaskTime());
                Date taskTimeMinsFromNow = currentTimeNow.getTime();
                tempTask.setTaskEndDate(""+taskTimeMinsFromNow);
                doughTask.setActive(false);
                doughTaskViewModel.update(tempTask);
                doughTaskViewModel.delete(doughTask);

                return true;

            }
            else
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
