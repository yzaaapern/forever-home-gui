
package foreverhome;

/**
 *
 * @author annga 
 * Name: Ann Del Rosario 
 * Student ID: 21143100
 */
public class DecrementStatsRunnable implements Runnable {

    /*
        INSTANCE AND CONSTANT VARIABLES
     */
    public final int TIME_INTERVAL = 50000; // time interval between stat decrements is 50 seconds = 50000ms

    public Player player;

    /*
        OBJECT CONSTRUCTORS
     */ 
    public DecrementStatsRunnable(Player player) {
        this.player = player;
    }

    /*
        METHODS
     */
    
    /* 
        run Method
    
        Parameters: None
        Return: None
        Description: While the player has a foster pet, the stats (happiness, hunger, and hygiene) and level xp will decrease over time. 
        If the foster pet's stats are all equal to 0, the pet will lose some xp because it is not being taken care of.
        Otherwise, if the foster pet has even some stats greater than 0, these stats will decrease overtime to simulate a real pet.
     */
    @Override
    public void run() {
        // while the Game class' startThreads variable is set to true
        while (Game.startThreads) {
            // while the player has a foster pet, the player's foster pet's happiness, hunger, and hygience will decrease over time
            while (this.player.hasFosterPet == true) {

                try {
                    Thread.sleep(TIME_INTERVAL); // thread will sleep for the allocated time interval
                } catch (InterruptedException e) {
                    System.err.println(e);
                } finally {
                    if (!Game.startThreads) {
                        break; // the thread gracefully exits after the startThreads variable is set to false
                    }
                    // pet is being neglected, so the level xp will decrease and alert message will be displayed
                    if (this.player.getFosterPet().getHappiness() == 0 && this.player.getFosterPet().getHunger() == 0 && this.player.getFosterPet().getHygiene() == 0) {

                        this.player.getFosterPet().decLevelXP();
                        this.alertMessage();
                    } // pet is not being neglected, stats will decrease because it will still get less happy/hungry/dirty.
                    else {
//                        System.out.println("\nDECREASING PET STATS!\n"); // alert message used for testing
                        this.player.getFosterPet().decHappiness();
                        this.player.getFosterPet().decHunger();
                        this.player.getFosterPet().decHygiene();
                    }
                }

            }
        }
    }

    /* 
        alertMessage Method
    
        Parameters: None
        Return: None
        Description: Prints a warning message.
     */
    public void alertMessage() {
        System.out.println("\n##############################");
        System.out.println("ALERT! ALERT!\nYour pet needs care!");
        System.out.println("##############################");

    }
    
}