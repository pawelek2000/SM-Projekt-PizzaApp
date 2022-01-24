package com.example.projekt;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "DoughRecipe")
public class DoughRecipe {

    private int NumberOfDoughballs;
    private int DoughballWeight;
    private int Hydration;
    private boolean IsActiveRecipe;
    private int TPBLOK;
    private int TKBLOK;
    private int TPKULKI;

    private int TKKULKI;
    private int TPKULKI2;

    private double Yeast;
    private double Flour;
    private double Water;
    private double Salt;
    private double OliveOil;

    private String Description;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public DoughRecipe(int NumberOfDoughballs, int DoughballWeight, int Hydration, int TPBLOK, int TKBLOK,int TPKULKI,int TKKULKI,int TPKULKI2)
    {
        this.NumberOfDoughballs = NumberOfDoughballs;
        this.DoughballWeight = DoughballWeight;
        this.Hydration = Hydration;
        this.IsActiveRecipe = true;
        this.Description = null;

        this.TPBLOK = TPBLOK;
        this.TKBLOK = TKBLOK;
        this.TPKULKI = TPKULKI;
        this.TKKULKI = TKKULKI;
        this.TPKULKI2 = TPKULKI2;

        double OneDoughballFlourAmount = 0.966 * DoughballWeight * 10 /(10+Hydration/10);
        this.Flour = OneDoughballFlourAmount * NumberOfDoughballs;
        this.Water = OneDoughballFlourAmount * (Hydration/100) * NumberOfDoughballs;
        this.Yeast = 0.0015 * OneDoughballFlourAmount * NumberOfDoughballs;
        this.Salt = 0.0312 * OneDoughballFlourAmount * NumberOfDoughballs;
        this.OliveOil = 0.025 * OneDoughballFlourAmount * NumberOfDoughballs;
    }

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int getNumberOfDoughballs()
    {
        return NumberOfDoughballs;
    }
    public void setNumberOfDoughballs(int NumberOfDoughballs)
    {
        this.NumberOfDoughballs = NumberOfDoughballs;
    }
    public int getDoughballWeight()
    {
        return DoughballWeight;
    }
    public void setDoughballWeight(int DoughballWeight)
    {
        this.DoughballWeight = DoughballWeight;
    }
    public int getHydration()
    {
        return Hydration;
    }
    public void setHydration(int Hydration)
    {
        this.Hydration = Hydration;
    }
    ///TPBLOK
    public int getTPBLOK()
    {
        return  TPBLOK;
    }
    public void setTPBLOK(int TPBLOK )
    {
        this.TPBLOK = TPBLOK;
    }
    //TKBLOK

    public int getTKBLOK()
    {
        return TKBLOK;
    }
    public void setTKBLOK(int TKBLOK)
    {
        this.TKBLOK = TKBLOK;
    }
    //TPKULKI
    public int getTPKULKI()
    {
        return TPKULKI;
    }
    public void setTPKULKI(int TPKULKI)
    {
        this.TPKULKI = TPKULKI;
    }
    //TKKULKI
    public  int getTKKULKI()
    {
        return TKKULKI;
    }
    public void setTKKULKI(int TKKULKI) {
        this.TKKULKI = TKKULKI;
    }
    //TPKULKI2
    public int getTPKULKI2()
    {
        return TPKULKI2;
    }

    public void setTPKULKI2(int TPKULKI2) {
        this.TPKULKI2 = TPKULKI2;
    }

    public double getFlour()
    {
        return Flour;
    }
    public void setFlour(double Flour)
    {
        this.Flour = Flour;
    }
    public double getWater()
    {
        return Water;
    }
    public void setWater(double Water)
    {
        this.Water = Water;
    }
    public double getSalt()
    {
        return Salt;
    }
    public void setSalt(double Salt)
    {
        this.Salt = Salt;
    }
    public double getYeast()
    {
        return Yeast;
    }
    public void setYeast(double Yeast)
    {
        this.Yeast = Yeast;
    }
    public double getOliveOil()
    {
        return  OliveOil;
    }
    public void setOliveOil(double OliveOil)
    {
        this.OliveOil = OliveOil;
    }

    public void setActiveRecipe(boolean activeRecipe)
    {
        IsActiveRecipe = activeRecipe;
    }

    public boolean isActiveRecipe() {
        return IsActiveRecipe;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }
}