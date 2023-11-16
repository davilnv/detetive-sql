package br.com.ihm.davilnv.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Credito extends TelaGenerica{
	private JButton voltarButton;
	
	public Credito(String imageBackground) {
		super(imageBackground);

		defaultPanel = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.drawImage(backgroundImage.getImage(), 0, 0, null);
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, TelaGenerica.DEFAULT_WIDTH, TelaGenerica.DEFAULT_HEIGHT);
				g.setColor(Color.BLACK);
				g.drawRect(15, 110, 610, 300);
				g.setColor(Color.BLACK);
				g.setFont(new Font("Arial", Font.BOLD, 30));
				g.drawString("Cr�ditos", 255, 150);
				g.setFont(new Font("Arial", Font.BOLD, 15));
				g.drawString("Jogo criado por: Davi de Lima das Neves", 175, 180);
				g.drawString("Projeto criado para a disciplina", 210, 200);
				g.drawString("Modelagem e Programa��o Orientada a Objetos (MPOO)", 125, 220);
				g.drawString("Professor: Richarlyson D'Emery", 210, 240);
				g.drawString("Universidade Federal Rural de Pernambuco - UFRPE", 135, 260);
				g.drawString("Unidade Acad�mica de Serra Talhada - UAST", 175, 280);
//				g.drawImage(logoUast.getImage(), 245, 300, null);
			}
		};
		defaultPanel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		voltarButton = new JButton("Voltar");
		voltarButton.setBounds(220, 450, 200, 20);

		add(voltarButton);
		add(defaultPanel);
		
	}
	
	public JButton getVoltarButton() {
		return voltarButton;
	}

}
