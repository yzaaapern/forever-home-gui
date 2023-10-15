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

    public static JFrame frame;
    private final LoadingView loadingView;
    private final StartGameView startGameView;
    private final LoginView loginView;

    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    public ForeverHomeView() {
        frame = new JFrame("Forever Home Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new CardLayout());

        loadingView = new LoadingView();
        loginView = new LoginView();
        startGameView = new StartGameView();
        
        frame.add(loadingView.loadingPanel, "loading");
        frame.add(startGameView.startGamePanel, "start");
        frame.add(loginView.loginPanel, "login");
        
        frame.setVisible(true);
    }

    public void display() {
        frame.setVisible(true);
    }

    public void showLoadingPanel() {
        loadingView.showLoadingTextAnimation();
    }
    
    public void showStartGamePanel(){
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "start");
    }

    public void showLoginPanel() {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "login");
    }

}
