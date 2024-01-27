package br.com.ihm.davilnv.view;

import lombok.Getter;

import javax.swing.*;
import java.util.Objects;
@Getter
public abstract class BasePanel extends JPanel{

	private final String key;
	public ImageIcon backgroundImage;

	public BasePanel(String key, String imagePath) {
		this.key = key;
		setSize(BaseFrame.DEFAULT_WIDTH, BaseFrame.DEFAULT_HEIGHT);
		setLocation(0, 0);

		backgroundImage = new ImageIcon(Objects.requireNonNull(BaseFrame.class.getResource(imagePath)));

		setVisible(false);
	}

	public BasePanel(String key) {
		this.key = key;
		setSize(BaseFrame.DEFAULT_WIDTH, BaseFrame.DEFAULT_HEIGHT);
		setLocation(0, 0);

		setVisible(false);
	}

}
