package br.com.ihm.davilnv.view.panels;

import br.com.ihm.davilnv.controller.GameController;
import br.com.ihm.davilnv.model.*;
import br.com.ihm.davilnv.view.components.DialogBox;
import br.com.ihm.davilnv.view.components.Phone;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class MapPanel extends BasePanel {

    private Logic logic;
    private Player player;
    private Image offscreenImage;
    private DialogBox dialogBox;
    private Phone phone;

    private static final double ZOOM_LEVEL = 2.0;

    public MapPanel(String key) {
        super(key);
        dialogBox = new DialogBox();
        phone = new Phone();
        setDoubleBuffered(true);
    }

    public void createOffscreenImage() {
        // Create an off-screen image for double buffering
//		offscreenImage = createImage(getWidth(), getHeight());
        offscreenImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
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

//        showColisionRectangle(g2d); // TODO: Mostar retângulos de colisão

        g2d.drawImage(player.getSprites()[player.getAparencia()], player.getX(), player.getY(), null);

        g2d.drawImage(logic.getLayer("top").layerImage, 0, 0, null);
        g2d.drawImage(logic.getLayer("front-top").layerImage, 0, 0, null);


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
    }

}
