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
 */
public class CustomFont {
    
    private static Font pixelFont;
    private static final String pixelBoldFilePath = "./resources/font/pixelmix_bold.ttf";
    private static final String pixelFilePath = "./resources/font/pixelmix.ttf";
    
    public static Font getPixelBoldFont(int fontSize){
        if(pixelFont == null){
            pixelFont = setPixelFont(pixelBoldFilePath, fontSize);
        }
        return pixelFont.deriveFont((float) fontSize);
    }
    
    public static Font getPixelFont(int fontSize){
        if(pixelFont == null){
            pixelFont = setPixelFont(pixelFilePath, fontSize);
        }
        return pixelFont.deriveFont((float) fontSize);
    }
    private static Font setPixelFont(String fontFilePath, int fontSize){
        Font customFont = null;
        try{
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath)).deriveFont((float) fontSize);
        }
        catch(IOException | FontFormatException e){
            e.printStackTrace();
        }
        
        return customFont;
    }
    
    
    
}
