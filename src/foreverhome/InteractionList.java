/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author annga
 */
public class InteractionList 
{
    // Instance variables & constants
    public static final int NUM_OF_INTERACTIONS = 8; 
    private Interaction givePat;
    private Interaction playWithFoster;
    private Interaction goPotty;
    private Interaction bow;
    private Interaction shakeHands;
    private Interaction spin;
    private Interaction playDead;
    private Interaction fetch;
    
    // array
    Interaction[] interactions = new Interaction[NUM_OF_INTERACTIONS];
    
    // instanciating the interaction list
    public InteractionList()
    {
        this.givePat = new Trick("givePat", 0, "will receive a pat :)");
        this.interactions[0] = givePat;
        
        this.playWithFoster = new Play("playWithFoster", 0, "goes to play!");
        this.interactions[1] = playWithFoster;
        
        this.goPotty = new Health("goPotty", 5, "goes for potty time!");
        this.interactions[2] = goPotty;
        
        this.bow = new Trick("bow", 6, "will bow :)");
        this.interactions[3] = bow;
        
        this.shakeHands = new Trick("shakeHands", 7, "will shake your hand :o");
        this.interactions[4] = shakeHands;
        
        this.spin = new Trick("spin", 8, "will spin beautifully :)");
        this.interactions[5] = spin;
        
        this.playDead = new Trick("playDead", 9, "will play dead :O");
        this.interactions[6] = playDead;
        
        this.fetch = new Play("fetch", 10, "will go play fetch with you!");
        this.interactions[7] = fetch;
        
    }
    
    // Interaction get method
    public Interaction[] getInteractions()
    {
        return this.interactions;
    }
    
    
    public Interaction getGivePat() {
        return givePat;
    }

    public Interaction getPlayWithFoster() {
        return playWithFoster;
    }

    public Interaction getGoPotty() {
        return goPotty;
    }

    public Interaction getBow() {
        return bow;
    }

    public Interaction getShakeHands() {
        return shakeHands;
    }

    public Interaction getSpin() {
        return spin;
    }

    public Interaction getPlayDead() {
        return playDead;
    }

    public Interaction getFetch() {
        return fetch;
    }
}
