package com.example.menmaxi;

import android.media.Image;

import java.util.ArrayList;

public class Piatto {

   private String name;
   private Image img;
   private double price;
   private String category;
   private ArrayList<Ingrediente> ing;

    public Piatto(String name, Image img,ArrayList<Ingrediente> ing, double price, String category){
        this.category = category;
        this.name = name;
        this.img = img;
        this.price = price;
        this.ing = ing;
    }

    public String getName() {
        return name;
    }

    public Image getImg() {
        return img;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
