/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author yzape
 */
public class PausedPetFosterView {

    public JPanel petFosterPanel;
    private JPanel pauseGamePanel;
    private JLabel pauseTitleLabel;
    private JLabel pauseMessageLabel;
    
    private GameButton goBackBtn;
    private GameButton quitBtn;

    public boolean isPaused = false;
    private final String BG_FILE_PATH = "./resources/images/backgrounds/petFoster_bg.png";

    private final String PAUSE_TITLE = "YOU PAUSED THE GAME!";
    private final String PAUSE_MESSAGE = "The game is currently paused. Do you want to resume playing?";

    public PausedPetFosterView() {
        GridBagConstraints gbc = new GridBagConstraints();
        initializePanels();
        initializeLabels();
        initializeButtons();
        addComponents(gbc);
    }

    private void addComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(0, 0, 10, 60);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.gridy = 0;
        pauseGamePanel.add(pauseTitleLabel, gbc);

        gbc.insets = new Insets(10, 0, 20, 60);
        gbc.gridy++;
        pauseGamePanel.add(pauseMessageLabel, gbc);

        gbc.insets = new Insets(100, 0, 0, 60);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy++;
        pauseGamePanel.add(goBackBtn, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx++;
        gbc.gridy = 2;
        pauseGamePanel.add(quitBtn, gbc);

        gbc.insets = new Insets(0, 100, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        petFosterPanel.add(pauseGamePanel, gbc);
    }

    private void initializeButtons() {
        Font buttonFont = GameFont.getPixelFont(15, 1);

        buttonFont = GameFont.getPixelFont(15, 0);
        goBackBtn = new GameButton("Go Back");
        goBackBtn.setFont(buttonFont);

        quitBtn = new GameButton("Quit");
        quitBtn.setFont(buttonFont);
    }

    private void initializeLabels() {
        Font labelFont = GameFont.getPixelFont(20, 1);
        labelFont = GameFont.getPixelFont(30, 1);
        pauseTitleLabel = new JLabel(PAUSE_TITLE);
        pauseTitleLabel.setFont(labelFont);
        pauseTitleLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 0);
        pauseMessageLabel = new JLabel(PAUSE_MESSAGE);
        pauseMessageLabel.setFont(labelFont);
        pauseMessageLabel.setForeground(Color.white);
    }

    private void initializePanels() {
        petFosterPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(BG_FILE_PATH);
                g.drawImage(bgImage.getImage(), 0, 0, null);
            }
        };

        pauseGamePanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Color transparentBG = new Color(209, 114, 114, 220);
                g.setColor(transparentBG);
                g.fillRect(petFosterPanel.getWidth() / 7, petFosterPanel.getHeight() / 5, 800, 550);
            }
        };
        pauseGamePanel.setOpaque(false);
        pauseGamePanel.setPreferredSize(new Dimension(800, 400));
    }

    public void addActionListener(ActionListener listener) {
        goBackBtn.addActionListener(listener);
        quitBtn.addActionListener(listener);
    }

    public GameButton getGoBackBtn() {
        return goBackBtn;
    }

    public GameButton getQuitBtn() {
        return quitBtn;
    }
}
