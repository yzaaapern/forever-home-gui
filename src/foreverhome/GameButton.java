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
 * Name: Yza Pernia
 * Student ID: 21137984
 */
public class GameButton extends JButton {

    /*
        INSTANCE VARIABLES
    */
    private GameImage normalImage;
    private GameImage hoverImage;
    private String text;
    private boolean isHovered = false;

    /*
        OBJECT CONSTRUCTORS
    */
    
    // 1st Constructor only takes the input text and makes a plain button
    public GameButton(String text) {
        super.setText(text);
        initializeButton();
    }

    // 2nd Constructor takes the file paths of the button's images for its normal and hover state and makes a button with an icon but no text
    public GameButton(String normalImageFilePath, String hoverImageFilePath) {
        normalImage = new GameImage(normalImageFilePath);
        hoverImage = new GameImage(hoverImageFilePath);
        initializeButton();
    }

    // 3rd Constructor takes the input text and file paths for the button's images and makes a button with an icon and text
    public GameButton(String text, String normalImageFilePath, String hoverImageFilePath) {
        normalImage = new GameImage(normalImageFilePath, true);
        hoverImage = new GameImage(hoverImageFilePath, true);
        super.setText(text);
        initializeButton();
    }

    /*
        initializeButton method
    Parameters: None
    Returns: None
    Description: Initialises the GameButton object to manually build the custom GameButton and its appearance
    */
    private void initializeButton() {
        if (!isFilePathNull()) {
            this.setIcon(normalImage.getImageIcon());
        }
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setForeground(Color.white); // Sets the text color to white

        addMouseListener(new MouseAdapter() { 
            @Override
            public void mouseEntered(MouseEvent e) { // Checks if the mouse is hovering over the button
                isHovered = true;
                if (isFilePathNull()) { // If the file path is null, do nothing
                } else { // If the file path is not null, change the image icon of the button to the hover image
                    setIcon(hoverImage.getImageIcon());
                }

                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) { // Checks if the mouse is not hovering over the button
                isHovered = false;
                if (isFilePathNull()) { // If the file path is null, do nothing
                } else { // If the file path is not null, change the image icon of the button to the normal image
                    setIcon(normalImage.getImageIcon());
                }
                repaint();
            }
        });
    }

    /*
        isFilePathNull method
    Parameters: None
    Returns: True if hoverImage or normalImage are null
    */
    private boolean isFilePathNull() {
        return (hoverImage == null || normalImage == null);
    }

    /*
        OVERRIDE paintComponent method
    Parameters: Graphics g
    Returns: None
    Description: Paints the button text bold and draws a line underneath the text if the button doesn't have any icons
    */
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
