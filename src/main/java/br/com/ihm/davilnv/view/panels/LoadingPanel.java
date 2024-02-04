package br.com.ihm.davilnv.view.panels;

import br.com.ihm.davilnv.view.frames.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LoadingPanel extends BasePanel {

	ImageIcon loadingGif;
	JLabel loadingLabel;

	public LoadingPanel(String key, String imageBackground) {
		super(key, imageBackground);
		setLayout(null);
		loadingGif = new ImageIcon(Objects.requireNonNull(LoadingPanel.class.getResource("/assets/images/dog.gif")));
		loadingLabel = new JLabel(loadingGif);
		loadingLabel.setBounds(550, 407, BaseFrame.DEFAULT_WIDTH, BaseFrame.DEFAULT_HEIGHT);

		add(loadingLabel);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage.getImage(), 0, 0, null);
		g.setColor(Color.WHITE);
		g.setFont(BaseFrame.DEFAULT_FONT.deriveFont(Font.BOLD, 50));
		g.drawString("Carregando...", 20, BaseFrame.DEFAULT_HEIGHT - 20);
	}

}
