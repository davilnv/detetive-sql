package br.com.ihm.davilnv.model;

import br.com.ihm.davilnv.controller.GameController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop {
    private Timer timer;
    private final int targetFPS;
    private final GameController gameController;
    public static int frameCount = 0;

    public GameLoop(int targetFPS, GameController gameController) {
        this.targetFPS = targetFPS;
        this.gameController = gameController;
    }

    public void start() {
        timer = new Timer(1000 / targetFPS, new ActionListener() {
            private long lastTime = System.currentTimeMillis();
            private int frames = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - lastTime;

                if (elapsedTime >= 1000 / targetFPS) {
                    gameController.montarMapa();
                    gameController.getMapPanel().repaint();
                    frames++;

                    if (currentTime - lastTime >= 1000) {
                        frameCount = frames;
                        frames = 0;
                        lastTime = currentTime;
                    }
                }
            }
        });
        timer.start();
    }

    public void restart() {
        if (timer != null) {
            timer.restart();
        }
    }

    public void stop() {
        if (timer != null) {
            timer.stop();
        }
    }
}