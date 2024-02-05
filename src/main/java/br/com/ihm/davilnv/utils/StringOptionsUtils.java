package br.com.ihm.davilnv.utils;

import java.awt.*;

public class StringOptionsUtils {

    public static int getTextOnLInes(Graphics2D g2d, FontMetrics fm, String text, int lineHeight, int curX, int curY, int textMaxWidth) {
        String[] words = text.split(" ");
        StringBuilder curLine = new StringBuilder(words[0]);

        for (int i = 1; i < words.length; i++) {
            int width = fm.stringWidth(curLine + " " + words[i]);
            if (width < textMaxWidth) {
                curLine.append(" ").append(words[i]);
            } else {
                g2d.drawString(curLine.toString(), curX, curY);
                curY += lineHeight;
                curLine = new StringBuilder(words[i]);
            }
        }
        g2d.drawString(curLine.toString(), curX, curY);
        return curY;
    }

}
