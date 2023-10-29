/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
 * @author yzape Name: Yza Pernia Student ID: 21137984
 */
public class FosterView {

    /*
        INSTANCE AND CONSTANT VARIABLES
     */
    // GUI Components
    public JPanel fosterPanel;
    private CardLayout cardLayout;
    private JPanel petsMenuPanel;
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
    private GameButton cancelBtn;

    // File Paths
    private final String BG_FILE_PATH = "./resources/images/backgrounds/bg3.png";
    private final String POPUP_FILE_PATH = "./resources/images/misc/popup_box.png";

    private final String DOG_FILE_PATH = "./resources/images/animals/dog.png";
    private final String DOG_HOVER_FILE_PATH = "./resources/images/animals/dog_hover.png";

    private final String CAT_FILE_PATH = "./resources/images/animals/cat.png";
    private final String CAT_HOVER_FILE_PATH = "./resources/images/animals/cat_hover.png";

    private final String RAT_FILE_PATH = "./resources/images/animals/rat.png";
    private final String RAT_HOVER_FILE_PATH = "./resources/images/animals/rat_hover.png";

    private final String PARROT_FILE_PATH = "./resources/images/animals/parrot.png";
    private final String PARROT_HOVER_FILE_PATH = "./resources/images/animals/parrot_hover.png";

    private final String CHICKEN_FILE_PATH = "./resources/images/animals/chicken.png";
    private final String CHICKEN_HOVER_FILE_PATH = "./resources/images/animals/chicken_hover.png";

    // Label Texts
    private final String TITLE = "FOSTER MENU";
    private final String INTRO = "Pick an animal you would like to foster!";

    public boolean showPopUpPanel = false;
    private GridBagConstraints gbc = new GridBagConstraints(); // Used for formating GUI components

    /*
        OBJECT CONSTRUCTOR
     */
    public FosterView() {
         
        initializePanels();
        initializeLabels();
        initializeButtons();
        initializeTextField();
        addComponents(gbc);
    }

    /*
        addComponents method
    Parameter: GridBagConstraints gbc
    Returns: None
    Description: Adds all initialized components and formats them according to GridBagConstraints
     */
    private void addComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(100, 0, 5, 0);
        gbc.gridwidth = 5;
        gbc.gridy = 0;
        petsMenuPanel.add(titleLabel, gbc);

        gbc.insets = new Insets(30, 0, 5, 0);
        gbc.gridy++;
        petsMenuPanel.add(introLabel, gbc);

        gbc.insets = new Insets(100, 30, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy++;
        petsMenuPanel.add(dogBtn, gbc);

        gbc.insets = new Insets(100, 10, 10, 10);
        gbc.gridx++;
        gbc.gridy = 2;
        petsMenuPanel.add(catBtn, gbc);

        gbc.gridx++;
        gbc.gridy = 2;
        petsMenuPanel.add(ratBtn, gbc);

        gbc.gridx++;
        gbc.gridy = 2;
        petsMenuPanel.add(parrotBtn, gbc);

        gbc.gridx++;
        gbc.gridy = 2;
        petsMenuPanel.add(chickenBtn, gbc);

        petsMenuPanel.revalidate();
        petsMenuPanel.repaint();

        fosterPanel.add(petsMenuPanel, BorderLayout.CENTER);
    }

    /*
        initializeButton method
    Parameters: None
    Returns: None
    Description: Initialises all of FosterView's GameButtons 
     */
    private void initializeButtons() {
        Font buttonFont = GameFont.getPixelFont(20, 0);

        dogBtn = new GameButton("dog", DOG_FILE_PATH, DOG_HOVER_FILE_PATH);
        dogBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        dogBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        dogBtn.setFont(buttonFont);

        catBtn = new GameButton("cat", CAT_FILE_PATH, CAT_HOVER_FILE_PATH);
        catBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        catBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        catBtn.setFont(buttonFont);

        ratBtn = new GameButton("rat", RAT_FILE_PATH, RAT_HOVER_FILE_PATH);
        ratBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        ratBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        ratBtn.setFont(buttonFont);

        parrotBtn = new GameButton("parrot", PARROT_FILE_PATH, PARROT_HOVER_FILE_PATH);
        parrotBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        parrotBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        parrotBtn.setFont(buttonFont);

        chickenBtn = new GameButton("chicken", CHICKEN_FILE_PATH, CHICKEN_HOVER_FILE_PATH);
        chickenBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        chickenBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        chickenBtn.setFont(buttonFont);

        buttonFont = GameFont.getPixelFont(15, 0);
        continueBtn = new GameButton("Continue");
        continueBtn.setFont(buttonFont);

        cancelBtn = new GameButton("Cancel");
        cancelBtn.setFont(buttonFont);
    }

    /*
        initializeLabels  method
    Parameters: None
    Returns: None
    Description: Initialises all of the FosterView's labels 
     */
    private void initializeLabels() {
        Font labelFont = GameFont.getPixelFont(35, 1);
        titleLabel = new JLabel(TITLE);
        titleLabel.setFont(labelFont);
        titleLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(20, 0);
        introLabel = new JLabel(INTRO);
        introLabel.setFont(labelFont);
        introLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 0);
        popUpLabel = new JLabel();
        popUpLabel.setFont(labelFont);
        popUpLabel.setForeground(Color.white);
    }

    /*
        initializeTextField  method
    Parameters: None
    Returns: None
    Description: Initialises all of the FosterView's GameTextFields 
     */
    private void initializeTextField() {
        petNameField = new GameTextField(15);
    }

    /*
        initializePanels method
    Parameters: None
    Returns: None
    Description: Initialises all of the FosterView's JPanels
     */
    private void initializePanels() {
        fosterPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(BG_FILE_PATH);
                g.drawImage(bgImage.getImage(), 0, 0, null);
            }
        };

        petsMenuPanel = new JPanel(new GridBagLayout());
        petsMenuPanel.setOpaque(false);

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

    /*
        addActionListener method
    Parameters: ActionListener listener
    Returns: None
    Description: Adds an ActionListener to all the buttons of FosterView
     */
    public void addActionListener(ActionListener listener) {
        dogBtn.addActionListener(listener);
        catBtn.addActionListener(listener);
        ratBtn.addActionListener(listener);
        parrotBtn.addActionListener(listener);
        chickenBtn.addActionListener(listener);
        continueBtn.addActionListener(listener);
        cancelBtn.addActionListener(listener);
    }

    public void updateFosterView() {
        if (showPopUpPanel) {
            gbc.insets = new Insets(0, 0, 0, 0);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.SOUTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.gridwidth = 5;
            gbc.gridx = 0;
            gbc.gridy++;
            petsMenuPanel.add(popUpPanel, gbc);

            petsMenuPanel.revalidate();
            petsMenuPanel.repaint();

            gbc.insets = new Insets(220, 50, 10, 20);
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridwidth = 5;
            gbc.weightx = 1.0;
            gbc.weighty = 0;
            gbc.gridx = 0;
            gbc.gridy++;
            popUpPanel.add(popUpLabel, gbc);

            gbc.insets = new Insets(20, 50, 20, 20);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridy++;
            popUpPanel.add(petNameField, gbc);

            gbc.insets = new Insets(10, 30, 0, 20);
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy++;
            popUpPanel.add(cancelBtn, gbc);

            gbc.insets = new Insets(10, 20, 0, 20);
            gbc.anchor = GridBagConstraints.SOUTHEAST;
            gbc.gridx = 1;
            gbc.gridy = 6;
            popUpPanel.add(continueBtn, gbc);
        } else {
            petsMenuPanel.remove(popUpPanel);
            petsMenuPanel.revalidate();
            petsMenuPanel.repaint();
        }
    }

    public void updatePopUpLabel(String popUpText) {
        popUpLabel.setText(popUpText);
    }

    public void updateShowPopUpPanel() {
        if (!showPopUpPanel) {
            showPopUpPanel = true;
        } else {
            showPopUpPanel = false;
        }
        updateFosterView();

    }

    /*
        GETBTN methods
    Parameters: None
    Returns: GameButtons of FosterView
    Description: Returns the GameButtons of FosterView, allowing other classes to access it.
     */
    public GameButton getDogBtn() {
        return dogBtn;
    }

    public GameButton getCatBtn() {
        return catBtn;
    }

    public GameButton getRatBtn() {
        return ratBtn;
    }

    public GameButton getParrotBtn() {
        return parrotBtn;
    }

    public GameButton getChickenBtn() {
        return chickenBtn;
    }

    public GameButton getContinueBtn() {
        return continueBtn;
    }

    public GameButton getCancelBtn() {
        return cancelBtn;
    }

    public String getPetName() {
        return petNameField.getText();
    }
}
