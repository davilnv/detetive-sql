package br.com.ihm.davilnv.view;

import br.com.ihm.davilnv.controller.GameController;
import br.com.ihm.davilnv.model.Camada;
import br.com.ihm.davilnv.model.Logica;
import br.com.ihm.davilnv.model.Personagem;

import java.awt.*;
import java.util.List;

public class MapPanel extends BasePanel {

	private Logica logica;
	private Personagem personagem;

	public MapPanel(String key) {
		super(key);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//
		g.drawImage(logica.getCamada("floor").camada, 0, 0, null);
		g.drawImage(logica.getCamada("second-floor").camada, 0, 0, null);
		g.drawImage(logica.getCamada("colision").camada, 0, 0, null);

		showColisionRectangle(g);

		g.drawImage(personagem.getSprites()[personagem.getAparencia()], personagem.getX(), personagem.getY(), null);
		g.drawImage(logica.getCamada("top").camada, 0, 0, null);
		g.drawImage(logica.getCamada("front-top").camada, 0, 0, null);


//		g.dispose();
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
