package com.example.projekt;

import android.app.Application;

import java.util.List;

public class DoughTaskRepository {
    private DoughTaskDao doughTaskDao;

    DoughTaskRepository(Application application)
    {
        doughTaskDao = PizzaAppDatabase.getDatabase(application).doughTaskDao();
    }

    void insert(DoughTask doughTask)
    {
        PizzaAppDatabase.databaseWriteExecutor.execute(() -> {
            doughTaskDao.insert(doughTask);
        });
    }

    void update(DoughTask doughTask)
    {
        PizzaAppDatabase.databaseWriteExecutor.execute(() -> {
            doughTaskDao.update(doughTask);
        });
    }

    void delete(DoughTask doughTask)
    {
        PizzaAppDatabase.databaseWriteExecutor.execute(() -> {
            doughTaskDao.delete(doughTask);
        });
    }

    List<DoughTask> getDoughTasksByRecipeId(int Id)
    {
        return doughTaskDao.getDoughTasksByRecipeId(Id);
    }
}
