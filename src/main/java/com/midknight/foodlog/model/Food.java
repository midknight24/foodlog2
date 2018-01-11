package com.midknight.foodlog.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Onlyme on 9/27/2017.
 */
@Entity
public class Food {
    public String getName() {
        return name;
    }

    public Food() {}

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDateUpLoaded() {
        return dateUpLoaded;
    }

    public void setDateUpLoaded(Timestamp dateUpLoaded) {
        this.dateUpLoaded = dateUpLoaded;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Timestamp dateUpLoaded = Timestamp.valueOf(LocalDateTime.now());
    private int calories;
    private String location;
    private String username = "you";

    private Long userID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    private boolean liked;
    @Lob
    private byte[] bytes;

}
