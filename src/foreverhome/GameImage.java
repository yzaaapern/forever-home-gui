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
 * @author yzape Name: Yza Pernia Student ID: 21137984
 */
public class GameImage extends JComponent {

    /*
        INSTANCE VARIABLES
     */
    private BufferedImage image;
    private boolean mustResize;
    private int x;
    private int y;

    /*
        OBJECT CONSTRUCTORS
     */
    // 1st Constructor takes a String filePath and sets the image's coordinates to (0,0)
    public GameImage(String imageFilePath) {
        this.mustResize = false;
        this.x = 0;
        this.y = 0;
        this.setImage(imageFilePath);
    }

    // 2nd Constructor takes a String filePath and sets the image's coordinates to (input x, input y)
    public GameImage(String imageFilePath, int x, int y) {
        this.mustResize = false;
        this.x = x;
        this.y = y;
        this.setImage(imageFilePath);
    }

    // 3rd Constructor takes a String filePath and a boolean value that determines the image size must 
    // be scaled down or up according to paintComponent method
    public GameImage(String imageFilePath, boolean mustResize) {
        this.mustResize = true;
        this.x = 0;
        this.y = 0;
        this.setImage(imageFilePath);
    }
    // 4th Constructor takes a String filePath, sets the image's coordinates to (input x, input y), and 
    // a boolean value that determines the image size must be scaled down or up according to paintComponent method
    public GameImage(String imageFilePath, boolean mustResize, int x, int y) {
        this.mustResize = true;
        this.x = x;
        this.y = y;
        this.setImage(imageFilePath);
    }

    /*
        setImage method
    Parameters: String imageFilePath
    Returns: None
    Description: Sets image to image retrieved from the input String imageFilePath
    */
    private void setImage(String imageFilePath) {
        try {
            this.image = ImageIO.read(new File(imageFilePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        getImage method
    Parameters: None
    Returns: Image image
    Description: Returns the private image method
    */
    public Image getImage() {
        return image;
    }

    /*
        getImageIcon method
    Parameters: None
    Returns: ImageIcon of image
    Description: Returns an image icon of the private image method
    */
    public ImageIcon getImageIcon() {
        return new ImageIcon(image);
    }

    /*
        OVERRIDE paintComponent method
    Parameters: Graphics g
    Returns: None
    Description: Repaints the image if it must be resized according to the calculated component size where the image is placed
                 and draws it on its x and y coordinates
    */
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
