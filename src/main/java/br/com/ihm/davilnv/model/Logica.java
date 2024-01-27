package br.com.ihm.davilnv.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Logica {
    private int num1, num2;
    private List<Camada> camadas;
    private List<NPC> npcs;
    private Computador computador;
    private boolean ganhou;

    public Logica() {
        camadas = new ArrayList<>();
        npcs = new ArrayList<>();
        camadas.add(new Camada("floor", 60, 40, 32, 32, "/assets/tiled/tiled.png", "/assets/tiled/floor.txt"));
        camadas.add(new Camada("second-floor", 60, 40, 32, 32, "/assets/tiled/tiled.png", "/assets/tiled/second-floor.txt"));
        camadas.add(new Camada("colision", 60, 40, 32, 32, "/assets/tiled/tiled.png", "/assets/tiled/colision.txt"));
        camadas.add(new Camada("top", 60, 40, 32, 32, "/assets/tiled/tiled.png", "/assets/tiled/top.txt"));
        camadas.add(new Camada("front-top", 60, 40, 32, 32, "/assets/tiled/tiled.png", "/assets/tiled/front-top.txt"));

        npcs.add(new NPC(2, 64, 64, 13, 21, 136, 128, "/assets/images/sprite/it-employee_universal.png", "Sophie Campbell", "TI"));
        npcs.add(new NPC(2, 64, 64, 13, 21, 148, 750, "/assets/images/sprite/sprite-director_universal.png", "Sir Alexander Kensington", "Diretor"));
        npcs.add(new NPC(2, 64, 64, 13, 21, 1325, 755, "/assets/images/sprite/chief-curator_universal.png", "Dra. Eleanor Thornton", "Curadora"));
        npcs.add(new NPC(2, 64, 64, 13, 21, 1045, 925, "/assets/images/sprite/security-guard_universal.png", "William Smith", "Segurança"));
        npcs.add(new NPC(2, 64, 64, 13, 21, 1010, 250, "/assets/images/sprite/caretaker_universal.png", "James Turner", "Zelador"));
        npcs.add(new NPC(2, 64, 64, 13, 21, 755, 545, "/assets/images/sprite/museum-visitor_universal.png", "Lucy Bennett", "Visitante"));
        npcs.add(new NPC(2, 64, 64, 13, 21, 940, 1040, "/assets/images/sprite/newscaster_universal.png", "Isabella Kensington", "Jornalista"));

//        104, 190,
        computador = new Computador(32, 190, 74, 84);
    }

    public void iniciarFase() {
//        if (personagens.get(0).getVida() == 0) {
//            personagens.get(0).setInimigo(resetarPosicaoInimigos());
//            camadaFundo = Camadas.fase1()[0];
//            camadaColisao = Camadas.fase1()[1];
//            camadaTopo = Camadas.fase1()[2];
//        }
//        if (personagens.get(0).colisaoResultado()) {
//            int pos = NumerosAleatorios.gerarNumeroAleatorio(posicoes.size());
//            resultado.setX(posicoes.get(pos).x);
//            resultado.setY(posicoes.get(pos).y);
//            personagens.get(0).setPontos(personagens.get(0).getPontos() + 1);
//
//            gerarAparenciaInimigo();
//
//            resultado.setAparencia("" + resultadoOperacao);
//            if (personagens.get(0).getPontos() == 15) {
//                personagens.get(0).setX(92);
//                personagens.get(0).setY(182);
//                personagens.get(1).setX(92);
//                personagens.get(1).setY(182);
//                resultado.setX(448);
//                resultado.setY(160);
//                for (Inimigo enemy : personagens.get(0).getInimigo()) {
//                    enemy.setX(448);
//                    enemy.setY(160);
//                }
//                camadaFundo = Camadas.fase2()[0];
//                camadaColisao = Camadas.fase2()[1];
//                camadaTopo = Camadas.fase2()[2];
//            } else if (personagens.get(0).getPontos() == 30) {
//                ganhou = true;
//            }
//        }
//        if (personagens.get(1).colisaoResultado()) {
//            int pos = NumerosAleatorios.gerarNumeroAleatorio(posicoes.size());
//            resultado.setX(posicoes.get(pos).x);
//            resultado.setY(posicoes.get(pos).y);
//            personagens.get(1).setPontos(personagens.get(1).getPontos() + 1);
//            gerarAparenciaInimigo();
//
//            resultado.setAparencia("" + resultadoOperacao);
//            if (personagens.get(1).getPontos() == 15) {
//                personagens.get(1).setX(92);
//                personagens.get(1).setY(182);
//                personagens.get(0).setX(92);
//                personagens.get(0).setY(182);
//                resultado.setX(448);
//                resultado.setY(160);
//                for (Inimigo enemy : personagens.get(1).getInimigo()) {
//                    enemy.setX(448);
//                    enemy.setY(160);
//                }
//                camadaFundo = Camadas.fase2()[0];
//                camadaColisao = Camadas.fase2()[1];
//                camadaTopo = Camadas.fase2()[2];
//            } else if (personagens.get(1).getPontos() == 30) {
//                ganhou = true;
//            }
//
//        }
    }

    private class Posicao {
        int x;
        int y;

        public Posicao(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Camada getCamada(String key) {
        for (Camada camada : camadas) {
            if (camada.getKey().equals(key)) {
                return camada;
            }
        }
        return null;
    }
}
