package br.com.ihm.davilnv.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Inicializacao extends TelaGenerica{
	
	private JButton voltarButton, jogarButton;
	private ImageIcon imagem, iconeSigleplayer, iconeMultplayer;
	private JRadioButton singleButton, multButton, facilButton, medioButton, dificilButton, 
		somaButton, subButton, multiplicaButton, divButton;
	
	public Inicializacao() {
		setLayout(null);
		
		voltarButton = new JButton("Voltar");
		voltarButton.setBounds(220, 450, 100, 20);
		jogarButton = new JButton("Jogar");
		jogarButton.setBounds(320, 450, 100, 20);
		
		imagem = new ImageIcon(Inicializacao.class.getResource("/res/tituloJogo.png"));
		iconeSigleplayer = new ImageIcon(Inicializacao.class.getResource("/res/iconeSingleplayer.png"));
		iconeMultplayer = new ImageIcon(Inicializacao.class.getResource("/res/iconeMultplayer.png"));
		
		ButtonGroup grupoJogador = new ButtonGroup();
		singleButton = new JRadioButton("Single Player", true);
		singleButton.setBounds(195, 180, 100, 20);
		multButton = new JRadioButton("Multplayer", false);
		multButton.setBounds(315, 180, 100, 20);
		grupoJogador.add(singleButton);
		grupoJogador.add(multButton);

		ButtonGroup grupoDificuldade = new ButtonGroup();
		facilButton = new JRadioButton("F�cil", true);
		facilButton.setBounds(215, 240, 55, 20);
		medioButton = new JRadioButton("M�dio", false);
		medioButton.setBounds(275, 240, 60, 20);
		dificilButton = new JRadioButton("Dif�cil", false);
		dificilButton.setBounds(340, 240, 60, 20);
		grupoDificuldade.add(facilButton);
		grupoDificuldade.add(medioButton);
		grupoDificuldade.add(dificilButton);
		
		ButtonGroup grupoOperacao = new ButtonGroup();
		somaButton = new JRadioButton("Soma (+)", true);
		somaButton.setBounds(265, 295, 78, 20);
		subButton = new JRadioButton("Subtra��o (-)", false);
		subButton.setBounds(255, 320, 100, 20);
		multiplicaButton = new JRadioButton("Multiplica��o (X)", false);
		multiplicaButton.setBounds(245, 345, 120, 20);
		divButton = new JRadioButton("Divis�o (/)", false);
		divButton.setBounds(265, 370, 80, 20);
		grupoOperacao.add(somaButton);
		grupoOperacao.add(subButton);
		grupoOperacao.add(multiplicaButton);
		grupoOperacao.add(divButton);
		
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, TelaGenerica.LARGURA, TelaGenerica.ALTURA);
				g.drawImage(imagem.getImage(), 220, 50, null);
				g.setColor(Color.BLACK);
				g.drawRect(110, 110, 400, 300);
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", Font.BOLD, 15));
				g.drawString("Escolha modo de jogo:", 230, 130);
				g.drawImage(iconeSigleplayer.getImage(), 230, 145, null);
				g.drawImage(iconeMultplayer.getImage(), 330, 145, null);
				g.drawString("Escolha a dificuldade:", 230, 230);
				g.drawString("Escolha a opera��o matem�tica:", 195, 285);
			}
		};
		panel.setBounds(0, 0, TelaGenerica.LARGURA, TelaGenerica.ALTURA);
		
		add(singleButton);
		add(multButton);
		add(facilButton);
		add(medioButton);
		add(dificilButton);
		add(voltarButton);
		add(jogarButton);
		add(somaButton);
		add(subButton);
		add(multiplicaButton);
		add(divButton);
		add(panel);
		
	}
	
	public JButton getVoltarButton() {
		return voltarButton;
	}

	public JButton getJogarButton() {
		return jogarButton;
	}

	public JRadioButton getSingleButton() {
		return singleButton;
	}

	public JRadioButton getMultButton() {
		return multButton;
	}

	public JRadioButton getFacilButton() {
		return facilButton;
	}

	public JRadioButton getMedioButton() {
		return medioButton;
	}

	public JRadioButton getDificilButton() {
		return dificilButton;
	}

	public JRadioButton getSomaButton() {
		return somaButton;
	}

	public JRadioButton getSubButton() {
		return subButton;
	}

	public JRadioButton getMultiplicaButton() {
		return multiplicaButton;
	}

	public JRadioButton getDivButton() {
		return divButton;
	}
	
}
