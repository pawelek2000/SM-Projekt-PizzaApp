package com.example.projekt;
import android.content.Context;

import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {DoughRecipe.class}, version = 1, exportSchema = false)
public abstract class PizzaAppDatabase extends RoomDatabase{

    public abstract DoughRecipeDao doughRecipeDao();
    public abstract DoughTaskDao doughTaskDao();

    public static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile PizzaAppDatabase INSTANCE;


    static PizzaAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PizzaAppDatabase.class, "pizza_db")
                    .allowMainThreadQueries()
                    .build();
        return INSTANCE;
    }
}
