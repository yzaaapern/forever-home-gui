/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.util.Objects;

/**
 *
 * @author AnnGa
 */
public class PetData 
{
    // INSTANCE VARIABLES
    
    public String petID; // PK
    // Attributes
    public String petName;
    public Animal petInstance; // not sure about this one
    public int petLevel;
    public int petLevelXP;
    public int petHunger;
    public int petHygiene;
    public int petHappiness;
    public String userName; // FK
    
    
    // Constructor
    public PetData(String petID, String petName, Animal petInstance, int petLevel, int petLevelXP, int petHunger, int petHygiene, int petHappiness, String userName)
    {
        this.petID = petID;
        this.petName = petName;
        this.petInstance = petInstance;
        this.petLevel = petLevel;
        this.petLevelXP = petLevelXP;
        this.petHunger = petHunger;
        this.petHygiene = petHygiene;
        this.petHappiness = petHappiness;
        this.userName = userName;
    }
    
    // METHODS
    
    // Override equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetData petData = (PetData) o;
        return petLevel == petData.petLevel &&
                petLevelXP == petData.petLevelXP &&
                petHunger == petData.petHunger &&
                petHygiene == petData.petHygiene &&
                petHappiness == petData.petHappiness &&
                Objects.equals(petID, petData.petID) &&
                Objects.equals(petName, petData.petName) &&
                Objects.equals(petInstance, petData.petInstance) &&
                Objects.equals(userName, petData.userName);
    }
    
    // Override hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(petID, petName, petInstance, petLevel, petLevelXP, petHunger, petHygiene, petHappiness, userName);
    }
    
    // toString method
    @Override
    public String toString() 
    {
        return "PetData{" +
                "petID='" + petID + '\'' +
                ", petName='" + petName + '\'' +
                ", petInstance=" + petInstance +
                ", petLevel=" + petLevel +
                ", petLevelXP=" + petLevelXP +
                ", petHunger=" + petHunger +
                ", petHygiene=" + petHygiene +
                ", petHappiness=" + petHappiness +
                ", userName='" + userName + '\'' +
                '}';
    }
}
