package br.com.ihm.davilnv.app;

import java.io.IOException;

import br.com.ihm.davilnv.controller.ControlePersonagem;
import br.com.ihm.davilnv.controller.ControleTelas;
import br.com.ihm.davilnv.model.Logica;
import br.com.ihm.davilnv.model.Inimigo;
import br.com.ihm.davilnv.model.Inimigos;
import br.com.ihm.davilnv.model.Personagem;
import br.com.ihm.davilnv.view.Ajuda;
import br.com.ihm.davilnv.view.Credito;
import br.com.ihm.davilnv.view.Inicializacao;
import br.com.ihm.davilnv.view.Menu;
import br.com.ihm.davilnv.view.Tela;

public class App {
    public static void main(String[] args) throws IOException {
        Inimigo resultado = new Inimigo(30, 176, "");
        Personagem personagem = null;
        Personagem personagem2 = null;
        try {
            personagem = new Personagem(Inimigos.iniciarInimigos(), resultado, 0, 128, 96, 4, 3,
                    236, 236, "personagem.png");
            personagem2 = new Personagem(personagem.getInimigo(), resultado, 0, 128, 96, 4, 3,
                    236, 280, "personagem2.png");
        } catch (Exception e) {
            System.out.println("Erro ao tentar carregar Sprite");
        }
        Tela tela = new Tela(personagem, personagem2);
        Menu menu = new Menu();
        Ajuda ajuda = new Ajuda();
        Inicializacao inicializacao = new Inicializacao();
        Credito credito = new Credito();

        ControlePersonagem controlePersonagem = new ControlePersonagem(personagem, personagem2);


        ControleTelas controleTelas = new ControleTelas(menu, tela, ajuda, inicializacao, credito,
                personagem, personagem2, controlePersonagem);
    }
}