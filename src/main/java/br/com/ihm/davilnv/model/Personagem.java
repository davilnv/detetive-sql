package br.com.ihm.davilnv.model;

import br.com.ihm.davilnv.controller.GameController;
import lombok.Getter;
import lombok.Setter;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

@Getter
@Setter
public class Personagem extends Sprite {
    private int vida;
    private int pontos;
    private Rectangle personagemRectangle;
    public static final int VELOCIDADE = 5;
    public static final int DIFF_COLISAO = -20;

    public Personagem(int aparencia, int largura, int altura, int colunas, int linhas, int x, int y, String endereco) {
        super(aparencia, largura, altura, colunas, linhas, x, y, endereco);
        vida = 100;
        getRectangle(0, 0);
    }

    @Override
    public void animar(String direcao) {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getSprites()[getAparencia()], getX(), getY(), null);
    }

    @Override
    public void mover(String direcao) {
    }

    public void getRectangle(int x, int y) {
        personagemRectangle = new Rectangle(getX() + x - DIFF_COLISAO, getY() + y - DIFF_COLISAO, getLarguraPersonagem() + (DIFF_COLISAO * 2), getAlturaPersonagem() + DIFF_COLISAO);
    }

//    public Rectangle getRectangle() {
//        return new Rectangle(getX() - DIFF_COLISAO, getY() - DIFF_COLISAO, getLarguraPersonagem() + (DIFF_COLISAO * 2), getAlturaPersonagem() + DIFF_COLISAO);
////        return new Rectangle(getX(), getY(), getLarguraPersonagem(), getAlturaPersonagem());
//    }

    public boolean isColliding(List<Rectangle> tmp, int x, int y) {
        getRectangle(x, y);
        for (Rectangle rectangle : tmp) {
            if (rectangle.intersects(personagemRectangle)) {
                return false;
            }
        }
        return true;
    }

    public boolean getNearbyComputer(Computador computador) {
        return personagemRectangle.intersects(computador.getRectangle());
    }

    public NPC getNearbyNPC(List<NPC> npcList) {
        for (NPC npc : npcList) {
            if (personagemRectangle.intersects(npc.getNPCRectangle())) {
                return npc;
            }
        }
        return null;
    }

    public String getStatus(int win, int lose) {
        if (vida == lose) {
            return "Game Over!";
        }
        if (pontos == win) {
            return "Ganhou!!!";
        }
        return "";

    }

    public void reset(int posX, int posY) {
        setLocale(posX, posY);
        vida = 100;
        pontos = 0;
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
