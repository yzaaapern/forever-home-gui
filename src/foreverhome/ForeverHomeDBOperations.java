/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author annga
 */
public class ForeverHomeDBOperations 
{
    // Instance variables
    
    public ForeverHomeDBManager dbManager;
    
    // Constructor
    public ForeverHomeDBOperations()
    {
        dbManager = new ForeverHomeDBManager();
    }
    
    // METHODS
    
    // MAIN METHOD
    public static void main(String[] args) 
    {
        ForeverHomeDBOperations dbOperations = new ForeverHomeDBOperations();
        
        // Create tables
//        dbOperations.createUserTable();
//        dbOperations.createPetTable();
//        dbOperations.createFoodInventoryTable();
        
        // Getting queries
        dbOperations.getUserQuery();
        dbOperations.getPetQuery();
        dbOperations.getFoodInventoryQuery();
        
        // Closing connection
        dbOperations.dbManager.closeConnection();
    }
    
    // Create the "user" table
    public void createUserTable() 
    {
        String sqlCreateUserTable = "CREATE TABLE userData (" +
                "username VARCHAR(20) PRIMARY KEY," +
                "userPassword VARCHAR(25)," +
                "userDabloons INT," +
                "userHasPet BOOLEAN" +
                ")";
        
        try
        {
            Statement statement = dbManager.getConnection().createStatement();
            
            statement.executeUpdate(sqlCreateUserTable);
            System.out.println("Table 'userData' created successfully.");
            
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }
    
    // Create the "food_inventory" table
    public void createFoodInventoryTable() 
    {
        String sqlCreateFoodInventoryTable = "CREATE TABLE food_inventory (" +
                "foodInventoryID VARCHAR(20) PRIMARY KEY," +
                "foodInventory VARCHAR(255)," +
                "userName VARCHAR(20)," +
                "FOREIGN KEY (userName) REFERENCES userData(userName)" + // Define a foreign key relationship to the user table
                ")";

        try 
        {
            Statement statement = dbManager.getConnection().createStatement();
            
            statement.executeUpdate(sqlCreateFoodInventoryTable);
            System.out.println("Table 'food_inventory' created successfully.");
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }
    
    // Create the "pet" table
    public void createPetTable() 
    {
        String sqlCreatePetTable = "CREATE TABLE pet (" +
                "petID VARCHAR(20) PRIMARY KEY," +
                "petName VARCHAR(50)," +
                "petInstance VARCHAR(50)," + // Assuming petInstance is a string representation of an animal type
                "petLevel INT," +
                "petLevelXP INT," +
                "petHunger INT," +
                "petHygiene INT," +
                "petHappiness INT," +
                "userName VARCHAR(20)," +
                "FOREIGN KEY (userName) REFERENCES userData(userName)" + // Define a foreign key relationship to the user table
                ")";

        try
        {
            Statement statement = dbManager.getConnection().createStatement();
            
            statement.executeUpdate(sqlCreatePetTable);
            System.out.println("Table 'pet' created successfully.");
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }
    
    // getUserQuery method
    public void getUserQuery()
    {
        ResultSet rs = null;
        
        try
        {
            System.out.println(" getting user query...");
            Statement statement = dbManager.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            String sqlQuery = "select userName, userPassword, userDabloons, userHasPet from userData";
            
            rs = statement.executeQuery(sqlQuery);
            rs.beforeFirst();
            
            while(rs.next())
            {
                String userName = rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                int userDabloons = rs.getInt("userDabloons");
                boolean userHasPet = rs.getBoolean("userHasPet");
                
            }
            
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }
    
    
    // getFoodInventoryQueryMethod
    public void getFoodInventoryQuery() 
    {
        ResultSet rs = null;

        try {
            System.out.println(" getting food_inventory query...");
            Statement statement = dbManager.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sqlQuery = "SELECT foodInventoryID, foodInventory, userName FROM food_inventory";

            rs = statement.executeQuery(sqlQuery);
            rs.beforeFirst();

            while (rs.next()) {
                String foodInventoryID = rs.getString("foodInventoryID");
                String foodInventory = rs.getString("foodInventory");
                String userName = rs.getString("userName");
            }

        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }
    
    // getPetQuery method
    public void getPetQuery()
    {
        ResultSet rs = null;
        
        try
        {
            System.out.println(" getting pet query...");
            Statement statement = dbManager.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            String sqlQuery = "SELECT petID, petName, petInstance, petLevel, petLevelXP, petHunger, petHygiene, petHappiness, userName FROM pet";
            
            rs = statement.executeQuery(sqlQuery);
            rs.beforeFirst();
            
            while(rs.next())
            {
                String petID = rs.getString("petID");
                String petName = rs.getString("petName");
                String petInstance = rs.getString("petInstance"); // Check the data type
                int petLevel = rs.getInt("petLevel");
                int petLevelXP = rs.getInt("petLevelXP");
                int petHunger = rs.getInt("petHunger");
                int petHygiene = rs.getInt("petHygiene");
                int petHappiness = rs.getInt("petHappiness");
                String userName = rs.getString("userName");
            }
            
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }
    
}
