/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author yzape
 * Name: Yza Pernia
 * Student ID: 21137984
 */
public class GameFont {

    /*
        INSTANCE AND CONSTANT VARIABLES
    */
    private static Font pixelFont;
    
    // File Paths
    private static final String PIXEL_BOLD_FILE_PATH = "./resources/font/pixelmix_bold.ttf";
    private static final String PIXEL_FILE_PATH = "./resources/font/pixelmix.ttf";
    
    /*
        getPixelFont method
    Parameters: int fontSize, int fontType
    Return: Font pixelFont
    Description: If the input fontType is 0, it sets the pixel font as the default font, whereas if it is any other number, then it returns a bold pixel font
    */
    public static Font getPixelFont(int fontSize, int fontType) {
        if (fontType == 0) {
            pixelFont = setPixelFont(PIXEL_FILE_PATH, fontSize); // Sets the pixelFont object according to filePath and fontSize
        } else {
            pixelFont = setPixelFont(PIXEL_BOLD_FILE_PATH, fontSize);

        }

        return pixelFont.deriveFont((float) fontSize);
    }

    /*
        setPixelFont method
    Parameters: String fontFilePath, int fontSize
    Returns: Font customFont
    Description: Sets the customFont object to a font that is retrieved from the input fontFilePath and fontSize
    */
    private static Font setPixelFont(String fontFilePath, int fontSize) {
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath)).deriveFont((float) fontSize);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        return customFont;
    }

}
