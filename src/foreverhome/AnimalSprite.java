/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author yzape
 */
public class AnimalSprite extends JComponent {

    private Map<String, Integer> animalFrames;
    private Image[] animationFrames;
    private Animal fosterPet;
    private String animalFramePath;
    private String animalType;
    private Timer animationTimer;
    private int currentFrame;
    private int frameCount;
    private boolean notIdle;
    private int idleFrame;
    private int nonIdleFrame;
    
    private final int FRAME_DURATION = 350;
    private final int NOT_IDLE_FRAME_START_COUNT = 6;
    private final int IDLE_FRAME_END_COUNT = 5;

    public AnimalSprite(Animal fosterPet) {
        this.setFosterPet(fosterPet);
        this.animalType = fosterPet.checkFosterPet(fosterPet);
        this.animalFramePath = "./resources/images/animal sprites/" + animalType;
        this.currentFrame = 0;
        this.setAnimalSets();
        this.setFrameCount();
        this.setAnimationFrames();
        this.setNotIdle(false);

        this.startAnimation();
    }

    private void setFosterPet(Animal fosterPet) {
        this.fosterPet = fosterPet;
    }

    private void setAnimalSets() {
        this.animalFrames = new HashMap<>();
        animalFrames.put("dog", 15);
        animalFrames.put("cat", 10);
        animalFrames.put("rat", 8);
        animalFrames.put("parrot", 8);
        animalFrames.put("chicken", 14);
    }

    private void setAnimationFrames() {
        animationFrames = new Image[frameCount];

        for (int i = 0; i < animationFrames.length; i++) {
            String imageFilePath = animalFramePath + (i + 1) + ".png";
            animationFrames[i] = Toolkit.getDefaultToolkit().getImage(imageFilePath);
        }
    }

    private void setFrameCount() {
        this.frameCount = animalFrames.get(animalType);
    }

    private void setNotIdle(boolean isIdle) {
        this.notIdle = isIdle;
    }

    private void startAnimation() {
        animationTimer = new Timer(FRAME_DURATION, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFrame = (currentFrame + 1) % frameCount;

                if (currentFrame == IDLE_FRAME_END_COUNT - 1 || currentFrame == frameCount - 1) {
                    currentFrame = 0;
                    setNotIdle(false);
                }

                repaint();
            }
        });

        animationTimer.start();
    }

    public void toggleIsIdleAnimation() {
        if (!notIdle) {
            setNotIdle(true);
            currentFrame = NOT_IDLE_FRAME_START_COUNT;
        } else {
            setNotIdle(false);
            currentFrame = 0;
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image currentImage = animationFrames[notIdle ? currentFrame : currentFrame % NOT_IDLE_FRAME_START_COUNT];
        g.drawImage(currentImage, 0, 0, this);
    }

    public static void main(String[] args) {
        Animal a = new Chicken("malaya");
        AnimalSprite as = new AnimalSprite(a);

        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.getContentPane().setBackground(Color.black);

        frame.add(as);

        JButton button = new JButton("Click Me");
        button.addActionListener(e -> {
            as.toggleIsIdleAnimation();
        });
        frame.add(button, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
