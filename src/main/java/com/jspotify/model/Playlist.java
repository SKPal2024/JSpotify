package com.jspotify.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Playlist {

    private long id;

    private long userId;

    private String name;

    private String description;

    private String coverPath;

    private LocalDateTime createdAt;


    // ==========================================
    // Constructors
    // ==========================================

    public Playlist() {
    }


    public Playlist(
            long id,
            long userId,
            String name,
            String description,
            String coverPath,
            LocalDateTime createdAt
    ) {

        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.coverPath = coverPath;
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


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
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

        return "Playlist{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", coverPath='" + coverPath + '\'' +
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

        if (!(o instanceof Playlist playlist)) {
            return false;
        }

        return id == playlist.id;
    }


    // ==========================================
    // hashCode
    // ==========================================

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}