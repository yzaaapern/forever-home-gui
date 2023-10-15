/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author yzape
 */
public class GameButton extends JButton {

    private GameImage NORMAL_IMAGE;
    private GameImage HOVER_IMAGE;
    private String text;
    private boolean isHovered = false;

    public GameButton(String text) {
        super.setText(text);
        this.text = text;
        initializeButton();
    }

    public GameButton(String normalImageFilePath, String hoverImageFilePath) {
        NORMAL_IMAGE = new GameImage(normalImageFilePath);
        HOVER_IMAGE = new GameImage(hoverImageFilePath);
        initializeButton();
    }

    public GameButton(String text, String normalImageFilePath, String hoverImageFilePath) {
        NORMAL_IMAGE = new GameImage(normalImageFilePath, true);
        HOVER_IMAGE = new GameImage(hoverImageFilePath, true);
        super.setText(text);
        this.text = text;
        initializeButton();
    }

    private void initializeButton() {
        if (!isFilePathNull()) {
            this.setIcon(NORMAL_IMAGE.getImageIcon());
        }
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if (isFilePathNull()) {
                } else {
                    setIcon(HOVER_IMAGE.getImageIcon());
                }

                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if (isFilePathNull()) {
                } else {
                    setIcon(NORMAL_IMAGE.getImageIcon());
                }
                repaint();
            }
        });
    }

    private boolean isFilePathNull() {
        return (HOVER_IMAGE == null || NORMAL_IMAGE == null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int xStart = (getWidth() - textWidth) / 2;
        int xEnd = xStart + textWidth;
        Font btnFont = this.getFont();
        int fontSize = btnFont.getSize();

        if (isHovered && isFilePathNull()) {

            this.setFont(GameFont.getPixelFont(fontSize, 1));
            g.setColor(Color.white);
            g.drawLine(xStart, getHeight() - 3, xEnd, getHeight() - 3);
        } else {
            this.setFont(GameFont.getPixelFont(fontSize, 0));
        }
    }
}
