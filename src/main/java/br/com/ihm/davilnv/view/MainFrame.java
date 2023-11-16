package br.com.ihm.davilnv.view;

import br.com.ihm.davilnv.view.components.GameButton;

import java.io.IOException;

public class MainFrame extends BaseFrame {

	public MainFrame() throws IOException {
		super("");

		panels.add(new MenuPanel("menu", "/assets/images/background/background-frames-menu.png"));
		panels.add(new StartPanel("start", "/assets/images/background/background-frames-inicio.png"));
		panels.add(new RankingPanel("ranking", "/assets/images/background/background-frames-ranking.png"));
		panels.add(new HelpPanel("help", "/assets/images/background/background-frames-tutorial.png"));
		panels.add(new CreditPanel("credit", "/assets/images/background/background-frames-creditos.png"));

		buttons.add(new GameButton("jogar", 0, 0, 0,0));
		buttons.add(new GameButton("ranking", 80, 0, 0,0));
		buttons.add(new GameButton("tutorial", 160, 0, 0,0));
		buttons.add(new GameButton("creditos", 240, 0, 0,0));
		buttons.add(new GameButton("sair", 320, 0, 0,0));
		buttons.add(new GameButton("seta-voltar", -400, -864, 52,52));;
		getButtonByKey("seta-voltar").setVisible(false);

		for (GameButton button : buttons) {
			add(button);
		}

		for(BasePanel panel : panels) {
			add(panel);
		}

		setVisible(true);
		
	}

}
