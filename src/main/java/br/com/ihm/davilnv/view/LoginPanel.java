package br.com.ihm.davilnv.view;

import br.com.ihm.davilnv.utils.ErrorHandler;
import com.formdev.flatlaf.FlatIntelliJLaf;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

@Getter
@Setter
public class LoginPanel extends BasePanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton closeButton;

    public LoginPanel(String key) {
        super(key);

        // Carrega o Tema FlatIntelliJLaf
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            ErrorHandler.logAndExit(e);
        }

        setLayout(null);

        Font defaultFont = BaseFrame.DEFAULT_FONT.deriveFont(Font.BOLD, 16);

        // Botão de fechar no canto superior direito
        closeButton = new JButton("X");
        closeButton.setContentAreaFilled(true);
        closeButton.setBackground(Color.RED);
        closeButton.setBorderPainted(true);
        closeButton.setOpaque(true);
        closeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        closeButton.setFont(defaultFont);
        closeButton.setBounds(1870, 10, 40, 40);
        add(closeButton);

        int defaultX = (BaseFrame.DEFAULT_WIDTH - 200) / 2;

        // Imagem no centro da tela
        JLabel imageLabel = new JLabel(new ImageIcon(Objects.requireNonNull(LoginPanel.class.getResource("/assets/images/icons/user-icon-100x100.png"))));
        imageLabel.setBounds((BaseFrame.DEFAULT_WIDTH - 100) / 2, BaseFrame.CENTER_DEFAULT_Y - 100, 100, 100);
        add(imageLabel);

        // Campo de username
        JLabel usernameLabel = new JLabel("Usuário:");
        usernameLabel.setFont(defaultFont);
        usernameLabel.setBounds(defaultX, imageLabel.getY() + imageLabel.getHeight() + 5, 70, 30);
        add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setFont(defaultFont);
        usernameField.setBounds(defaultX, usernameLabel.getY() + usernameLabel.getHeight() + 5, 200, 30);
        add(usernameField);

        // Campo de senha
        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setFont(defaultFont);
        passwordLabel.setBounds(defaultX, usernameField.getY() + usernameField.getHeight() + 5, 70, 30);
        add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setFont(defaultFont);
        passwordField.setBounds(defaultX, passwordLabel.getY() + passwordLabel.getHeight() + 5, 200, 30);
        add(passwordField);

        // Botão de username
        loginButton = new JButton("Entrar");
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(true);
        loginButton.setOpaque(false);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        loginButton.setFont(defaultFont);
        loginButton.setBounds(defaultX, passwordField.getY() + passwordField.getHeight() + 20, 200, 30);
        add(loginButton);
    }

}