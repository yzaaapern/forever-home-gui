/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author yzape
 * Name: Yza Pernia
 * Student ID: 21137984
 */
public class ForeverHomeView {

    /*
        INSTANCE AND CONSTANT VARIABLES
    */
    // GUI Components
    public static JFrame frame;
    private final LoadingView LOADING_VIEW;
    private final StartGameView STARTGAME_VIEW;
    private final SignupAndLoginView LOGIN_VIEW;
    private final FosterView FOSTER_VIEW;
    private final PetFosterView PET_FOSTER_VIEW;
    private final FoodInventoryView FOOD_INVEN_VIEW;
    private final InteractionView INTERACTION_VIEW;
    private final AdoptionView ADOPTION_VIEW;

    // Frame Size
    private final int WIDTH = 1400;
    private final int HEIGHT = 1050;

    /*
        OBJECT CONSTRUCTOR
    */
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

    /*
        display method
    Parameters: None
    Returns: None
    Description: Calls the showloadingPanel function
    */
    public void display() {
        showLoadingPanel();
    }

    /*
        showLoadingPanel method
    Parameters: None
    Returns: None
    Description: Displays the loadingTextAnimation of the loadingView
    */
    public void showLoadingPanel() {
        getLOADING_VIEW().showLoadingTextAnimation();
    }

    /*
        SHOWPANELS methods 
    Parameters: None
    Returns: None
    Description: Displays every subview's main panel using CardLayout
    */
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

    /*
        GETVIEW methods
    Parameters: None
    Returns: private subviews of ForeverHomeView
    Description: Returns the private subviews of ForeverHomeView 
    */
    
    public LoadingView getLOADING_VIEW() {
        return LOADING_VIEW;
    }

    public StartGameView getSTARTGAME_VIEW() {
        return STARTGAME_VIEW;
    }

    public SignupAndLoginView getLOGIN_VIEW() {
        return LOGIN_VIEW;
    }

    public FosterView getFOSTER_VIEW() {
        return FOSTER_VIEW;
    }

    public PetFosterView getPET_FOSTER_VIEW() {
        return PET_FOSTER_VIEW;
    }

    public FoodInventoryView getFOOD_INVEN_VIEW() {
        return FOOD_INVEN_VIEW;
    }

    public InteractionView getINTERACTION_VIEW() {
        return INTERACTION_VIEW;
    }

    public AdoptionView getADOPTION_VIEW() {
        return ADOPTION_VIEW;  
    }

//    public void addActionListeners(ActionListener listener)
//    {
//        LOGIN_VIEW.addActionListener(listener);
//        STARTGAME_VIEW.addActionListener(listener);
//        FOSTER_VIEW.addActionListener(listener);
//        PET_FOSTER_VIEW.addActionListener(listener);
//        FOOD_INVEN_VIEW.addActionListener(listener);
//        INTERACTION_VIEW.addActionListener(listener);
//        ADOPTION_VIEW.addActionListener(listener);
//    }
}
