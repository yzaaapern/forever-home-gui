/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author annga
 */

// Trick 'is an' interaction
public class Trick extends Interaction 
{
    // constructor
    public Trick(String trickName, int levelUnlocked, String interactionDesc)
    {
        super(trickName, levelUnlocked, "Trick", interactionDesc);
    }
}
