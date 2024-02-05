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
    private static final int BREAK_LINE_OFFSET = 25;
    private static final int RECT_WIDTH = 200;
    private static final int RECT_HEIGHT = 105;
    private static final int FONT_TITLE_SIZE = 13;
    private static final int FONT_SIZE = 11;
    private static final int TEXT_X_OFFSET = 99;
    private static final int TEXT_Y_OFFSET = 50;
    private static final int TEXT_MAX_WIDTH = 115;

    private boolean isVisible = false;
    private BufferedImage background;

    private List<Question> questions;
    private Question currentQuestion;
    ;
    private int currentQuestionIndex = 0;

    public Phone() {

        String imagePath = "/assets/images/phone.png";
        try {
            background = ImageIO.read(Objects.requireNonNull(Phone.class.getResource(imagePath)));
        } catch (IOException e) {
            ErrorHandler.logAndExit("Erro ao carregar imagem da celular: " + imagePath);
        }

        questions = Questions.getQuestions();
        currentQuestion = questions.get(currentQuestionIndex);

    }

    public void nextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
        } else {
            currentQuestionIndex = 0;
        }
    }

    public void draw(Graphics2D g2d, int x, int y) {
        drawImage(g2d, x, y);
        drawText(g2d, x, y);
    }

    private void drawText(Graphics2D g2d, int x, int y) {

        // Configuração da fonte do titulo e cor
        g2d.setColor(Color.BLACK);
        g2d.setFont(BaseFrame.getFont(FONT_TITLE_SIZE));
        g2d.drawString("Questão " + (currentQuestionIndex + 1), x + TEXT_X_OFFSET, y + TEXT_Y_OFFSET);

        g2d.setFont(BaseFrame.getFont(FONT_SIZE));

        FontMetrics fm = g2d.getFontMetrics();

        currentQuestion = questions.get(currentQuestionIndex);
        String text = currentQuestion.getText();

        int lineHeight = fm.getHeight();
        int curX = x + TEXT_X_OFFSET;
        int curY = y + TEXT_Y_OFFSET + BREAK_LINE_OFFSET;

        curY = StringOptionsUtils.getTextOnLInes(g2d, fm, text, lineHeight, curX, curY, TEXT_MAX_WIDTH) + BREAK_LINE_OFFSET;
        if (currentQuestion.isAnswered() && currentQuestion.getAnswer() != null) {
            drawTextAnswer(g2d, fm, lineHeight, curX, curY );
        }
    }

    private void drawTextAnswer(Graphics2D g2d, FontMetrics fm, int lineHeight, int curX, int curY) {
        // Configuração da fonte do titulo e cor
        g2d.setColor(Color.RED);

        String text = currentQuestion.getAnswer();

        StringOptionsUtils.getTextOnLInes(g2d, fm, text, lineHeight, curX, curY, TEXT_MAX_WIDTH);
    }

    private void drawImage(Graphics2D g2d, int x, int y) {
        g2d.drawImage(background, x + RECT_X_OFFSET, y - RECT_Y_OFFSET, null);
    }

}