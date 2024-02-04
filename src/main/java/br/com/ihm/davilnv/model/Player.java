package br.com.ihm.davilnv.model;

import br.com.ihm.davilnv.controller.GameController;
import lombok.Getter;
import lombok.Setter;

import java.awt.Rectangle;
import java.util.List;

@Getter
@Setter
public class Player extends Sprite {
    private int vida;
    private int pontos;
    private Rectangle personagemRectangle;
    public static final int VELOCIDADE = 5;
    public static final int DIFF_COLISAO = -20;
    private boolean cima, baixo, direita, esquerda;
    private int up, down, left, right;
    private Direction lastDirectionPressed = null;
    private NPC nearbyNPC;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Player(int aparencia, int largura, int altura, int colunas, int linhas, int x, int y, String endereco) {
        super(aparencia, largura, altura, colunas, linhas, x, y, endereco);
        vida = 100;
        getRectangle(0, 0);
    }

    @Override
    public void movimento() {
        if (cima && getY() > Player.DIFF_COLISAO) {
            setY(getY() - Player.VELOCIDADE);
            setAparencia(8 + (up * 21));
            up = (up == 10) ? 0 : up + 1;
        }
        if (baixo && getY() < 1016 - Player.DIFF_COLISAO) {
            setY(getY() + Player.VELOCIDADE);
            setAparencia(10 + (down * 21));
            down = (down == 10) ? 0 : down + 1;
        }
        if (esquerda && getX() > Player.DIFF_COLISAO) {
            setX(getX() - Player.VELOCIDADE);
            setAparencia(9 + (left * 21));
            left = (left == 10) ? 0 : left + 1;
        }
        if (direita && getX() < 1856 - Player.DIFF_COLISAO) {
            setX(getX() + Player.VELOCIDADE);
            setAparencia(11 + (right * 21));
            right = (right == 10) ? 0 : right + 1;
        }
    }

    public void getRectangle(int x, int y) {
        personagemRectangle = new Rectangle(getX() + x - DIFF_COLISAO, getY() + y - DIFF_COLISAO, getLarguraPersonagem() + (DIFF_COLISAO * 2), getAlturaPersonagem() + DIFF_COLISAO);
    }

    public boolean isColliding(List<Rectangle> tmp, int x, int y) {
        getRectangle(x, y);
        for (Rectangle rectangle : tmp) {
            if (rectangle.intersects(personagemRectangle)) {
                return false;
            }
        }
        return true;
    }

    public boolean getNearbyComputer(Computer computer) {
        return personagemRectangle.intersects(computer.getRectangle());
    }

    public NPC getNearbyNPC(List<NPC> npcList) {
        for (NPC npc : npcList) {
            if (personagemRectangle.intersects(npc.getNPCRectangle())) {
                this.nearbyNPC = npc;
                return npc;
            }
        }
        this.nearbyNPC = null;
        return null;
    }

    @Override
    public void setX(int posX) {
        if (isColliding(GameController.colisao, posX - getX(), 0))
            super.setX(posX);

    }

    @Override
    public void setY(int posY) {
        if (isColliding(GameController.colisao, 0, posY - getY()))
            super.setY(posY);
    }

}
