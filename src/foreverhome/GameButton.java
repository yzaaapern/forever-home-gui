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

    private GameImage normalImage;
    private GameImage hoverImage;
    private String text;
    private boolean isHovered = false;
    private boolean isChanged = false;

    public GameButton(String text) {
        super.setText(text);
        initializeButton();
    }

    public GameButton(String normalImageFilePath, String hoverImageFilePath) {
        normalImage = new GameImage(normalImageFilePath);
        hoverImage = new GameImage(hoverImageFilePath);
        initializeButton();
    }

    public GameButton(String text, String normalImageFilePath, String hoverImageFilePath) {
        normalImage = new GameImage(normalImageFilePath, true);
        hoverImage = new GameImage(hoverImageFilePath, true);
        super.setText(text);
        initializeButton();
    }

    public void updateNormalAndHoverImages(String normalImageFilePath, String hoverImageFilePath){
        normalImage = new GameImage(normalImageFilePath);
        hoverImage = new GameImage(hoverImageFilePath);   
        isChanged = true;
        updateButtonState();
    }
    
    private void initializeButton() {
        if (!isFilePathNull()) {
            this.setIcon(normalImage.getImageIcon());
        }
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setForeground(Color.white);
        
        updateButtonState();
    }

    private void updateButtonState(){
        if(isChanged){
            this.setIcon(normalImage.getImageIcon());
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if (isFilePathNull()) {
                } else {
                    setIcon(hoverImage.getImageIcon());
                }

                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if (isFilePathNull()) {
                } else {
                    setIcon(normalImage.getImageIcon());
                }
                repaint();
            }
        });
    }
    
    private boolean isFilePathNull() {
        return (hoverImage == null || normalImage == null);
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
