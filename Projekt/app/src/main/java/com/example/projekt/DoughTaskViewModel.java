package com.example.projekt;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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
    public LiveData<List<DoughTask>> getDoughTasksByRecipeId()
    {
        return  doughTaskRepository.getDoughTasksByRecipeId();
    }
}
