package com.jspotify.controller;

import com.jspotify.model.Album;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class AlbumCardController {

    @FXML
    private Label albumTitleLabel;

    @FXML
    private Label artistLabel;

    @FXML
    private Rectangle coverPlaceholder;


    public void setAlbum(
            Album album,
            String artistName) {

        if (album == null) {
            return;
        }

        albumTitleLabel.setText(
                album.getTitle()
        );

        artistLabel.setText(
                artistName
        );
    }
}
