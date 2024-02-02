package br.com.ihm.davilnv.view;

import br.com.ihm.davilnv.controller.GameController;
import br.com.ihm.davilnv.model.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class MapPanel extends BasePanel {

    private Logica logica;
    private Personagem personagem;
    private Image offscreenImage;
    private DialogBox dialogBox;

    private static final double ZOOM_LEVEL = 2.0;

    public MapPanel(String key) {
        super(key);
        dialogBox = new DialogBox();
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

        double xOffset = personagem.getX() - getWidth() / (2 * ZOOM_LEVEL);
        double yOffset = personagem.getY() - getHeight() / (2 * ZOOM_LEVEL);

        g2d.scale(ZOOM_LEVEL, ZOOM_LEVEL);
        g2d.translate(-xOffset, -yOffset);

        g2d.drawImage(logica.getCamada("floor").camada, 0, 0, null);
        g2d.drawImage(logica.getCamada("second-floor").camada, 0, 0, null);
        g2d.drawImage(logica.getCamada("colision").camada, 0, 0, null);

        for (NPC npc : logica.getNpcs()) {
            g2d.drawImage(npc.getSprites()[npc.getAparencia()], npc.getX(), npc.getY(), null);
        }

//        showColisionRectangle(g2d); // TODO: Mostar retângulos de colisão

        g2d.drawImage(personagem.getSprites()[personagem.getAparencia()], personagem.getX(), personagem.getY(), null);

        g2d.drawImage(logica.getCamada("top").camada, 0, 0, null);
        g2d.drawImage(logica.getCamada("front-top").camada, 0, 0, null);


        if (dialogBox.isVisible()) {
            dialogBox.draw(g2d, personagem.getNearbyNPC().getX(), personagem.getNearbyNPC().getY());
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
        g.drawRect((int) personagem.getPersonagemRectangle().getX(), (int) personagem.getPersonagemRectangle().getY(), (int) personagem.getPersonagemRectangle().getWidth(), (int) personagem.getPersonagemRectangle().getHeight());

        for (NPC npc : logica.getNpcs()) {
            g.drawRect((int) npc.getNPCRectangle().getX(), (int) npc.getNPCRectangle().getY(), (int) npc.getNPCRectangle().getWidth(), (int) npc.getNPCRectangle().getHeight());
        }

        g.drawRect((int) logica.getComputador().getRectangle().getX(), (int) logica.getComputador().getRectangle().getY(), (int) logica.getComputador().getRectangle().getWidth(), (int) logica.getComputador().getRectangle().getHeight());
    }

}
