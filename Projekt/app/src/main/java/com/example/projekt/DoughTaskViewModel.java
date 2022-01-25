package com.example.projekt;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class DoughTaskViewModel extends AndroidViewModel {
    private DoughTaskRepository doughTaskRepository;

    public DoughTaskViewModel(@NonNull Application application)
    {
        super(application);
        doughTaskRepository = new DoughTaskRepository(application);
    }
    public void insert(DoughTask doughTask)
    {
        doughTaskRepository.insert(doughTask);
    }
    public void update(DoughTask doughTask)
    {
        doughTaskRepository.update(doughTask);
    }
    public void delete(DoughTask doughTask)
    {
        doughTaskRepository.delete(doughTask);
    }
    public List<DoughTask> getDoughTasksByRecipeId(int Id)
    {
        return  doughTaskRepository.getDoughTasksByRecipeId(Id);
    }
}
