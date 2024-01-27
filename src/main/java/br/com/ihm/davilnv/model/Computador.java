package br.com.ihm.davilnv.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@NoArgsConstructor
public class Computador {
    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle rectangle;

    public Computador(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width  = width;
        this.height = height;
        this.rectangle = new Rectangle(x, y, width, height);
    }

}