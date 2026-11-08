package com.jspotify.database;

import com.jspotify.dao.AlbumDAO;
import com.jspotify.dao.ArtistDAO;
import com.jspotify.dao.SongDAO;
import com.jspotify.model.Album;
import com.jspotify.model.Artist;
import com.jspotify.model.Song;

import java.time.LocalDate;
import java.util.List;

public class MusicSeeder {

    private final ArtistDAO artistDAO;
    private final AlbumDAO albumDAO;
    private final SongDAO songDAO;

    public MusicSeeder() {

        artistDAO = new ArtistDAO();
        albumDAO = new AlbumDAO();
        songDAO = new SongDAO();
    }


    public void seed() throws Exception {

        System.out.println(
                "================================"
        );

        System.out.println(
                "     JSPOTIFY MUSIC SEEDER"
        );

        System.out.println(
                "================================"
        );


        /*
         * ARTIST 1
         */
        Artist neonEcho =
                getOrCreateArtist(
                        "Neon Echo",
                        "Electronic and dream-pop artist."
                );


        /*
         * ARTIST 2
         */
        Artist midnightTheory =
                getOrCreateArtist(
                        "Midnight Theory",
                        "Alternative artist creating late-night sounds."
                );


        /*
         * ARTIST 3
         */
        Artist solarAvenue =
                getOrCreateArtist(
                        "Solar Avenue",
                        "Indie artist inspired by summer evenings."
                );


        /*
         * ALBUM 1
         */
        Album digitalDreams =
                getOrCreateAlbum(
                        "Digital Dreams",
                        neonEcho.getId(),
                        "storage/covers/digital-dreams.jpg",
                        LocalDate.of(2026, 1, 15)
                );


        /*
         * ALBUM 2
         */
        Album afterMidnight =
                getOrCreateAlbum(
                        "After Midnight",
                        midnightTheory.getId(),
                        "storage/covers/after-midnight.jpg",
                        LocalDate.of(2026, 2, 10)
                );


        /*
         * ALBUM 3
         */
        Album goldenSkies =
                getOrCreateAlbum(
                        "Golden Skies",
                        solarAvenue.getId(),
                        "storage/covers/golden-skies.jpg",
                        LocalDate.of(2026, 3, 20)
                );


        /*
         * SONGS
         */

        getOrCreateSong(
                "Neon Lights",
                neonEcho.getId(),
                digitalDreams.getId(),
                214,
                "storage/music/neon-lights.wav",
                "storage/covers/digital-dreams.jpg"
        );


        getOrCreateSong(
                "Digital Dreams",
                neonEcho.getId(),
                digitalDreams.getId(),
                238,
                "storage/music/digital-dreams.wav",
                "storage/covers/digital-dreams.jpg"
        );


        getOrCreateSong(
                "Midnight Drive",
                midnightTheory.getId(),
                afterMidnight.getId(),
                256,
                "storage/music/midnight-drive.wav",
                "storage/covers/after-midnight.jpg"
        );


        getOrCreateSong(
                "After Midnight",
                midnightTheory.getId(),
                afterMidnight.getId(),
                221,
                "storage/music/after-midnight.wav",
                "storage/covers/after-midnight.jpg"
        );


        getOrCreateSong(
                "Golden Skies",
                solarAvenue.getId(),
                goldenSkies.getId(),
                247,
                "storage/music/golden-skies.wav",
                "storage/covers/golden-skies.jpg"
        );


        getOrCreateSong(
                "Sunset Avenue",
                solarAvenue.getId(),
                goldenSkies.getId(),
                231,
                "storage/music/sunset-avenue.wav",
                "storage/covers/golden-skies.jpg"
        );


        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "✅ MUSIC SEEDING COMPLETE!"
        );

        System.out.println(
                "================================"
        );
    }


    private Artist getOrCreateArtist(
            String name,
            String bio)
            throws Exception {

        Artist existing =
                artistDAO.findByName(name);

        if (existing != null) {

            System.out.println(
                    "Artist already exists: "
                            + name
            );

            return existing;
        }


        Artist artist =
                new Artist();

        artist.setName(name);
        artist.setBio(bio);
        artist.setImagePath(
                null
        );

        Artist created =
                artistDAO.create(artist);

        System.out.println(
                "Created artist: "
                        + name
        );

        return created;
    }


    private Album getOrCreateAlbum(
            String title,
            long artistId,
            String coverPath,
            LocalDate releaseDate)
            throws Exception {

        List<Album> albums =
                albumDAO.findByArtistId(
                        artistId
                );

        for (Album album : albums) {

            if (album.getTitle()
                    .equalsIgnoreCase(title)) {

                System.out.println(
                        "Album already exists: "
                                + title
                );

                return album;
            }
        }


        Album album =
                new Album();

        album.setTitle(title);

        album.setArtistId(
                artistId
        );

        album.setCoverPath(
                coverPath
        );

        album.setReleaseDate(
                releaseDate
        );


        Album created =
                albumDAO.create(album);

        System.out.println(
                "Created album: "
                        + title
        );

        return created;
    }


    private Song getOrCreateSong(
            String title,
            long artistId,
            long albumId,
            int durationSeconds,
            String audioPath,
            String coverPath)
            throws Exception {

        /*
         * Search by title.
         */
        List<Song> existingSongs =
                songDAO.search(title);


        /*
         * Make sure the exact song
         * belonging to this artist exists.
         */
        for (Song existingSong :
                existingSongs) {

            if (
                    existingSong.getTitle()
                            .equalsIgnoreCase(title)
                    &&
                    existingSong.getArtistId()
                            == artistId
            ) {

                System.out.println(
                        "Song already exists: "
                                + title
                );

                return existingSong;
            }
        }


        Song song =
                new Song();

        song.setTitle(title);

        song.setArtistId(
                artistId
        );

        song.setAlbumId(
                albumId
        );

        song.setDurationSeconds(
                durationSeconds
        );

        song.setAudioPath(
                audioPath
        );

        song.setCoverPath(
                coverPath
        );


        Song created =
                songDAO.create(song);

        System.out.println(
                "Created song: "
                        + title
        );

        return created;
    }


    public static void main(String[] args) {

        try {

            MusicSeeder seeder =
                    new MusicSeeder();

            seeder.seed();

        } catch (Exception e) {

            System.out.println();

            System.out.println(
                    "❌ MUSIC SEEDING FAILED!"
            );

            e.printStackTrace();
        }
    }
}