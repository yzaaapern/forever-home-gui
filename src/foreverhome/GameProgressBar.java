/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import javax.swing.JProgressBar;

/**
 *
 * @author yzape
 */
public class GameProgressBar extends JProgressBar {

    private GameImage progressBarImage;

    public GameProgressBar(String progressBarImageFilePath) {
        progressBarImage = new GameImage(progressBarImageFilePath);
        initializeProgressBar();
    }

    private void initializeProgressBar() {

    }
}
