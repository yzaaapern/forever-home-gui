/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author yzape
 * Name: Yza Pernia
 * Student ID: 21137984
 */

public class ForeverHomeController implements ActionListener{
    private ForeverHomeModel model;
    private ForeverHomeView view;
    
    /*
        OBJECT CONSTRUCTOR
    */
    public ForeverHomeController(ForeverHomeModel model, ForeverHomeView view){
        this.model = model;
        this.view = view;
//        this.view.addActionListeners(this);
    }
    
    /*
        start method
    Parameters: None
    Returns: None
    Description: Starts the program by simulating the loading screen and calling the startGamePanel
    */
    public void start(){
//        view.display();
//        simulateLoading();
        view.showStartGamePanel();
//        view.showLoginPanel();
//        view.showFosterPanel();
//        view.showPetFosterPanel();
//        view.showFoodInventoryPanel();
//        view.showInteractionPanel();
//        view.showAdoptionPanel();
    }
    
    /*
        simulateLoading method
    Parameters: None
    Returns: None
    Description: Simulates the loading screen by putting the other views to sleep for a given number of time.
    */
    private void simulateLoading(){
        try{
            Thread.sleep(16000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    /*
        OVERRIDE actionPerformed method
    Parameters: ActionEvent e
    Returns: None
    Description:
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource(); // Get source of the event

        // Check which button was clicked and perform the corresponding action
        if (source == view.getADOPTION_VIEW().getNoBtn()) 
        {
            /*
            MODEL:
            - No change
            
            VIEW:
            - closes pop-up
            - goes to pet foster menu
            
            */
            
        } 
        else if (source == view.getADOPTION_VIEW().getYesBtn()) 
        {
            /*
            MODEL:
            - pet is adopted status true
            - player has no active pet
            
            VIEW:
            - end game message
            - asks user if they want to continue fostering
            */
        } 
        else if (source == view.getFOOD_INVEN_VIEW().getBuyFoodForAll()) 
        {
            /*
            MODEL:
            - inc food cunt
            - dec player dabloons
            
            VIEW:
            - food inventory increase
            - dec player dabloons
            - stay on food inventory view
            */
        } 
        else if (source == view.getFOOD_INVEN_VIEW().getBuyRainbowTreat()) {
            // Handle "Buy Rainbow Treat" button click
        } else if (source == view.getFOOD_INVEN_VIEW().getBuyKibble()) {
            // Handle "Buy Kibble" button click
        } else if (source == view.getFOOD_INVEN_VIEW().getBuyCannedFood()) {
            // Handle "Buy Canned Food" button click
        } else if (source == view.getFOOD_INVEN_VIEW().getBuyVeggieMix()) {
            // Handle "Buy Veggie Mix" button click
        } else if (source == view.getFOOD_INVEN_VIEW().getBuySeeds()) {
            // Handle "Buy Seeds" button click
        } else if (source == view.getFOOD_INVEN_VIEW().getBuyWater()) {
            // Handle "Buy Water" button click
        } else if (source == view.getFOOD_INVEN_VIEW().getFeedWithFoodForAll()) {
            // Handle "Feed with Food for All" button click
        } else if (source == view.getFOOD_INVEN_VIEW().getFeedWithRainbowTreat()) {
            // Handle "Feed with Rainbow Treat" button click
        } else if (source == view.getFOOD_INVEN_VIEW().getFeedWithKibble()) {
            // Handle "Feed with Kibble" button click
        } else if (source == view.getFOOD_INVEN_VIEW().getFeedWithCannedFood()) {
            // Handle "Feed with Canned Food" button click
        } else if (source == view.getFOOD_INVEN_VIEW().getFeedWithVeggieMix()) {
            // Handle "Feed with Veggie Mix" button click
        } else if (source == view.getFOOD_INVEN_VIEW().getFeedWithSeeds()) {
            // Handle "Feed with Seeds" button click
        } else if (source == view.getFOOD_INVEN_VIEW().getDrinkWater()) {
            // Handle "Drink Water" button click
        } else if (source == view.getFOOD_INVEN_VIEW().getGoBackBtn()) {
            // Handle "Go Back" button click
        } else if (source == view.getADOPTION_VIEW().getNoBtn()) {
            // Handle "No" button click
        } else if (source == view.getADOPTION_VIEW().getYesBtn()) {
            // Handle "Yes" button click
        } else if (source == view.getINTERACTION_VIEW().getPatBtn()) {
            // Handle "Pat" button click
        } else if (source == view.getINTERACTION_VIEW().getPlayBtn()) {
            // Handle "Play" button click
        } else if (source == view.getINTERACTION_VIEW().getPottyBtn()) {
            // Handle "Potty" button click
        } else if (source == view.getINTERACTION_VIEW().getBowBtn()) {
            // Handle "Bow" button click
        } else if (source == view.getINTERACTION_VIEW().getHandshakeBtn()) {
            // Handle "Handshake" button click
        } else if (source == view.getINTERACTION_VIEW().getSpinBtn()) {
            // Handle "Spin" button click
        } else if (source == view.getINTERACTION_VIEW().getPlayDeadBtn()) {
            // Handle "Play Dead" button click
        } else if (source == view.getINTERACTION_VIEW().getFetchBtn()) {
            // Handle "Fetch" button click
        } else if (source == view.getINTERACTION_VIEW().getGoBackBtn()) {
            // Handle "Go Back" button click
        } else if (source == view.getLOGIN_VIEW().getBackBtn()){
        } else if (source == view.getLOGIN_VIEW().getContinueBtn()){
        } else if (source == view.getSTARTGAME_VIEW().getStartButton()) {
            // Handle start button click
        } else if (source == view.getSTARTGAME_VIEW().getContinueButton()) {
            // Handle continue button click
        } else if (source == view.getSTARTGAME_VIEW().getExitButton()) {
            // Handle exit button click
        } else if (source == view.getFOSTER_VIEW().getDogBtn()) {
            // Handle "Dog" button click
        } else if (source == view.getFOSTER_VIEW().getCatBtn()) {
            // Handle "Cat" button click
        } else if (source == view.getFOSTER_VIEW().getRatBtn()) {
            // Handle "Rat" button click
        } else if (source == view.getFOSTER_VIEW().getParrotBtn()) {
            // Handle "Parrot" button click
        } else if (source == view.getFOSTER_VIEW().getChickenBtn()) {
            // Handle "Chicken" button click
        } else if (source == view.getFOSTER_VIEW().getContinueBtn()) {
            // Handle "Continue" button click
        } else if (source == view.getFOSTER_VIEW().getCancelBtn()) {
            // Handle "Cancel" button click
        } else if (source == view.getPET_FOSTER_VIEW().getFeedBtn()) {
            // Handle "Feed" button click
        } else if (source == view.getPET_FOSTER_VIEW().getInteractBtn()) {
            // Handle "Interact" button click
        } else if (source == view.getPET_FOSTER_VIEW().getBatheBtn()) {
            // Handle "Bathe" button click
        } else if (source == view.getPET_FOSTER_VIEW().getPauseBtn()) {
            // Handle "Esc" button click
        }

    }

}
