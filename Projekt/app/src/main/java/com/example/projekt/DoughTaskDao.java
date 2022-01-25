package com.example.projekt;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface DoughTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DoughTask doughTask);

    @Update
    void update(DoughTask doughTask);

    @Delete
    void delete(DoughTask doughTask);

    @Query("SELECT * FROM DoughTask WHERE DoughRecipeId = :Id")
    List<DoughTask> getDoughTasksByRecipeId(int Id);
}
