package com.example.projekt;
import androidx.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
public interface DoughRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DoughRecipe doughRecipe);

    @Update
    void update(DoughRecipe doughRecipe);

    @Delete
    void delete(DoughRecipe doughRecipe);

    @Query("SELECT * FROM DoughRecipe")
    LiveData<List<DoughRecipe>> getDoughRecipes();

    @Query("SELECT * FROM DoughRecipe WHERE IsActiveRecipe = 1")
    List<DoughRecipe> getActiveDoughRecipe();

}
