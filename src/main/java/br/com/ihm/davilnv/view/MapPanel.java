package br.com.ihm.davilnv.view;

import br.com.ihm.davilnv.controller.GameController;
import br.com.ihm.davilnv.model.*;

import java.awt.*;
import java.util.List;

public class MapPanel extends BasePanel {

	private Logica logica;
	private Personagem personagem;

	private static final double ZOOM_LEVEL = 2.0;

	public MapPanel(String key) {
		super(key);
		setDoubleBuffered(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Create an off-screen image for double buffering
		Image offscreenImage = createImage(getWidth(), getHeight());
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

		g2d.drawImage(personagem.getSprites()[personagem.getAparencia()], personagem.getX(), personagem.getY(), null);
		g2d.drawImage(logica.getCamada("top").camada, 0, 0, null);
		g2d.drawImage(logica.getCamada("front-top").camada, 0, 0, null);

		// TODO: FPS
		g2d.setColor(Color.WHITE);
		System.out.println(GameLoop.frameCount);
		g2d.setFont(BaseFrame.DEFAULT_FONT.deriveFont(Font.BOLD, 50));
		g2d.drawString("FPS: " + GameLoop.frameCount, 20, BaseFrame.DEFAULT_HEIGHT - 20);

		// Dispose the off-screen graphics
		offscreenGraphics.dispose();

		// Draw the off-screen image to the screen
		g.drawImage(offscreenImage, 0, 0, this);
	}

	private void showColisionRectangle(Graphics g) {
		g.setColor(Color.RED); // Cor dos retângulos
		for (Rectangle collisionRect : GameController.colisao) {
			g.drawRect(collisionRect.x, collisionRect.y, collisionRect.width, collisionRect.height);
		}
		g.drawRect(personagem.getX()-Personagem.DIFF_COLISAO, personagem.getY()-Personagem.DIFF_COLISAO, personagem.getLarguraPersonagem()+(Personagem.DIFF_COLISAO*2), personagem.getAlturaPersonagem()+Personagem.DIFF_COLISAO);
	}


	public Logica getLogica() {
		return logica;
	}

	public void setLogica(Logica logica) {
		this.logica = logica;
	}

	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}
}
