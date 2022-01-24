package com.example.projekt;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DoughRecipeViewModel extends AndroidViewModel {
    private DoughRecipeRepository doughRecipeRepository;
    public DoughRecipeViewModel(@NonNull Application application)
    {
        super(application);
        doughRecipeRepository = new DoughRecipeRepository(application);
    }
    public void insert(DoughRecipe doughRecipe)
    {
        doughRecipeRepository.insert(doughRecipe);
    }
    public void update(DoughRecipe doughRecipe)
    {
        doughRecipeRepository.update(doughRecipe);
    }
    public void delete(DoughRecipe doughRecipe)
    {
        doughRecipeRepository.delete(doughRecipe);
    }
    public LiveData<List<DoughRecipe>> getDoughRecipes()
    {
        return doughRecipeRepository.getDoughRecipes();
    }
    public List<DoughRecipe> getActiveDoughRecipe()
    {
        return doughRecipeRepository.getActiveDoughRecipe();
    }
}
