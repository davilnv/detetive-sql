package br.com.ihm.davilnv.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.ihm.davilnv.model.Personagem;

public class Inventario extends JPanel{
	private BufferedImage iconeCoracao, iconeEstrela;
	private Personagem personagem, personagem2;
	private JButton sairButton;
	private String status = "Somando", status2 = "Somando";
	private boolean multplayer;
	
	public Inventario(Personagem personagem, Personagem personagem2) throws IOException {
		setSize(125, 512);
		setLocation(514, 0);
		setLayout(null);
		this.personagem = personagem;
		this.personagem2 = personagem2;
		sairButton = new JButton("Sair");
		sairButton.setBounds(535, 480, 80, 20);

		iconeCoracao = ImageIO.read(new FileInputStream("src/main/resources/res/iconeCoracao.png"));
		iconeEstrela = ImageIO.read(new FileInputStream("src/main/resources/res/iconeEstrela.png"));
		add(sairButton);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (multplayer) {
			multPlayer(g);
		} else {
			singlePlayer(g);
		}
	}
	
	public void singlePlayer(Graphics g) {
		Color marrom = new Color(57, 43, 30);
		g.setColor(marrom);
		g.fillRect(514, 0, 125, 512);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.drawString("Invet�rio", 553, 15);
		g.drawLine(514, 17, Tela.LARGURA, 17);
		g.drawImage(iconeCoracao, 530, 27, null);
		g.drawString("HP: " + personagem.getVida(), 553, 40);
		g.drawImage(iconeEstrela, 530, 47, null);
		g.drawString("Pontos: " + personagem.getPontos(), 553, 58);
		if (personagem.getVida() == 0) {
			status = "Game Over!";
		}
		if (personagem.getPontos() == 60) {
			status = "Ganhou!!!";
		}
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString("Status: ", 530, 240);
		g.drawString(status, 530, 260);
	}
	
	public void multPlayer(Graphics g) {
		Color marrom = new Color(57, 43, 30);
		g.setColor(marrom);
		g.fillRect(514, 0, 125, 512);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.drawString("Invet�rio", 553, 15);
		g.drawLine(514, 17, Tela.LARGURA, 17);
		g.drawLine(514, Tela.ALTURA/2, Tela.LARGURA, Tela.ALTURA/2);
		
		g.drawString("Player 1", 530, 33);
		g.drawImage(iconeCoracao, 530, 40, null);
		g.drawString("HP: " + personagem.getVida(), 553, 52);
		g.drawImage(iconeEstrela, 530, 57, null);
		g.drawString("Pontos: " + personagem.getPontos(), 553, 69);
		if (personagem.getVida() == 0) {
			status = "Game Over!";
		}
		if (personagem.getPontos() == 60) {
			status = "Ganhou!!!";
		}
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString("Status: ", 530, 150);
		g.drawString(status, 530, 170);
		
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.drawString("Player 2", 530, 275);
		g.drawImage(iconeCoracao, 530, 282, null);
		g.drawString("HP: " + personagem2.getVida(), 553, 294);
		g.drawImage(iconeEstrela, 530, 299, null);
		g.drawString("Pontos: " + personagem2.getPontos(), 553, 311);
		// Fazer um status para o player 2
		if (personagem2.getVida() == 0) {
			status2 = "Game Over!";
		}
		if (personagem.getPontos() == 60) {
			status2 = "Ganhou!!!";
		}
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString("Status: ", 530, 380);
		g.drawString(status, 530, 400);
	}

	public JButton getSairButton() {
		return sairButton;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isMultplayer() {
		return multplayer;
	}

	public void setMultplayer(boolean multplayer) {
		this.multplayer = multplayer;
	}
	
}
