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
 * Name: Yza Pernia 
 * Student ID: 21137984
 */
public class AnimalSprite extends JComponent {

    /*
        INSTANCE AND CONSTANT VARIABLES
     */
    // Sprite-building variables
    private Map<String, Integer> animalFrames;
    private Image[] animationFrames;
    private Animal fosterPet;
    private String animalFramePath;
    private String animalType;
    private Timer animationTimer;
    private int currentFrame;
    private int frameCount;
    private boolean notIdle;

    // Constant Sprite variables
    private final int FRAME_DURATION = 350;
    private final int NOT_IDLE_FRAME_START_COUNT = 6;
    private final int IDLE_FRAME_END_COUNT = 5;

    /*
        OBJECT CONSTRUCTOR
     */
    public AnimalSprite(Animal fosterPet) {
        this.setFosterPet(fosterPet);
        this.animalType = fosterPet.checkFosterPet(fosterPet); // Animal Type is decided by checking the fosterPet Animal instance
        this.animalFramePath = "./resources/images/animal sprites/" + animalType; // File path is constructed with a concatenated string of the a permanent directory and animal type
        this.currentFrame = 0;
        this.setAnimalSets();
        this.setFrameCount();
        this.setAnimationFrames();
        this.setNotIdle(false);

        this.startAnimation(); // Starts Sprite animation
    }

    /*
        setFosterPet method
    Parameter: Animal fosterPet
    Returns: None
    Descriptoin: Sets the global fosterPet as the input fosterPet 
     */
    private void setFosterPet(Animal fosterPet) {
        this.fosterPet = fosterPet;
    }

    /*
        setAnimalSets method
    Parameters: None
    Returns: None
    Description: Initialises the animalFrames into a HashMap and defines the frameCount (value) for every possible animal sprite (key)
     */
    private void setAnimalSets() {
        this.animalFrames = new HashMap<>();
        animalFrames.put("dog", 15);
        animalFrames.put("cat", 10);
        animalFrames.put("rat", 8);
        animalFrames.put("parrot", 8);
        animalFrames.put("chicken", 14);
    }

    /*
        setAnimationFrames method
    Parameters: None
    Returns: None
    Description: Initialises the animationFrames with the frameCount and defines the animationFrames array by 
                 retrieving every sprite image from the animalFramePath, concatenated with the index number of the image and the file type.
     */
    private void setAnimationFrames() {
        animationFrames = new Image[frameCount];

        for (int i = 0; i < animationFrames.length; i++) {
            String imageFilePath = animalFramePath + (i + 1) + ".png";
            animationFrames[i] = Toolkit.getDefaultToolkit().getImage(imageFilePath);
        }
    }

    /*
        setFrameCount method
    Parameters: None
    Returns: None
    Description: Sets the frameCount as the value of the animalType in the animalFrames HashMap
     */
    private void setFrameCount() {
        this.frameCount = animalFrames.get(animalType);
    }

    /*
        setNotIdle method
    Parameters: boolean notIdle
    Returns: None
    Description: Sets the notIdle value as the input notIdle value 
     */
    private void setNotIdle(boolean notIdle) {
        this.notIdle = notIdle;
    }

    /*
        startAnimation method
    Parameters: None
    Returns: None
    Description: Initialises the animationTimer where the duration for each frame that is displayed is defined by FRAME_DURATION
                 and adds an ActionListener that performs an action after the timer ends. The ActionListener changes the frame
                 after the timer ends. If the currentFrame number is equal to the last idle frame number or the last frame,
                 then the currentFrame number returns to the first one.
     */
    private void startAnimation() {
        animationTimer = new Timer(FRAME_DURATION, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFrame = (currentFrame + 1) % frameCount;

                if (currentFrame == IDLE_FRAME_END_COUNT - 1 || currentFrame == frameCount - 1) {
                    currentFrame = 0;
                    setNotIdle(false); // Set notIdle to false to signify that idle frames should only be displayed
                }

                repaint();
            }
        });

        animationTimer.start(); // Start the animation
    }

    /*
        toggleIsIdleAnimation method
    Parameters: None
    Returns: None
    Description: Used to change the frames displayed in the sprite if its ActionListener is triggered
                 and display the notIdle frames if notIdle is currently false. if it is already true,
                 then the idleFrames must be changed back to the idle frames.
     */    
    public void toggleIsIdleAnimation() {
        if (!notIdle) {
            setNotIdle(true);
            currentFrame = NOT_IDLE_FRAME_START_COUNT; // Change the currentFrame to the non-idle frames
        } else {
            setNotIdle(false);
            currentFrame = 0; // Changes the currentFrame to the idle frames 
        }

        repaint();
    }

    /*
        OVERRIDE paintComponent method
    Parameters: Graphics g
    Returns: None
    Description: Paints the current sprite image
    */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image currentImage = animationFrames[notIdle ? currentFrame : currentFrame % NOT_IDLE_FRAME_START_COUNT]; // Paints the current sprite frame from the animationFrames depending if notIdle is true or not
        g.drawImage(currentImage, 0, 0, this);
    }

//    public static void main(String[] args) {
//        Animal a = new Chicken("malaya");
//        AnimalSprite as = new AnimalSprite(a);
//
//        JFrame frame = new JFrame("Test");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 400);
//        frame.getContentPane().setBackground(Color.black);
//
//        frame.add(as);
//
//        JButton button = new JButton("Click Me");
//        button.addActionListener(e -> {
//            as.toggleIsIdleAnimation();
//        });
//        frame.add(button, BorderLayout.SOUTH);
//        frame.setVisible(true);
//    }
}
