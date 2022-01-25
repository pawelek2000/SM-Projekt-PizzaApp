package com.example.projekt;

import android.renderscript.ScriptIntrinsicYuvToRGB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DoughTask")
public class DoughTask {
    private String Title;
    private String Description;
    private int TaskTime;
    private Date TaskEndDate;
    private boolean Active;
    private boolean LastTask;
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int DoughRecipeId;

    public DoughTask(String Title, String Description, int TaskTime, int DoughRecipeId,boolean LastTask)
    {
        this.Title = Title;
        this.Description = Description;
        this.TaskTime = TaskTime;
        this.DoughRecipeId = DoughRecipeId;
        this.Active = true;
        this.LastTask = LastTask;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTitle() {
        return Title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }

    public void setTaskTime(int taskTime) {
        TaskTime = taskTime;
    }

    public int getTaskTime() {
        return TaskTime;
    }

    public void setTaskEndDate(Date taskEndDate) {
        TaskEndDate = taskEndDate;
    }

    public Date getTaskEndDate() {
        return TaskEndDate;
    }

    public void setDoughRecipeId(int doughRecipeId) {
        DoughRecipeId = doughRecipeId;
    }

    public int getDoughRecipeId() {
        return DoughRecipeId;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public boolean isActive() {
        return Active;
    }

    public void setLastTask(boolean lastTask) {
        LastTask = lastTask;
    }

    public boolean isLastTask() {
        return LastTask;
    }
}