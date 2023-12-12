package br.com.ihm.davilnv.view;

import br.com.ihm.davilnv.utils.MusicPlayer;
import br.com.ihm.davilnv.view.components.GameCustomSlider;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ConfigPanel extends BasePanel {

	private MusicPlayer musicPlayer;
	private GameCustomSlider volumeSlider;

	public ConfigPanel(String key, String imageBackground){
		super(key, imageBackground);
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage.getImage(), 0, 0, null);

		g.setColor(Color.BLACK);
		g.setFont(BaseFrame.DEFAULT_FONT.deriveFont(Font.BOLD, 35));
		FontMetrics fm = g.getFontMetrics();
		String text = "Volume da Música:  ";
		int x = (BaseFrame.DEFAULT_WIDTH - fm.stringWidth(text)) / 2;
		g.drawString(text, x, 320 );

	}

	public void initVolumeSlider() {
		double volume = musicPlayer.getVolume();
		volumeSlider = new GameCustomSlider(0, 100, (int) (volume * 100));
		volumeSlider.setBounds( BaseFrame.CENTER_DEFAULT_X - 320 / 2, 335, 300, 30);

		volumeSlider.setBackground(BaseFrame.DEFAULT_COLOR);
		volumeSlider.setForeground(Color.BLACK);

		volumeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int volume = volumeSlider.getValue();
				musicPlayer.setVolume(volume / 100.0);
			}
		});
		this.add(volumeSlider);
	}

	public void setMusicPlayer(MusicPlayer musicPlayer) {
		this.musicPlayer = musicPlayer;
	}

}
