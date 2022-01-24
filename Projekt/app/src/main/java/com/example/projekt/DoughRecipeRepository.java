package com.example.projekt;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DoughRecipeRepository {

    private DoughRecipeDao doughRecipeDao;

    DoughRecipeRepository(Application application)
    {
        doughRecipeDao = PizzaAppDatabase.getDatabase(application).doughRecipeDao();
    }
    void insert(DoughRecipe doughRecipe)
    {
        PizzaAppDatabase.databaseWriteExecutor.execute(() -> {
            doughRecipeDao.insert(doughRecipe);
        });
    }

    void update(DoughRecipe doughRecipe)
    {
        PizzaAppDatabase.databaseWriteExecutor.execute(() -> {
            doughRecipeDao.update(doughRecipe);
        });
    }

    void delete(DoughRecipe doughRecipe)
    {
        PizzaAppDatabase.databaseWriteExecutor.execute(() -> {
            doughRecipeDao.delete(doughRecipe);
        });
    }

    LiveData<List<DoughRecipe>> getDoughRecipes()
    {
        return doughRecipeDao.getDoughRecipes();
    }

    List<DoughRecipe> getActiveDoughRecipe()
    {
        return doughRecipeDao.getActiveDoughRecipe();
    }
}
