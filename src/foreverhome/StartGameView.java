/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author yzape
 */
public class StartGameView {

    public JPanel startGamePanel;
    private JLabel titleLabel;
    private GameButton startButton;
    private GameButton continueButton;
    private GameButton exitButton;

    private final String BG_FILE_PATH = "./resources/images/backgrounds/bg2.png";
    private final String NORMAL_BTN_FILE_PATH = "./resources/images/buttons/mainButton.png";
    private final String HOVER_BTN_FILE_PATH = "./resources/images/buttons/mainButton_hover.png";
    private final String START_GAME_TITLE = "WELCOME TO FOREVER HOME!";

    public StartGameView() {
        GridBagConstraints gbc = new GridBagConstraints();
        initializePanel();
        initializeLabel();
        initializeButtons();
        addComponents(gbc);
    }

    private void addComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(0, 0, 70, 0);

        gbc.gridy = 0;
        startGamePanel.add(titleLabel, gbc);

        gbc.insets = new Insets(40, 0, 0, 0);

        gbc.gridy = 1;
        startGamePanel.add(startButton, gbc);

        gbc.gridy = 2;
        startGamePanel.add(continueButton, gbc);

        gbc.gridy = 3;
        startGamePanel.add(exitButton, gbc);
    }

    private void initializeButtons() {
        Font buttonFont = GameFont.getPixelFont(20, 0);

        startButton = new GameButton("New Game", NORMAL_BTN_FILE_PATH, HOVER_BTN_FILE_PATH);
        startButton.setFont(buttonFont);

        continueButton = new GameButton("Continue", NORMAL_BTN_FILE_PATH, HOVER_BTN_FILE_PATH);
        continueButton.setFont(buttonFont);

        exitButton = new GameButton("Exit Game", NORMAL_BTN_FILE_PATH, HOVER_BTN_FILE_PATH);
        exitButton.setFont(buttonFont);
    }

    private void initializeLabel() {
        Font labelFont = GameFont.getPixelFont(60, 1);
        titleLabel = new JLabel(START_GAME_TITLE);
        titleLabel.setFont(labelFont);
        titleLabel.setForeground(Color.white);
    }

    private void initializePanel() {
        startGamePanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(BG_FILE_PATH);
                g.drawImage(bgImage.getImage(), 0, 0, null);
            }
        };
    }

//    public void addActionListener(ActionListener listener)
//    {
//        startButton.addActionListener(listener);
//        continueButton.addActionListener(listener);
//        exitButton.addActionListener(listener);
//    }
    
    public GameButton getStartButton() {
        return startButton;
    }

    public GameButton getContinueButton() {
        return continueButton;
    }

    public GameButton getExitButton() {
        return exitButton;
    }
}
