package com.jspotify.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class PlaylistSong {

    private long playlistId;

    private long songId;

    private int position;

    private LocalDateTime addedAt;


    // ==========================================
    // Constructors
    // ==========================================

    public PlaylistSong() {
    }


    public PlaylistSong(
            long playlistId,
            long songId,
            int position,
            LocalDateTime addedAt
    ) {

        this.playlistId = playlistId;
        this.songId = songId;
        this.position = position;
        this.addedAt = addedAt;
    }


    // ==========================================
    // Getters and Setters
    // ==========================================

    public long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(long playlistId) {
        this.playlistId = playlistId;
    }


    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }


    // ==========================================
    // toString
    // ==========================================

    @Override
    public String toString() {

        return "PlaylistSong{" +
                "playlistId=" + playlistId +
                ", songId=" + songId +
                ", position=" + position +
                ", addedAt=" + addedAt +
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

        if (!(o instanceof PlaylistSong that)) {
            return false;
        }

        return playlistId == that.playlistId &&
                songId == that.songId;
    }


    // ==========================================
    // hashCode
    // ==========================================

    @Override
    public int hashCode() {

        return Objects.hash(playlistId, songId);
    }
}