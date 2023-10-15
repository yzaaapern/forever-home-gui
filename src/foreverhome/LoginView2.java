/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author yzape
 */
public class LoginView2 {
     private JLabel introLabel;
    public JPanel loginPanel;
    private JPanel loginForm;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private GameTextField usernameField;
    private GameTextField passwordField;
    private GameButton continueBtn;
    private GameButton goBackBtn;

    private final String bgFilePath = "./resources/images/bg3.png";
    private final String formFilePath = "./resources/images/login_box.png";
    private final String normalBtnFilePath = "./resources/images/mainButton_normal.png";
    private final String hoverBtnFilePath = "./resources/images/mainButton_hover.png";
    public String intro = "If your username doesn't exist or your password is incorrect, we will let you know!";
    public String username;
    public String password;

    public LoginView2() {
        GridBagConstraints gbc = new GridBagConstraints();
        initializeLoginPanel();
        initializeIntroLabel();
        initializeLoginForm(gbc);
        addComponents(gbc);
    }

private void addComponents(GridBagConstraints gbc) {
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 10, 10, 10);

    // Add introLabel at the top
    gbc.gridx = 0;
    gbc.gridy = 0;
    loginForm.add(introLabel, gbc);

    usernameLabel = new JLabel("Username:");
    gbc.gridx = 1;
    gbc.gridy = 1;
    loginForm.add(usernameLabel, gbc);

    usernameField = new GameTextField(20);
    gbc.gridx = 2;
    gbc.gridy = 1;
    loginForm.add(usernameField, gbc);

    passwordLabel = new JLabel("Password:");
    gbc.gridx = 1;
    gbc.gridy = 2;
    loginForm.add(passwordLabel, gbc);

    passwordField = new GameTextField(20);
    gbc.gridx = 2;
    gbc.gridy = 2;
    loginForm.add(passwordField, gbc);

    continueBtn = new GameButton("Continue", normalBtnFilePath, hoverBtnFilePath);
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    loginForm.add(continueBtn, gbc);

    goBackBtn = new GameButton("Go Back", normalBtnFilePath, hoverBtnFilePath);
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    loginForm.add(goBackBtn, gbc);

    loginPanel.add(loginForm, BorderLayout.CENTER);
}



    private void initializeLoginPanel() {
        loginPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(bgFilePath);
                g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
    }

    private void initializeIntroLabel() {
        Font labelFont = GameFont.getPixelFont(12, 0);
        introLabel = new JLabel(intro);
        introLabel.setFont(labelFont);
        introLabel.setForeground(Color.white);

    }

    private void initializeLoginForm(GridBagConstraints gbc) {
        GameImage bgImage = new GameImage(formFilePath);
        loginForm = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage.getImage(), (loginPanel.getWidth()/4)-20, loginPanel.getHeight()/5, this);
            }
        };
        loginForm.setOpaque(false);
    }

}
