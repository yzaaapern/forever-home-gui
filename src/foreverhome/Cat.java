/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author yzape
 * Name: Yza Pernia
 * Student ID: 21137984
 */
// Cat is an animal
public class Cat extends Animal{
    
    /*
        OBJECT CONSTRUCTORS
    */
    
    public Cat(String name){
        super(name);
        this.setAnimalFoodType(1);
    }
    
    // Used to generate Cat objects based on the foster pet info retrieved from usersAndPets_data text file.
    public Cat(String name, int happiness, int hunger, int hygiene, int levelXP, int levelXPBar, int level){
        super(name, happiness, hunger, hygiene, levelXP, levelXPBar, level);
        this.setAnimalFoodType(1);
    }
}
