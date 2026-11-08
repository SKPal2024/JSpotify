package com.jspotify.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ModelTest {

    public static void main(String[] args) {

        // ==========================================
        // Create a User
        // ==========================================

        User user = new User(
                1L,
                "surya",
                "surya@example.com",
                "hashed-password",
                null,
                UserRole.USER,
                LocalDateTime.now()
        );


        // ==========================================
        // Create an Artist
        // ==========================================

        Artist artist = new Artist(
                1L,
                "Example Artist",
                "An example artist",
                "covers/example-artist.jpg",
                LocalDateTime.now()
        );


        // ==========================================
        // Create an Album
        // ==========================================

        Album album = new Album(
                1L,
                "Example Album",
                artist.getId(),
                "covers/example-album.jpg",
                LocalDate.of(2026, 9, 13),
                LocalDateTime.now()
        );


        // ==========================================
        // Create a Song
        // ==========================================

        Song song = new Song(
                1L,
                "Example Song",
                artist.getId(),
                album.getId(),
                240,
                "music/example-song.mp3",
                "covers/example-album.jpg",
                LocalDateTime.now()
        );


        // ==========================================
        // Print objects
        // ==========================================

        System.out.println("========== USER ==========");
        System.out.println(user);

        System.out.println();

        System.out.println("========== ARTIST ==========");
        System.out.println(artist);

        System.out.println();

        System.out.println("========== ALBUM ==========");
        System.out.println(album);

        System.out.println();

        System.out.println("========== SONG ==========");
        System.out.println(song);
    }
}