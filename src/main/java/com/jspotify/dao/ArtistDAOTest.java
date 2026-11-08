package com.jspotify.dao;

import com.jspotify.model.Artist;

import java.util.List;

public class ArtistDAOTest {

    public static void main(String[] args) {

        ArtistDAO artistDAO =
                new ArtistDAO();

        try {

            System.out.println(
                    "========== CREATE ARTIST =========="
            );

            Artist artist = new Artist();

            artist.setName(
                    "Test Artist " +
                    System.currentTimeMillis()
            );

            artist.setBio(
                    "Test artist biography."
            );

            artist.setImagePath(
                    "storage/covers/test-artist.jpg"
            );

            Artist createdArtist =
                    artistDAO.create(artist);

            System.out.println(
                    createdArtist
            );


            System.out.println();
            System.out.println(
                    "========== FIND BY ID =========="
            );

            Artist foundArtist =
                    artistDAO.findById(
                            createdArtist.getId()
                    );

            System.out.println(
                    foundArtist
            );


            System.out.println();
            System.out.println(
                    "========== FIND ALL =========="
            );

            List<Artist> artists =
                    artistDAO.findAll();

            for (Artist a : artists) {
                System.out.println(a);
            }


            System.out.println();
            System.out.println(
                    "========== UPDATE =========="
            );

            createdArtist.setBio(
                    "Updated biography."
            );

            boolean updated =
                    artistDAO.update(
                            createdArtist
                    );

            System.out.println(
                    "Updated: " + updated
            );


            System.out.println();
            System.out.println(
                    "========== DELETE =========="
            );

            boolean deleted =
                    artistDAO.delete(
                            createdArtist.getId()
                    );

            System.out.println(
                    "Deleted: " + deleted
            );


            System.out.println();
            System.out.println(
                    "✅ ARTIST DAO TEST PASSED!"
            );

        } catch (Exception e) {

            System.out.println();
            System.out.println(
                    "❌ ARTIST DAO TEST FAILED!"
            );

            e.printStackTrace();
        }
    }
}