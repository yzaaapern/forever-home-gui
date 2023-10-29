package foreverhome;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 * Custom progress bar with added features.
 */
public class GameProgressBar extends JProgressBar {

    private final GameImage BORDER_IMAGE;
    private final Color BAR_COLOR;
    private final int BAR_OFFSETX = 40;
    private final int BAR_OFFSETY = 15;
    private int maxValue = Level.DEFAULT_LEVELXP_CAP; // Set the maximum value for the progress bar

    public GameProgressBar(String progressBarImageFilePath, String progressBarColor) {
        BORDER_IMAGE = new GameImage(progressBarImageFilePath);
        BAR_COLOR = Color.decode(progressBarColor);
        setUI(new GameProgressBarUI());
        setOpaque(false);
        setBorderPainted(false);
        setPreferredSize(new Dimension(490, 70));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(BORDER_IMAGE != null)
        {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            g2d.drawImage(BORDER_IMAGE.getImage(), 0, 0, width, height, null);
        }
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    private class GameProgressBarUI extends BasicProgressBarUI {
    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        if (progressBar != null) {
            Graphics2D g2d = (Graphics2D) g;
            int width = progressBar.getWidth() - 50;
            int heightDiff = 30;
            int height = progressBar.getHeight() - heightDiff;

            int progressWidth = (int) (width * ((double) progressBar.getValue() / progressBar.getMaximum()));

            if (progressWidth > width) {
                progressWidth = width; // Ensure the progress bar doesn't go over the border
            }

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
