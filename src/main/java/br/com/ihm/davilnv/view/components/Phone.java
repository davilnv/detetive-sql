package br.com.ihm.davilnv.view.components;

import br.com.ihm.davilnv.model.Question;
import br.com.ihm.davilnv.statics.Questions;
import br.com.ihm.davilnv.utils.ErrorHandler;
import br.com.ihm.davilnv.utils.StringOptionsUtils;
import br.com.ihm.davilnv.view.frames.BaseFrame;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Phone {

    private static final int RECT_X_OFFSET = 64;
    private static final int RECT_Y_OFFSET = 30;
    private static final int RECT_WIDTH = 200;
    private static final int RECT_HEIGHT = 105;
    private static final int FONT_TITLE_SIZE = 14;
    private static final int FONT_SIZE = 12;
    private static final int TEXT_X_OFFSET = 99;
    private static final int TEXT_Y_OFFSET = 50;
    private static final int TEXT_MAX_WIDTH = 115;

    private boolean isVisible = false;
    private BufferedImage background;

    private List<Question> questions;
    private int currentQuestionIndex = 0;

    public Phone() {

        String imagePath = "/assets/images/phone.png";
        try {
            background = ImageIO.read(Objects.requireNonNull(Phone.class.getResource(imagePath)));
        } catch (IOException e) {
            ErrorHandler.logAndExit("Erro ao carregar imagem da celular: " + imagePath);
        }

        questions = Questions.getQuestions();

    }

    public void draw(Graphics2D g2d, int x, int y) {
        drawImage(g2d, x, y);
        drawText(g2d, x, y);
    }

    private void drawText(Graphics2D g2d, int x, int y) {

        // Configuração da fonte do titulo e cor
        g2d.setColor(Color.BLACK);
        g2d.setFont(BaseFrame.getFont(FONT_TITLE_SIZE));
        g2d.drawString("Questão " + currentQuestionIndex+1, x + TEXT_X_OFFSET, y  + TEXT_Y_OFFSET);

        g2d.setFont(BaseFrame.getFont(FONT_SIZE));

        FontMetrics fm = g2d.getFontMetrics();
        String text = questions.get(currentQuestionIndex).getText();

        int lineHeight = fm.getHeight();
        int curX = x + TEXT_X_OFFSET;
        int curY = y + TEXT_Y_OFFSET + 30;

        StringOptionsUtils.getTextOnLInes(g2d, fm, text, lineHeight, curX, curY, TEXT_MAX_WIDTH);
    }

    private void drawImage(Graphics2D g2d, int x, int y) {
        g2d.drawImage(background, x + RECT_X_OFFSET, y - RECT_Y_OFFSET, null);
    }

}