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
import javax.swing.SwingConstants;

/**
 *
 * @author yzape
 * Name: Yza Pernia 
 * Student ID: 21137984
 */
public class AdoptionView {

    /*
        INSTANCE AND CONSTANT VARIABLES
     */
    // GUI Components
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

    private AnimalSprite animalSprite;

    private final String BG_FILE_PATH = "./resources/images/backgrounds/bg2.png";
    private final String POPUP_FILE_PATH = "./resources/images/misc/popup_box.png";
    private final String ADOPTION_NOTIF_FILE_PATH = "./resources/images/misc/adoption_notif.png";
    // Label Texts
    private final String TITLE = "THANK YOU FOR TAKING CARE OF";
    private final String MESSAGE = "They have now found their forever home. You get a 100 dabloons as a reward!";
    private final String POPUP1 = "Thanks to your constant love and care,  was able to heal and open their heart to love and be loved again.";
    private final String POPUP2 = "With every pet you care for, we are able to save one life at a time. Would you like to play again and save another?";

    private GridBagConstraints gbc = new GridBagConstraints();

    public AdoptionView() {
        initializePanels();
        initializeLabels();
        initializeButtons();
        addComponents();
    }

    private void addComponents() {
        gbc.insets = new Insets(20, 300, 0, 20);
        gbc.gridwidth = 2;
        gbc.gridy = 0;
        adoptedPetPanel.add(titleLabel, gbc);

        gbc.insets = new Insets(10, 20, 0, 20);
        gbc.gridy++;
        adoptedPetPanel.add(fosterPetLabel, gbc);

        gbc.insets = new Insets(20,0,0,0);
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridy = 2;
        adoptedPetPanel.add(messageLabel, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 4;
        adoptedPetPanel.add(popupPanel, gbc);

        gbc.insets = new Insets(240, 20, 5, 20);
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 5;
        popupPanel.add(popup1Label, gbc);

        gbc.insets = new Insets(10, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 6;
        popupPanel.add(popup2Label, gbc);

        gbc.insets = new Insets(10, 20, 0, 20);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 7;
        popupPanel.add(noBtn, gbc);

        gbc.gridx++;
        gbc.gridy = 7;
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

    /*
        initializeLabels  method
    Parameters: None
    Returns: None
    Description: Initialises all of the AdoptionView's labels 
     */
    private void initializeLabels() {
        Font labelFont = GameFont.getPixelFont(35, 1);
        titleLabel = new JLabel(TITLE);
        titleLabel.setFont(labelFont);
        titleLabel.setVerticalTextPosition(SwingConstants.EAST);
        titleLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        GameImage messageImage = new GameImage(ADOPTION_NOTIF_FILE_PATH);
        titleLabel.setIcon(messageImage.getImageIcon());
        titleLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(40, 1);
        fosterPetLabel = new JLabel();
        fosterPetLabel.setFont(labelFont);
        fosterPetLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(20, 0);
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

    /*
        initializePanels method
    Parameters: None
    Returns: None
    Description: Initialises all of the AdoptionView's JPanels
     */
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
        popupPanel.setPreferredSize(new Dimension(1400, 200));
    }

    /*
        addActionListener method
    Parameters: ActionListener listener
    Returns: None
    Description: Adds an ActionListener to all the buttons of AdoptionView
     */
    public void addActionListener(ActionListener listener) {
        this.noBtn.addActionListener(listener);
        this.yesBtn.addActionListener(listener);
    }

    public void updateAdoptionView(String petFosterName, Animal petFoster) {
        fosterPetLabel.setText(petFosterName);
        this.animalSprite = new AnimalSprite(petFoster);
        
        gbc.insets = new Insets(60, 550, 0, 10);
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.weightx = 2.0;
        gbc.weighty = 4.5;
        gbc.gridx = 0;
        gbc.gridy = 3;
        adoptedPetPanel.add(animalSprite, gbc);
    }

    public GameButton getYesBtn() {
        return yesBtn;
    }

    /*
        geNoBtn method
    Parameters: None
    Returns: GameButton noBtn
    Description: Returns the private noBtn
     */
    public GameButton getNoBtn() {
        return noBtn;
    }
}
