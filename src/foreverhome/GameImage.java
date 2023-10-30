/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author yzape
 */
public class GameImage extends JComponent {

    private BufferedImage image;
    private boolean mustResize;
    private int x;
    private int y;

    public GameImage(String imageFilePath) {
        this.mustResize = false;
        this.x = 0;
        this.y = 0;
        this.setImage(imageFilePath);
    }

    public GameImage(String imageFilePath, int x, int y) {
        this.mustResize = false;
        this.x = x;
        this.y = y;
        this.setImage(imageFilePath);
    }

    public GameImage(String imageFilePath, boolean mustResize) {
        this.mustResize = true;
        this.x = 0;
        this.y = 0;
        this.setImage(imageFilePath);
    }
    
    public GameImage(String imageFilePath, boolean mustResize, int x, int y) {
        this.mustResize = true;
        this.x = x;
        this.y = y;
        this.setImage(imageFilePath);
    }

    private void setImage(String imageFilePath) {
        try {
            this.image = ImageIO.read(new File(imageFilePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Image getImage(){
        return image;
    }
    
    public ImageIcon getImageIcon(){
        return new ImageIcon(image);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        
        if (image != null && mustResize) {
            int width = getWidth();
            int height = getHeight();

            double scale = Math.min((double) width / imageWidth, (double) height / imageHeight);
            imageWidth = (int) (imageWidth * scale);
            imageHeight = (int) (imageHeight * scale);
        }
        g.drawImage(image, x, y, imageWidth, imageHeight, this);
    }

}
