/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author yzape
 */
public class LoadingView extends JPanel {

    private final JLabel titleLabel;
    private final JLabel loadingTextLabel;
    public JPanel loadingPanel;
    private Image backgroundImage;

    private final String imageFilePath = "./resources/images/bg.png";
    private final String gameTitle = "FOREVER HOME";
    public final String loadingText = "LOADING...";
    private int loadingTextIndex;

    public LoadingView() {

        loadingPanel = new JPanel(new GridBagLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackgroundImage(g);
            }
        };
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 40, 0);

        Font titleFont = CustomFont.getPixelBoldFont(50);
        titleLabel = new JLabel(gameTitle);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 0;
        loadingPanel.add(titleLabel, gbc);

        Font loadingTextFont = CustomFont.getPixelFont(25);
        loadingTextLabel = new JLabel("");
        loadingTextLabel.setFont(loadingTextFont);
        loadingTextLabel.setForeground(Color.white);
        gbc.gridy = 1;
        loadingPanel.add(loadingTextLabel, gbc);
    }

    private void setBackgroundImage(Graphics g) {
        try {
            BufferedImage buffImage = ImageIO.read(new File(imageFilePath));
            g.drawImage(buffImage, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showLoadingTextAnimation(){
        loadingTextLabel.setText("");
        loadingTextIndex = 1;
        CardLayout cardLayout = (CardLayout) ForeverHomeView.frame.getContentPane().getLayout();
        cardLayout.show(ForeverHomeView.frame.getContentPane(), loadingText);
        
        Timer timer =  new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loadingTextIndex <= loadingText.length()){
                    loadingTextLabel.setText(loadingText.substring(0, loadingTextIndex));
                    loadingTextIndex++;
                }
                else{
                    ((Timer) e.getSource()).stop();
                }
            }
            
        });
        timer.start();
    }
}
