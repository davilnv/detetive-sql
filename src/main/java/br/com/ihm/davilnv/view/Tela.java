package br.com.ihm.davilnv.view;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

import br.com.ihm.davilnv.model.Personagem;

public class Tela extends TelaGenerica {
	private Mapa mapa;
	private Inventario inventario;
	private List<Personagem> personagens;
	
	public Tela(List<Personagem> personagens) throws IOException {
		super("");
		this.personagens = personagens;
		mapa = new Mapa();
		inventario = new Inventario(personagens);
		add(mapa);
		add(inventario);
		
	}

	public Mapa getMapa() {
		return mapa;
	}
	
	public Inventario getInventario() {
		return inventario;
	}

	public List<Personagem> getPersonagens() {
		return personagens;
	}

	public void setPersonagens(List<Personagem> personagens) {
		this.personagens = personagens;
	}

}
