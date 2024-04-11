package com.example.projectapplication;

public class PlayRecord {
    Long id;
    String name;
    int score;

    public PlayRecord(long id, String name, int score){
        super();
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setId(long id) {
        this.id = id;
    }
}
