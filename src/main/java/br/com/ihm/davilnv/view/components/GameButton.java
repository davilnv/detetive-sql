package br.com.ihm.davilnv.view.components;

import br.com.ihm.davilnv.view.TelaGenerica;

import javax.swing.*;
import java.util.Objects;

public class GameButton extends JButton {

    private String key;

    public GameButton(String key, int marginBottom, int marginLeft, int width, int height) {
        this.key = key;
        setBounds(
                ((TelaGenerica.DEFAULT_WIDTH - isZero(width, TelaGenerica.DEFAULT_WIDTH_BUTTON)) / 2) + marginLeft,
                ((TelaGenerica.DEFAULT_HEIGHT - isZero(height, TelaGenerica.DEFAULT_HEIGHT_BUTTON)) / 2) + marginBottom,
                isZero(width, TelaGenerica.DEFAULT_WIDTH_BUTTON),
                isZero(height, TelaGenerica.DEFAULT_HEIGHT_BUTTON)
        );
        setIcon(new ImageIcon(Objects.requireNonNull(GameButton.class.getResource(TelaGenerica.DEFAULT_BUTTONS_PATH + "btn-" + key + ".png"))));
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int isZero(int x, int xDefault) {
    	return x == 0 ? xDefault : x;
    }

}
