package br.com.ihm.davilnv.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public abstract class TelaGenerica extends JFrame{
	public static final int DEFAULT_WIDTH = 1920;
	public static final int DEFAULT_HEIGHT = 1080;

	public static final int DEFAULT_WIDTH_BUTTON = 300;
	public static final int DEFAULT_HEIGHT_BUTTON = 60;

	public static final String DEFAULT_BUTTONS_PATH = "/assets/images/buttons/";

	public Font DEFAULT_FONT;

	public JPanel defaultPanel;

	public ImageIcon backgroundImage;

	public TelaGenerica(String imagePath) {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLayout(null);

		try {
			String fName = "/assets/fonts/aesymatt.ttf";
			InputStream is = TelaGenerica.class.getResourceAsStream(fName);
			DEFAULT_FONT = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			System.err.println("Erro ao carregar fonte");
			throw new RuntimeException(e);
		}

		backgroundImage = new ImageIcon(Objects.requireNonNull(TelaGenerica.class.getResource(imagePath)));

		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

}
