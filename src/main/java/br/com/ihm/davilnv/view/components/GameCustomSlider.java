package br.com.ihm.davilnv.view.components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class GameCustomSlider extends JSlider {

    public GameCustomSlider(int min, int max, int value) {
        super(min, max, value);
        this.setUI(new CustomSliderUI(this));
    }

    private static class CustomSliderUI extends BasicSliderUI {

        private static final int THUMB_SIZE = 20;
        private static final int TRACK_WIDTH = 10;

        private CustomSliderUI(JSlider b) {
            super(b);
        }

        @Override
        public void paintTrack(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(7f));
            g2d.drawLine(trackRect.x, trackRect.y + trackRect.height / 2,
                    trackRect.x + trackRect.width, trackRect.y + trackRect.height / 2);
        }

        @Override
        public void paintThumb(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            g2d.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
        }

        @Override
        protected Dimension getThumbSize() {
            return new Dimension(THUMB_SIZE, THUMB_SIZE);
        }

        @Override
        protected int getTickLength() {
            return TRACK_WIDTH;
        }
    }
}
