package br.com.ihm.davilnv.view.components;

import br.com.ihm.davilnv.view.BaseFrame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class GameScrollBar extends BasicScrollBarUI {
    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = BaseFrame.DEFAULT_COLOR;
        this.trackColor = BaseFrame.DEFAULT_COLOR;
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }

}
