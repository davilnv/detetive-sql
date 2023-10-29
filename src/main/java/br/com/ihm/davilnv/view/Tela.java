package br.com.ihm.davilnv.view;

import java.io.IOException;

import javax.swing.JFrame;

import br.com.ihm.davilnv.model.Personagem;

public class Tela extends TelaGenerica {
	private Mapa mapa;
	private Inventario inventario;
	private Personagem personagem, personagem2;
	
	public Tela(Personagem personagem, Personagem personagem2) throws IOException {		
		this.personagem = personagem;
		mapa = new Mapa();
		inventario = new Inventario(personagem, personagem2);
		add(mapa);
		add(inventario);
		
	}

	public Mapa getMapa() {
		return mapa;
	}
	
	public Inventario getInventario() {
		return inventario;
	}

	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}
	
}
