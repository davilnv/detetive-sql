package br.com.ihm.davilnv.view.panels;

import br.com.ihm.davilnv.controller.GameController;
import br.com.ihm.davilnv.model.*;
import br.com.ihm.davilnv.view.components.DialogBox;
import br.com.ihm.davilnv.view.components.Phone;
import br.com.ihm.davilnv.view.frames.BaseFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

@Getter
@Setter
public class MapPanel extends BasePanel {

    private Logic logic;
    private Player player;
    private Image offscreenImage;
    private DialogBox dialogBox;
    private Phone phone;

    private static final double ZOOM_LEVEL = 2.0;
    private static final int RECT_X_OFFSET = 64;
    private static final int RECT_Y_OFFSET = 15;
    private static final int RECT_WIDTH = 312;
    private static final int RECT_HEIGHT = 50;
    private static final int FONT_SIZE = 8;
    private static final int TEXT_X_OFFSET = 74;
    private static final int TEXT_Y_OFFSET = 30;
    private static final int TEXT_MAX_WIDTH = 185;

    private static final int FRAMES_PER_SECOND = 20;
    private int frameCounter = 0;
    private boolean isActive;
    private ImageIcon enterKeyIcon;

    public MapPanel(String key) {
        super(key);
        dialogBox = new DialogBox();
        phone = new Phone();
        setDoubleBuffered(true);
    }

    public void createOffscreenImage() {
        offscreenImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        enterKeyIcon = new ImageIcon(Objects.requireNonNull(DialogBox.class.getResource("/assets/images/icons/enter-keyboard-50-perc.png")));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics offscreenGraphics = offscreenImage.getGraphics();

        // Draw on the off-screen image
        super.paintComponent(offscreenGraphics);
        Graphics2D g2d = (Graphics2D) offscreenGraphics;

        double xOffset = player.getX() - getWidth() / (2 * ZOOM_LEVEL);
        double yOffset = player.getY() - getHeight() / (2 * ZOOM_LEVEL);

        g2d.scale(ZOOM_LEVEL, ZOOM_LEVEL);
        g2d.translate(-xOffset, -yOffset);

        g2d.drawImage(logic.getLayer("floor").layerImage, 0, 0, null);
        g2d.drawImage(logic.getLayer("second-floor").layerImage, 0, 0, null);
        g2d.drawImage(logic.getLayer("colision").layerImage, 0, 0, null);

        for (NPC npc : logic.getNpcs()) {
            g2d.drawImage(npc.getSprites()[npc.getAparencia()], npc.getX(), npc.getY(), null);
        }

        //showColisionRectangle(g2d); // TODO: Mostar retângulos de colisão

        g2d.drawImage(player.getSprites()[player.getAparencia()], player.getX(), player.getY(), null);

        g2d.drawImage(logic.getLayer("top").layerImage, 0, 0, null);
        g2d.drawImage(logic.getLayer("front-top").layerImage, 0, 0, null);

        NPC nearbyNPC = player.getNearbyNPC(logic.getNpcs());

        if (player.getNearbyComputer(logic.getComputer()) || player.getNearbyComputer(logic.getInfoPanelComputer()) || nearbyNPC != null) {
            drawKeyboardWarning(g2d, player.getX(), player.getY());
        }

        if (dialogBox.isVisible()) {
            dialogBox.draw(g2d, player.getNearbyNPC().getX(), player.getNearbyNPC().getY());
        }

        if (phone.isVisible()) {
            phone.draw(g2d, player.getX(), player.getY());
        }

        // TODO: FPS
//		g2d.setColor(Color.WHITE);
//		g2d.setFont(BaseFrame.DEFAULT_FONT.deriveFont(Font.BOLD, 50));
//		g2d.drawString("FPS: " + GameLoop.frameCount, 20, BaseFrame.DEFAULT_HEIGHT - 20);

        // Dispose the off-screen graphics
        offscreenGraphics.dispose();

        // Draw the off-screen image to the screen
        g.drawImage(offscreenImage, 0, 0, this);
    }

    private void drawKeyboardWarning(Graphics2D g2d, int x, int y) {
        g2d.setFont(BaseFrame.getFont(FONT_SIZE));
        frameCounter++;
        if (frameCounter >= FRAMES_PER_SECOND) {
            frameCounter = 0;
            isActive = !isActive;
        }

        if (isActive) {
            g2d.drawString("Para interagir pressione", x + TEXT_X_OFFSET, y + RECT_HEIGHT + 10);
            g2d.drawImage(enterKeyIcon.getImage(), x + RECT_X_OFFSET + 118, y + RECT_HEIGHT + 3, null);
        }
    }

    private void showColisionRectangle(Graphics2D g) {
        g.setColor(Color.RED); // Cor dos retângulos
        for (Rectangle collisionRect : GameController.colisao) {
            g.drawRect(collisionRect.x, collisionRect.y, collisionRect.width, collisionRect.height);
        }
        g.drawRect((int) player.getPersonagemRectangle().getX(), (int) player.getPersonagemRectangle().getY(), (int) player.getPersonagemRectangle().getWidth(), (int) player.getPersonagemRectangle().getHeight());

        for (NPC npc : logic.getNpcs()) {
            g.drawRect((int) npc.getNPCRectangle().getX(), (int) npc.getNPCRectangle().getY(), (int) npc.getNPCRectangle().getWidth(), (int) npc.getNPCRectangle().getHeight());
        }

        g.drawRect((int) logic.getComputer().getRectangle().getX(), (int) logic.getComputer().getRectangle().getY(), (int) logic.getComputer().getRectangle().getWidth(), (int) logic.getComputer().getRectangle().getHeight());
        g.drawRect((int) logic.getInfoPanelComputer().getRectangle().getX(), (int) logic.getInfoPanelComputer().getRectangle().getY(), (int) logic.getInfoPanelComputer().getRectangle().getWidth(), (int) logic.getInfoPanelComputer().getRectangle().getHeight());
    }

}
