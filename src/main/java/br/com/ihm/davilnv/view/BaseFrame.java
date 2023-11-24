package br.com.ihm.davilnv.view;

import br.com.ihm.davilnv.view.components.GameButton;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class BaseFrame extends JFrame{
	public static final int DEFAULT_WIDTH = 1920;
	public static final int DEFAULT_HEIGHT = 1080;
	public static final int DEFAULT_WIDTH_BUTTON = 300;
	public static final int DEFAULT_HEIGHT_BUTTON = 60;
	public static final String DEFAULT_BUTTONS_PATH = "/assets/images/buttons/";
	private static final String DEFAULT_MENU_PANEL_KEY = "menu";
	private static final String DEFAULT_BACK_BUTTON_KEY = "seta-voltar";

	public static final Font DEFAULT_FONT;

	static {
		try {
			String fName = "/assets/fonts/aesymatt.ttf";
			InputStream is = BaseFrame.class.getResourceAsStream(fName);
			DEFAULT_FONT = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			System.err.println("Erro ao carregar fonte");
			throw new RuntimeException(e);
		}
	}

	public JPanel defaultPanel;

	public List<BasePanel> panels;
	public List<GameButton> buttons;

	public ImageIcon backgroundImage;

	public BaseFrame(String imagePath) {
		panels = new ArrayList<>();
		buttons = new ArrayList<>();
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLayout(null);

		backgroundImage = new ImageIcon(Objects.requireNonNull(BaseFrame.class.getResource(imagePath)));

		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	public JButton getButtonByKey(String key) {
		for (GameButton button : buttons) {
			if (button.getKey().equals(key)) {
				return button;
			}
		}
		return null;
	}

	public void disableAllButtons() {
		for (GameButton button : buttons) {
			button.setVisible(false);
		}
	}

	public void enableAllButtons() {
		for (GameButton button : buttons) {
			button.setVisible(true);
		}
	}

	public BasePanel getPanelByKey(String key) {
		for (BasePanel panel : panels) {
			if (panel.getKey().equals(key)) {
				return panel;
			}
		}
		return null;
	}

	public BasePanel getCurrentPanel() {
		for (BasePanel panel : panels) {
			if (panel.isVisible()) {
				return panel;
			}
		}
		return null;
	}

	public void disableMenuComponents(String panelKey) {
		getPanelByKey(DEFAULT_MENU_PANEL_KEY).setVisible(false);
		disableAllButtons();
		getPanelByKey(panelKey).setVisible(true);
		getButtonByKey(DEFAULT_BACK_BUTTON_KEY).setVisible(true);
	}

	public void enableMenuComponents(String panelKey) {
		getPanelByKey(DEFAULT_MENU_PANEL_KEY).setVisible(true);
		enableAllButtons();
		getPanelByKey(panelKey).setVisible(false);
		getButtonByKey(DEFAULT_BACK_BUTTON_KEY).setVisible(false);
	}

}
