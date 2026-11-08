package com.jspotify.dao;

import com.jspotify.database.DatabaseConnection;
import com.jspotify.model.Album;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    public Album create(Album album)
            throws SQLException {

        String sql =
                """
                INSERT INTO albums
                (title, artist_id, cover_path, release_date)
                VALUES (?, ?, ?, ?)
                RETURNING id, created_at
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    album.getTitle()
            );

            statement.setLong(
                    2,
                    album.getArtistId()
            );

            statement.setString(
                    3,
                    album.getCoverPath()
            );

            statement.setDate(
                    4,
                    album.getReleaseDate() != null
                            ? Date.valueOf(
                                    album.getReleaseDate()
                            )
                            : null
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    album.setId(
                            resultSet.getLong("id")
                    );

                    Timestamp timestamp =
                            resultSet.getTimestamp(
                                    "created_at"
                            );

                    if (timestamp != null) {

                        album.setCreatedAt(
                                timestamp
                                        .toLocalDateTime()
                        );
                    }

                    return album;
                }
            }
        }

        throw new SQLException(
                "Album creation failed."
        );
    }


    public Album findById(long id)
            throws SQLException {

        String sql =
                """
                SELECT
                    id,
                    title,
                    artist_id,
                    cover_path,
                    release_date,
                    created_at
                FROM albums
                WHERE id = ?
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    return mapResultSetToAlbum(
                            resultSet
                    );
                }
            }
        }

        return null;
    }


    public List<Album> findAll()
            throws SQLException {

        String sql =
                """
                SELECT
                    id,
                    title,
                    artist_id,
                    cover_path,
                    release_date,
                    created_at
                FROM albums
                ORDER BY title
                """;

        List<Album> albums =
                new ArrayList<>();

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                albums.add(
                        mapResultSetToAlbum(
                                resultSet
                        )
                );
            }
        }

        return albums;
    }


    public List<Album> findByArtistId(long artistId)
            throws SQLException {

        String sql =
                """
                SELECT
                    id,
                    title,
                    artist_id,
                    cover_path,
                    release_date,
                    created_at
                FROM albums
                WHERE artist_id = ?
                ORDER BY title
                """;

        List<Album> albums =
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

                    albums.add(
                            mapResultSetToAlbum(
                                    resultSet
                            )
                    );
                }
            }
        }

        return albums;
    }


    public boolean update(Album album)
            throws SQLException {

        String sql =
                """
                UPDATE albums
                SET
                    title = ?,
                    artist_id = ?,
                    cover_path = ?,
                    release_date = ?
                WHERE id = ?
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    album.getTitle()
            );

            statement.setLong(
                    2,
                    album.getArtistId()
            );

            statement.setString(
                    3,
                    album.getCoverPath()
            );

            statement.setDate(
                    4,
                    album.getReleaseDate() != null
                            ? Date.valueOf(
                                    album.getReleaseDate()
                            )
                            : null
            );

            statement.setLong(
                    5,
                    album.getId()
            );

            return statement.executeUpdate() > 0;
        }
    }


    public boolean delete(long id)
            throws SQLException {

        String sql =
                """
                DELETE FROM albums
                WHERE id = ?
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        }
    }


    private Album mapResultSetToAlbum(
            ResultSet resultSet)
            throws SQLException {

        Album album = new Album();

        album.setId(
                resultSet.getLong("id")
        );

        album.setTitle(
                resultSet.getString("title")
        );

        album.setArtistId(
                resultSet.getLong("artist_id")
        );

        album.setCoverPath(
                resultSet.getString("cover_path")
        );

        Date releaseDate =
                resultSet.getDate("release_date");

        if (releaseDate != null) {

            album.setReleaseDate(
                    releaseDate.toLocalDate()
            );
        }

        Timestamp createdAt =
                resultSet.getTimestamp("created_at");

        if (createdAt != null) {

            album.setCreatedAt(
                    createdAt.toLocalDateTime()
            );
        }

        return album;
    }
}