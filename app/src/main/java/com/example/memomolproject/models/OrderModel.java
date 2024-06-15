package com.example.memomolproject.models;

import java.io.Serializable;

public class OrderModel implements Serializable{
    private String title;
    private int picUrl;
    private String documentId;
    private double rating;
    private int numberinCart;
    private int price;

    public OrderModel(String title, int picUrl, String documentId, double rating, int price) {
        this.title = title;
        this.picUrl = picUrl;
        this.documentId = documentId;
        this.rating = rating;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(int picUrl) {
        this.picUrl = picUrl;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumberinCart() {
        return numberinCart;
    }

    public void setNumberinCart(int numberinCart) {
        this.numberinCart = numberinCart;
    }
}