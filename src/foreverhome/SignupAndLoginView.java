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
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author yzape Name: Yza Pernia Student ID: 21137984
 */
public final class SignupAndLoginView {

    public JPanel signupAndLoginPanel;
    public boolean isLogin = false;
    private JLabel signUpAndLoginLabel;
    private JLabel introLabel;
    private JPanel signUpAndLoginForm;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private GameTextField usernameField;
    private GameTextField passwordField;
    private GameButton continueBtn;
    private GameButton goBackBtn;

    private final String BG_FILE_PATH = "./resources/images/backgrounds/bg3.png";
    private final String TITLE = isLogin ? "WELCOME BACK, PLAYER! PLEASE LOGIN." : "WHY HELLO, NEW PLAYER! PLEASE SIGN UP.";
    public String intro = isLogin ? "Please make sure to enter your correct information." : "Please fill in the form below.";

    public SignupAndLoginView() {
        GridBagConstraints gbc = new GridBagConstraints();
        initializePanels();
        initializeLabels();
        initializeButtons();
        initializeTextField();
        addComponents(gbc);
    }

    private void addComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(10, 20, 10, 50);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        signUpAndLoginForm.add(signUpAndLoginLabel, gbc);

        gbc.gridy = 1;
        signUpAndLoginForm.add(introLabel, gbc);

        gbc.insets = new Insets(30, 20, 0, 50);
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        signUpAndLoginForm.add(usernameLabel, gbc);

        gbc.insets = new Insets(20, 20, 0, 50);
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 3;
        signUpAndLoginForm.add(usernameField, gbc);

        gbc.insets = new Insets(30, 20, 0, 50);
        gbc.gridwidth = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        signUpAndLoginForm.add(passwordLabel, gbc);

        gbc.insets = new Insets(20, 20, 0, 50);
        gbc.gridwidth = 3;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        signUpAndLoginForm.add(passwordField, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(50, 20, 0, 50);
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 6;
        signUpAndLoginForm.add(continueBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        signUpAndLoginForm.add(goBackBtn, gbc);

        signupAndLoginPanel.add(signUpAndLoginForm, BorderLayout.CENTER);

    }

    private void initializeLabels() {
        Font labelFont = GameFont.getPixelFont(30, 1);
        signUpAndLoginLabel = new JLabel(TITLE);
        signUpAndLoginLabel.setFont(labelFont);
        signUpAndLoginLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 0);
        introLabel = new JLabel(intro);
        introLabel.setFont(labelFont);
        introLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 0);
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        usernameLabel.setForeground(Color.white);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(Color.white);

    }

    private void initializeButtons() {
        Font buttonFont = GameFont.getPixelFont(15, 0);
        continueBtn = new GameButton("Continue");
        continueBtn.setFont(buttonFont);

        goBackBtn = new GameButton("Go Back");
        goBackBtn.setFont(buttonFont);

    }

    private void initializeTextField() {
        usernameField = new GameTextField();
        passwordField = new GameTextField();
    }

    private void initializePanels() {
        signupAndLoginPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(BG_FILE_PATH);
                g.drawImage(bgImage.getImage(), 0, 0, null);
            }
        };
        
        signUpAndLoginForm = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.decode("#D17272"));
                g.fillRect(signupAndLoginPanel.getWidth()/8, signupAndLoginPanel.getHeight() / 6, 1000, 600);
            }
        };

        signUpAndLoginForm.setOpaque(false);
        signUpAndLoginForm.setPreferredSize(new Dimension(1000, 600));
    }
    
    public void addActionListener(ActionListener listener)
    {
        continueBtn.addActionListener(listener);
        goBackBtn.addActionListener(listener);
    }
    
    public GameButton getContinueBtn()
    {
        return continueBtn;
    }
    
    public GameButton getBackBtn()
    {
        return goBackBtn;
    }
    
    public String getUsername()
    {
        return this.usernameField.getText();
    }
    
    public String getPassword()
    {
        return this.passwordField.getText();
    }
}
