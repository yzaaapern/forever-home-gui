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
 * @author yzape
 */
public class FeedPetView {

    public JPanel foodInventoryPanel;
    private JPanel foodMenuPanel;
    private JLabel titleLabel;
    private JLabel introLabel;

    private JLabel foodForAllTitle;
    private JLabel foodForAllDesc;

    private JLabel rainbowTreatTitle;
    private JLabel rainbowTreatDesc;

    private JLabel kibbleTitle;
    private JLabel kibbleDesc;

    private JLabel cannedFoodTitle;
    private JLabel cannedFoodDesc;

    private JLabel veggieMixTitle;
    private JLabel veggieMixDesc;

    private JLabel seedsTitle;
    private JLabel seedsDesc;

    private JLabel waterTitle;
    private JLabel waterDesc;

    private GameButton feedWithFoodForAll;
    private GameButton feedWithRainbowTreat;
    private GameButton feedWithKibble;
    private GameButton feedWithCannedFood;
    private GameButton feedWithVeggieMix;
    private GameButton feedWithSeeds;
    private GameButton drinkWater;

    private GameButton goBackBtn;

    private final String BG_FILE_PATH = "./resources/images/backgrounds/foodInventory_bg.png";
    private final String BTN_FILE_PATH = "./resources/images/buttons/mainButton.png";
    private final String BTN_HOVER_FILE_PATH = "./resources/images/buttons/mainButton_hover.png";
    
    private final String TITLE = "FEED PET MENU";
    private final String INTRO = "Feed your pet something tasty!";

    public FeedPetView() {
        GridBagConstraints gbc = new GridBagConstraints();
        initializePanels();
        initializeLabels();
        initializeButtons();
        addComponents(gbc);
    }

    private void addComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(30, 20, 0, 20);
        gbc.gridwidth = 3;
        gbc.gridy = 0;
        foodMenuPanel.add(titleLabel, gbc);

        gbc.insets = new Insets(10, 20, 0, 20);
        gbc.gridy++;
        foodMenuPanel.add(introLabel, gbc);

        feedPetComponents(gbc);


        gbc.insets = new Insets(50, 20, 0, 20);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy++;
        foodMenuPanel.add(goBackBtn, gbc);

        foodInventoryPanel.add(foodMenuPanel, BorderLayout.CENTER);
    }

    private void feedPetComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(30, 20, 0, 50);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy++;
        foodMenuPanel.add(foodForAllTitle, gbc);

        gbc.insets = new Insets(10, 20, 10, 50);
        gbc.gridx = 0;
        gbc.gridy++;
        foodMenuPanel.add(foodForAllDesc, gbc);

        gbc.insets = new Insets(0, 20, 0, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 3;
        foodMenuPanel.add(feedWithFoodForAll, gbc);

        gbc.insets = new Insets(20, 20, 0, 50);
        gbc.gridx = 0;
        gbc.gridy++;
        foodMenuPanel.add(kibbleTitle, gbc);

        gbc.insets = new Insets(10, 20, 10, 50);
        gbc.gridx = 0;
        gbc.gridy++;
        foodMenuPanel.add(kibbleDesc, gbc);

        gbc.insets = new Insets(0, 20, 0, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 5;
        foodMenuPanel.add(feedWithKibble, gbc);

        gbc.insets = new Insets(20, 20, 0, 50);
        gbc.gridx = 0;
        gbc.gridy++;
        foodMenuPanel.add(cannedFoodTitle, gbc);

        gbc.insets = new Insets(10, 20, 10, 50);
        gbc.gridx = 0;
        gbc.gridy++;
        foodMenuPanel.add(cannedFoodDesc, gbc);

        gbc.insets = new Insets(0, 20, 0, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 7;
        foodMenuPanel.add(feedWithCannedFood, gbc);

        gbc.insets = new Insets(20, 20, 0, 50);
        gbc.gridx = 0;
        gbc.gridy++;
        foodMenuPanel.add(veggieMixTitle, gbc);

        gbc.insets = new Insets(10, 20, 10, 50);
        gbc.gridx = 0;
        gbc.gridy++;
        foodMenuPanel.add(veggieMixDesc, gbc);

        gbc.insets = new Insets(0, 20, 0, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 9;
        foodMenuPanel.add(feedWithVeggieMix, gbc);

        gbc.insets = new Insets(20, 20, 0, 50);
        gbc.gridx = 0;
        gbc.gridy++;
        foodMenuPanel.add(seedsTitle, gbc);

        gbc.insets = new Insets(10, 20, 10, 50);
        gbc.gridx = 0;
        gbc.gridy++;
        foodMenuPanel.add(seedsDesc, gbc);

        gbc.insets = new Insets(0, 20, 0, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 11;
        foodMenuPanel.add(feedWithSeeds, gbc);

        gbc.insets = new Insets(20, 20, 0, 50);
        gbc.gridx = 0;
        gbc.gridy++;
        foodMenuPanel.add(rainbowTreatTitle, gbc);

        gbc.insets = new Insets(10, 20, 10, 50);
        gbc.gridx = 0;
        gbc.gridy++;
        foodMenuPanel.add(rainbowTreatDesc, gbc);

        gbc.insets = new Insets(0, 20, 0, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 13;
        foodMenuPanel.add(feedWithRainbowTreat, gbc);

        gbc.insets = new Insets(20, 20, 0, 50);
        gbc.gridx = 0;
        gbc.gridy++;
        foodMenuPanel.add(waterTitle, gbc);

        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridx = 0;
        gbc.gridy++;
        foodMenuPanel.add(waterDesc, gbc);

        gbc.insets = new Insets(0, 20, 0, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 15;
        foodMenuPanel.add(drinkWater, gbc);
    }

    private void initializeButtons() {
        Font buttonFont = GameFont.getPixelFont(13, 0);
        feedWithFoodForAll = new GameButton("Feed with Food For All", BTN_FILE_PATH, BTN_HOVER_FILE_PATH);
        feedWithFoodForAll.setFont(buttonFont);

        feedWithRainbowTreat = new GameButton("Give them a Rainbow Treat!", BTN_FILE_PATH, BTN_HOVER_FILE_PATH);
        feedWithRainbowTreat.setFont(buttonFont);

        feedWithKibble = new GameButton("Feed with Kibble", BTN_FILE_PATH, BTN_HOVER_FILE_PATH);
        feedWithKibble.setFont(buttonFont);

        feedWithCannedFood = new GameButton("Feed with Canned Food", BTN_FILE_PATH, BTN_HOVER_FILE_PATH);
        feedWithCannedFood.setFont(buttonFont);

        feedWithVeggieMix = new GameButton("Feed with Veggie Mix", BTN_FILE_PATH, BTN_HOVER_FILE_PATH);
        feedWithVeggieMix.setFont(buttonFont);

        feedWithSeeds = new GameButton("Feed with Seeds", BTN_FILE_PATH, BTN_HOVER_FILE_PATH);
        feedWithSeeds.setFont(buttonFont);

        drinkWater = new GameButton("Give them some Water", BTN_FILE_PATH, BTN_HOVER_FILE_PATH);
        drinkWater.setFont(buttonFont);

        goBackBtn = new GameButton("Go Back");
        goBackBtn.setFont(buttonFont);
    }

    private void initializeLabels() {
        Font labelFont = GameFont.getPixelFont(30, 1);

        titleLabel = new JLabel(TITLE);
        titleLabel.setFont(labelFont);
        titleLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 0);
        introLabel = new JLabel(INTRO);
        introLabel.setFont(labelFont);
        introLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 1);
        foodForAllTitle = new JLabel("Food For All");
        foodForAllTitle.setFont(labelFont);
        foodForAllTitle.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(12, 0);
        foodForAllDesc = new JLabel("A basic but yummy food for any foster pet. Quantity: ");
        foodForAllDesc.setFont(labelFont);
        foodForAllDesc.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 1);
        rainbowTreatTitle = new JLabel("Rainbow Treat");
        rainbowTreatTitle.setFont(labelFont);
        rainbowTreatTitle.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(12, 0);
        rainbowTreatDesc = new JLabel("The tastiest treat of all! Quantity: ");
        rainbowTreatDesc.setFont(labelFont);
        rainbowTreatDesc.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 1);
        kibbleTitle = new JLabel("Kibble");
        kibbleTitle.setFont(labelFont);
        kibbleTitle.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(12, 0);
        kibbleDesc = new JLabel("An essential for every dog and cat foster. Quantity: ");
        kibbleDesc.setFont(labelFont);
        kibbleDesc.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 1);
        cannedFoodTitle = new JLabel("Canned Food");
        cannedFoodTitle.setFont(labelFont);
        cannedFoodTitle.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(12, 0);
        cannedFoodDesc = new JLabel("A yummy food that's easy for dogs and cats to digest. Quantity: ");
        cannedFoodDesc.setFont(labelFont);
        cannedFoodDesc.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 1);
        veggieMixTitle = new JLabel("Veggie Mix");
        veggieMixTitle.setFont(labelFont);
        veggieMixTitle.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(12, 0);
        veggieMixDesc = new JLabel("An essential for every rat, parrot, and chicken foster. Quantity: ");
        veggieMixDesc.setFont(labelFont);
        veggieMixDesc.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 1);
        seedsTitle = new JLabel("Seeds");
        seedsTitle.setFont(labelFont);
        seedsTitle.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(12, 0);
        seedsDesc = new JLabel("Seeds are always so so tasty! Quantity: ");
        seedsDesc.setFont(labelFont);
        seedsDesc.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 1);
        waterTitle = new JLabel("Water");
        waterTitle.setFont(labelFont);
        waterTitle.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(12, 0);
        waterDesc = new JLabel("Can't let your foster pet go thirsty now! Quantity: ");
        waterDesc.setFont(labelFont);
        waterDesc.setForeground(Color.white);

    }

    private void initializePanels() {
        foodInventoryPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(BG_FILE_PATH);
                g.drawImage(bgImage.getImage(), 0, 0, null);
            }
        };

        foodMenuPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Color transparentBG = new Color(209, 114, 114, 220);
                g.setColor(transparentBG);
                g.fillRect(foodInventoryPanel.getWidth() / 8, foodInventoryPanel.getHeight() / 6, 1000, 700);

            }
        };
        foodMenuPanel.setOpaque(false);
        foodMenuPanel.setPreferredSize(new Dimension(1000, 700));
    }

    public void addActionListener(ActionListener listener)
    {

        feedWithFoodForAll.addActionListener(listener);
        feedWithRainbowTreat.addActionListener(listener);
        feedWithKibble.addActionListener(listener);
        feedWithCannedFood.addActionListener(listener);
        feedWithVeggieMix.addActionListener(listener);
        feedWithSeeds.addActionListener(listener);
        drinkWater.addActionListener(listener);

        goBackBtn.addActionListener(listener);
    }

    public GameButton getFeedWithFoodForAll() {
        return feedWithFoodForAll;
    }

    public GameButton getFeedWithRainbowTreat() {
        return feedWithRainbowTreat;
    }

    public GameButton getFeedWithKibble() {
        return feedWithKibble;
    }

    public GameButton getFeedWithCannedFood() {
        return feedWithCannedFood;
    }

    public GameButton getFeedWithVeggieMix() {
        return feedWithVeggieMix;
    }

    public GameButton getFeedWithSeeds() {
        return feedWithSeeds;
    }

    public GameButton getDrinkWater() {
        return drinkWater;
    }

    public GameButton getGoBackBtn() {
        return goBackBtn;
    }
    
}
