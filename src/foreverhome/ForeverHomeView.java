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
    private final LoadingView LOADING_VIEW;
    private final StartGameView STARTGAME_VIEW;
    private final SignupAndLoginView LOGIN_VIEW;
    private final FosterView FOSTER_VIEW;
    private final PetFosterView PET_FOSTER_VIEW;
    private final FoodInventoryView FOOD_INVEN_VIEW;
    private final InteractionView INTERACTION_VIEW;
    private final AdoptionView ADOPTION_VIEW;

    private final int WIDTH = 1400;
    private final int HEIGHT = 1040;

    public ForeverHomeView() {
        frame = new JFrame("Forever Home Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new CardLayout());

        LOADING_VIEW = new LoadingView();
        LOGIN_VIEW = new SignupAndLoginView();
        STARTGAME_VIEW = new StartGameView();
        FOSTER_VIEW = new FosterView();
        PET_FOSTER_VIEW = new PetFosterView();
        FOOD_INVEN_VIEW = new FoodInventoryView();
        INTERACTION_VIEW = new InteractionView();
        ADOPTION_VIEW = new AdoptionView();
        

        frame.add(LOADING_VIEW.loadingPanel, "loading");
        frame.add(STARTGAME_VIEW.startGamePanel, "start");
        frame.add(LOGIN_VIEW.signupAndLoginPanel, "login");
        frame.add(FOSTER_VIEW.fosterPanel, "foster");
        frame.add(PET_FOSTER_VIEW.petFosterPanel, "pet foster");
        frame.add(FOOD_INVEN_VIEW.foodInventoryPanel, "food inventory");
        frame.add(INTERACTION_VIEW.interactionPanel, "interaction");
        frame.add(ADOPTION_VIEW.adoptionPanel, "adoption");
        
        frame.setVisible(true);
    }

    public void display() {
        frame.setVisible(true);
        showLoadingPanel();
    }

    public void showLoadingPanel() {
        LOADING_VIEW.showLoadingTextAnimation();
    }

    public void showStartGamePanel() {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "start");
    }

    public void showLoginPanel() {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "login");
    }

    public void showFosterPanel() {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "foster");
    }

    public void showPetFosterPanel(){
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "pet foster");
    }
    
    public void showFoodInventoryPanel(){
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "food inventory");       
    }
    
    public void showInteractionPanel(){
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "interaction");       
    }
    
    public void showAdoptionPanel(){
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "adoption");       
    }
}
