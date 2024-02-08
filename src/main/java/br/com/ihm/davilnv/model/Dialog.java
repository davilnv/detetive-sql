package br.com.ihm.davilnv.model;

import br.com.ihm.davilnv.statics.Character;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dialog {

    private String text;
    private Character character;

    public Dialog(String text, Character character) {
        this.text = text;
        this.character = character;
    }

}
