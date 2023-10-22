/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 *
 * @author yzape
 */
public class GameProgressBar extends JProgressBar {

    private final GameImage BORDER_IMAGE;
    private final Color BAR_COLOR;
    private final int BAR_OFFSETX = 40;
    private final int BAR_OFFSETY = 15;

    public GameProgressBar(String progressBarImageFilePath, String progressBarColor) {
        BORDER_IMAGE = new GameImage(progressBarImageFilePath);
        BAR_COLOR = Color.decode(progressBarColor);
        setUI(new GameProgressBarUI());
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        g2d.drawImage(BORDER_IMAGE.getImage(), 0, 0, width, height, null);
    }
    
    private class GameProgressBarUI extends BasicProgressBarUI{
        @Override
        protected void paintDeterminate(Graphics g, JComponent c){
            if(progressBar != null){
                Graphics2D g2d = (Graphics2D) g;
                int width = progressBar.getWidth();
                
                int heightDiff = 30;
                int height = progressBar.getHeight() - heightDiff;
                
                int progressWidth = (int) (width * getPercentComplete());
                
                RoundRectangle2D progressBarShape = new RoundRectangle2D.Double(BAR_OFFSETX, BAR_OFFSETY,
                                                        progressWidth, height, height, height);
                
                g2d.setClip(progressBarShape);
                g2d.setColor(BAR_COLOR);
                g2d.fill(progressBarShape);
                g2d.setClip(null);
            }
        }
    }
}
