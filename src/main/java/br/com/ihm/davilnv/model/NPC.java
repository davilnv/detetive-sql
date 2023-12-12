package br.com.ihm.davilnv.model;

import br.com.ihm.davilnv.controller.GameController;

import java.awt.*;
import java.util.Random;

public class NPC extends Personagem {
    private int contadorAnimacao = 0;
    private int contadorQuadros = 0;
    private Random random = new Random();
    private int raioMovimento;
    private String direcaoAtual = "cima";
    private boolean estaMovendo = false;

    public NPC(int aparencia, int largura, int altura, int colunas, int linhas, int x, int y, String endereco, int raioMovimento) {
        super(aparencia, largura, altura, colunas, linhas, x, y, endereco);
        this.raioMovimento = raioMovimento;
    }

    @Override
    public void animar(String direcao) {
        contadorQuadros++;
        if (contadorQuadros % 10 == 0) { // Altera a aparência a cada 10 quadros
            if (contadorAnimacao >= Integer.parseInt(direcao)) {
                contadorAnimacao = 0;
            }
            int aparenciaInicial = getAparenciaInicial(estaMovendo ? direcaoAtual : "parado");
            int aparenciaAni = (aparenciaInicial + (contadorAnimacao * 21)) % getSprites().length;
            System.out.println("Aparencia NPC:  " + aparenciaAni);
            setAparencia(aparenciaAni);
            contadorAnimacao++;
        }
    }

    public void moverAleatoriamente() {
        if (contadorQuadros % 20 == 0) { // Move o NPC a cada 60 quadros
            int dx = (random.nextInt(3) - 1) * 5; // Gera um número aleatório entre -5 e 5
            int dy = (random.nextInt(3) - 1) * 5; // Gera um número aleatório entre -5 e 5

            int novoX = getX() + dx;
            int novoY = getY() + dy;

            // Verifica se a nova posição está dentro do raio de movimento
            if (Math.sqrt(Math.pow(novoX - getX(), 2) + Math.pow(novoY - getY(), 2)) <= raioMovimento) {
                // Verifica se a nova posição causa uma colisão
                if (!colisao(GameController.colisao, dx, dy)) {
                    setX(novoX);
                    setY(novoY);
                    System.out.println("NPC: X - " + getX() + ", Y - " + getY());
                    direcaoAtual = getDirecao(dx, dy);
                    estaMovendo = true;
                }
            } else {
                estaMovendo = false;
            }
        }
    }

    private int getAparenciaInicial(String direcao) {
        switch (direcao) {
            case "cima":
                return 8;
            case "esquerda":
                return 9;
            case "baixo":
                return 10;
            case "direita":
                return 11;
            case "parado":
                // Retorne a aparência inicial para quando o NPC está parado
                return 0;
            default:
                return 8;
        }
    }

    private String getDirecao(int dx, int dy) {
        if (dx < 0) {
            return "esquerda";
        } else if (dx > 0) {
            return "direita";
        } else if (dy < 0) {
            return "cima";
        } else {
            return "baixo";
        }
    }
}