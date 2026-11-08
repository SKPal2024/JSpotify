package com.jspotify.dao;

import com.jspotify.database.DatabaseConnection;
import com.jspotify.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {

    public Song create(Song song)
            throws SQLException {

        String sql =
                """
                INSERT INTO songs
                (
                    title,
                    artist_id,
                    album_id,
                    duration_seconds,
                    audio_path,
                    cover_path
                )
                VALUES (?, ?, ?, ?, ?, ?)
                RETURNING id, created_at
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    song.getTitle()
            );

            statement.setLong(
                    2,
                    song.getArtistId()
            );

            if (song.getAlbumId() != null) {

                statement.setLong(
                        3,
                        song.getAlbumId()
                );

            } else {

                statement.setNull(
                        3,
                        Types.BIGINT
                );
            }

            statement.setInt(
                    4,
                    song.getDurationSeconds()
            );

            statement.setString(
                    5,
                    song.getAudioPath()
            );

            statement.setString(
                    6,
                    song.getCoverPath()
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    song.setId(
                            resultSet.getLong("id")
                    );

                    Timestamp timestamp =
                            resultSet.getTimestamp(
                                    "created_at"
                            );

                    if (timestamp != null) {

                        song.setCreatedAt(
                                timestamp
                                        .toLocalDateTime()
                        );
                    }

                    return song;
                }
            }
        }

        throw new SQLException(
                "Song creation failed."
        );
    }


    public Song findById(long id)
            throws SQLException {

        String sql =
                """
                SELECT
                    id,
                    title,
                    artist_id,
                    album_id,
                    duration_seconds,
                    audio_path,
                    cover_path,
                    created_at
                FROM songs
                WHERE id = ?
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setLong(
                    1,
                    id
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    return mapResultSetToSong(
                            resultSet
                    );
                }
            }
        }

        return null;
    }


    public List<Song> findAll()
            throws SQLException {

        String sql =
                """
                SELECT
                    id,
                    title,
                    artist_id,
                    album_id,
                    duration_seconds,
                    audio_path,
                    cover_path,
                    created_at
                FROM songs
                ORDER BY title
                """;

        List<Song> songs =
                new ArrayList<>();

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                songs.add(
                        mapResultSetToSong(
                                resultSet
                        )
                );
            }
        }

        return songs;
    }


    public List<Song> findByArtistId(long artistId)
            throws SQLException {

        String sql =
                """
                SELECT
                    id,
                    title,
                    artist_id,
                    album_id,
                    duration_seconds,
                    audio_path,
                    cover_path,
                    created_at
                FROM songs
                WHERE artist_id = ?
                ORDER BY title
                """;

        List<Song> songs =
                new ArrayList<>();

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setLong(
                    1,
                    artistId
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                while (resultSet.next()) {

                    songs.add(
                            mapResultSetToSong(
                                    resultSet
                            )
                    );
                }
            }
        }

        return songs;
    }


    public List<Song> findByAlbumId(long albumId)
            throws SQLException {

        String sql =
                """
                SELECT
                    id,
                    title,
                    artist_id,
                    album_id,
                    duration_seconds,
                    audio_path,
                    cover_path,
                    created_at
                FROM songs
                WHERE album_id = ?
                ORDER BY title
                """;

        List<Song> songs =
                new ArrayList<>();

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setLong(
                    1,
                    albumId
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                while (resultSet.next()) {

                    songs.add(
                            mapResultSetToSong(
                                    resultSet
                            )
                    );
                }
            }
        }

        return songs;
    }


    public List<Song> search(String keyword)
            throws SQLException {

        String sql =
                """
                SELECT
                    id,
                    title,
                    artist_id,
                    album_id,
                    duration_seconds,
                    audio_path,
                    cover_path,
                    created_at
                FROM songs
                WHERE LOWER(title)
                      LIKE LOWER(?)
                ORDER BY title
                """;

        List<Song> songs =
                new ArrayList<>();

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    "%" + keyword + "%"
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                while (resultSet.next()) {

                    songs.add(
                            mapResultSetToSong(
                                    resultSet
                            )
                    );
                }
            }
        }

        return songs;
    }


    public boolean update(Song song)
            throws SQLException {

        String sql =
                """
                UPDATE songs
                SET
                    title = ?,
                    artist_id = ?,
                    album_id = ?,
                    duration_seconds = ?,
                    audio_path = ?,
                    cover_path = ?
                WHERE id = ?
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    song.getTitle()
            );

            statement.setLong(
                    2,
                    song.getArtistId()
            );

            if (song.getAlbumId() != null) {

                statement.setLong(
                        3,
                        song.getAlbumId()
                );

            } else {

                statement.setNull(
                        3,
                        Types.BIGINT
                );
            }

            statement.setInt(
                    4,
                    song.getDurationSeconds()
            );

            statement.setString(
                    5,
                    song.getAudioPath()
            );

            statement.setString(
                    6,
                    song.getCoverPath()
            );

            statement.setLong(
                    7,
                    song.getId()
            );

            return statement.executeUpdate() > 0;
        }
    }


    public boolean delete(long id)
            throws SQLException {

        String sql =
                """
                DELETE FROM songs
                WHERE id = ?
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setLong(
                    1,
                    id
            );

            return statement.executeUpdate() > 0;
        }
    }


    private Song mapResultSetToSong(
            ResultSet resultSet)
            throws SQLException {

        Song song = new Song();

        song.setId(
                resultSet.getLong("id")
        );

        song.setTitle(
                resultSet.getString("title")
        );

        song.setArtistId(
                resultSet.getLong("artist_id")
        );

        long albumId =
                resultSet.getLong("album_id");

        if (resultSet.wasNull()) {

            song.setAlbumId(null);

        } else {

            song.setAlbumId(albumId);
        }

        song.setDurationSeconds(
                resultSet.getInt(
                        "duration_seconds"
                )
        );

        song.setAudioPath(
                resultSet.getString("audio_path")
        );

        song.setCoverPath(
                resultSet.getString("cover_path")
        );

        Timestamp timestamp =
                resultSet.getTimestamp(
                        "created_at"
                );

        if (timestamp != null) {

            song.setCreatedAt(
                    timestamp.toLocalDateTime()
            );
        }

        return song;
    }
}