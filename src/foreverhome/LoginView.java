/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author yzape Name: Yza Pernia Student ID: 21137984
 */
public final class LoginView {
    
    public JPanel loginPanel;
    private JLabel loginLabel;
    private JLabel introLabel;
    private JPanel loginForm;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private GameTextField usernameField;
    private GameTextField passwordField;
    private GameButton continueBtn;
    private GameButton goBackBtn;

    private final String BG_FILE_PATH = "./resources/images/bg3.png";
    public String intro = "Please make sure to enter your correct information.";

    public LoginView() {
        GridBagConstraints gbc = new GridBagConstraints();
        initializeLoginPanel();
        initializeLabels();
        initializeLoginForm();
        initializeButtons();
        initializeTextField();
        addComponents(gbc);
    }

    private void addComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(10, 20, 5, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginForm.add(loginLabel, gbc);

        gbc.gridy = 1;
        loginForm.add(introLabel, gbc);

        gbc.insets = new Insets(30, 20, 0, 20);
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        loginForm.add(usernameLabel, gbc);

        gbc.insets = new Insets(10, 20, 0, 20);
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 3;
        loginForm.add(usernameField, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        loginForm.add(passwordLabel, gbc);

        gbc.gridwidth = 3;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginForm.add(passwordField, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(50, 20, 0, 10);
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 6;
        loginForm.add(continueBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        loginForm.add(goBackBtn, gbc);

        loginPanel.add(loginForm, BorderLayout.CENTER);

    }

    private void initializeLabels() {
        Font labelFont = GameFont.getPixelFont(20, 1);
        loginLabel = new JLabel("WELCOME BACK, PLAYER! PLEASE LOGIN.");
        loginLabel.setFont(labelFont);
        loginLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(13, 0);
        introLabel = new JLabel(intro);
        introLabel.setFont(labelFont);
        introLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(13, 0);
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        usernameLabel.setForeground(Color.white);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(Color.white);

    }

    private void initializeButtons() {
        Font buttonFont = GameFont.getPixelFont(12, 0);
        continueBtn = new GameButton("Continue");
        continueBtn.setFont(buttonFont);
        continueBtn.setForeground(Color.white);

        goBackBtn = new GameButton("Go Back");
        goBackBtn.setFont(buttonFont);
        goBackBtn.setForeground(Color.white);

    }

    private void initializeTextField() {
        usernameField = new GameTextField(10);
        passwordField = new GameTextField(10);
    }

    private void initializeLoginForm() {
        loginForm = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.decode("#D17272"));
                g.fillRect(loginPanel.getWidth() / 8, loginPanel.getHeight() / 5, 600, 350);
            }
        };

        loginForm.setOpaque(false);
        loginForm.setPreferredSize(new Dimension(600, 300));
    }

    private void initializeLoginPanel() {
        loginPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(BG_FILE_PATH);
                g.drawImage(bgImage.getImage(), 0, 0, null);
            }
        };
    }
}
