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
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    private GameButton dogBtn;
    private GameButton catBtn;
    private GameButton ratBtn;
    private GameButton parrotBtn;
    private GameButton chickenBtn;
    private GameButton continueBtn;
    private GameButton goBackBtn;

    private final String BG_FILE_PATH = "./resources/images/bg3.png";
    private final String POPUP_FILE_PATH = "./resources/images/popup_box.png";
    private final String DOG_FILE_PATH = "./resources/images/dog1.png";
    private final String CAT_FILE_PATH = "./resources/images/cat1.png";
    private final String RAT_FILE_PATH = "./resources/images/rat1.png";
    private final String PARROT_FILE_PATH = "./resources/images/parrot1.png";
    private final String CHICKEN_FILE_PATH = "./resources/images/chicken1.png";
    private final String TITLE = "FOSTER MENU";
    private final String INTRO = "Pick an animal you would like to foster!";

    public FosterView() {
        GridBagConstraints gbc = new GridBagConstraints();
        initializePanels();
        initializeLabels();
        addComponents(gbc);
    }

    private void addComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(20, 0, 5, 0);
        gbc.gridwidth = 5;
        gbc.gridy = 0;
        fosterPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        fosterPanel.add(introLabel, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridy = 2;
//       gbc.anchor = GridBagConstraints.SOUTH;
        fosterPanel.add(popUpPanel);
    }

    private void initializeLabels() {
        Font labelFont = GameFont.getPixelFont(20, 1);
        titleLabel = new JLabel(TITLE);
        titleLabel.setFont(labelFont);
        titleLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(13, 0);
        introLabel = new JLabel(INTRO);
        introLabel.setFont(labelFont);
        introLabel.setForeground(Color.white);
    }

    private void initializePanels() {
        fosterPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(BG_FILE_PATH);
                g.drawImage(bgImage.getImage(), 0, 0, null);
            }
        };

        petsPanel = new JPanel(new GridBagLayout());
        popUpPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(POPUP_FILE_PATH);
                int yCoordinate = getHeight() - bgImage.getImage().getHeight(this);
                g.drawImage(bgImage.getImage(), 0, yCoordinate, this
                );
            }
        };
        popUpPanel.setOpaque(false);
    }
}
