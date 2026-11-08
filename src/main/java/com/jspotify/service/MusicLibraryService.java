package com.jspotify.service;

import com.jspotify.dao.AlbumDAO;
import com.jspotify.dao.ArtistDAO;
import com.jspotify.dao.SongDAO;
import com.jspotify.model.Album;
import com.jspotify.model.Artist;
import com.jspotify.model.Song;

import java.sql.SQLException;
import java.util.List;

public class MusicLibraryService {

    private final ArtistDAO artistDAO;
    private final AlbumDAO albumDAO;
    private final SongDAO songDAO;


    public MusicLibraryService() {

        artistDAO = new ArtistDAO();
        albumDAO = new AlbumDAO();
        songDAO = new SongDAO();
    }


    public List<Artist> getAllArtists()
            throws SQLException {

        return artistDAO.findAll();
    }


    public List<Album> getAllAlbums()
            throws SQLException {

        return albumDAO.findAll();
    }


    public List<Song> getAllSongs()
            throws SQLException {

        return songDAO.findAll();
    }


    public List<Album> getAlbumsByArtist(
            long artistId)
            throws SQLException {

        return albumDAO.findByArtistId(
                artistId
        );
    }


    public List<Song> getSongsByArtist(
            long artistId)
            throws SQLException {

        return songDAO.findByArtistId(
                artistId
        );
    }


    public List<Song> getSongsByAlbum(
            long albumId)
            throws SQLException {

        return songDAO.findByAlbumId(
                albumId
        );
    }


    public List<Song> searchSongs(
            String keyword)
            throws SQLException {

        if (keyword == null ||
                keyword.isBlank()) {

            return List.of();
        }

        return songDAO.search(
                keyword.trim()
        );
    }
}