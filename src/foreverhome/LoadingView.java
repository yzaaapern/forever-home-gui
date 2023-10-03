/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author yzape
 */
public class LoadingView extends JPanel {
    
    private final JLabel titleLabel;
    private final JLabel loadingTextLabel;
    public JPanel loadingPanel;
    private Image backgroundImage;
    
    private final String gameTitle = "FOREVER HOME";
    public final String loadingText = "LOADING...";
    private int loadingTextIndex;
    
    public LoadingView(){
              try{
            backgroundImage = ImageIO.read(getClass().getResource("./resources/images/bg.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        loadingPanel = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                setBackgroundImage(g);
            }
        };
        loadingPanel.setLayout(new GridBagLayout());

        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,5,0);
        
        titleLabel = new JLabel(gameTitle);
        gbc.gridx = 0;
        gbc.gridy = 0;
        loadingPanel.add(titleLabel, gbc);
        
        loadingTextLabel = new JLabel("");
        gbc.gridy = 1;
        loadingPanel.add(loadingTextLabel, gbc);
    }
    
    private void setBackgroundImage(Graphics g){
        try{
            BufferedImage buffImage = ImageIO.read(new File("/resources/images/bg.png"));
            g.drawImage(buffImage, 0, 0, null);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
