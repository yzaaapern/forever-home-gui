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
    
    private String userName; // PK
    // Attributes
    private String userPassword; 
    private int userDabloons;
    private boolean userHasPet;
    
    // CONSTRUCTORS
    
    // 1-Parameter constructor
    public UserData(String userName)
    {
        this.userName = userName;
    }
    
    // 3-Paramter constructor
    public UserData(String userName, String userPassword, int userDabloons, boolean userHasPet)
    {
        this.userName = userName;
        this.setUserPassword(userPassword);
        this.setUserDabloons(userDabloons);
        this.setUserHasPet(userHasPet);
    }
    
    // GET & SET METHODS
    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword the userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * @return the userDabloons
     */
    public int getUserDabloons() {
        return userDabloons;
    }

    /**
     * @param userDabloons the userDabloons to set
     */
    public void setUserDabloons(int userDabloons) {
        this.userDabloons = userDabloons;
    }

    /**
     * @return the userHasPet
     */
    public boolean isUserHasPet() {
        return userHasPet;
    }

    /**
     * @param userHasPet the userHasPet to set
     */
    public void setUserHasPet(boolean userHasPet) {
        this.userHasPet = userHasPet;
    }
    
    
    // METHODS
    
    // Override equals method
    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return getUserDabloons() == userData.getUserDabloons() &&
                isUserHasPet() == userData.isUserHasPet() &&
                Objects.equals(getUserName(), userData.getUserName()) &&
                Objects.equals(getUserPassword(), userData.getUserPassword());
    }

    // Override hashCode method
    @Override
    public int hashCode() 
    {
        return Objects.hash(getUserName(), getUserPassword(), getUserDabloons(), isUserHasPet());
    }
    
    // toString method
    @Override
    public String toString() {
    return "UserData{" +
            "userName='" + getUserName() + '\'' +
            ", userPassword='" + getUserPassword() + '\'' +
            ", userDabloons=" + getUserDabloons() +
            ", userHasPet=" + isUserHasPet() +
            '}';
    }

    
}
