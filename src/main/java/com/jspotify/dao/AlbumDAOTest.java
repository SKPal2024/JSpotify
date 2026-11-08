package com.jspotify.dao;

import com.jspotify.model.Album;
import com.jspotify.model.Artist;

import java.time.LocalDate;
import java.util.List;

public class AlbumDAOTest {

    public static void main(String[] args) {

        ArtistDAO artistDAO =
                new ArtistDAO();

        AlbumDAO albumDAO =
                new AlbumDAO();

        try {

            System.out.println(
                    "========== CREATE ARTIST =========="
            );

            Artist artist =
                    new Artist();

            artist.setName(
                    "Album Test Artist " +
                    System.currentTimeMillis()
            );

            artist.setBio(
                    "Temporary artist for album test."
            );

            artist =
                    artistDAO.create(artist);

            System.out.println(
                    artist
            );


            System.out.println();
            System.out.println(
                    "========== CREATE ALBUM =========="
            );

            Album album =
                    new Album();

            album.setTitle(
                    "Test Album"
            );

            album.setArtistId(
                    artist.getId()
            );

            album.setCoverPath(
                    "storage/covers/test-album.jpg"
            );

            album.setReleaseDate(
                    LocalDate.of(
                            2026,
                            1,
                            1
                    )
            );

            album =
                    albumDAO.create(album);

            System.out.println(
                    album
            );


            System.out.println();
            System.out.println(
                    "========== FIND BY ID =========="
            );

            Album found =
                    albumDAO.findById(
                            album.getId()
                    );

            System.out.println(
                    found
            );


            System.out.println();
            System.out.println(
                    "========== FIND BY ARTIST =========="
            );

            List<Album> albums =
                    albumDAO.findByArtistId(
                            artist.getId()
                    );

            for (Album a : albums) {
                System.out.println(a);
            }


            System.out.println();
            System.out.println(
                    "========== FIND ALL =========="
            );

            List<Album> allAlbums =
                    albumDAO.findAll();

            for (Album a : allAlbums) {
                System.out.println(a);
            }


            System.out.println();
            System.out.println(
                    "========== UPDATE =========="
            );

            album.setTitle(
                    "Updated Test Album"
            );

            boolean updated =
                    albumDAO.update(album);

            System.out.println(
                    "Updated: " + updated
            );


            System.out.println();
            System.out.println(
                    "========== DELETE ALBUM =========="
            );

            boolean deleted =
                    albumDAO.delete(
                            album.getId()
                    );

            System.out.println(
                    "Deleted: " + deleted
            );


            /*
             * Artist is no longer needed.
             * Delete it as well.
             */
            artistDAO.delete(
                    artist.getId()
            );


            System.out.println();
            System.out.println(
                    "✅ ALBUM DAO TEST PASSED!"
            );

        } catch (Exception e) {

            System.out.println();
            System.out.println(
                    "❌ ALBUM DAO TEST FAILED!"
            );

            e.printStackTrace();
        }
    }
}