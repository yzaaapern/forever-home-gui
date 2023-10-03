/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.CardLayout;
import javax.swing.JFrame;

/**
 *
 * @author yzape
 */
public class ForeverHomeView {
    
    private JFrame frame;
    private LoadingView loadingView;
    
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    
    public ForeverHomeView(){
        frame = new JFrame("Forever Home Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setLayout(new CardLayout());
        
        loadingView = new LoadingView();
        frame.add(loadingView.loadingPanel, loadingView.loadingText);
        frame.setVisible(true);
    }
    
    
    public void display(){
        frame.setVisible(true);
    }
}
