package com.jspotify.controller;

import com.jspotify.model.Song;
import com.jspotify.player.MusicPlayer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SongRowControllerTest {

    @Test
    void handlePlayLoadsSelectedSongOnSharedPlayer() throws Exception {
        Platform.startup(() -> {});

        SongRowController controller = new SongRowController();

        Field songTitleLabelField = SongRowController.class.getDeclaredField("songTitleLabel");
        songTitleLabelField.setAccessible(true);
        songTitleLabelField.set(controller, new Label());

        Field durationLabelField = SongRowController.class.getDeclaredField("durationLabel");
        durationLabelField.setAccessible(true);
        durationLabelField.set(controller, new Label());

        Field playButtonField = SongRowController.class.getDeclaredField("playButton");
        playButtonField.setAccessible(true);
        playButtonField.set(controller, new Button());

        Song song = new Song(
                1L,
                "Neon Lights",
                7L,
                null,
                214,
                "storage/music/neon-lights.wav",
                "",
                LocalDateTime.now()
        );

        controller.setSong(song);

        Method handlePlay = SongRowController.class.getDeclaredMethod("handlePlay");
        handlePlay.setAccessible(true);
        handlePlay.invoke(controller);

        MusicPlayer player = MusicPlayer.getInstance();

        assertTrue(player.isLoaded());
        assertEquals("Neon Lights", player.getCurrentSong().getTitle());
    }
}
