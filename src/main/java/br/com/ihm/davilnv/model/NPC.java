package br.com.ihm.davilnv.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class NPC extends Personagem {

    private String nome;
    private String profissao;

    public NPC(int aparencia, int largura, int altura, int colunas, int linhas, int x, int y, String endereco, String nome, String profissao) {
        super(aparencia, largura, altura, colunas, linhas, x, y, endereco);
        this.nome = nome;
        this.profissao = profissao;
    }

    public Rectangle getNPCRectangle() {
        return new Rectangle(
                getPersonagemRectangle().x - 10,
                getPersonagemRectangle().y - 10,
                getLarguraPersonagem()-15,
                getAlturaPersonagem()
        );
    }
}