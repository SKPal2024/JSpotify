package com.jspotify.player;

import com.jspotify.model.Song;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MusicPlayer {

    private static final MusicPlayer INSTANCE =
            new MusicPlayer();

    private MediaPlayer mediaPlayer;

    private Song currentSong;

    private PlaybackState playbackState =
            PlaybackState.STOPPED;


    private MusicPlayer() {
    }


    public static MusicPlayer getInstance() {
        return INSTANCE;
    }


    public void loadSong(Song song) {

        if (song == null) {
            return;
        }


        /*
         * Stop the previous song.
         */
        stop();


        File audioFile =
                new File(
                        song.getAudioPath()
                );


        if (!audioFile.exists()) {

            System.out.println(
                    "❌ Audio file not found:"
            );

            System.out.println(
                    audioFile.getAbsolutePath()
            );

            return;
        }


        /*
         * Convert the local file into
         * a URL that JavaFX Media understands.
         */
        String mediaUrl =
                audioFile
                        .toURI()
                        .toString();


        System.out.println(
                "Loading:"
        );

        System.out.println(
                mediaUrl
        );


        Media media =
                new Media(mediaUrl);


        mediaPlayer =
                new MediaPlayer(media);


        currentSong =
                song;


        playbackState =
                PlaybackState.STOPPED;


        /*
         * When the song finishes,
         * change state to STOPPED.
         */
        mediaPlayer.setOnEndOfMedia(() -> {

            playbackState =
                    PlaybackState.STOPPED;

            System.out.println(
                    "Song finished: "
                            + currentSong.getTitle()
            );
        });


        /*
         * Handle media errors.
         */
        mediaPlayer.setOnError(() -> {

            System.out.println(
                    "❌ MediaPlayer error:"
            );

            if (mediaPlayer.getError()
                    != null) {

                mediaPlayer
                        .getError()
                        .printStackTrace();
            }
        });
    }


    public void play() {

        if (mediaPlayer == null) {

            System.out.println(
                    "No song loaded."
            );

            return;
        }


        mediaPlayer.play();


        playbackState =
                PlaybackState.PLAYING;


        System.out.println(
                "▶ Playing: "
                        + currentSong.getTitle()
        );
    }


    public void pause() {

        if (mediaPlayer == null) {
            return;
        }


        mediaPlayer.pause();


        playbackState =
                PlaybackState.PAUSED;


        System.out.println(
                "⏸ Paused"
        );
    }


    public void stop() {

        if (mediaPlayer != null) {

            mediaPlayer.stop();

            mediaPlayer.dispose();

            mediaPlayer = null;
        }


        playbackState =
                PlaybackState.STOPPED;
    }


    public void togglePlayPause() {

        if (mediaPlayer == null) {

            System.out.println(
                    "No song loaded."
            );

            return;
        }


        if (playbackState ==
                PlaybackState.PLAYING) {

            pause();

        } else {

            play();
        }
    }


    public void seek(
            double seconds) {

        if (mediaPlayer == null) {
            return;
        }


        mediaPlayer.seek(
                Duration.seconds(seconds)
        );
    }


    public void setVolume(
            double volume) {

        if (mediaPlayer == null) {
            return;
        }


        /*
         * JavaFX volume range:
         *
         * 0.0 = muted
         * 1.0 = maximum
         */
        volume =
                Math.max(
                        0.0,
                        Math.min(
                                1.0,
                                volume
                        )
                );


        mediaPlayer.setVolume(
                volume
        );
    }


    public Duration getCurrentTime() {

        if (mediaPlayer == null) {

            return Duration.ZERO;
        }

        return mediaPlayer
                .getCurrentTime();
    }


    public Duration getTotalDuration() {

        if (mediaPlayer == null) {

            return Duration.ZERO;
        }

        return mediaPlayer
                .getTotalDuration();
    }


    public Song getCurrentSong() {

        return currentSong;
    }


    public PlaybackState getPlaybackState() {

        return playbackState;
    }


    public boolean isLoaded() {

        return mediaPlayer != null;
    }
}