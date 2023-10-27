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
 */
public class InteractionView {

    public JPanel interactionPanel;
    private JPanel interactionMenuPanel;
    private JLabel titleLabel;
    private JLabel introLabel;

    private GameButton patBtn;
    private GameButton playBtn;
    private GameButton pottyBtn;
    private GameButton bowBtn;
    private GameButton handshakeBtn;
    private GameButton spinBtn;
    private GameButton playDeadBtn;
    private GameButton fetchBtn;
    
    private GameButton goBackBtn;

    private final String BG_FILE_PATH = "./resources/images/backgrounds/bg3.png";

    private final String PAT_FILE_PATH = "./resources/images/buttons/pat.png";
    private final String PAT_HOVER_FILE_PATH = "./resources/images/buttons/pat_hover.png";

    private final String PLAY_FILE_PATH = "./resources/images/buttons/play.png";
    private final String PLAY_HOVER_FILE_PATH = "./resources/images/buttons/play_hover.png";

    private final String POTTY_FILE_PATH = "./resources/images/buttons/potty.png";
    private final String POTTY_HOVER_FILE_PATH = "./resources/images/buttons/potty_hover.png";

    private final String BOW_FILE_PATH = "./resources/images/buttons/bow.png";
    private final String BOW_HOVER_FILE_PATH = "./resources/images/buttons/bow_hover.png";

    private final String HANDSHAKE_FILE_PATH = "./resources/images/buttons/handshake.png";
    private final String HANDSHAKE_HOVER_FILE_PATH = "./resources/images/buttons/handshake_hover.png";

    private final String SPIN_FILE_PATH = "./resources/images/buttons/spin.png";
    private final String SPIN_HOVER_FILE_PATH = "./resources/images/buttons/spin_hover.png";

    private final String PLAYDEAD_FILE_PATH = "./resources/images/buttons/play_dead.png";
    private final String PLAYDEAD_HOVER_FILE_PATH = "./resources/images/buttons/playDead_hover.png";

    private final String FETCH_FILE_PATH = "./resources/images/buttons/fetch.png";
    private final String FETCH_HOVER_FILE_PATH = "./resources/images/buttons/fetch_hover.png";

    private final String TITLE = "INTERACTION MENU";
    private final String INTRO = "Play with your foster pet or teach them a trick!";

    public InteractionView() {
        GridBagConstraints gbc = new GridBagConstraints();
        initializePanels();
        initializeLabels();
        initializeButtons();
        addComponents(gbc);
    }

    private void addComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(30, 20, 0, 20);
        gbc.gridwidth = 4;
        gbc.gridy = 0;
        interactionMenuPanel.add(titleLabel, gbc);

        gbc.insets = new Insets(10, 20, 0, 20);
        gbc.gridy++;
        interactionMenuPanel.add(introLabel, gbc);

        gbc.insets = new Insets(30, 0, 0, 20);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy++;
        interactionMenuPanel.add(patBtn, gbc);

        gbc.gridx++;
        gbc.gridy = 2;
        interactionMenuPanel.add(playBtn, gbc);

        gbc.gridx++;
        gbc.gridy = 2;
        interactionMenuPanel.add(pottyBtn, gbc);

        gbc.gridx++;
        gbc.gridy = 2;
        interactionMenuPanel.add(bowBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        interactionMenuPanel.add(handshakeBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        interactionMenuPanel.add(spinBtn, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        interactionMenuPanel.add(playDeadBtn, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        interactionMenuPanel.add(fetchBtn, gbc);

        gbc.insets = new Insets(50, 0, 0, 20);
        gbc.gridx = 3;
        gbc.gridy++;
        interactionMenuPanel.add(goBackBtn, gbc);

        interactionPanel.add(interactionMenuPanel, BorderLayout.CENTER);

    }

    private void initializeButtons() {
        Font buttonFont = GameFont.getPixelFont(15, 0);

        patBtn = new GameButton("pat", PAT_FILE_PATH, PAT_HOVER_FILE_PATH);
        patBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        patBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        patBtn.setFont(buttonFont);

        playBtn = new GameButton("play", PLAY_FILE_PATH, PLAY_HOVER_FILE_PATH);
        playBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        playBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        playBtn.setFont(buttonFont);

        pottyBtn = new GameButton("potty time", POTTY_FILE_PATH, POTTY_HOVER_FILE_PATH);
        pottyBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        pottyBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        pottyBtn.setFont(buttonFont);

        bowBtn = new GameButton("bow", BOW_FILE_PATH, BOW_HOVER_FILE_PATH);
        bowBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        bowBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        bowBtn.setFont(buttonFont);

        handshakeBtn = new GameButton("handshake", HANDSHAKE_FILE_PATH, HANDSHAKE_HOVER_FILE_PATH);
        handshakeBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        handshakeBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        handshakeBtn.setFont(buttonFont);

        spinBtn = new GameButton("spin", SPIN_FILE_PATH, SPIN_HOVER_FILE_PATH);
        spinBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        spinBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        spinBtn.setFont(buttonFont);

        playDeadBtn = new GameButton("play dead", PLAYDEAD_FILE_PATH, PLAYDEAD_HOVER_FILE_PATH);
        playDeadBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        playDeadBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        playDeadBtn.setFont(buttonFont);

        fetchBtn = new GameButton("fetch", FETCH_FILE_PATH, FETCH_HOVER_FILE_PATH);
        fetchBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        fetchBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        fetchBtn.setFont(buttonFont);

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
    }

    private void initializePanels() {
        interactionPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GameImage bgImage = new GameImage(BG_FILE_PATH);
                g.drawImage(bgImage.getImage(), 0, 0, null);
            }
        };

        interactionMenuPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Color transparentBG = new Color(209, 114, 114, 220);
                g.setColor(transparentBG);
                g.fillRect(interactionPanel.getWidth() / 8, interactionPanel.getHeight() / 6, 1000, 700);
            }
        };
        interactionMenuPanel.setOpaque(false);
        interactionMenuPanel.setPreferredSize(new Dimension(1000, 700));
    }
    
    public void addActionListener(ActionListener listener)
    {
        patBtn.addActionListener(listener);
        playBtn.addActionListener(listener);
        pottyBtn.addActionListener(listener);
        bowBtn.addActionListener(listener);
        handshakeBtn.addActionListener(listener);
        spinBtn.addActionListener(listener);
        playDeadBtn.addActionListener(listener);
        fetchBtn.addActionListener(listener);

        goBackBtn.addActionListener(listener);
    }

    public GameButton getPatBtn() {
        return patBtn;
    }

    public GameButton getPlayBtn() {
        return playBtn;
    }

    public GameButton getPottyBtn() {
        return pottyBtn;
    }

    public GameButton getBowBtn() {
        return bowBtn;
    }

    public GameButton getHandshakeBtn() {
        return handshakeBtn;
    }

    public GameButton getSpinBtn() {
        return spinBtn;
    }

    public GameButton getPlayDeadBtn() {
        return playDeadBtn;
    }

    public GameButton getFetchBtn() {
        return fetchBtn;
    }

    public GameButton getGoBackBtn() {
        return goBackBtn;
    }

}
