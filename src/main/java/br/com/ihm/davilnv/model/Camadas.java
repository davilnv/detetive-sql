package br.com.ihm.davilnv.model;

import java.io.FileNotFoundException;

public class Camadas {

	static Camada fase1Fundo;
	static Camada fase1Colisao;
	static Camada fase1Topo;
	static Camada fase2Fundo;
	static Camada fase2Colisao;
	static Camada fase2Topo;

	static {
		try {
			String tiledPath = "src/main/resources/res/tiled.png";
			// Fase 1
			fase1Fundo = new Camada(16, 16, 32, 32, tiledPath, getPathFase(1, "fundo"));
			fase1Colisao = new Camada(16, 16, 32, 32, tiledPath, getPathFase(1, "colisao"));
			fase1Topo = new Camada(16, 16, 32, 32, tiledPath, getPathFase(1, "topo"));
			// Fase 2
			fase2Fundo = new Camada(16, 16, 32, 32, tiledPath, getPathFase(2, "fundo"));
			fase2Colisao = new Camada(16, 16, 32, 32, tiledPath, getPathFase(2, "colisao"));
			fase2Topo = new Camada(16, 16, 32, 32, tiledPath, getPathFase(2, "topo"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private static String getPathFase(int fase, String tipo) {
		return "src/main/resources/res/fase"+fase+tipo+".txt";
	}
	
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
