package br.com.ihm.davilnv.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Tutorial extends TelaGenerica{
	
	private JButton voltarButton;
	
	public Tutorial(String imageBackground) {
		super(imageBackground);

		defaultPanel = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.drawImage(backgroundImage.getImage(), 0, 0, null);
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, TelaGenerica.DEFAULT_WIDTH, TelaGenerica.DEFAULT_HEIGHT);
				g.setColor(Color.BLACK);
				g.drawRect(15, 110, 610, 250);
				g.setFont(new Font("Arial", Font.BOLD, 15));
				g.drawString("O objetivo do jogo � ensinar de uma forma din�mica as opera��es matem�tica.", 20, 130);
				g.drawString("Os n�meros podem ser inimigos ou o resultado da opera��o. A opera��o � mostrado", 20, 150);
				g.drawString("na lousa e o jogador precisa encostar apenas no que ele considera ser o resultado", 20, 170);
				g.drawString("da opera��o, perdendo vida quando encostar em um n�mero errado. Para a segunda", 20, 190);
				g.drawString("fase precisa conseguir 15 pontos, ganha quando conseguir 30 pontos. Para o", 20, 210);
				g.drawString("multplayer funciona da mesma forma, ganha quem conseguir a pontua��o primeiro.", 20, 230);

				g.drawString("- Status do jogador:", 20, 250);
				g.drawString("    -> Somando: O jogador est� com vida e pode continuar acumulando pontos;", 20, 270);
				g.drawString("    -> Game Over: O jogador chegou sua vida a zero e o jogo finaliza, para reiniciar", 20, 290);
				g.drawString("       apertar o bot�o sair;", 20, 310);
				g.drawString("    -> Ganhou: O jogador conseguiu alcan�ar 30 pontos e conseguiu ganhar o jogo, ", 20, 330);
				g.drawString("       para reiniciar apertar o bot�o sair", 20, 350);
				g.drawString("Criado por Davi Lima", 450, 460);
			}
		};
		defaultPanel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		voltarButton = new JButton("Voltar");
		voltarButton.setBounds(220, 480, 200, 20);

		add(voltarButton);
		add(defaultPanel);
	}

	public JButton getVoltarButton() {
		return voltarButton;
	}
	
	
}
