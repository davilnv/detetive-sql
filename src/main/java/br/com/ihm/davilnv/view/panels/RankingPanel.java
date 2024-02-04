package br.com.ihm.davilnv.view.panels;

import java.awt.*;

public class RankingPanel extends BasePanel {
	public RankingPanel(String key, String imageBackground) {
		super(key, imageBackground);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage.getImage(), 0, 0, null);
//		g.dispose();
	}

}
