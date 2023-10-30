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

    private static Font pixelFont;
    private static final String PIXEL_BOLD_FILE_PATH = "./resources/font/pixelmix_bold.ttf";
    private static final String PIXEL_FILE_PATH = "./resources/font/pixelmix.ttf";
    
    public static Font getPixelFont(int fontSize, int fontType) {
        if (fontType == 0) {
            pixelFont = setPixelFont(PIXEL_FILE_PATH, fontSize);
        } else {
            pixelFont = setPixelFont(PIXEL_BOLD_FILE_PATH, fontSize);

        }

        return pixelFont.deriveFont((float) fontSize);
    }

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
