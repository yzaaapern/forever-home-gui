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
public class UserData 
{
    // INSTANCE VARIABLES
    
    public String userName; // PK
    // Attributes
    public String userPassword; 
    public int userDabloons;
    public boolean userHasPet;
    
    // constructor
    public UserData(String userName, String userPassword, int userDabloons, boolean userHasPet)
    {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userDabloons = userDabloons;
        this.userHasPet = userHasPet;
    }
    
    // METHODS
    
    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return userDabloons == userData.userDabloons &&
                userHasPet == userData.userHasPet &&
                Objects.equals(userName, userData.userName) &&
                Objects.equals(userPassword, userData.userPassword);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(userName, userPassword, userDabloons, userHasPet);
    }
    
    // toString method
    @Override
    public String toString() {
    return "UserData{" +
            "userName='" + userName + '\'' +
            ", userPassword='" + userPassword + '\'' +
            ", userDabloons=" + userDabloons +
            ", userHasPet=" + userHasPet +
            '}';
    }
}
