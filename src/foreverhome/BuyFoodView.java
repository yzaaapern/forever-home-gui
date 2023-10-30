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
public class BuyFoodView {

    private FoodInventory foodInventory;
    public boolean canAfford = false;
    
    public JPanel buyFoodPanel;
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

    private GameButton buyFoodForAll;
    private GameButton buyRainbowTreat;
    private GameButton buyKibble;
    private GameButton buyCannedFood;
    private GameButton buyVeggieMix;
    private GameButton buySeeds;
    private GameButton buyWater;

    private GameButton goBackBtn;

    private final String BG_FILE_PATH = "./resources/images/backgrounds/foodInventory_bg.png";
    private final String BTN_FILE_PATH = "./resources/images/buttons/mainButton_small.png";
    private final String BTN_HOVER_FILE_PATH = "./resources/images/buttons/mainButton_hover_small.png";
    
    private final String TITLE = "STORE MENU";
    private final String INTRO = "Buy more foods for your foster pet!";

    public BuyFoodView() {
        foodInventory = new FoodInventory();
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

        buyFoodComponents(gbc);

        gbc.insets = new Insets(50, 20, 0, 20);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy++;
        foodMenuPanel.add(goBackBtn, gbc);

        buyFoodPanel.add(foodMenuPanel, BorderLayout.CENTER);
    }

    private void buyFoodComponents(GridBagConstraints gbc) {
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
        foodMenuPanel.add(buyFoodForAll, gbc);

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
        foodMenuPanel.add(buyKibble, gbc);

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
        foodMenuPanel.add(buyCannedFood, gbc);

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
        foodMenuPanel.add(buyVeggieMix, gbc);

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
        foodMenuPanel.add(buySeeds, gbc);

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
        foodMenuPanel.add(buyRainbowTreat, gbc);

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
        foodMenuPanel.add(buyWater, gbc);
    }

    private void initializeButtons() {
        Font buttonFont = GameFont.getPixelFont(13, 0);
        buyFoodForAll = new GameButton("Buy Food For All", BTN_FILE_PATH, BTN_HOVER_FILE_PATH);
        buyFoodForAll.setFont(buttonFont);

        buyRainbowTreat = new GameButton("Buy Rainbow Treat", BTN_FILE_PATH, BTN_HOVER_FILE_PATH);
        buyRainbowTreat.setFont(buttonFont);

        buyKibble = new GameButton("Buy Kibble", BTN_FILE_PATH, BTN_HOVER_FILE_PATH);
        buyKibble.setFont(buttonFont);

        buyCannedFood = new GameButton("Buy Canned Food", BTN_FILE_PATH, BTN_HOVER_FILE_PATH);
        buyCannedFood.setFont(buttonFont);

        buyVeggieMix = new GameButton("Buy Veggie Mix", BTN_FILE_PATH, BTN_HOVER_FILE_PATH);
        buyVeggieMix.setFont(buttonFont);

        buySeeds = new GameButton("Buy Seeds", BTN_FILE_PATH, BTN_HOVER_FILE_PATH);
        buySeeds.setFont(buttonFont);

        buyWater = new GameButton("Buy Water", BTN_FILE_PATH, BTN_HOVER_FILE_PATH);
        buyWater.setFont(buttonFont);

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
        foodForAllDesc = new JLabel("A basic but yummy food for any foster pet. Hunger points: " + foodInventory.foodForAll.getFoodValue() + ". Cost: " + foodInventory.foodForAll.getFoodCost() + ". Quantity: " + foodInventory.foodForAll.getFoodCount());
        foodForAllDesc.setFont(labelFont);
        foodForAllDesc.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 1);
        rainbowTreatTitle = new JLabel("Rainbow Treat");
        rainbowTreatTitle.setFont(labelFont);
        rainbowTreatTitle.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(12, 0);
        rainbowTreatDesc = new JLabel("The tastiest treat of all! Hunger points: " + foodInventory.rainbowTreat.getFoodValue() + ". Cost: " + foodInventory.rainbowTreat.getFoodCost() + ". Quantity: " + foodInventory.rainbowTreat.getFoodCount());
        rainbowTreatDesc.setFont(labelFont);
        rainbowTreatDesc.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 1);
        kibbleTitle = new JLabel("Kibble");
        kibbleTitle.setFont(labelFont);
        kibbleTitle.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(12, 0);
        kibbleDesc = new JLabel("An essential for every dog and cat foster. Hunger points: " + foodInventory.kibble.getFoodValue() + ". Cost: " + foodInventory.kibble.getFoodCost() + ". Quantity: " + foodInventory.kibble.getFoodCount());
        kibbleDesc.setFont(labelFont);
        kibbleDesc.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 1);
        cannedFoodTitle = new JLabel("Canned Food");
        cannedFoodTitle.setFont(labelFont);
        cannedFoodTitle.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(12, 0);
        cannedFoodDesc = new JLabel("A yummy food that's easy for dogs and cats to digest. Hunger points: " + foodInventory.cannedFood.getFoodValue() + ". Cost: " + foodInventory.cannedFood.getFoodCost() + ". Quantity: " + foodInventory.cannedFood.getFoodCount());
        cannedFoodDesc.setFont(labelFont);
        cannedFoodDesc.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 1);
        veggieMixTitle = new JLabel("Veggie Mix");
        veggieMixTitle.setFont(labelFont);
        veggieMixTitle.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(12, 0);
        veggieMixDesc = new JLabel("An essential for every rat, parrot, and chicken foster. Hunger points: " + foodInventory.veggieMix.getFoodValue() + ". Cost: " + foodInventory.veggieMix.getFoodCost() + ". Quantity: " + foodInventory.veggieMix.getFoodCount());
        veggieMixDesc.setFont(labelFont);
        veggieMixDesc.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 1);
        seedsTitle = new JLabel("Seeds");
        seedsTitle.setFont(labelFont);
        seedsTitle.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(12, 0);
        seedsDesc = new JLabel("Seeds are always so so tasty! Hunger points: " + foodInventory.seeds.getFoodValue() + ". Cost: " + foodInventory.seeds.getFoodCost() + ". Quantity: " + foodInventory.seeds.getFoodCount());
        seedsDesc.setFont(labelFont);
        seedsDesc.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 1);
        waterTitle = new JLabel("Water");
        waterTitle.setFont(labelFont);
        waterTitle.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(12, 0);
        waterDesc = new JLabel("Can't let your foster pet go thirsty now! Hunger points: " + foodInventory.water.getFoodValue() + ". Cost: " + foodInventory.water.getFoodCost() + ". Quantity: " + foodInventory.water.getFoodCount());
        waterDesc.setFont(labelFont);
        waterDesc.setForeground(Color.white);

    }

    private void initializePanels() {
        buyFoodPanel = new JPanel(new BorderLayout()) {
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
                g.fillRect(buyFoodPanel.getWidth() / 12, buyFoodPanel.getHeight() / 9, 1140, 790);

            }
        };
        foodMenuPanel.setOpaque(false);
        foodMenuPanel.setPreferredSize(new Dimension(1200, 800));
    }

    public void addActionListener(ActionListener listener)
    {
        buyFoodForAll.addActionListener(listener);
        buyRainbowTreat.addActionListener(listener);
        buyKibble.addActionListener(listener);
        buyCannedFood.addActionListener(listener);
        buyVeggieMix.addActionListener(listener);
        buySeeds.addActionListener(listener);
        buyWater.addActionListener(listener);
        
        goBackBtn.addActionListener(listener);
    }

    public GameButton getBuyFoodForAll() {
        return buyFoodForAll;
    }

    public GameButton getBuyRainbowTreat() {
        return buyRainbowTreat;
    }

    public GameButton getBuyKibble() {
        return buyKibble;
    }

    public GameButton getBuyCannedFood() {
        return buyCannedFood;
    }

    public GameButton getBuyVeggieMix() {
        return buyVeggieMix;
    }

    public GameButton getBuySeeds() {
        return buySeeds;
    }

    public GameButton getBuyWater() {
        return buyWater;
    }

    public GameButton getGoBackBtn() {
        return goBackBtn;
    }
    
    public void updateFoodCounts(FoodInventory userFoodInventory)
    {
        Food[] foods = userFoodInventory.getFoods();
        this.foodInventory.setFoods(foods);
        
        this.refreshText();
    }
    
    public void updateFoodCount(Food userFood)
    {
        for(Food f : foodInventory.getFoods())
        {
            if(f.getFoodName().equals(userFood.getFoodName()))
            {
                f.setFoodCount(userFood.getFoodCount());
            }
        }
        
        this.refreshText();
    }
        
    public void updateFoodTexts()
    {
        this.foodForAllDesc.setText("A basic but yummy food for any foster pet. Hunger points: " + foodInventory.foodForAll.getFoodValue() + ". Cost: " + foodInventory.foodForAll.getFoodCost() + ". Quantity: " + foodInventory.getFoods()[0].getFoodCount());
        this.kibbleDesc.setText("An essential for every dog and cat foster. Hunger points: " + foodInventory.kibble.getFoodValue() + ". Cost: " + foodInventory.kibble.getFoodCost() + ". Quantity: " + foodInventory.getFoods()[1].getFoodCount());
        this.cannedFoodDesc.setText("A yummy food that's easy for dogs and cats to digest. Hunger points: " + foodInventory.cannedFood.getFoodValue() + ". Cost: " + foodInventory.cannedFood.getFoodCost() + ". Quantity: " + foodInventory.getFoods()[2].getFoodCount());
        this.veggieMixDesc.setText("An essential for every rat, parrot, and chicken foster. Hunger points: " + foodInventory.veggieMix.getFoodValue() + ". Cost: " + foodInventory.veggieMix.getFoodCost() + ". Quantity: " + foodInventory.getFoods()[3].getFoodCount());
        this.seedsDesc.setText("Seeds are always so so tasty! Hunger points: " + foodInventory.seeds.getFoodValue() + ". Cost: " + foodInventory.seeds.getFoodCost() + ". Quantity: " + foodInventory.getFoods()[4].getFoodCount());
        this.rainbowTreatDesc.setText("The tastiest treat of all! Hunger points: " + foodInventory.rainbowTreat.getFoodValue() + ". Cost: " + foodInventory.rainbowTreat.getFoodCost() + ". Quantity: " + foodInventory.getFoods()[5].getFoodCount());
        this.waterDesc.setText("Can't let your foster pet go thirsty now! Hunger points: " + foodInventory.water.getFoodValue() + ". Cost: " + foodInventory.water.getFoodCost() + ". Quantity: " + foodInventory.getFoods()[6].getFoodCount());
    }
    
    public void updateText(Food food) 
    {
        if (canAfford) {
            this.introLabel.setText(INTRO);
        } else {
            this.introLabel.setText("You cannot afford " + food.getFoodName() + "! Insufficient funds. ");
        }
        this.foodMenuPanel.revalidate();
        this.foodMenuPanel.repaint();
        
    }
    
    public void refreshText()
    {
        canAfford = true;
        this.introLabel.setText(INTRO);
        this.updateFoodTexts();
        this.foodMenuPanel.revalidate();
        this.foodMenuPanel.repaint();
    }
    
}
