package br.com.ihm.davilnv.view.panels;

import br.com.ihm.davilnv.model.NPC;
import br.com.ihm.davilnv.utils.ErrorHandler;
import br.com.ihm.davilnv.view.frames.BaseFrame;
import com.formdev.flatlaf.FlatIntelliJLaf;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SuspectPanel extends BasePanel {
//    private List<JLabel> suspectLabels;
    private List<JButton> suspectButtons;
    private JButton closeButton;

    public SuspectPanel(String key) {
        super(key);

        suspectButtons = new ArrayList<>();

        // Carrega o Tema FlatIntelliJLaf
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            ErrorHandler.logAndExit(e);
        }

        // Define o layout com espaçamento
        setLayout(new GridLayout(3, 3, 10, 10));

    }

    public void setSuspectLabels(List<NPC> npcs) {
        for (NPC npc : npcs) {
            JButton button = getButtonLayout(npc.getNome(), 16);//new JButton(npc.getNome());

            // Set the icon of the JButton to the image of the NPC
            ImageIcon npcImage = new ImageIcon(npc.getScene().getSubScenes().get(0));
            button.setIcon(npcImage);

            suspectButtons.add(button);

            add(button);
        }

        // Cria o botão de fechar e adiciona o botão de fechar ao painel
        closeButton = getButtonLayout("Fechar", 24);//new JButton("Fechar");
        closeButton.setForeground(Color.RED);

        add(closeButton);
    }

    private JButton getButtonLayout(String text, int fontSize) {
        JButton button = new JButton(text);
        button.setFont(BaseFrame.getFont(fontSize));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setOpaque(true);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return button;
    }

}