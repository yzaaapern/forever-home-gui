/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author annga
 */

// Play 'is an' interaction
public class Play extends Interaction 
{   
    // Constructor
    public Play(String playName, int levelUnlocked, String interactionDesc)
    {
        super(playName, levelUnlocked, "Play", interactionDesc);
    }
}
