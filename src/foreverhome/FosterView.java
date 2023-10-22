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
import javax.swing.SwingConstants;

/**
 *
 * @author yzape
 */
public class FosterView {

    public JPanel fosterPanel;
    private JPanel petsPanel;
    private JPanel popUpPanel;
    private JLabel titleLabel;
    private JLabel introLabel;
    private JLabel popUpLabel;
    private GameTextField petNameField;
    private GameButton dogBtn;
    private GameButton catBtn;
    private GameButton ratBtn;
    private GameButton parrotBtn;
    private GameButton chickenBtn;
    private GameButton continueBtn;
    private GameButton cancelkBtn;

    private final String BG_FILE_PATH = "./resources/images/bg3.png";
    private final String POPUP_FILE_PATH = "./resources/images/popup_box.png";

    private final String DOG_FILE_PATH = "./resources/images/dog1.png";
    private final String DOG_HOVER_FILE_PATH = "./resources/images/dog1_hover.png";

    private final String CAT_FILE_PATH = "./resources/images/cat1.png";
    private final String CAT_HOVER_FILE_PATH = "./resources/images/cat1_hover.png";

    private final String RAT_FILE_PATH = "./resources/images/rat1.png";
    private final String RAT_HOVER_FILE_PATH = "./resources/images/rat1_hover.png";

    private final String PARROT_FILE_PATH = "./resources/images/parrot1.png";
    private final String PARROT_HOVER_FILE_PATH = "./resources/images/parrot1_hover.png";

    private final String CHICKEN_FILE_PATH = "./resources/images/chicken1.png";
    private final String CHICKEN_HOVER_FILE_PATH = "./resources/images/chicken1_hover.png";

    private final String TITLE = "FOSTER MENU";
    private final String INTRO = "Pick an animal you would like to foster!";

    public FosterView() {
        GridBagConstraints gbc = new GridBagConstraints();
        initializePanels();
        initializeLabels();
        initializeButtons();
        initializeTextField();
        addComponents(gbc);
    }

    private void addComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(50, 0, 5, 0);
        gbc.gridwidth = 5;
        gbc.gridy = 0;
        petsPanel.add(titleLabel, gbc);

        gbc.insets = new Insets(15, 0, 5, 0);
        gbc.gridy++;
        petsPanel.add(introLabel, gbc);

        gbc.insets = new Insets(100, 0, 0, 0);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy++;
        petsPanel.add(dogBtn, gbc);

        gbc.gridx++;
        gbc.gridy = 2;
        petsPanel.add(catBtn, gbc);

        gbc.gridx++;
        gbc.gridy = 2;
        petsPanel.add(ratBtn, gbc);

        gbc.gridx++;
        gbc.gridy = 2;
        petsPanel.add(parrotBtn, gbc);

        gbc.gridx++;
        gbc.gridy = 2;
        petsPanel.add(chickenBtn, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 5;
        gbc.gridx = 0;
        gbc.gridy++;
        petsPanel.add(popUpPanel, gbc);

        petsPanel.revalidate();
        petsPanel.repaint();

        gbc.insets = new Insets(90, 20, 5, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy++;
        popUpPanel.add(popUpLabel, gbc);
        
        gbc.insets = new Insets(10, 20, 0, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy++;
        popUpPanel.add(petNameField, gbc);

        
        gbc.insets = new Insets(10, 20, 0, 20);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy++;
        popUpPanel.add(cancelkBtn, gbc);
        
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 5;
        gbc.gridy = 6;
        popUpPanel.add(continueBtn, gbc);
        
        fosterPanel.add(petsPanel, BorderLayout.CENTER);
    }

    private void initializeButtons() {
        dogBtn = new GameButton("dog", DOG_FILE_PATH, DOG_HOVER_FILE_PATH);
        dogBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        dogBtn.setHorizontalTextPosition(SwingConstants.CENTER);

        catBtn = new GameButton("cat", CAT_FILE_PATH, CAT_HOVER_FILE_PATH);
        catBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        catBtn.setHorizontalTextPosition(SwingConstants.CENTER);

        ratBtn = new GameButton("rat", RAT_FILE_PATH, RAT_HOVER_FILE_PATH);
        ratBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        ratBtn.setHorizontalTextPosition(SwingConstants.CENTER);

        parrotBtn = new GameButton("parrot", PARROT_FILE_PATH, PARROT_HOVER_FILE_PATH);
        parrotBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        parrotBtn.setHorizontalTextPosition(SwingConstants.CENTER);

        chickenBtn = new GameButton("chicken", CHICKEN_FILE_PATH, CHICKEN_HOVER_FILE_PATH);
        chickenBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        chickenBtn.setHorizontalTextPosition(SwingConstants.CENTER);

        continueBtn = new GameButton("Continue");
        cancelkBtn = new GameButton("Cancel");
    }

    private void initializeLabels() {
        Font labelFont = GameFont.getPixelFont(30, 1);
        titleLabel = new JLabel(TITLE);
        titleLabel.setFont(labelFont);
        titleLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(13, 0);
        introLabel = new JLabel(INTRO);
        introLabel.setFont(labelFont);
        introLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(13, 0);
        popUpLabel = new JLabel("Would you like to give them a name?");
        popUpLabel.setFont(labelFont);
        popUpLabel.setForeground(Color.white);
    }

    private void initializeTextField(){
        petNameField = new GameTextField(10);
    }
    private void initializePanels() {
        fosterPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(BG_FILE_PATH);
                g.drawImage(bgImage.getImage(), 0, 0, null);
            }
        };

        petsPanel = new JPanel(new GridBagLayout());
        petsPanel.setOpaque(false);

        popUpPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(POPUP_FILE_PATH);
                int yCoordinate = getHeight() - bgImage.getImage().getHeight(this);
                g.drawImage(bgImage.getImage(), 0, yCoordinate, bgImage.getImage().getWidth(this), bgImage.getImage().getHeight(this), this);
            }
        };
        popUpPanel.setOpaque(false);
    }
}
