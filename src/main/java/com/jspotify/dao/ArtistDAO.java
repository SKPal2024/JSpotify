package com.jspotify.dao;

import com.jspotify.database.DatabaseConnection;
import com.jspotify.model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {

    public Artist create(Artist artist)
            throws SQLException {

        String sql =
                """
                INSERT INTO artists
                (name, bio, image_path)
                VALUES (?, ?, ?)
                RETURNING id, created_at
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    artist.getName()
            );

            statement.setString(
                    2,
                    artist.getBio()
            );

            statement.setString(
                    3,
                    artist.getImagePath()
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    artist.setId(
                            resultSet.getLong("id")
                    );

                    artist.setCreatedAt(
                            resultSet
                                    .getTimestamp("created_at")
                                    .toLocalDateTime()
                    );

                    return artist;
                }
            }
        }

        throw new SQLException(
                "Artist creation failed."
        );
    }


    public Artist findById(long id)
            throws SQLException {

        String sql =
                """
                SELECT
                    id,
                    name,
                    bio,
                    image_path,
                    created_at
                FROM artists
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
                    return mapResultSetToArtist(
                            resultSet
                    );
                }
            }
        }

        return null;
    }


    public Artist findByName(String name)
            throws SQLException {

        String sql =
                """
                SELECT
                    id,
                    name,
                    bio,
                    image_path,
                    created_at
                FROM artists
                WHERE name = ?
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, name);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapResultSetToArtist(
                            resultSet
                    );
                }
            }
        }

        return null;
    }


    public List<Artist> findAll()
            throws SQLException {

        String sql =
                """
                SELECT
                    id,
                    name,
                    bio,
                    image_path,
                    created_at
                FROM artists
                ORDER BY name
                """;

        List<Artist> artists =
                new ArrayList<>();

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                artists.add(
                        mapResultSetToArtist(
                                resultSet
                        )
                );
            }
        }

        return artists;
    }


    public boolean update(Artist artist)
            throws SQLException {

        String sql =
                """
                UPDATE artists
                SET
                    name = ?,
                    bio = ?,
                    image_path = ?
                WHERE id = ?
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    artist.getName()
            );

            statement.setString(
                    2,
                    artist.getBio()
            );

            statement.setString(
                    3,
                    artist.getImagePath()
            );

            statement.setLong(
                    4,
                    artist.getId()
            );

            return statement.executeUpdate() > 0;
        }
    }


    public boolean delete(long id)
            throws SQLException {

        String sql =
                """
                DELETE FROM artists
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


    private Artist mapResultSetToArtist(
            ResultSet resultSet)
            throws SQLException {

        Artist artist = new Artist();

        artist.setId(
                resultSet.getLong("id")
        );

        artist.setName(
                resultSet.getString("name")
        );

        artist.setBio(
                resultSet.getString("bio")
        );

        artist.setImagePath(
                resultSet.getString("image_path")
        );

        Timestamp timestamp =
                resultSet.getTimestamp("created_at");

        if (timestamp != null) {

            artist.setCreatedAt(
                    timestamp.toLocalDateTime()
            );
        }

        return artist;
    }
}