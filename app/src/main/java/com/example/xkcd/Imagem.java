package com.example.xkcd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Imagem {

    @SerializedName("num")
    @Expose
    private Integer num;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("title")
    @Expose
    private String title;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Imagem{" +
                "num=" + num +
                ", img='" + img + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

 }