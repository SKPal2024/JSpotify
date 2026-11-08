package com.jspotify.controller;

import com.jspotify.model.Artist;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class ArtistCardController {

    @FXML
    private Label artistNameLabel;

    @FXML
    private Circle artistImagePlaceholder;


    public void setArtist(Artist artist) {

        if (artist == null) {
            return;
        }

        artistNameLabel.setText(
                artist.getName()
        );
    }
}