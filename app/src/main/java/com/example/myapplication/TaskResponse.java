package com.example.myapplication;

public class TaskResponse {
    private String nom;
    private int points;

    public TaskResponse(String nom, int points) {
        this.nom = nom;
        this.points = points;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
