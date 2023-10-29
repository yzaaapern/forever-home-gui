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
 * @author yzape Name: Yza Pernia Student ID: 21137984
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
    private final BuyFoodView BUYFOOD_VIEW;
    private final FeedPetView FEEDPET_VIEW;
    private final NotPausedPetFosterView NOT_PAUSED_PET_FOSTER_VIEW;
    private final PausedPetFosterView PAUSED_PET_FOSTER_VIEW;
    private final InteractionView INTERACTION_VIEW;
    private final AdoptionView ADOPTION_VIEW;

    private final int WIDTH = 1400;
    private final int HEIGHT = 1040;

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
        NOT_PAUSED_PET_FOSTER_VIEW = new NotPausedPetFosterView();
        PAUSED_PET_FOSTER_VIEW = new PausedPetFosterView();
        BUYFOOD_VIEW = new BuyFoodView();
        FEEDPET_VIEW = new FeedPetView();
        INTERACTION_VIEW = new InteractionView();
        ADOPTION_VIEW = new AdoptionView();

        frame.add(LOADING_VIEW.loadingPanel, "loading");
        frame.add(STARTGAME_VIEW.startGamePanel, "start");
        frame.add(LOGIN_VIEW.signupAndLoginPanel, "login");
        frame.add(FOSTER_VIEW.fosterPanel, "foster");
        frame.add(NOT_PAUSED_PET_FOSTER_VIEW.petFosterPanel, "pet foster");
        frame.add(PAUSED_PET_FOSTER_VIEW.petFosterPanel, "paused pet foster");
        frame.add(BUYFOOD_VIEW.buyFoodPanel, "buy food");
        frame.add(FEEDPET_VIEW.foodInventoryPanel, "feed pet");
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
        frame.setVisible(true);
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

    public void showNotPausedPetFosterPanel() {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "pet foster");
    }

    public void showPausedPetFosterPanel() {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "paused pet foster");
           
    }
    
    public void showBuyFoodPanel(){
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "buy food");    
    }
    
    public void showFeedPetPanel(){
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "feed pet");       
        
    }

    public void showFoodInventoryPanel() {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "food inventory");
    }

    public void showInteractionPanel() {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "interaction");
    }

    public void showAdoptionPanel() {
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

    public NotPausedPetFosterView getNOT_PAUSED_PET_FOSTER_VIEW() {
        return NOT_PAUSED_PET_FOSTER_VIEW;
    }

    public PausedPetFosterView getPAUSED_PET_FOSTER_VIEW(){
        return PAUSED_PET_FOSTER_VIEW;
    }

    public BuyFoodView getBUYFOOD_VIEW() {
        return BUYFOOD_VIEW;
    }
    
    public FeedPetView getFEEDPET_VIEW()
    {
        return FEEDPET_VIEW;
    }

    public InteractionView getINTERACTION_VIEW() {
        return INTERACTION_VIEW;
    }

    public AdoptionView getADOPTION_VIEW() {
        return ADOPTION_VIEW;
    }

    public void addActionListeners(ActionListener listener) {
        LOGIN_VIEW.addActionListener(listener);
        STARTGAME_VIEW.addActionListener(listener);
        FOSTER_VIEW.addActionListener(listener);
        NOT_PAUSED_PET_FOSTER_VIEW.addActionListener(listener);
        PAUSED_PET_FOSTER_VIEW.addActionListener(listener);
        BUYFOOD_VIEW.addActionListener(listener);
        FEEDPET_VIEW.addActionListener(listener);
        INTERACTION_VIEW.addActionListener(listener);
        ADOPTION_VIEW.addActionListener(listener);
    }
}
