package com.jspotify.controller;

import com.jspotify.model.Song;
import com.jspotify.player.MusicPlayer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SongRowController {

    @FXML
    private Button playButton;

    @FXML
    private Label songTitleLabel;

    @FXML
    private Label durationLabel;

    private Song song;


    public void setSong(Song song) {

        if (song == null) {
            return;
        }

        this.song = song;

        songTitleLabel.setText(
                song.getTitle()
        );

        durationLabel.setText(
                formatDuration(
                        song.getDurationSeconds()
                )
        );
    }


    @FXML
    private void handlePlay() {

        if (song == null) {
            return;
        }

        MusicPlayer musicPlayer =
                MusicPlayer.getInstance();

        musicPlayer.loadSong(song);
        musicPlayer.play();

        System.out.println(
                "Play requested: "
                        + song.getTitle()
        );

        System.out.println(
                "Audio path: "
                        + song.getAudioPath()
        );
    }


    private String formatDuration(
            int totalSeconds) {

        int minutes =
                totalSeconds / 60;

        int seconds =
                totalSeconds % 60;

        return String.format(
                "%d:%02d",
                minutes,
                seconds
        );
    }
}