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
public class AdoptionView {

    public JPanel adoptionPanel;
    private JPanel adoptedPetPanel;
    private JPanel popupPanel;
    private JLabel titleLabel;
    private JLabel fosterPetLabel;
    private JLabel messageLabel;
    private JLabel popup1Label;
    private JLabel popup2Label;

    private GameButton yesBtn;
    private GameButton noBtn;

    private final String BG_FILE_PATH = "./resources/images/backgrounds/bg2.png";
    private final String POPUP_FILE_PATH = "./resources/images/misc/popup_box.png";

    private final String TITLE = "THANK YOU FOR TAKING CARE OF";
    private final String MESSAGE = "THEY HAVE NOW FOUND THEIR FOREVER HOME.";
    private final String POPUP1 = "Thanks to your constant love and care,  was able to heal and open their heart to love and be loved again.";
    private final String POPUP2 = "With every pet you care for, we are able to save one life at a time. Would you like to play again and save another?";

    public AdoptionView() {
        GridBagConstraints gbc = new GridBagConstraints();
        
        initializePanels();
        initializeLabels();
        initializeButtons();
        addComponents(gbc);
    }

    private void addComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(30, 20, 0, 20);
        gbc.gridwidth = 2;
        gbc.gridy = 0;
        adoptedPetPanel.add(titleLabel, gbc);
        
        gbc.gridy++;
        adoptedPetPanel.add(fosterPetLabel, gbc);
        
        gbc.gridy++;
        adoptedPetPanel.add(messageLabel, gbc);
        
        gbc.insets = new Insets(0,0,0,0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy++;
        adoptedPetPanel.add(popupPanel, gbc);
        
        gbc.insets = new Insets(700, 20, 10, 20);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy++;
        popupPanel.add(popup1Label, gbc);
        
        gbc.insets = new Insets(10, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy++;
        popupPanel.add(popup2Label, gbc);
        
        gbc.insets = new Insets(10, 20, 0, 20);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy++;
        popupPanel.add(noBtn, gbc);
        
        gbc.gridx++;
        gbc.gridy = 6;
        popupPanel.add(yesBtn, gbc);
        
        adoptionPanel.add(adoptedPetPanel, BorderLayout.CENTER);
    }

    private void initializeButtons() {
        Font buttonFont = GameFont.getPixelFont(15, 0);

        yesBtn = new GameButton("Yes");
        yesBtn.setFont(buttonFont);

        noBtn = new GameButton("No");
        noBtn.setFont(buttonFont);
    }

    private void initializeLabels() {
        Font labelFont = GameFont.getPixelFont(30, 1);
        titleLabel = new JLabel(TITLE);
        titleLabel.setFont(labelFont);
        titleLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(40, 1);
        fosterPetLabel = new JLabel();
        fosterPetLabel.setFont(labelFont);
        fosterPetLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(20, 1);
        messageLabel = new JLabel(MESSAGE);
        messageLabel.setFont(labelFont);
        messageLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 0);
        popup1Label = new JLabel(POPUP1);
        popup1Label.setFont(labelFont);
        popup1Label.setForeground(Color.white);
        
        popup2Label = new JLabel(POPUP2);
        popup2Label.setFont(labelFont);
        popup2Label.setForeground(Color.white);
    }

    private void initializePanels() {
        adoptionPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(BG_FILE_PATH);
                g.drawImage(bgImage.getImage(), 0, 0, null);
            }
        };

        adoptedPetPanel = new JPanel(new GridBagLayout());
        adoptedPetPanel.setOpaque(false);

        popupPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(POPUP_FILE_PATH);
                int yCoordinate = getHeight() - bgImage.getImage().getHeight(this);
                g.drawImage(bgImage.getImage(), 0, yCoordinate, bgImage.getImage().getWidth(this), bgImage.getImage().getHeight(this), this);
            }
        };
        popupPanel.setOpaque(false);
    }
}
