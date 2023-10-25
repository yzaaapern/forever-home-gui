/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author yzape
 */
public class GameTextField extends JTextField {

    private final GameImage FIELD_IMAGE;
    private final String TEXT_FIELD_FILE_PATH = "./resources/images/misc/input_field.png";

    public GameTextField() {
        super();
        FIELD_IMAGE = new GameImage(TEXT_FIELD_FILE_PATH);
        initializeField();
    }

    public GameTextField(int length) {
        super(length);
        FIELD_IMAGE = new GameImage(TEXT_FIELD_FILE_PATH);
        initializeField();
    }

    public GameTextField(String text) {
        super(text);
        FIELD_IMAGE = new GameImage(TEXT_FIELD_FILE_PATH);
        initializeField();
    }

    private void initializeField() {
        Font textFieldFont = GameFont.getPixelFont(13, 0);
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setFont(textFieldFont);
        this.setForeground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (FIELD_IMAGE != null) {
            int fieldWidth = getWidth();
            int fieldHeight = getHeight();

            double scaleXFactor = (double) fieldWidth / FIELD_IMAGE.getImage().getWidth(this);
            double scaleYFactor = (double) fieldHeight / FIELD_IMAGE.getImage().getWidth(this);

            int scaledWidth = (int) (FIELD_IMAGE.getImage().getWidth(this) * scaleXFactor);
            int scaledHeight = (int) (FIELD_IMAGE.getImage().getHeight(this) * scaleYFactor);

            int y = (getHeight() / 2 - scaledHeight / 2) + 1;

            g.drawImage(FIELD_IMAGE.getImage(), 0, y, scaledWidth, 10, this);

        }
    }
}
