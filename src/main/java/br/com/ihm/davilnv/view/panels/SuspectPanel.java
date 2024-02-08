package br.com.ihm.davilnv.view.panels;

import br.com.ihm.davilnv.model.NPC;
import br.com.ihm.davilnv.utils.ErrorHandler;
import br.com.ihm.davilnv.view.frames.BaseFrame;
import com.formdev.flatlaf.FlatIntelliJLaf;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class SuspectPanel extends BasePanel {
    private List<JLabel> suspectLabels;
    private JButton closeButton;

    public SuspectPanel(String key) {
        super(key);

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
            JLabel label = new JLabel(npc.getNome());
            label.setFont(BaseFrame.DEFAULT_FONT.deriveFont(Font.BOLD, 16));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setOpaque(true);
            label.setBackground(Color.WHITE);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // Set the icon of the JLabel to the image of the NPC
            ImageIcon npcImage = new ImageIcon(npc.getScene().getSubScenes().get(0));
            label.setIcon(npcImage);

            add(label);
        }

        // Cria o botão de fechar e adiciona o botão de fechar ao painel
        closeButton = new JButton("Fechar");
        closeButton.setForeground(Color.RED);
        closeButton.setFont(BaseFrame.getFont(24));
        add(closeButton);
    }
}