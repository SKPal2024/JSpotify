package com.jspotify.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class ListeningHistory {

    private long id;

    private long userId;

    private long songId;

    private LocalDateTime playedAt;


    // ==========================================
    // Constructors
    // ==========================================

    public ListeningHistory() {
    }


    public ListeningHistory(
            long id,
            long userId,
            long songId,
            LocalDateTime playedAt
    ) {

        this.id = id;
        this.userId = userId;
        this.songId = songId;
        this.playedAt = playedAt;
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


    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }


    public LocalDateTime getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(LocalDateTime playedAt) {
        this.playedAt = playedAt;
    }


    // ==========================================
    // toString
    // ==========================================

    @Override
    public String toString() {

        return "ListeningHistory{" +
                "id=" + id +
                ", userId=" + userId +
                ", songId=" + songId +
                ", playedAt=" + playedAt +
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

        if (!(o instanceof ListeningHistory history)) {
            return false;
        }

        return id == history.id;
    }


    // ==========================================
    // hashCode
    // ==========================================

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}