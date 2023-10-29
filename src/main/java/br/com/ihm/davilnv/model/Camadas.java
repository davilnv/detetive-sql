package br.com.ihm.davilnv.model;

public class Camadas {
	 // Fase 1
	static Camada fase1Fundo = new Camada(16, 16, 32, 32, "tiled.png", "fase1fundo.txt");
	static Camada fase1Colisao = new Camada(16, 16, 32, 32, "tiled.png", "fase1colisao.txt");
	static Camada fase1Topo = new Camada(16, 16, 32, 32, "tiled.png", "fase1topo.txt");
	
	// Fase 2
	static Camada fase2Fundo = new Camada(16, 16, 32, 32, "tiled.png", "fase2fundo.txt");
	static Camada fase2Colisao = new Camada(16, 16, 32, 32, "tiled.png", "fase2colisao.txt");
	static Camada fase2Topo = new Camada(16, 16, 32, 32, "tiled.png", "fase2topo.txt");
	
	public static Camada[] fase1() {
		Camada[] camadas = new Camada[3];
		camadas[0] = fase1Fundo;
		camadas[1] = fase1Colisao;
		camadas[2] = fase1Topo;
		return camadas;
	}
	
	public static Camada[] fase2() {
		Camada[] camadas = new Camada[3];
		camadas[0] = fase2Fundo;
		camadas[1] = fase2Colisao;
		camadas[2] = fase2Topo;
		return camadas;
	}
}
