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
public class ForeverHomeController implements ActionListener {

    private ForeverHomeModel model;
    private ForeverHomeView view;

    public ForeverHomeController(ForeverHomeModel model, ForeverHomeView view) {
        this.view = view;
        this.model = model;
        this.view.addActionListeners(this);
    }

    public void start() {
//        view.display();
//        simulateLoading();
        view.showStartGamePanel();
//        view.showLoginPanel();
//        view.showFosterPanel();
//        view.showNotPausedPetFosterPanel();
//        view.showFoodInventoryPanel();
//        view.showInteractionPanel();
//        view.showAdoptionPanel();
    }

    private void simulateLoading() {
        try {
            Thread.sleep(14000);
        } catch (InterruptedException e) {
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
            if(!view.getLOGIN_VIEW().userExists){
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
            if(view.getLOGIN_VIEW().userExists){
                view.getLOGIN_VIEW().updateText();
            }
            view.showLoginPanel();
        } else if (source == view.getSTARTGAME_VIEW().getExitButton()) {
            this.handleExitGameAction();
            
        } // LOGIN VIEW
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
            } else // if username and password is valid
            {
                validUsernameAndPassword = true;

                if (view.getLOGIN_VIEW().isLogin) // if LOGIN MENU
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
                            view.getBUYFOOD_VIEW().updateFoodCounts(model.player.getFoodInventory());
                            view.getFEEDPET_VIEW().updateFoodCounts(model.player.getFoodInventory());
                        } else {// if password incorrect
                            if (view.getLOGIN_VIEW().isPasswordCorrect) {
                                view.getLOGIN_VIEW().updateIsPasswordCorrect();
                            }
                        }
                    } else// if user does not exist
                    // - message: user does not exist, please create new user
                    {
                        if (view.getLOGIN_VIEW().userExists) {
                            view.getLOGIN_VIEW().updateUserExists();
                            System.out.println(view.getLOGIN_VIEW().userExists);
                        }
                    }
                } else // if SIGNUP MENU
                {
                    // check if user exists
                    userExists = model.isExistingUser(view.getLOGIN_VIEW().getUsername());

                    // if user exists 
                    if (userExists) {
                        view.getLOGIN_VIEW().updateUserExists();
                        view.getLOGIN_VIEW().updateText();
                    } else// if user does not exist
                    // - message: user does not exist, please create new user
                    {
                        model.newUser(view.getLOGIN_VIEW().getUsername(), view.getLOGIN_VIEW().getPassword());
                        model.setPlayer(view.getLOGIN_VIEW().getUsername());
                        userExists = true;
                        playerIsPlaying = true;
                        view.getBUYFOOD_VIEW().updateFoodCounts(model.player.getFoodInventory());
                        view.getFEEDPET_VIEW().updateFoodCounts(model.player.getFoodInventory());
                    }
                }
            }

            // check if player has a foster pet
            if (playerIsPlaying) {
                // if the player has a fosterpet
                if (model.player.hasFosterPet == true) {
                    this.model.setPet();

                    Animal a = model.getPet();
                    view.getNOT_PAUSED_PET_FOSTER_VIEW().setAnimalSprite(a);
//                    view.showNotPausedPetFosterPanel();
                    // show pet foster menu
                    view.getNOT_PAUSED_PET_FOSTER_VIEW().updateBarValue(model.player.fosterPet.getLevel(), model.player.fosterPet.getLevelXP(), model.player.fosterPet.getHappiness(), model.player.fosterPet.getHunger(), model.player.fosterPet.getHygiene(), model.player.getDabloons(), model.player.getFosterPet().getLevelXPBar());
                    view.showNotPausedPetFosterPanel();

                } else // if the player does not have a fosterpet
                {
                    view.showFosterPanel();
                }
            }
        } // FOSTER VIEW
        else if (source == view.getFOSTER_VIEW().getDogBtn()) {
            String chosenAnimalType = "dog";
            this.selectFosterPetAction(chosenAnimalType);
        } else if (source == view.getFOSTER_VIEW().getCatBtn()) {
            String chosenAnimalType = "cat";
            this.selectFosterPetAction(chosenAnimalType);   
        } else if (source == view.getFOSTER_VIEW().getRatBtn()) {
            String chosenAnimalType = "rat";
            this.selectFosterPetAction(chosenAnimalType);
        } else if (source == view.getFOSTER_VIEW().getParrotBtn()) {
            String chosenAnimalType = "parrot";
            this.selectFosterPetAction(chosenAnimalType);
        } else if (source == view.getFOSTER_VIEW().getChickenBtn()) {
            String chosenAnimalType = "chicken";
            this.selectFosterPetAction(chosenAnimalType);
        } else if (source == view.getFOSTER_VIEW().getContinueBtn()) {
            String petName = view.getFOSTER_VIEW().getPetName();
            if (model.chosenAnimalType == null) {
                System.out.println("No animal selected - please choose an animal");
            } else {
                if (!petName.isEmpty()) {
                    model.newPetFoster(petName);
                    Animal a = model.getPet();
                    view.getNOT_PAUSED_PET_FOSTER_VIEW().setAnimalSprite(a);
                    view.showNotPausedPetFosterPanel();
                } else {
                    System.out.println("no name");
                    view.getFOSTER_VIEW().updatePopUpLabel("Please give your " + model.chosenAnimalType + " a name!");
                }
            }
        } else if(source == view.getFOSTER_VIEW().getMainMenuBtn())
        {
            this.handleFosterMainMenuAction();
        } else if (source == view.getFOSTER_VIEW().getCancelBtn()) {             
            view.getFOSTER_VIEW().updateShowPopUpPanel(); 
        } 

        // NOT PAUSED PET FOSTER VIEW
        else if (source == view.getNOT_PAUSED_PET_FOSTER_VIEW().getBackpackBtn()) {
            view.showBuyFoodPanel();
        } else if (source == view.getNOT_PAUSED_PET_FOSTER_VIEW().getFeedBtn()) {
            view.showFeedPetPanel();
        } else if (source == view.getNOT_PAUSED_PET_FOSTER_VIEW().getInteractBtn()) {
            view.getINTERACTION_VIEW().refreshText();
            view.showInteractionPanel();
        } else if (source == view.getNOT_PAUSED_PET_FOSTER_VIEW().getBatheBtn()) {
            this.handleBathePetAction();
        } else if (source == view.getNOT_PAUSED_PET_FOSTER_VIEW().getPauseBtn()) {
            this.handlePauseGameAction();
        }
        
        // PAUSED PET FOSTER VIEW
        
        else if (source == view.getPAUSED_PET_FOSTER_VIEW().getAdoptionBtn()) {
            Animal a = model.getPet();
            view.getADOPTION_VIEW().updateAdoptionView(a.getName(), a);
            view.showAdoptionPanel();
        }
        else if (source == view.getPAUSED_PET_FOSTER_VIEW().getGoBackBtn()) {
            view.getNOT_PAUSED_PET_FOSTER_VIEW().updateBarValue(model.player.fosterPet.getLevel(), model.player.fosterPet.getLevelXP(), model.player.fosterPet.getHappiness(), model.player.fosterPet.getHunger(), model.player.fosterPet.getHygiene(), model.player.getDabloons(), model.player.getFosterPet().getLevelXPBar());
            view.showNotPausedPetFosterPanel();
        } else if (source == view.getPAUSED_PET_FOSTER_VIEW().getMainMenuBtn())
        {
            this.handleFosterMainMenuAction();
            // may not need view.getFOSTER_VIEW().updateShowPopUpPanel();
        }
        else if (source == view.getPAUSED_PET_FOSTER_VIEW().getQuitBtn()) {
            this.handleExitGameAction();
            
        } // INTERACTION VIEW
        else if (source == view.getINTERACTION_VIEW().getPatBtn()) {
            Interaction chosenInteraction = model.player.getInteractionList().getGivePat();
            this.interactWithPetAction(chosenInteraction);
        } else if (source == view.getINTERACTION_VIEW().getPlayBtn()) {
            Interaction chosenInteraction = model.player.getInteractionList().getPlayWithFoster();
            this.interactWithPetAction(chosenInteraction);
        } else if (source == view.getINTERACTION_VIEW().getPottyBtn()) {
            Interaction chosenInteraction = model.player.getInteractionList().getGoPotty();
            this.interactWithPetAction(chosenInteraction);
        } else if (source == view.getINTERACTION_VIEW().getBowBtn()) {
            Interaction chosenInteraction = model.player.getInteractionList().getBow();
            this.interactWithPetAction(chosenInteraction);
        } else if (source == view.getINTERACTION_VIEW().getHandshakeBtn()) {
            Interaction chosenInteraction = model.player.getInteractionList().getShakeHands();
            this.interactWithPetAction(chosenInteraction);
        } else if (source == view.getINTERACTION_VIEW().getSpinBtn()) {
            Interaction chosenInteraction = model.player.getInteractionList().getSpin();
            this.interactWithPetAction(chosenInteraction);
        } else if (source == view.getINTERACTION_VIEW().getPlayDeadBtn()) {
            Interaction chosenInteraction = model.player.getInteractionList().getPlayDead();
            this.interactWithPetAction(chosenInteraction);
        } else if (source == view.getINTERACTION_VIEW().getFetchBtn()) {
            Interaction chosenInteraction = model.player.getInteractionList().getFetch();
            this.interactWithPetAction(chosenInteraction);
        } else if (source == view.getINTERACTION_VIEW().getGoBackBtn()) {
            view.showNotPausedPetFosterPanel();
        } 
        
        // BUY FOOD VIEW
        
        else if (source == view.getBUYFOOD_VIEW().getBuyFoodForAll()) {
            Food chosenFood = model.player.getFoodInventory().foodForAll;
            this.buyFoodAction(chosenFood);
        } else if (source == view.getBUYFOOD_VIEW().getBuyRainbowTreat()) {
            Food chosenFood = model.player.getFoodInventory().rainbowTreat;
            this.buyFoodAction(chosenFood);
        } else if (source == view.getBUYFOOD_VIEW().getBuyKibble()) {
            Food chosenFood = model.player.getFoodInventory().kibble;
            this.buyFoodAction(chosenFood);
        } else if (source == view.getBUYFOOD_VIEW().getBuyCannedFood()) {
            Food chosenFood = model.player.getFoodInventory().cannedFood;
            this.buyFoodAction(chosenFood);
        } else if (source == view.getBUYFOOD_VIEW().getBuyVeggieMix()) {
            Food chosenFood = model.player.getFoodInventory().veggieMix;
            this.buyFoodAction(chosenFood);
        } else if (source == view.getBUYFOOD_VIEW().getBuySeeds()) {
            Food chosenFood = model.player.getFoodInventory().seeds;
            this.buyFoodAction(chosenFood);
        } else if (source == view.getBUYFOOD_VIEW().getBuyWater()) {
            Food chosenFood = model.player.getFoodInventory().water;
            this.buyFoodAction(chosenFood);
        } else if(source == view.getBUYFOOD_VIEW().getGoBackBtn()) {
            view.showNotPausedPetFosterPanel();
        }
        
        // FEED PET VIEW
        else if (source == view.getFEEDPET_VIEW().getFeedWithFoodForAll()) {
            Food chosenFood = model.player.getFoodInventory().foodForAll;
            this.feedPetAction(chosenFood);
        } else if (source == view.getFEEDPET_VIEW().getFeedWithRainbowTreat()) {
            Food chosenFood = model.player.getFoodInventory().rainbowTreat;
            this.feedPetAction(chosenFood);
        } else if (source == view.getFEEDPET_VIEW().getFeedWithKibble()) {
            Food chosenFood = model.player.getFoodInventory().kibble;
            this.feedPetAction(chosenFood);
        } else if (source == view.getFEEDPET_VIEW().getFeedWithCannedFood()) {
            Food chosenFood = model.player.getFoodInventory().cannedFood;
            this.feedPetAction(chosenFood);
        } else if (source == view.getFEEDPET_VIEW().getFeedWithVeggieMix()) {
            Food chosenFood = model.player.getFoodInventory().veggieMix;
            this.feedPetAction(chosenFood);
        } else if (source == view.getFEEDPET_VIEW().getFeedWithSeeds()) {
            Food chosenFood = model.player.getFoodInventory().seeds;
            this.feedPetAction(chosenFood);
        } else if (source == view.getFEEDPET_VIEW().getDrinkWater()) {
            Food chosenFood = model.player.getFoodInventory().water;
            this.feedPetAction(chosenFood);
        } else if (source == view.getFEEDPET_VIEW().getGoBackBtn()) {
            view.showNotPausedPetFosterPanel();
        } // ADOPTION VIEW
        else if (source == view.getADOPTION_VIEW().getYesBtn()) {
            model.saveGame();
            view.showFosterPanel();
            model.player.fosterPet.setIsAdopted(true);
            model.player.hasFosterPet = false;
            model.player.fosterPet = null;
        } // ADOPTION VIEW
        else if (source == view.getADOPTION_VIEW().getNoBtn()) {
            model.saveGame();
            view.getLOADING_VIEW().updateIsShuttingDown();
            view.showLoadingPanel();
            model.player.fosterPet.setIsAdopted(true);
            model.player.hasFosterPet = false;
            model.player.fosterPet = null;
        }

    }
    
    private void selectFosterPetAction(String chosenAnimalType)
    {
        model.chosenAnimalType = chosenAnimalType;
        view.getFOSTER_VIEW().updatePopUpLabel("You chose a " + model.chosenAnimalType + ". Would you like to give it a name?");
        if (!view.getFOSTER_VIEW().showPopUpPanel) {
            view.getFOSTER_VIEW().updateShowPopUpPanel();
        }
    }
    
    private void handleFosterMainMenuAction() {
        model.saveGame();
        model.resetGame();
        view.showStartGamePanel();
        view.getFOSTER_VIEW().updateShowPopUpPanel();
    }
    
    private void handlePauseGameAction() {
        model.saveGame();
        view.showPausedPetFosterPanel();
    }
    
    private void handleBathePetAction() {
        model.bathePet();
        view.getNOT_PAUSED_PET_FOSTER_VIEW().checkIsReadyForAdoption();
        view.getNOT_PAUSED_PET_FOSTER_VIEW().getAnimalSprite().toggleIsIdleAnimation();
        view.getNOT_PAUSED_PET_FOSTER_VIEW().updateBarValue(model.player.fosterPet.getLevel(), model.player.fosterPet.getLevelXP(), model.player.fosterPet.getHappiness(), model.player.fosterPet.getHunger(), model.player.fosterPet.getHygiene(), model.player.getDabloons(), model.player.getFosterPet().getLevelXPBar());
    }
    
    private void handleExitGameAction() {
        model.quitGame();
        view.getLOADING_VIEW().updateIsShuttingDown();
        view.showLoadingPanel();
        System.exit(0);
    }
    
    private void interactWithPetAction(Interaction chosenInteraction)
    {
        if(model.isInteractUnlocked(chosenInteraction))
            {
                model.interactWithPet(chosenInteraction);
                view.getNOT_PAUSED_PET_FOSTER_VIEW().updateBarValue(model.player.fosterPet.getLevel(), model.player.fosterPet.getLevelXP(), model.player.fosterPet.getHappiness(), model.player.fosterPet.getHunger(), model.player.fosterPet.getHygiene(), model.player.getDabloons(), model.player.getFosterPet().getLevelXPBar());
                view.showNotPausedPetFosterPanel();
                view.getNOT_PAUSED_PET_FOSTER_VIEW().checkIsReadyForAdoption();
                view.getNOT_PAUSED_PET_FOSTER_VIEW().getAnimalSprite().toggleIsIdleAnimation();
            }
            else
            {
                view.getINTERACTION_VIEW().isInteractionUnlocked = false;
                view.getINTERACTION_VIEW().updateText(chosenInteraction);
            }
    }
    
    private void buyFoodAction(Food chosenFood)
    {
        if(model.canAfford(chosenFood))
            {
                model.buyFood(chosenFood);
                view.getNOT_PAUSED_PET_FOSTER_VIEW().setDabloonsLabel(model.player.getDabloons());
                view.getBUYFOOD_VIEW().updateFoodCount(chosenFood);
            }
            else
            {
                view.getBUYFOOD_VIEW().canAfford = false;
                view.getBUYFOOD_VIEW().updateCanAffordText(chosenFood);
            }
    }
    
    private void feedPetAction(Food chosenFood)
    {
        if(!model.petFull())
        {
            if(model.compatibleFood(chosenFood))
            {
                if(model.sufficientSupply(chosenFood))
                {
                    model.feedPet(chosenFood);
                    view.getNOT_PAUSED_PET_FOSTER_VIEW().updateBarValue(model.player.fosterPet.getLevel(), model.player.fosterPet.getLevelXP(), model.player.fosterPet.getHappiness(), model.player.fosterPet.getHunger(), model.player.fosterPet.getHygiene(), model.player.getDabloons(), model.player.getFosterPet().getLevelXPBar());
                    view.showNotPausedPetFosterPanel();
                    view.getFEEDPET_VIEW().updateFoodCount(model.player.getFoodInventory().foodForAll);
                    view.getNOT_PAUSED_PET_FOSTER_VIEW().checkIsReadyForAdoption();
                    view.getNOT_PAUSED_PET_FOSTER_VIEW().getAnimalSprite().toggleIsIdleAnimation();
                }
                else
                {
                    view.getFEEDPET_VIEW().sufficientSupply = false;
                    view.getFEEDPET_VIEW().updateSufficientSupplyText(chosenFood);
                }
            }
            else
            {
                view.getFEEDPET_VIEW().compatibleFood = false;
                view.getFEEDPET_VIEW().updateCompatibleFoodText(chosenFood);
            }
        }
        else
        {
            view.getFEEDPET_VIEW().petFull = true;
            view.getFEEDPET_VIEW().updatePetFullText();
        }
    }
}
