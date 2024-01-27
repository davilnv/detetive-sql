package br.com.ihm.davilnv.model;

import br.com.ihm.davilnv.controller.GameController;
import lombok.Getter;
import lombok.Setter;

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
    private boolean cima, baixo, direita, esquerda;
    private int up, down, left, right;

    public Personagem(int aparencia, int largura, int altura, int colunas, int linhas, int x, int y, String endereco) {
        super(aparencia, largura, altura, colunas, linhas, x, y, endereco);
        vida = 100;
        getRectangle(0, 0);
    }

    @Override
    public void movimento() {
        if (esquerda) {
            int xL = getX();
            int yL = getY();
            if (xL > Personagem.DIFF_COLISAO) {
                setX(xL - Personagem.VELOCIDADE);
                setY(yL);
                switch (left) {
                    case 0:
                        setAparencia(9);
                        break;
                    case 1:
                        setAparencia(30);
                        break;
                    case 2:
                        setAparencia(51);
                        break;
                    case 3:
                        setAparencia(72);
                        break;
                    case 4:
                        setAparencia(93);
                        break;
                    case 5:
                        setAparencia(114);
                        break;
                    case 6:
                        setAparencia(135);
                        break;
                    case 7:
                        setAparencia(156);
                        break;
                    case 8:
                        setAparencia(177);
                        break;
                    case 9:
                        setAparencia(198);
                        break;
                    case 10:
                        setAparencia(219);
                        break;
                }
                if (left == 10) {
                    left = 0;
                } else {
                    left++;
                }

            }
        }
        if (cima) {
            int xL = getX();
            int yL = getY();
            if (yL > Personagem.DIFF_COLISAO) {
                setX(xL);
                setY(yL - Personagem.VELOCIDADE);
                switch (up) {
                    case 0:
                        setAparencia(8);
                        break;
                    case 1:
                        setAparencia(29);
                        break;
                    case 2:
                        setAparencia(50);
                        break;
                    case 3:
                        setAparencia(71);
                        break;
                    case 4:
                        setAparencia(92);
                        break;
                    case 5:
                        setAparencia(113);
                        break;
                    case 6:
                        setAparencia(134);
                        break;
                    case 7:
                        setAparencia(155);
                        break;
                    case 8:
                        setAparencia(176);
                        break;
                    case 9:
                        setAparencia(197);
                        break;
                    case 10:
                        setAparencia(218);
                        break;
                }
                if (up == 10) {
                    up = 0;
                } else {
                    up++;
                }
            }

        }
        if (direita) {
            int xL = getX();
            int yL = getY();
            if (xL < 1856 - Personagem.DIFF_COLISAO) {
                setX(xL + Personagem.VELOCIDADE);
                setY(yL);
                switch (right) {
                    case 0:
                        setAparencia(11);
                        break;
                    case 1:
                        setAparencia(32);
                        break;
                    case 2:
                        setAparencia(53);
                        break;
                    case 3:
                        setAparencia(74);
                        break;
                    case 4:
                        setAparencia(95);
                        break;
                    case 5:
                        setAparencia(116);
                        break;
                    case 6:
                        setAparencia(137);
                        break;
                    case 7:
                        setAparencia(158);
                        break;
                    case 8:
                        setAparencia(179);
                        break;
                    case 9:
                        setAparencia(200);
                        break;
                    case 10:
                        setAparencia(221);
                        break;
                }
                if (right == 10) {
                    right = 0;
                } else {
                    right++;
                }
            }
        }
        if (baixo) {
            int xL = getX();
            int yL = getY();
            if (yL < 1016 - Personagem.DIFF_COLISAO) {
                setX(xL);
                setY(yL + Personagem.VELOCIDADE);
                switch (down) {
                    case 0:
                        setAparencia(10);
                        break;
                    case 1:
                        setAparencia(31);
                        break;
                    case 2:
                        setAparencia(52);
                        break;
                    case 3:
                        setAparencia(73);
                        break;
                    case 4:
                        setAparencia(94);
                        break;
                    case 5:
                        setAparencia(115);
                        break;
                    case 6:
                        setAparencia(136);
                        break;
                    case 7:
                        setAparencia(157);
                        break;
                    case 8:
                        setAparencia(178);
                        break;
                    case 9:
                        setAparencia(199);
                        break;
                    case 10:
                        setAparencia(220);
                        break;
                }
                if (down == 10) {
                    down = 0;
                } else {
                    down++;
                }

            }
        }
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
