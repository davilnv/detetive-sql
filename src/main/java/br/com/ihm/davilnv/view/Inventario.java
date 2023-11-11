package br.com.ihm.davilnv.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.ihm.davilnv.exception.ViewException;
import br.com.ihm.davilnv.model.InventoryInfo;
import br.com.ihm.davilnv.model.Personagem;
import br.com.ihm.davilnv.statics.InventoryInfos;

public class Inventario extends JPanel{
	private List<Personagem> personagens;

	private JButton sairButton;
	private String status = "Somando", status2 = "Somando";
	private boolean multplayer;
	
	public Inventario(List<Personagem> personagens) {
		setSize(125, 512);
		setLocation(514, 0);
		setLayout(null);

		this.personagens = personagens;

		sairButton = new JButton("Sair");
		sairButton.setBounds(535, 480, 80, 20);

		add(sairButton);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		try {
			if (multplayer) {
				multiPlayer(g);
			} else {
				singlePlayer(g);
			}
		} catch (ViewException | IOException e) {
			e.printStackTrace();
		}
	}

	private void drawInventorySection(Graphics g) {
		Color marrom = new Color(57, 43, 30);
		g.setColor(marrom);
		g.fillRect(514, 0, 125, 512);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.drawString("Inventário", 553, 15);
		g.drawLine(514, 17, Tela.DEFAULT_WIDTH, 17);
	}
	
	public void singlePlayer(Graphics g) throws ViewException, IOException {
		drawInventorySection(g);
		for (InventoryInfo info : InventoryInfos.getSingleInventoryInfos(personagens)) {
			info.draw(g);
		}
	}

	public void multiPlayer(Graphics g) throws IOException {

		drawInventorySection(g);
		g.drawLine(514, Tela.DEFAULT_HEIGHT /2, Tela.DEFAULT_WIDTH, Tela.DEFAULT_HEIGHT /2);
		for (InventoryInfo info : InventoryInfos.getMultiInventoryInfos(personagens)) {
			info.draw(g);
		}

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
