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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author yzape
 */
public class LoadingView {

    public JPanel loadingPanel;
    private JLabel titleLabel;
    private JLabel loadingTextLabel;

    public boolean isShuttingDown = false;
    private int loadingTextIndex;
    private final String imageFilePath = "./resources/images/backgrounds/bg.png";
    private final String gameTitle = "FOREVER HOME";
    public String loadingText = "LOADING...";

    public LoadingView() {
        GridBagConstraints gbc = new GridBagConstraints();
        initializePanel();
        initializeLabels();
        addComponents(gbc);
    }

    public void showLoadingTextAnimation() {
        loadingTextLabel.setText("");
        loadingTextIndex = 1;
        CardLayout cardLayout = (CardLayout) ForeverHomeView.frame.getContentPane().getLayout();
        cardLayout.show(ForeverHomeView.frame.getContentPane(), "loading");

        Timer timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loadingTextIndex <= loadingText.length()) {
                    loadingTextLabel.setText(loadingText.substring(0, loadingTextIndex));
                    loadingTextIndex++;
                } else {
                    ((Timer) e.getSource()).stop();
                    if (isShuttingDown) {
                        loadingTextLabel.setText("GAME OVER");
                    }
                }
            }

        });
        timer.start();
    }

    private void addComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(0, 0, 40, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        loadingPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        loadingPanel.add(loadingTextLabel, gbc);
    }

    private void initializeLabels() {
        Font labelFont = GameFont.getPixelFont(80, 1);
        titleLabel = new JLabel(gameTitle);
        titleLabel.setFont(labelFont);
        titleLabel.setForeground(Color.white);

        labelFont = GameFont.getPixelFont(40, 0);
        loadingTextLabel = new JLabel("");
        loadingTextLabel.setFont(labelFont);
        loadingTextLabel.setForeground(Color.white);
    }

    private void initializePanel() {
        loadingPanel = new JPanel(new GridBagLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(imageFilePath);
                g.drawImage(bgImage.getImage(), 0, 0, null);
            }
        };
    }

    public void updateIsShuttingDown() {
        if (!isShuttingDown) {
            isShuttingDown = true;
            loadingText = "SHUTTING DOWN...";
        } else {
            isShuttingDown = false;
            loadingText = "LOADING...";
        }
    }
}
