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
import javax.swing.SwingConstants;

/**
 *
 * @author yzape
 */
public class NotPausedPetFosterView {

    public JPanel petFosterPanel;
    private JPanel statBarsPanel;
    private JPanel buttonsPanel;
    private JPanel petSpritePanel;
    private JPanel popupPanel;
    private JLabel dabloonsLabel;
    private JLabel popupLabel;

    private GameProgressBar levelBar;
    private GameProgressBar xpBar;
    private GameProgressBar hungerBar;
    private GameProgressBar hygieneBar;
    private GameProgressBar happinessBar;

    private int maxXPValue = Level.DEFAULT_LEVELXP_CAP; // Set the maximum value for XP
    private int maxHappinessValue = Level.DEFAULT_LEVELXP_CAP; // Set the maximum value for happiness
    private int maxHungerValue = Level.DEFAULT_LEVELXP_CAP; // Set the maximum value for hunger
    private int maxHygieneValue = Level.DEFAULT_LEVELXP_CAP; // Set the maximum value for hygiene
    
    private GameButton backpackBtn;
    private GameButton feedBtn;
    private GameButton interactBtn;
    private GameButton batheBtn;
    private GameButton pauseBtn;
    private GameButton yesBtn;
    private GameButton noBtn;

    private final String POPUP = "Your foster pet is now ready for adoption! Would you like to help them find their forever home?";
    private final String PAUSE_FILE_PATH = "./resources/images/buttons/pause_small.png";
    private final String PAUSE_HOVER_FILE_PATH = "./resources/images/buttons/pause_hover_small.png";

    private final String BG_FILE_PATH = "./resources/images/backgrounds/petFoster_bg.png";
    private final String POPUP_FILE_PATH = "./resources/images/misc/popup_box.png";

    private final String DABLOONS_FILE_PATH = "./resources/images/misc/dabloons.png";

    private final String HAPPINESS_BAR_FILE_PATH = "./resources/images/statbars/happiness_bar.png";
    private final String HAPPINESS_BAR_COLOR = "#FFEAB8";

    private final String HUNGER_BAR_FILE_PATH = "./resources/images/statbars/hunger_bar.png";
    private final String HUNGER_BAR_COLOR = "#FA9275";

    private final String HYGIENE_BAR_FILE_PATH = "./resources/images/statbars/hygiene_bar.png";
    private final String HYGIENE_BAR_COLOR = "#B3EBFF";

    private final String XP_BAR_FILE_PATH = "./resources/images/statbars/xp_bar.png";
    private final String XP_BAR_COLOR = "#8AD6B2";

    private final String LEVEL_BAR_FILE_PATH = "./resources/images/statbars/level_bar.png";
    private final String LEVEL_BAR_COLOR = "#ADC1EC";

    private final String LEVEL_NOTIF_FILE_PATH = "./resources/images/misc/levelup_notif.png";

    private final String BACKPACK_FILE_PATH = "./resources/images/buttons/backpack.png";
    private final String BACKPACK_HOVER_FILE_PATH = "./resources/images/buttons/backpack_hover.png";

    private final String FEED_FILE_PATH = "./resources/images/buttons/feed_icon.png";
    private final String FEED_HOVER_FILE_PATH = "./resources/images/buttons/feed_icon_hover.png";

    private final String BATHE_FILE_PATH = "./resources/images/buttons/bathe_icon.png";
    private final String BATHE_HOVER_FILE_PATH = "./resources/images/buttons/bathe_icon_hover.png";

    private final String INTERACT_FILE_PATH = "./resources/images/buttons/interact_icon.png";
    private final String INTERACT_HOVER_FILE_PATH = "./resources/images/buttons/interact_icon_hover.png";

    public NotPausedPetFosterView() {
        GridBagConstraints gbc = new GridBagConstraints();
        initializePanels();
        initializeLabels();
        initializeProgressBars();
        initializeButtons();
        addComponents(gbc);
    }

    private void addComponents(GridBagConstraints gbc) {
        addStatBarsComponents(gbc);

        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        petFosterPanel.add(statBarsPanel, gbc);

        addButtonsComponents(gbc);

        gbc.insets = new Insets(10, 25, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy++;
        petFosterPanel.add(buttonsPanel, gbc);

        gbc.insets = new Insets(0, 700, 450, 0);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx++;
        gbc.gridy = 0;
        petFosterPanel.add(pauseBtn, gbc);
    }

    private void addStatBarsComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        statBarsPanel.add(levelBar, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        statBarsPanel.add(xpBar, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        statBarsPanel.add(hungerBar, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        statBarsPanel.add(hygieneBar, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        statBarsPanel.add(happinessBar, gbc);

        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy++;
        statBarsPanel.add(dabloonsLabel, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy++;
        statBarsPanel.add(backpackBtn, gbc);
    }

    private void addButtonsComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(0, 0, 0, 40);
        gbc.gridx = 0;
        buttonsPanel.add(batheBtn, gbc);

        gbc.gridx++;
        buttonsPanel.add(feedBtn, gbc);

        gbc.gridx++;
        buttonsPanel.add(interactBtn, gbc);
    }

    private void initializeButtons() {
        Font buttonFont = GameFont.getPixelFont(15, 1);

        feedBtn = new GameButton("Feed", FEED_FILE_PATH, FEED_HOVER_FILE_PATH);
        feedBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        feedBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        feedBtn.setFont(buttonFont);

        batheBtn = new GameButton("Bathe", BATHE_FILE_PATH, BATHE_HOVER_FILE_PATH);
        batheBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        batheBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        batheBtn.setFont(buttonFont);

        interactBtn = new GameButton("Interact", INTERACT_FILE_PATH, INTERACT_HOVER_FILE_PATH);
        interactBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        interactBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        interactBtn.setFont(buttonFont);

        backpackBtn = new GameButton(BACKPACK_FILE_PATH, BACKPACK_HOVER_FILE_PATH);

        buttonFont = GameFont.getPixelFont(15, 0);
        yesBtn = new GameButton("Yes");
        yesBtn.setFont(buttonFont);

        noBtn = new GameButton("No");
        noBtn.setFont(buttonFont);

        buttonFont = GameFont.getPixelFont(20, 1);
        pauseBtn = new GameButton(PAUSE_FILE_PATH, PAUSE_HOVER_FILE_PATH);
        pauseBtn.setFont(buttonFont);
    }

    private void initializeProgressBars() {
        levelBar = new GameProgressBar(LEVEL_BAR_FILE_PATH, LEVEL_BAR_COLOR);
        levelBar.setMaxValue(Level.MAX_LEVEL);
        xpBar = new GameProgressBar(XP_BAR_FILE_PATH, XP_BAR_COLOR);
        xpBar.setMaxValue(this.maxXPValue);
        hungerBar = new GameProgressBar(HUNGER_BAR_FILE_PATH, HUNGER_BAR_COLOR);
        hungerBar.setMaxValue(this.maxHungerValue);
        hygieneBar = new GameProgressBar(HYGIENE_BAR_FILE_PATH, HYGIENE_BAR_COLOR);
        hygieneBar.setMaxValue(this.maxHygieneValue);
        happinessBar = new GameProgressBar(HAPPINESS_BAR_FILE_PATH, HAPPINESS_BAR_COLOR);
        happinessBar.setMaxValue(this.maxHappinessValue);
    }

    private void initializeLabels() {
        Font labelFont = GameFont.getPixelFont(20, 1);
        GameImage labelImage = new GameImage(DABLOONS_FILE_PATH);
        dabloonsLabel = new JLabel("test", labelImage.getImageIcon(), JLabel.LEFT);
        dabloonsLabel.setFont(labelFont);
        dabloonsLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(15, 0);
        popupLabel = new JLabel(POPUP);
        popupLabel.setFont(labelFont);
        popupLabel.setForeground(Color.white);
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

        buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setOpaque(false);
        buttonsPanel.setPreferredSize(new Dimension(400, 115));

        statBarsPanel = new JPanel(new GridBagLayout());
        statBarsPanel.setOpaque(false);
        statBarsPanel.setPreferredSize(new Dimension(490, 350));

        petSpritePanel = new JPanel(new GridBagLayout());
        petSpritePanel.setOpaque(false);
        petSpritePanel.setPreferredSize(new Dimension(280, 280));

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

    public void addActionListener(ActionListener listener) {
        backpackBtn.addActionListener(listener);
        feedBtn.addActionListener(listener);
        interactBtn.addActionListener(listener);
        batheBtn.addActionListener(listener);
        pauseBtn.addActionListener(listener);
        yesBtn.addActionListener(listener);
        noBtn.addActionListener(listener);
    }

    public GameButton getBackpackBtn() {
        return backpackBtn;
    }

    public GameButton getFeedBtn() {
        return feedBtn;
    }

    public GameButton getInteractBtn() {
        return interactBtn;
    }

    public GameButton getBatheBtn() {
        return batheBtn;
    }

    public GameButton getPauseBtn() {
        return pauseBtn;
    }

    public GameButton getYesBtn() {
        return yesBtn;
    }

    public GameButton getNoBtn() {
        return noBtn;
    }
    
    public void updateBarValue(int level, int xp, int happiness, int hunger, int hygiene, int dabloons, int newBarCap)
    {
        this.setLevelBarValue(level);
        this.setXPBarValue(xp);
        xpBar.setMaxValue(newBarCap);
        this.setHappinessBarValue(happiness);
        happinessBar.setMaxValue(newBarCap);
        this.setHungerBarValue(hunger);
        hungerBar.setMaxValue(newBarCap);
        this.setHygieneBarValue(hygiene);
        hygieneBar.setMaxValue(newBarCap);
        this.setDabloonsLabel(dabloons);
        
    }
            
    
    public void setLevelBarValue(int value) {
        levelBar.setValue(value);
        this.levelBar.repaint();
    }

    public void setXPBarValue(int value) {
        xpBar.setValue(value);
        this.xpBar.repaint();
    }

    public void setHappinessBarValue(int value) {
        happinessBar.setValue(value);
        this.happinessBar.repaint();
    }
    
    public void setHungerBarValue(int value) {
        hungerBar.setValue(value);
        this.hungerBar.repaint();
    }

    public void setHygieneBarValue(int value) {
        hygieneBar.setValue(value);
        this.hygieneBar.repaint();
    }
    
    public void setDabloonsLabel(int value) {
    dabloonsLabel.setText(Integer.toString(value));
    }   
}
