package com.jspotify.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Artist {

    private long id;

    private String name;

    private String bio;

    private String imagePath;

    private LocalDateTime createdAt;


    // ==========================================
    // Constructors
    // ==========================================

    public Artist() {
    }


    public Artist(
            long id,
            String name,
            String bio,
            String imagePath,
            LocalDateTime createdAt
    ) {

        this.id = id;
        this.name = name;
        this.bio = bio;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
    }


    // ==========================================
    // Getters and Setters
    // ==========================================

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    // ==========================================
    // toString
    // ==========================================

    @Override
    public String toString() {

        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }


    // ==========================================
    // equals
    // ==========================================

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Artist artist)) {
            return false;
        }

        return id == artist.id;
    }


    // ==========================================
    // hashCode
    // ==========================================

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}