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
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author yzape
 */
public class StartGameView{
    
    private final JLabel TITLE_LABEL;
    public JPanel startGamePanel;
    private GameButton startButton;
    private GameButton continueButton;
    private GameButton exitButton;
    
    private final String BG_FILE_PATH = "./resources/images/bg2.png";
    private final String NORMAL_BTN_FILE_PATH = "./resources/images/mainButton_normal.png";
    private final String HOVER_BTN_FILE_PATH = "./resources/images/mainButton_hover.png";
    private final String START_GAME_TITLE = "WELCOME TO FOREVER HOME!";
    
    public StartGameView(){
        startGamePanel = new JPanel(new GridBagLayout()){
          @Override
          protected void paintComponent(Graphics g){
              super.paintComponent(g);
              GameImage bgImage = new GameImage(BG_FILE_PATH);
              g.drawImage(bgImage.getImage(), 0, 0, null);
          }
        };
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0, 40,0);
        
        Font titleFont = GameFont.getPixelFont(35, 1);
        TITLE_LABEL = new JLabel(START_GAME_TITLE);
        TITLE_LABEL.setFont(titleFont);
        TITLE_LABEL.setForeground(Color.white);
        gbc.gridy = 0;
        startGamePanel.add(TITLE_LABEL, gbc);

        gbc.insets = new Insets(5,0,0,0);        
        Font btnFont = GameFont.getPixelFont(15, 0);
        
        startButton = new GameButton("New Game", NORMAL_BTN_FILE_PATH, HOVER_BTN_FILE_PATH);
        startButton.setFont(btnFont);
        startButton.setForeground(Color.white);
        gbc.gridy = 1;
        startGamePanel.add(startButton, gbc);
        
        continueButton = new GameButton("Continue", NORMAL_BTN_FILE_PATH, HOVER_BTN_FILE_PATH);
        continueButton.setFont(btnFont);
        continueButton.setForeground(Color.white);
        gbc.gridy = 2;
        startGamePanel.add(continueButton, gbc);
        
        exitButton = new GameButton("Exit Game", NORMAL_BTN_FILE_PATH, HOVER_BTN_FILE_PATH);
        exitButton.setFont(btnFont);
        exitButton.setForeground(Color.white);
        gbc.gridy = 3;
        startGamePanel.add(exitButton, gbc);
    }
}
