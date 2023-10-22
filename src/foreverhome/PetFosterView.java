/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author yzape
 */
public class PetFosterView {

    public JPanel petFosterPanel;
    private JPanel petStatsPanel;
    private JPanel petSpritePanel;
    private JPanel petFosterMenuPanel;
    private GameButton feedBtn;
    private GameButton interactBtn;
    private GameButton batheBtn;
    private GameButton escBtn;

    private final String BG_FILE_PATH = "./resources/images/petFoster_bg.png";

    private final String HAPPINESS_BAR_FILE_PATH = "./resources/images/happiness_bar.png";
    private final String HUNGER_BAR_FILE_PATH = "./resources/images/hunger_bar.png";
    private final String HYGIENE_BAR_FILE_PATH = "./resources/images/hygiene_bar.png";
    private final String XP_BAR_FILE_PATH = "./resources/images/xp_bar.png";
    private final String LEVEL_BAR_FILE_PATH = "./resources/images/level_bar.png";
    private final String LEVEL_NOTF_FILE_PATH = "./resources/images/levelup_notif.png";

    public PetFosterView() {
        initializePanels();
    }

    private void addComponents() {

    }

    private void initializePanels() {
       petFosterPanel = new JPanel(new GridBagLayout()){
           @Override
           protected void paintComponent(Graphics g){
               super.paintComponent(g);
               GameImage bgImage = new GameImage(BG_FILE_PATH);
               g.drawImage(bgImage.getImage(), 0, 0, null);
           }
       };
    }

}
