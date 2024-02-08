package br.com.ihm.davilnv.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
    private String text;
    private String answer;
    private boolean answered;

    public Question(String text) {
        this.text = text;
    }

}
