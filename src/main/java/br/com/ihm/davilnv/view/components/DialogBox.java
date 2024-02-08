package br.com.ihm.davilnv.view.components;

import br.com.ihm.davilnv.model.Dialog;
import br.com.ihm.davilnv.utils.StringOptionsUtils;
import br.com.ihm.davilnv.view.frames.BaseFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

@Getter
@Setter
public class DialogBox {

    private static final int RECT_X_OFFSET = 64;
    private static final int RECT_Y_OFFSET = 15;
    private static final int RECT_WIDTH = 312;
    private static final int RECT_HEIGHT = 105;
    private static final int FONT_SIZE = 8;
    private static final int TEXT_X_OFFSET = 74;
    private static final int TEXT_Y_OFFSET = 30;
    private static final int TEXT_MAX_WIDTH = 185;

    private Dialog[] dialogues;
    private int currentDialogueIndex = 0;
    private boolean isVisible = false;

    private Scene scene;
    private BufferedImage characterSprite;
    private int currentSceneIndex = 0;

    private static final int FRAMES_PER_SECOND = 20;
    private int frameCounter = 0;
    private boolean isActive;
    ImageIcon spaceKeyIcon;

    public DialogBox() {
        spaceKeyIcon = new ImageIcon(Objects.requireNonNull(DialogBox.class.getResource("/assets/images/icons/space-keyboard-50-perc.png")));
    }

    public void nextDialogue() {
        if (currentDialogueIndex < dialogues.length - 1) {
            currentDialogueIndex++;
        } else {
            isVisible = false;
            currentDialogueIndex = 0;
            currentSceneIndex = 0;
        }
    }

    public void nextScene() {
        if (currentSceneIndex < scene.getSubScenes().size() - 1) {
            currentSceneIndex++;
        } else {
            currentSceneIndex = 0;
        }
        characterSprite = scene.getSubScenes().get(currentSceneIndex);
    }

    public void draw(Graphics2D g2d, int x, int y) {
        if (isVisible) {
            drawRectangle(g2d, x, y);
            drawText(g2d, x, y);
            drawCharacterSprite(g2d, x, y);

            frameCounter++;
            if (frameCounter >= FRAMES_PER_SECOND) {
                frameCounter = 0;
                isActive = !isActive;
            }

            if (isActive) {
                drawKeyboardWarning(g2d, x, y);
            }

        }
    }

    private void drawRectangle(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x + RECT_X_OFFSET, y + RECT_Y_OFFSET, RECT_WIDTH, RECT_HEIGHT);
    }

    private void drawText(Graphics2D g2d, int x, int y) {
        g2d.setFont(BaseFrame.getFont(FONT_SIZE));
        g2d.setColor(Color.BLACK);

        FontMetrics fm = g2d.getFontMetrics();
        String text = dialogues[currentDialogueIndex].getText();
        int lineHeight = fm.getHeight();
        int curX = x + TEXT_X_OFFSET;
        int curY = y + TEXT_Y_OFFSET;

        StringOptionsUtils.getTextOnLInes(g2d, fm, text, lineHeight, curX, curY, TEXT_MAX_WIDTH);
    }

    private void drawCharacterSprite(Graphics2D g2d, int x, int y) {
        g2d.drawImage(characterSprite, x + RECT_X_OFFSET + 200, y + RECT_Y_OFFSET, null);
    }

    private void drawKeyboardWarning(Graphics2D g2d, int x, int y) {
        g2d.drawString("Para continuar pressione", x + TEXT_X_OFFSET, y + RECT_HEIGHT + 10);
        g2d.drawImage(spaceKeyIcon.getImage(), x + RECT_X_OFFSET + 118, y + RECT_HEIGHT + 3, null);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        if (currentSceneIndex <= 1) {
            characterSprite = scene.getSubScenes().get(0);
        }
    }

}