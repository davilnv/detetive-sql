package br.com.ihm.davilnv.statics;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MP3Player {

    private AdvancedPlayer player;
    private String mp3FilePath;

    public MP3Player(String mp3FilePath) throws JavaLayerException {
        this.mp3FilePath = mp3FilePath;
        InputStream is = MP3Player.class.getResourceAsStream(mp3FilePath);
        player = new AdvancedPlayer(is);
    }

    public static void main(String[] args) throws JavaLayerException, IOException {

        new MP3Player("/assets/audios/duck-dodgers-theme-song.mp3").playMp3InLoop();

    }

    public void playMp3() throws JavaLayerException {
        player.setPlayBackListener(new PlaybackListener() {
            @Override
            public void playbackFinished(PlaybackEvent evt) {
                // Reprodução completa
            }
        });
        player.play();
    }

    public void playMp3InLoop() throws JavaLayerException, IOException {
        if (player == null) {
            player = new AdvancedPlayer(Objects.requireNonNull(MP3Player.class.getResourceAsStream(mp3FilePath)));
        }
        player.setPlayBackListener(new PlaybackListener() {
            @Override
            public void playbackFinished(PlaybackEvent evt) {
                // Reinicie a reprodução
                try {
                    player.close();
                    player = new AdvancedPlayer(Objects.requireNonNull(MP3Player.class.getResourceAsStream(mp3FilePath)));
                    player.play();
                } catch (JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        player.play();
    }

    public void stopLoopingPlayback() {
        if (player != null) {
            player.stop();
        }
    }

}