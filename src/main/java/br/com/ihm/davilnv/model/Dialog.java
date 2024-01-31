package br.com.ihm.davilnv.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dialog {

    private String text;
    private String character;

    public Dialog(String text, String character) {
        this.text = text;
        this.character = character;
    }

}
