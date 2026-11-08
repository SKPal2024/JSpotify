package com.jspotify.controller;
import javafx.geometry.Pos;

import com.jspotify.model.Album;
import com.jspotify.model.Artist;
import com.jspotify.model.Song;
import com.jspotify.model.User;
import com.jspotify.service.MusicLibraryService;
import com.jspotify.session.UserSession;
import com.jspotify.util.SceneManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private HBox artistsContainer;

    @FXML
    private HBox albumsContainer;

    @FXML
    private VBox songsContainer;


    private final MusicLibraryService musicLibraryService;


    public DashboardController() {

        musicLibraryService =
                new MusicLibraryService();
    }


    @FXML
    private void initialize() {

        /*
         * Make sure a user is logged in.
         */
        if (!UserSession.isLoggedIn()) {

            System.out.println(
                    "No user is logged in."
            );

            return;
        }


        /*
         * Get current user.
         */
        User user =
                UserSession.getCurrentUser();


        /*
         * Display user information.
         */
        welcomeLabel.setText(
                "Welcome, " + user.getUsername()
        );

        usernameLabel.setText(
                "Username: " + user.getUsername()
        );

        roleLabel.setText(
                "Role: " + user.getRole()
        );


        /*
         * Load music data.
         */
        loadArtists();
        loadAlbums();
        loadSongs();
    }


    private void loadArtists() {

        try {

            List<Artist> artists =
                    musicLibraryService
                            .getAllArtists();


            artistsContainer
                    .getChildren()
                    .clear();


            if (artists.isEmpty()) {

                Label emptyLabel =
                        new Label(
                                "No artists found"
                        );

                artistsContainer
                        .getChildren()
                        .add(emptyLabel);

                return;
            }


            for (Artist artist : artists) {

                try {

                    FXMLLoader loader =
                            new FXMLLoader(
                                    getClass()
                                            .getResource(
                                                    "/fxml/ArtistCard.fxml"
                                            )
                            );

                    Parent card =
                            loader.load();


                    ArtistCardController controller =
                            loader.getController();


                    controller.setArtist(
                            artist
                    );


                    artistsContainer
                            .getChildren()
                            .add(card);

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            Label errorLabel =
                    new Label(
                            "Could not load artists"
                    );

            artistsContainer
                    .getChildren()
                    .add(errorLabel);
        }
    }


 private void loadAlbums() {

    try {

        List<Album> albums =
                musicLibraryService
                        .getAllAlbums();

        albumsContainer
                .getChildren()
                .clear();

        if (albums.isEmpty()) {

            albumsContainer
                    .getChildren()
                    .add(
                            new Label(
                                    "No albums found"
                            )
                    );

            return;
        }


        for (Album album : albums) {

            try {

                FXMLLoader loader =
                        new FXMLLoader(
                                getClass()
                                        .getResource(
                                                "/fxml/AlbumCard.fxml"
                                        )
                        );

                Parent card =
                        loader.load();

                AlbumCardController controller =
                        loader.getController();


                /*
                 * We don't yet have a dedicated
                 * artist-name query, so display
                 * the artist ID temporarily.
                 */
                controller.setAlbum(
                        album,
                        "Artist #" +
                        album.getArtistId()
                );


                albumsContainer
                        .getChildren()
                        .add(card);

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    } catch (Exception e) {

        e.printStackTrace();

        albumsContainer
                .getChildren()
                .add(
                        new Label(
                                "Could not load albums"
                        )
                );
    }
}
 
private void loadSongs() {

    try {

        List<Song> songs =
                musicLibraryService
                        .getAllSongs();

        songsContainer
                .getChildren()
                .clear();


        if (songs.isEmpty()) {

            songsContainer
                    .getChildren()
                    .add(
                            new Label(
                                    "No songs found"
                            )
                    );

            return;
        }


        for (Song song : songs) {

            try {

                FXMLLoader loader =
                        new FXMLLoader(
                                getClass()
                                        .getResource(
                                                "/fxml/SongRow.fxml"
                                        )
                        );


                Parent row =
                        loader.load();


                SongRowController controller =
                        loader.getController();


                controller.setSong(song);


                songsContainer
                        .getChildren()
                        .add(row);

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    } catch (Exception e) {

        e.printStackTrace();

        songsContainer
                .getChildren()
                .add(
                        new Label(
                                "Could not load songs"
                        )
                );
    }
}

 
    @FXML
    private void handleLogout() {

        UserSession.logout();


        try {

            Stage stage =
                    (Stage) logoutButton
                            .getScene()
                            .getWindow();


            SceneManager.switchScene(
                    stage,
                    "Login.fxml",
                    "JSpotify - Login"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}