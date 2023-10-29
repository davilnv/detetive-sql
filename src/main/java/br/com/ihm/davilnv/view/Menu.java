package br.com.ihm.davilnv.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends TelaGenerica{
	private JButton jogarButton, ajudaButton, creditoButton,sairButton;
	
	public Menu(){
		setLayout(null);
		
		ImageIcon imagem = new ImageIcon(getClass().getClassLoader().getResource("capaMenu.png"));
		
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.drawImage(imagem.getImage(), 0, 0, null);
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", Font.BOLD, 12));
				g.drawString("Vers�o: 2.3", 15, 500);
				g.drawString("Criado por Davi Lima", 510, 500);
			}
		};
		
		panel.setBounds(0, 0, TelaGenerica.LARGURA, TelaGenerica.ALTURA);
		
		jogarButton = new JButton("Jogar");
		jogarButton.setBounds(220, 300, 200, 20);
		ajudaButton = new JButton("Ajuda");
		ajudaButton.setBounds(220, 330, 200, 20);
		creditoButton = new JButton("Cr�ditos");
		creditoButton.setBounds(220, 360, 200, 20);
		sairButton = new JButton("Sair");
		sairButton.setBounds(220, 390, 200, 20);
		
		add(jogarButton);
		add(ajudaButton);
		add(creditoButton);
		add(sairButton);
		add(panel);
		
		setVisible(true);
	}

	public JButton getJogarButton() {
		return jogarButton;
	}

	public JButton getAjudaButton() {
		return ajudaButton;
	}

	public JButton getCreditoButton() {
		return creditoButton;
	}

	public JButton getSairButton() {
		return sairButton;
	}

}
