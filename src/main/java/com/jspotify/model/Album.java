package com.jspotify.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Album {

    private long id;

    private String title;

    private long artistId;

    private String coverPath;

    private LocalDate releaseDate;

    private LocalDateTime createdAt;


    // ==========================================
    // Constructors
    // ==========================================

    public Album() {
    }


    public Album(
            long id,
            String title,
            long artistId,
            String coverPath,
            LocalDate releaseDate,
            LocalDateTime createdAt
    ) {

        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.coverPath = coverPath;
        this.releaseDate = releaseDate;
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }


    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
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

        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                ", coverPath='" + coverPath + '\'' +
                ", releaseDate=" + releaseDate +
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

        if (!(o instanceof Album album)) {
            return false;
        }

        return id == album.id;
    }


    // ==========================================
    // hashCode
    // ==========================================

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}