package br.com.ihm.davilnv.model;

import java.util.ArrayList;

public class Logica {
	private Personagem personagem, personagem2;
	private Inimigo resultado;
	private int num1, num2;
	private int resultadoOperacao;
	private Camada camadaFundo, camadaColisao, camadaTopo;
	private ArrayList<Posicao> posicoes = new ArrayList<Posicao>();
	private boolean ganhou;
	private String operacao;
	
	public Logica(Personagem personagem, Personagem personagem2, Inimigo resultado, String operacao) {
		this.personagem = personagem;
		this.personagem2 = personagem2;
		this.resultado = resultado;
		this.operacao = operacao;
		calcular();
		
		resultado.setAparencia("" + resultadoOperacao);
		
		gerarAparenciaInimigo();

		camadaFundo = Camadas.fase1()[0];
		camadaColisao = Camadas.fase1()[1];
		camadaTopo = Camadas.fase1()[2];
		
		setarPosicoesResultado();
		
	}
	
	public void calcular() {
		num1 = NumerosAleatorios.gerarNumeroAleatorio();
		num2 = NumerosAleatorios.gerarNumeroAleatorio();
		switch (operacao) {
		case "soma":
			resultadoOperacao = num1 + num2;
			break;
		case "subtracao":
			resultadoOperacao = num1 - num2;
			break;
		case "multiplicacao":
			resultadoOperacao = num1 * num2;
			break;
		case "divisao":
			int resultadoLocal = 0;
			if (num2 == 0) {
				num2 = 1;
			}
			resultadoLocal = num1 * num2;
			num1=resultadoLocal;
			
			resultadoOperacao = num1 / num2;
			break;
		}
	}

	public void iniciarFase() {
		if (personagem.getVida() == 0) {
			personagem.setInimigo(resetarPosicaoInimigos());
			camadaFundo = Camadas.fase1()[0];
			camadaColisao = Camadas.fase1()[1];
			camadaTopo = Camadas.fase1()[2];
		}
		if (personagem.colisaoResultado()) {
			int pos = NumerosAleatorios.gerarNumeroAleatorio(posicoes.size()); 
			resultado.setX(posicoes.get(pos).x);
			resultado.setY(posicoes.get(pos).y);
			personagem.setPontos(personagem.getPontos() + 1);
//			num1 = NumerosAleatorios.gerarNumeroAleatorio();
//			num2 = NumerosAleatorios.gerarNumeroAleatorio();
			calcular();
			gerarAparenciaInimigo();
			
			resultado.setAparencia("" + resultadoOperacao);
			if (personagem.getPontos() == 15) {
				personagem.setX(92);
				personagem.setY(182);
				personagem2.setX(92);
				personagem2.setY(182);
				resultado.setX(448);
				resultado.setY(160);
				for (Inimigo enemy : personagem.getInimigo()) {
					enemy.setX(448);
					enemy.setY(160);
				}
				camadaFundo = Camadas.fase2()[0];
				camadaColisao = Camadas.fase2()[1];
				camadaTopo = Camadas.fase2()[2];
			} else if (personagem.getPontos() == 30) {
				ganhou = true;
			}
		}
		if (personagem2.colisaoResultado()) {
			int pos = NumerosAleatorios.gerarNumeroAleatorio(posicoes.size()); 
			resultado.setX(posicoes.get(pos).x);
			resultado.setY(posicoes.get(pos).y);
			personagem2.setPontos(personagem2.getPontos() + 1);
//			num1 = NumerosAleatorios.gerarNumeroAleatorio();
//			num2 = NumerosAleatorios.gerarNumeroAleatorio();
			calcular();
			gerarAparenciaInimigo();
			
			resultado.setAparencia("" + resultadoOperacao);
			if (personagem2.getPontos() == 15) {
				personagem2.setX(92);
				personagem2.setY(182);
				personagem.setX(92);
				personagem.setY(182);
				resultado.setX(448);
				resultado.setY(160);
				for (Inimigo enemy : personagem2.getInimigo()) {
					enemy.setX(448);
					enemy.setY(160);
				}
				camadaFundo = Camadas.fase2()[0];
				camadaColisao = Camadas.fase2()[1];
				camadaTopo = Camadas.fase2()[2];
			} else if (personagem2.getPontos() == 30) {
				ganhou = true;
			}
			
		}
	}
	
	public void setarPosicoesResultado() {
		posicoes.add(new Posicao(48, 176));
		posicoes.add(new Posicao(50, 448));
		posicoes.add(new Posicao(206, 271));
		posicoes.add(new Posicao(460, 152));
		posicoes.add(new Posicao(465, 446));
		posicoes.add(new Posicao(15, 271));
		posicoes.add(new Posicao(237, 167));
		posicoes.add(new Posicao(471, 267));
	}
	
	public Inimigo[] resetarPosicaoInimigos() {
		Inimigo[] inimigo = new Inimigo[10];
		inimigo[0] = new Inimigo(10, 100, "0");
		inimigo[1] = new Inimigo(20, 200, "1");
		inimigo[2] = new Inimigo(30, 300, "2");
		inimigo[3] = new Inimigo(10, 400, "3");
		inimigo[4] = new Inimigo(65, 100, "4");
		inimigo[5] = new Inimigo(130, 200, "5");
		inimigo[6] = new Inimigo(400, 100, "6");
		inimigo[7] = new Inimigo(480, 90, "7");
		inimigo[8] = new Inimigo(380, 100, "8");
		inimigo[9] = new Inimigo(280, 100, "9");
		return inimigo;
	}
	
	public void gerarAparenciaInimigo() {
		int cont = 9;
		while (cont > -1) {
			int num = NumerosAleatorios.gerarNumeroAleatorio(19);
			if (num != resultadoOperacao) {
				personagem.getInimigo()[cont].setAparencia("" + num);
				cont--;
			}
		}
	}
	
	private class Posicao {
		int x;
		int y;
		
		public Posicao(int x, int y ) {
			this.x = x;
			this.y = y;
		}
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}
	
	public int getResultadoOperacao() {
		return resultadoOperacao;
	}

	public Camada getCamadaFundo() {
		return camadaFundo;
	}

	public Camada getCamadaColisao() {
		return camadaColisao;
	}
	
	public Camada getCamadaTopo() {
		return camadaTopo;
	}
	
	public void setCamadaFundo(Camada camadaFundo) {
		this.camadaFundo = camadaFundo;
	}

	public void setCamadaColisao(Camada camadaColisao) {
		this.camadaColisao = camadaColisao;
	}

	public void setCamadaTopo(Camada camadaTopo) {
		this.camadaTopo = camadaTopo;
	}

	public boolean isGanhou() {
		return ganhou;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
}
