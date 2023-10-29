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
 */
public class ForeverHomeController implements ActionListener{
    private ForeverHomeModel model;
    private ForeverHomeView view;
    
    public ForeverHomeController(ForeverHomeModel model, ForeverHomeView view){
        this.view = view;
        this.model = model;
        this.view.addActionListeners(this);
    }
    
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
    
    private void simulateLoading(){
        try{
            Thread.sleep(14000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource(); // Get source of the event

        // START GAME 
        if (source == view.getSTARTGAME_VIEW().getStartButton()) {
            if (view.getLOGIN_VIEW().isLogin) {
                view.getLOGIN_VIEW().updateIsLogin();
            }
            if (view.getLOGIN_VIEW().userExists) {
                view.getLOGIN_VIEW().updateUserExists();
                view.getLOGIN_VIEW().updateText();
            }
            view.showLoginPanel();

        } else if (source == view.getSTARTGAME_VIEW().getContinueButton()) {
            if (!view.getLOGIN_VIEW().isLogin) {
                view.getLOGIN_VIEW().updateIsLogin();
            }
            if (!view.getLOGIN_VIEW().userExists) {
                view.getLOGIN_VIEW().updateUserExists();
                view.getLOGIN_VIEW().updateText();
            }
            view.showLoginPanel();
        } else if (source == view.getSTARTGAME_VIEW().getExitButton()) {
            model.quitGame();
            view.getLOADING_VIEW().isShuttingDown = true;
            view.showLoadingPanel();
//            System.exit(0);
            // Handle exit game (close views)
        }
        
        // LOGIN VIEW
        
        else if (source == view.getLOGIN_VIEW().getBackBtn()) {
            view.showStartGamePanel();
            view.getLOGIN_VIEW().updateText();
            if (!view.getLOGIN_VIEW().isPasswordCorrect) {
                view.getLOGIN_VIEW().updateIsPasswordCorrect();
            }

        } else if (source == view.getLOGIN_VIEW().getContinueBtn()) {
            boolean validUsernameAndPassword = false;
            boolean userExists = false;
            boolean playerIsPlaying = false;
            boolean passwordCorrect = false;
            
            if (!model.isValid(view.getLOGIN_VIEW().getUsername()) || !model.isValid(view.getLOGIN_VIEW().getPassword())) // if username or password is invalid
            {
                view.getLOGIN_VIEW().updateText("PLEASE FILL UP THE FORM!", "You cannot submit empty inputs.");
            } 
            else // if username and password is valid
            {
                validUsernameAndPassword = true;
                
                if(view.getLOGIN_VIEW().isLogin) // if LOGIN MENU
                {
                    // check if user exists
                    userExists = model.isExistingUser(view.getLOGIN_VIEW().getUsername());
                    
                    // if user exists 
                    if (userExists) {
                        passwordCorrect = model.authenticateUser(view.getLOGIN_VIEW().getUsername(), view.getLOGIN_VIEW().getPassword()); // check password                        
                        if (!view.getLOGIN_VIEW().userExists) {
                            view.getLOGIN_VIEW().updateUserExists();
                        }

                        if (passwordCorrect)// if password correct
                        {
                            model.setPlayer(view.getLOGIN_VIEW().getUsername());
                            model.username = view.getLOGIN_VIEW().getUsername();
                            playerIsPlaying = true;
                        } else {// if password incorrect
                            if (view.getLOGIN_VIEW().isPasswordCorrect) {
                                view.getLOGIN_VIEW().updateIsPasswordCorrect();
                            }
                        }
                    }
                    else// if user does not exist
                    // - message: user does not exist, please create new user
                    {
                        if (view.getLOGIN_VIEW().userExists) 
                        {
                            view.getLOGIN_VIEW().updateUserExists();
                            System.out.println(view.getLOGIN_VIEW().userExists);
                        }
                    }
                }
                else // if SIGNUP MENU
                {
                    // check if user exists
                    userExists = model.isExistingUser(view.getLOGIN_VIEW().getUsername());
                    
                    // if user exists 
                    if(userExists)
                    {
                        view.getLOGIN_VIEW().updateUserExists();
                        view.getLOGIN_VIEW().updateText();                  
                    }
                    else// if user does not exist
                    // - message: user does not exist, please create new user
                    {
                        model.newUser(view.getLOGIN_VIEW().getUsername(), view.getLOGIN_VIEW().getPassword());
                        model.setPlayer(view.getLOGIN_VIEW().getUsername());
                        userExists = true;
                        playerIsPlaying = true;
                    }
                }
            }
            
            // check if player has a foster pet

            if(playerIsPlaying)
            {
                // if the player does not have a fosterpet
                if(model.player.hasFosterPet == true)
                {
                    this.model.setPet();

                    // show pet foster menu
                    view.showPetFosterPanel();
                    
                }
                else // if the player has a fosterpet
                {
                    view.showFosterPanel();
                }
            }
        } 
        
        // FOSTER VIEW
        
        else if (source == view.getFOSTER_VIEW().getDogBtn()) {
            model.chosenAnimalType = "dog";
            view.getFOSTER_VIEW().updatePopUpLabel("You chose a " + model.chosenAnimalType + ". Would you like to give it a name?");
            System.out.println("dog selected");
            if (!view.getFOSTER_VIEW().showPopUpPanel) {
                view.getFOSTER_VIEW().updateShowPopUpPanel();
            }
        } else if (source == view.getFOSTER_VIEW().getCatBtn()) {
            model.chosenAnimalType = "cat";
            view.getFOSTER_VIEW().updatePopUpLabel("You chose a " + model.chosenAnimalType + ". Would you like to give it a name?");
            if (!view.getFOSTER_VIEW().showPopUpPanel) {
                view.getFOSTER_VIEW().updateShowPopUpPanel();
            }
        } else if (source == view.getFOSTER_VIEW().getRatBtn()) {
            model.chosenAnimalType = "rat";
            view.getFOSTER_VIEW().updatePopUpLabel("You chose a " + model.chosenAnimalType + ". Would you like to give it a name?");
            if (!view.getFOSTER_VIEW().showPopUpPanel) {
                view.getFOSTER_VIEW().updateShowPopUpPanel();
            }
        } else if (source == view.getFOSTER_VIEW().getParrotBtn()) {
            model.chosenAnimalType = "parrot";
            view.getFOSTER_VIEW().updatePopUpLabel("You chose a " + model.chosenAnimalType + ". Would you like to give it a name?");
            if (!view.getFOSTER_VIEW().showPopUpPanel) {
                view.getFOSTER_VIEW().updateShowPopUpPanel();
            }
        } else if (source == view.getFOSTER_VIEW().getChickenBtn()) {
            model.chosenAnimalType = "chicken";
            view.getFOSTER_VIEW().updatePopUpLabel("You chose a " + model.chosenAnimalType + ". Would you like to give it a name?");
            if (!view.getFOSTER_VIEW().showPopUpPanel) {
                view.getFOSTER_VIEW().updateShowPopUpPanel();
            }
        } else if (source == view.getFOSTER_VIEW().getContinueBtn()) {
            String petName = view.getFOSTER_VIEW().getPetName();
            if(model.chosenAnimalType == null)
            {
                System.out.println("No animal selected - please choose an animal");
            }
            else
            {
                if (!petName.isEmpty()) {
                    model.newPetFoster(petName);
                    view.showPetFosterPanel();
                } else {
                    System.out.println("no name");
                    view.getFOSTER_VIEW().updatePopUpLabel("Please give your " + model.chosenAnimalType + " a name!");
                }
            }
        } else if (source == view.getFOSTER_VIEW().getCancelBtn()) {
            view.getFOSTER_VIEW().updateShowPopUpPanel();
            System.out.println(view.getFOSTER_VIEW().showPopUpPanel);
        } 

        // PET FOSTER VIEW
        
        else if (source == view.getPET_FOSTER_VIEW().getBackpackBtn()) {
            view.showFoodInventoryPanel();
        }
        
        else if (source == view.getPET_FOSTER_VIEW().getFeedBtn()) 
        {
            view.showFoodInventoryPanel();
        } else if (source == view.getPET_FOSTER_VIEW().getInteractBtn()) {
            view.getINTERACTION_VIEW().refreshText();
            view.showInteractionPanel();
        } else if (source == view.getPET_FOSTER_VIEW().getBatheBtn()) {
            model.bathePet();
        } else if (source == view.getPET_FOSTER_VIEW().getPauseBtn()) {
            // pause game
            view.getPET_FOSTER_VIEW().updateIsPaused();
            model.saveGame();
            view.getPET_FOSTER_VIEW().isPaused = true;
            view.showPetFosterPanel();
        } else if (source == view.getPET_FOSTER_VIEW().getGoBackBtn()) {
            // model: save player's stats
            view.getPET_FOSTER_VIEW().updateIsPaused();

        } else if (source == view.getPET_FOSTER_VIEW().getQuitBtn()) {
            // model: save player's stats
            model.quitGame();
            // view: close
        } else if (source == view.getPET_FOSTER_VIEW().getYesBtn()) {
            view.showAdoptionPanel();
            model.player.fosterPet.setIsAdopted(true);
            model.player.hasFosterPet = false;
            model.player.fosterPet = null;
        } else if (source == view.getPET_FOSTER_VIEW().getNoBtn()) {
            view.showFosterPanel();
        }
        
        // INTERACTION VIEW
        
        else if (source == view.getINTERACTION_VIEW().getPatBtn()) {
            model.interactWithPet(model.player.getInteractionList().getGivePat());   
            view.showPetFosterPanel();
        } else if (source == view.getINTERACTION_VIEW().getPlayBtn()) {
            model.interactWithPet(model.player.getInteractionList().getPlayWithFoster());
            view.showPetFosterPanel();
        } else if (source == view.getINTERACTION_VIEW().getPottyBtn()) {
            model.interactWithPet(model.player.getInteractionList().getGoPotty());
            if(model.isInteractUnlocked(model.player.getInteractionList().getGoPotty()))
            {
                view.showPetFosterPanel();
            }
            else
            {
                view.getINTERACTION_VIEW().isInteractionUnlocked = false;
                view.getINTERACTION_VIEW().updateText(model.player.getInteractionList().getGoPotty());
            }
        } else if (source == view.getINTERACTION_VIEW().getBowBtn()) {
            model.interactWithPet(model.player.getInteractionList().getBow());
                        if(model.isInteractUnlocked(model.player.getInteractionList().getBow()))
            {
                view.showPetFosterPanel();
            }
            else
            {
                view.getINTERACTION_VIEW().isInteractionUnlocked = false;
                view.getINTERACTION_VIEW().updateText(model.player.getInteractionList().getBow());
            }
        } else if (source == view.getINTERACTION_VIEW().getHandshakeBtn()) {
            model.interactWithPet(model.player.getInteractionList().getShakeHands());
                        if(model.isInteractUnlocked(model.player.getInteractionList().getShakeHands()))
            {
                view.showPetFosterPanel();
            }
            else
            {
                view.getINTERACTION_VIEW().isInteractionUnlocked = false;
                view.getINTERACTION_VIEW().updateText(model.player.getInteractionList().getShakeHands());
            }
        } else if (source == view.getINTERACTION_VIEW().getSpinBtn()) {
            model.interactWithPet(model.player.getInteractionList().getSpin());
                        if(model.isInteractUnlocked(model.player.getInteractionList().getSpin()))
            {
                view.showPetFosterPanel();
            }
            else
            {
                view.getINTERACTION_VIEW().isInteractionUnlocked = false;
                view.getINTERACTION_VIEW().updateText(model.player.getInteractionList().getSpin());
            }
        } else if (source == view.getINTERACTION_VIEW().getPlayDeadBtn()) {
            model.interactWithPet(model.player.getInteractionList().getPlayDead());
                        if(model.isInteractUnlocked(model.player.getInteractionList().getPlayDead()))
            {
                view.showPetFosterPanel();
            }
            else
            {
                view.getINTERACTION_VIEW().isInteractionUnlocked = false;
                view.getINTERACTION_VIEW().updateText(model.player.getInteractionList().getPlayDead());
            }
        } else if (source == view.getINTERACTION_VIEW().getFetchBtn()) {
            model.interactWithPet(model.player.getInteractionList().getFetch());
                        if(model.isInteractUnlocked(model.player.getInteractionList().getFetch()))
            {
                view.showPetFosterPanel();
            }
            else
            {
                view.getINTERACTION_VIEW().isInteractionUnlocked = false;
                view.getINTERACTION_VIEW().updateText(model.player.getInteractionList().getFetch());
            }
        } else if (source == view.getINTERACTION_VIEW().getGoBackBtn()) {
            view.showPetFosterPanel();
        } 
        
        // FOOD INVENTORY
        
        else if (source == view.getFOOD_INVEN_VIEW().getBuyFoodForAll()) 
        {
            model.buyFood(model.player.getFoodInventory().foodForAll);
            view.showPetFosterPanel();
        } else if (source == view.getFOOD_INVEN_VIEW().getBuyRainbowTreat()) {
            model.buyFood(model.player.getFoodInventory().rainbowTreat);
            view.showPetFosterPanel();
        } else if (source == view.getFOOD_INVEN_VIEW().getBuyKibble()) {
            model.buyFood(model.player.getFoodInventory().kibble);
            view.showPetFosterPanel();
        } else if (source == view.getFOOD_INVEN_VIEW().getBuyCannedFood()) {
            model.buyFood(model.player.getFoodInventory().cannedFood);
            view.showPetFosterPanel();
        } else if (source == view.getFOOD_INVEN_VIEW().getBuyVeggieMix()) {
            model.buyFood(model.player.getFoodInventory().veggieMix);
            view.showPetFosterPanel();
        } else if (source == view.getFOOD_INVEN_VIEW().getBuySeeds()) {
            model.buyFood(model.player.getFoodInventory().seeds);
            view.showPetFosterPanel();
        } else if (source == view.getFOOD_INVEN_VIEW().getBuyWater()) {
            model.buyFood(model.player.getFoodInventory().water);
            view.showPetFosterPanel();
        } 
        
        else if (source == view.getFOOD_INVEN_VIEW().getFeedWithFoodForAll()) {
            model.feedPet(model.player.getFoodInventory().foodForAll);
            view.showPetFosterPanel();
        } else if (source == view.getFOOD_INVEN_VIEW().getFeedWithRainbowTreat()) {
            model.feedPet(model.player.getFoodInventory().rainbowTreat);
            view.showPetFosterPanel();
        } else if (source == view.getFOOD_INVEN_VIEW().getFeedWithKibble()) {
            model.feedPet(model.player.getFoodInventory().kibble);
            view.showPetFosterPanel();
        } else if (source == view.getFOOD_INVEN_VIEW().getFeedWithCannedFood()) {
            model.feedPet(model.player.getFoodInventory().cannedFood);
            view.showPetFosterPanel();
        } else if (source == view.getFOOD_INVEN_VIEW().getFeedWithVeggieMix()) {
            model.feedPet(model.player.getFoodInventory().veggieMix);
            view.showPetFosterPanel();
        } else if (source == view.getFOOD_INVEN_VIEW().getFeedWithSeeds()) {
            model.feedPet(model.player.getFoodInventory().seeds);
            view.showPetFosterPanel();
        } else if (source == view.getFOOD_INVEN_VIEW().getDrinkWater()) {
            model.feedPet(model.player.getFoodInventory().water);
            view.showPetFosterPanel();
        } 
        else if (source == view.getFOOD_INVEN_VIEW().getGoBackBtn()) {
            view.showPetFosterPanel();
        } 
        
        // ADOPTION VIEW
        
        else if (source == view.getADOPTION_VIEW().getNoBtn()) 
        {
            model.saveGame();
            view.showStartGamePanel();
            
            
        } 
        else if (source == view.getADOPTION_VIEW().getYesBtn()) 
        {
            view.showFosterPanel();
        } 

//        model.db.getDBManager().closeConnection();
    }
}
