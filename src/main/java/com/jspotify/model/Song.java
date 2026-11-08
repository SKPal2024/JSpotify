package com.jspotify.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Song {

    private long id;

    private String title;

    private long artistId;

    private Long albumId;

    private int durationSeconds;

    private String audioPath;

    private String coverPath;

    private LocalDateTime createdAt;


    // ==========================================
    // Constructors
    // ==========================================

    public Song() {
    }


    public Song(
            long id,
            String title,
            long artistId,
            Long albumId,
            int durationSeconds,
            String audioPath,
            String coverPath,
            LocalDateTime createdAt
    ) {

        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.albumId = albumId;
        this.durationSeconds = durationSeconds;
        this.audioPath = audioPath;
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


    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }


    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }


    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
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

        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                ", albumId=" + albumId +
                ", durationSeconds=" + durationSeconds +
                ", audioPath='" + audioPath + '\'' +
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

        if (!(o instanceof Song song)) {
            return false;
        }

        return id == song.id;
    }


    // ==========================================
    // hashCode
    // ==========================================

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}