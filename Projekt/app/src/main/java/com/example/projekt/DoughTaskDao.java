package com.example.projekt;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface DoughTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DoughTask doughTask);

    @Update
    void update(DoughTask doughTask);

    @Delete
    void delete(DoughTask doughTask);

    @Query("SELECT * FROM DoughTask WHERE DoughRecipeId = :Id ORDER BY taskNumer")
    LiveData<List<DoughTask>> getDoughTasksByRecipeId(int Id);

    @Query("SELECT * FROM DoughTask WHERE DoughRecipeId = :Id AND taskNumer = :number ORDER BY taskNumer")
    List<DoughTask> getDoughTasksByRecipeIdAndUmber(int Id,int number);

    @Query("SELECT * FROM DoughTask WHERE DoughRecipeId = :Id ORDER BY taskNumer")
    List<DoughTask> getDoughTasksByRecipeIdButNormalListNotThisWeirdLiveData(int Id);
}
