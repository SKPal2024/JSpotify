package com.jspotify.dao;

import com.jspotify.model.Album;
import com.jspotify.model.Artist;
import com.jspotify.model.Song;

import java.time.LocalDate;
import java.util.List;

public class SongDAOTest {

    public static void main(String[] args) {

        ArtistDAO artistDAO =
                new ArtistDAO();

        AlbumDAO albumDAO =
                new AlbumDAO();

        SongDAO songDAO =
                new SongDAO();

        Artist artist = null;
        Album album = null;
        Song song = null;

        try {

            System.out.println(
                    "========== CREATE ARTIST =========="
            );

            artist = new Artist();

            artist.setName(
                    "Song Test Artist " +
                    System.currentTimeMillis()
            );

            artist.setBio(
                    "Temporary artist for song test."
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

            album = new Album();

            album.setTitle(
                    "Song Test Album"
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
                    "========== CREATE SONG =========="
            );

            song = new Song();

            song.setTitle(
                    "Test Song"
            );

            song.setArtistId(
                    artist.getId()
            );

            song.setAlbumId(
                    album.getId()
            );

            song.setDurationSeconds(
                    245
            );

            song.setAudioPath(
                    "storage/music/test-song.mp3"
            );

            song.setCoverPath(
                    "storage/covers/test-album.jpg"
            );

            song =
                    songDAO.create(song);

            System.out.println(
                    song
            );


            System.out.println();
            System.out.println(
                    "========== FIND BY ID =========="
            );

            Song found =
                    songDAO.findById(
                            song.getId()
                    );

            System.out.println(
                    found
            );


            System.out.println();
            System.out.println(
                    "========== FIND BY ARTIST =========="
            );

            List<Song> artistSongs =
                    songDAO.findByArtistId(
                            artist.getId()
                    );

            for (Song s : artistSongs) {

                System.out.println(s);
            }


            System.out.println();
            System.out.println(
                    "========== FIND BY ALBUM =========="
            );

            List<Song> albumSongs =
                    songDAO.findByAlbumId(
                            album.getId()
                    );

            for (Song s : albumSongs) {

                System.out.println(s);
            }


            System.out.println();
            System.out.println(
                    "========== SEARCH =========="
            );

            List<Song> searchResults =
                    songDAO.search("Test");

            for (Song s : searchResults) {

                System.out.println(s);
            }


            System.out.println();
            System.out.println(
                    "========== FIND ALL =========="
            );

            List<Song> allSongs =
                    songDAO.findAll();

            for (Song s : allSongs) {

                System.out.println(s);
            }


            System.out.println();
            System.out.println(
                    "========== UPDATE =========="
            );

            song.setTitle(
                    "Updated Test Song"
            );

            song.setDurationSeconds(
                    300
            );

            boolean updated =
                    songDAO.update(song);

            System.out.println(
                    "Updated: " + updated
            );


            System.out.println();
            System.out.println(
                    "========== DELETE SONG =========="
            );

            boolean deleted =
                    songDAO.delete(
                            song.getId()
                    );

            System.out.println(
                    "Deleted: " + deleted
            );


            /*
             * Clean up test data.
             */
            albumDAO.delete(
                    album.getId()
            );

            artistDAO.delete(
                    artist.getId()
            );


            System.out.println();
            System.out.println(
                    "✅ SONG DAO TEST PASSED!"
            );

        } catch (Exception e) {

            System.out.println();
            System.out.println(
                    "❌ SONG DAO TEST FAILED!"
            );

            e.printStackTrace();
        }
    }
}