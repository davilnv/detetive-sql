package br.com.ihm.davilnv.view.components;

import br.com.ihm.davilnv.view.TelaGenerica;

import javax.swing.*;
import java.util.Objects;

public class GameButton extends JButton {

    private String key;

    public GameButton(String key, int marginBottom) {
        this.key = key;
        setBounds(
                (TelaGenerica.DEFAULT_WIDTH - TelaGenerica.DEFAULT_WIDTH_BUTTON) / 2,
                ((TelaGenerica.DEFAULT_HEIGHT - TelaGenerica.DEFAULT_HEIGHT_BUTTON) / 2) + marginBottom,
                TelaGenerica.DEFAULT_WIDTH_BUTTON,
                TelaGenerica.DEFAULT_HEIGHT_BUTTON
        );
        setIcon(new ImageIcon(Objects.requireNonNull(GameButton.class.getResource(TelaGenerica.DEFAULT_BUTTONS_PATH + "btn-" + key + ".png"))));
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
