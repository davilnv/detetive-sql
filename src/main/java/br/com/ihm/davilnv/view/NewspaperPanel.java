package br.com.ihm.davilnv.view;

import br.com.ihm.davilnv.model.Dialog;
import br.com.ihm.davilnv.model.Scene;
import br.com.ihm.davilnv.statics.Character;
import br.com.ihm.davilnv.statics.SceneInfo;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

@Getter
@Setter
public class NewspaperPanel extends BasePanel {

    private ImageIcon newsBackground;
    private ImageIcon newsBackgroundDiamond;
    private BufferedImage characterSprite;
    private Scene sceneNewscaster;
    private Scene sceneDirector;
    private JTextArea dialogueBox;
    private Dialog[] dialogues;
    private int currentDialogueIndex;
    private boolean isFinished;
    private Timer timer;

    public NewspaperPanel(String key) {
        super(key);

        setLayout(null);

        // Carrega as imagens de fundo
        newsBackground = new ImageIcon(Objects.requireNonNull(NewspaperPanel.class.getResource("/assets/images/background/background-news.jpg")));
        newsBackgroundDiamond = new ImageIcon(Objects.requireNonNull(NewspaperPanel.class.getResource("/assets/images/background/background-news-diamond.jpg")));
        // Carrega a cena da jornalista e corta as subcenas
        sceneNewscaster = new Scene("/assets/images/scene/scene-newscaster.png");
        sceneNewscaster.cutScenes(112, 105);
        // Carrega a cena do diretor e corta as subcenas
        sceneDirector = new Scene("/assets/images/scene/scene-director.png");
        sceneDirector.cutScenes(112, 105);
        // Carrega a primeira subcena da jornalista
        characterSprite = sceneNewscaster.getSubScenes().get(0);

        dialogueBox = new JTextArea();
        dialogueBox.setBounds(20, 955, BaseFrame.DEFAULT_WIDTH - 152, 105);
        dialogueBox.setEditable(false);
        dialogueBox.setLineWrap(true);
        dialogueBox.setWrapStyleWord(true);
        dialogueBox.setFont(BaseFrame.DEFAULT_FONT.deriveFont(Font.BOLD, 20));

        dialogues = SceneInfo.dialoguesNewspaper;
        currentDialogueIndex = 0;
        dialogueBox.setText(dialogues[currentDialogueIndex].getText());

        add(dialogueBox);

        JPanel spaceIndicatorPanel = new JPanel();
        spaceIndicatorPanel.setLayout(new BoxLayout(spaceIndicatorPanel, BoxLayout.X_AXIS));
        spaceIndicatorPanel.setBounds(20, 920, 300, 30);
        spaceIndicatorPanel.setOpaque(false);

        JLabel spaceIndicatorText = new JLabel("Para continuar pressione  ");
        spaceIndicatorText.setForeground(Color.WHITE);
        spaceIndicatorText.setFont(BaseFrame.DEFAULT_FONT.deriveFont(Font.BOLD, 15));
        spaceIndicatorPanel.add(spaceIndicatorText);

        ImageIcon spaceKeyIcon = new ImageIcon(Objects.requireNonNull(NewspaperPanel.class.getResource("/assets/images/icons/space-keyboard.png")));
        JLabel spaceIndicatorImage = new JLabel();
        spaceIndicatorImage.setIcon(spaceKeyIcon);
        spaceIndicatorPanel.add(spaceIndicatorImage);

        add(spaceIndicatorPanel);

        timer = new Timer(1000, e -> spaceIndicatorPanel.setVisible(!spaceIndicatorPanel.isVisible()));
        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(newsBackground.getImage(), 0, 0, null);
        g2d.drawImage(characterSprite, BaseFrame.DEFAULT_WIDTH - 132, 955, null);

        g2d.setStroke(new BasicStroke(4.0f));
        g2d.drawRect(19, 954, dialogueBox.getWidth(), 106);
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.drawRect(dialogueBox.getWidth() , 953, 132, 108);

    }

    public void nextDialogue() {
        currentDialogueIndex++;
        if (currentDialogueIndex >= dialogues.length) {
            currentDialogueIndex = 0;
            isFinished = true;
            return;
        }
        Dialog currentDialogue = dialogues[currentDialogueIndex];
        dialogueBox.setText(currentDialogue.getText());
        if (currentDialogue.getCharacter().equals(Character.DIRECTOR)) {
            characterSprite = sceneDirector.getSubScenes().get(0);
            newsBackground = newsBackgroundDiamond;
        } else if (currentDialogue.getCharacter().equals(Character.NEWSCASTER)) {
            characterSprite = sceneNewscaster.getSubScenes().get(currentDialogueIndex);
        }
        repaint();
    }

}