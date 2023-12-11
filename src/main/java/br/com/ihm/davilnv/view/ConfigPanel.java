package br.com.ihm.davilnv.view;

import java.awt.*;

public class ConfigPanel extends BasePanel {
	public ConfigPanel(String key, String imageBackground){
		super(key, imageBackground);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage.getImage(), 0, 0, null);
		g.setColor(Color.WHITE);
		g.setFont(BaseFrame.DEFAULT_FONT.deriveFont(Font.BOLD, 20));
		g.drawString("Versão: 1.0 - Criado por Davi Lima", 20, BaseFrame.DEFAULT_HEIGHT - 20);
	}

}
