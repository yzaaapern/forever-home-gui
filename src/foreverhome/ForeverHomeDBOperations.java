/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        
        // Delete tables
        
        dbOperations.dropTable("pet");
        dbOperations.dropTable("food_inventory");
        dbOperations.dropTable("user_data");
        
        // Create tables
        dbOperations.createTable("user_data");
        dbOperations.createTable("pet");
        dbOperations.createTable("food_inventory");
        
        // Getting queries
        dbOperations.getQuery("user_data");
        dbOperations.getQuery("pet");
        dbOperations.getQuery("food_inventory");
        
        // Queries
        System.out.println(dbOperations.getAll("user_data"));
        System.out.println(dbOperations.getAll("pet"));
        System.out.println(dbOperations.getAll("food_inventory"));
        System.out.println(dbOperations.getUserByUserName("anny"));
        System.out.println(dbOperations.getPetByPetNameAndUserName("anny", "Pookie"));
        
        // Closing connection
        dbOperations.dbManager.closeConnection();
    }
    
    /* createTable method
    
    Parameters: String tableName
    Return: None
    Description: creates table with the tableName as its name
    */
    public void createTable(String tableName)
    {
        String sqlCreateTable = "";
        
        // 'user_data' Table
        if(tableName == "user_data")
        {
            // SQL syntax for create table
            sqlCreateTable = "CREATE TABLE user_data (" +
                "userName VARCHAR(20) PRIMARY KEY," +
                "userPassword VARCHAR(25)," +
                "userDabloons INT," +
                "userHasPet BOOLEAN" +
                ")";
        }
        // 'pet' Table
        else if(tableName == "pet")
        {
            sqlCreateTable = "CREATE TABLE pet (" +
                "petID VARCHAR(20) PRIMARY KEY," +
                "petName VARCHAR(25)," +
                "petInstance VARCHAR(10)," + // Assuming petInstance is a string representation of an animal type
                "petLevel INT," +
                "petLevelXP INT," +
                "petHunger INT," +
                "petHygiene INT," +
                "petHappiness INT," +
                "userName VARCHAR(20)," +
                "FOREIGN KEY (userName) REFERENCES user_data(userName)" + // Define a foreign key relationship to the u table
                ")";
        }
        // 'food_inventory' Table
        else if(tableName == "food_inventory")
        {
            sqlCreateTable = "CREATE TABLE food_inventory (" +
                "foodInventoryID VARCHAR(20) PRIMARY KEY," +
                "foodInventory VARCHAR(255)," +
                "userName VARCHAR(20)," +
                "FOREIGN KEY (userName) REFERENCES user_data(userName)" + // Define a foreign key relationship to the u table
                ")";
        }
        
        try
        {
            Statement statement = dbManager.getConnection().createStatement();
            
            // If the table doesn't exist, create the table
            if (!this.tableExists(dbManager.getConnection(), tableName)) 
            {
                // Table doesn't exist, so create it
                statement.executeUpdate(sqlCreateTable);
                System.out.println("Table '" + tableName + "' created successfully.");
                
                // Insert sample data
                
                // 'user_data' sample data
                if(tableName == "user_data")
                {
                    String sqlInsertData1 = "INSERT INTO user_data (userName, userPassword, userDabloons, userHasPet) VALUES ('anny', '123', 100, true)";
                    String sqlInsertData2 = "INSERT INTO user_data (userName, userPassword, userDabloons, userHasPet) VALUES ('fruit cake', '321', 20, false)";
                    String sqlInsertData3 = "INSERT INTO user_data (userName, userPassword, userDabloons, userHasPet) VALUES ('desmond', '000', 50, true)";

                    statement.executeUpdate(sqlInsertData1);
                    statement.executeUpdate(sqlInsertData2);
                    statement.executeUpdate(sqlInsertData3);
                    
                    System.out.println("Sample data for '"+ tableName +"' inserted successfully.");
                }           
                // 'pet' sample data
                else if(tableName == "pet")
                {
                    String sqlInsertData1 = "INSERT INTO pet (petID, petName, petInstance, petLevel, petLevelXP, petHunger, petHygiene, petHappiness, userName) " +
                            "VALUES ('pet1', 'Pookie', 'Dog', 5, 1, 75, 70, 80, 'anny')";
                    String sqlInsertData2 = "INSERT INTO pet (petID, petName, petInstance, petLevel, petLevelXP, petHunger, petHygiene, petHappiness, userName) " +
                            "VALUES ('pet2', 'Whiskers', 'Cat', 10, 0, 20, 60, 70, 'desmond')";
    
                    statement.executeUpdate(sqlInsertData1);
                    statement.executeUpdate(sqlInsertData2);
                    
                    System.out.println("Sample data for '"+ tableName +"' inserted successfully.");
                }
                
            } 
            // if the table already exists, do not create it.
            else 
            {
                System.out.println("Table '"+ tableName+"' already exists."); // prompt sent to user
            }
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        
    }

    // QUERY METHODS
    
    /* getQuery method
    
    Parameters: String tableName
    Return: None
    Description: 
    */
    public void getQuery(String tableName)
    {
        ResultSet rs = null;
        String sqlQuery = "SELECT * FROM "+ tableName;
        
        try
        {
            if(this.tableExists(this.dbManager.getConnection(), tableName))
            {
                System.out.println(" getting '" + tableName + "' query...");
                Statement statement = dbManager.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                rs = statement.executeQuery(sqlQuery);
                rs.beforeFirst();

                while(rs.next())
                {
                    if(tableName == "user_data")
                    {
                        String userName = rs.getString("userName");
                        String userPassword = rs.getString("userPassword");
                        int userDabloons = rs.getInt("userDabloons");
                        boolean userHasPet = rs.getBoolean("userHasPet");
                    }
                    else if(tableName == "pet")
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
                    else if(tableName == "food_inventory")
                    {
                        String foodInventoryID = rs.getString("foodInventoryID");
                        String foodInventory = rs.getString("foodInventory");
                        String userName = rs.getString("userName");
                    }

                }
            }
            else
            {
                System.out.println("Table '"+ tableName +"' does not exist.");
            }
            
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    } 
    
    /* getAll method
    
    Parameters: String tableName
    Return: List<Object>
    Description: The specified table's entity instances are all returned.
    */
    public List<Object> getAll(String tableName)
    {
        List<Object> list = new ArrayList<>();
        ResultSet rs = dbManager.myQuery("SELECT * FROM " + tableName);
        
        try
        {
            while(rs.next())
            {
                if(tableName == "user_data")
                {
                    String userName = rs.getString("userName");
                    String userPassword = rs.getString("userPassword");
                    int userDabloons = rs.getInt("userDabloons");
                    boolean userHasPet = rs.getBoolean("userHasPet");

                    UserData u = new UserData(userName, userPassword, userDabloons, userHasPet);
                    list.add(u);
                }
                else if(tableName == "pet")
                {
                    String petID = rs.getString("petID");
                    String petName = rs.getString("petName");
                    String petInstance = rs.getString("petInstance");
                    int petLevel = rs.getInt("petLevel");
                    int petLevelXP = rs.getInt("petLevelXP");
                    int petHunger = rs.getInt("petHunger");
                    int petHygiene = rs.getInt("petHygiene");
                    int petHappiness = rs.getInt("petHappiness");
                    String userName = rs.getString("userName");

                    PetData p = new PetData(petID, petName, petInstance, petLevel, petLevelXP, petHunger, petHygiene, petHappiness, userName);
                    list.add(p);
                }
                else if(tableName == "food_inventory")
                {
                    String foodInventoryID = rs.getString("foodInventoryID");
                    String foodInventory = rs.getString("foodInventory");
                    String userName = rs.getString("userName");

                    FoodInventoryData f = new FoodInventoryData(foodInventoryID, foodInventory, userName);
                    list.add(f);
                }
            }
        } catch(SQLException e)
        {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        
        return list;
    }

    /* getUserByUserName method
    
    Parameters: String name
    Return: UserData
    Description: The user's data for a specified name is returned 
    */
    public UserData getUserByUserName(String name)
    {
        ResultSet rs = dbManager.myQuery("SELECT * FROM user_data WHERE userName='" + name + "'");
        
        if(rs == null)
        {
            return null;
        }
        try
        {
            if(rs.next())
            {
                String userName = rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                int userDabloons = rs.getInt("userDabloons");
                boolean userHasPet = rs.getBoolean("userHasPet");

                // Create and return a UserData object with the retrieved data
                UserData u = new UserData(userName, userPassword, userDabloons, userHasPet);
                return u;
            }
        } catch(SQLException e)
        {
            return null;
        }
        return null;
    }
    
    /* getUserByFoodInventoryID method
    
    Parameters: String id (ID of foodInventory)
    Return: UserData object
    Description: The user's data for a specified foodInventoryID is returned 
    */
    
    public UserData getUserByFoodInventoryID(String id) 
    {
        ResultSet rs = dbManager.myQuery("SELECT u.userName, u.userPassword, u.userHasPet "
                                       + "FROM user_data u, food_inventory f "
                                       + "WHERE f.foodInventoryID = " + id 
                                       + " AND f.userName = u.userName");

        // Check if the ResultSet is null
        if (rs == null) {
            return null; // or throw an exception, depending on your design
        }

        try {
            if (rs.next()) {
                String userName = rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                int userDabloons = rs.getInt("userDabloons");
                boolean userHasPet = rs.getBoolean("userHasPet");

                // Create a userData object and return it
                UserData u = new UserData(userName, userPassword, userDabloons, userHasPet);
                return u;
            }
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }

        // Return null if no matching user is found
        return null;
    }

    
    /* getPetByPetID method
    
    Parameters: String id
    Return: PetData object
    Description: The pet's data for a specified pet ID is returned 
    */
    public PetData getPetByPetID(String id)
    {
        ResultSet rs = dbManager.myQuery("SELECT * FROM pet WHERE petID='" + id + "'");
        
        // Check if the ResultSet is null
        if (rs == null) {
            return null; // or throw an exception, depending on your design
        }
        
        try {
            if (rs.next()) 
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

                // Create and return a PetData object with the retrieved data
                PetData p = new PetData(petID, petName, petInstance, petLevel, petLevelXP, petHunger, petHygiene, petHappiness, userName);
                return p;
            }
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        
        return null; // Return null if no matching pet is found
        
    }
    
    /* getPetByPetNameAndUserName method
    
    Parameters: String userName & String petName
    Return: PetData object
    Description: Returns pet's data for a specified user and petname
    */
    public PetData getPetByPetNameAndUserName(String userName, String petName) 
    {
        ResultSet rs = dbManager.myQuery("SELECT * FROM pet WHERE userName='" + userName + "' AND petName='" + petName + "'");

        // Check if the ResultSet is null
        if (rs == null) {
            return null; // or throw an exception, depending on your design
        }

        try {
            if (rs.next()) {
                String petID = rs.getString("petID");
                String petInstance = rs.getString("petInstance");
                int petLevel = rs.getInt("petLevel");
                int petLevelXP = rs.getInt("petLevelXP");
                int petHunger = rs.getInt("petHunger");
                int petHygiene = rs.getInt("petHygiene");
                int petHappiness = rs.getInt("petHappiness");

                // Create and return a PetData object with the retrieved data
                PetData p = new PetData(petID, petName, petInstance, petLevel, petLevelXP, petHunger, petHygiene, petHappiness, userName);
                return p;
            }
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        
        return null; // Return null if no matching pet is found
    }

    /* getFoodInventoryByUserName method
    
    Parameters: String name
    Return: FoodInventoryData
    Description: Returns food inventory data for a specified username
    */
    public FoodInventoryData getFoodInventoryByUserName(String name)
    {
        ResultSet rs = dbManager.myQuery("SELECT * FROM food_inventory WHERE userName='" + name + "'");
        
        // Check if the ResultSet is null
        if (rs == null) {
            return null; // or throw an exception, depending on your design
        }
        
        try
        {
            if (rs.next()) 
            {
                String foodInventoryID = rs.getString("foodInventoryID");
                String foodInventory = rs.getString("foodInventory");
                String userName = rs.getString("userName");
                
                FoodInventoryData f = new FoodInventoryData(foodInventoryID, foodInventory, userName);
                return f;
            }
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        
        return null;
    }
            
    /* getFoodInventoryByFoodInventoryID method
    
    Parameters: String id
    Return: FoodInventoryData object
    Description: Returns food inventory data for a specified food inventory ID
    */
    public FoodInventoryData getFoodInventoryByFoodInventoryID(String id)
    {
        ResultSet rs = dbManager.myQuery("SELECT * FROM food_inventory WHERE foodInventoryID='" + id + "'");
        
        // Check if the ResultSet is null
        if (rs == null) {
            return null; // or throw an exception, depending on your design
        }
        
        try
        {
            if (rs.next()) 
            {
                String foodInventoryID = rs.getString("foodInventoryID");
                String foodInventory = rs.getString("foodInventory");
                String userName = rs.getString("userName");
                
                FoodInventoryData f = new FoodInventoryData(foodInventoryID, foodInventory, userName);
                return f;
            }
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        
        return null;
    }
          
    /* dropTable method
    
    Parameters: String tableName
    Return: void
    Description: Drops specified table if it exists
    */
    public void dropTable(String tableName)
    {
        String sqlDropTable = "DROP TABLE " + tableName;
        
        try
        {
            if(this.tableExists(this.dbManager.getConnection(), tableName))
            {
                Statement statement = dbManager.getConnection().createStatement();
                statement.executeUpdate(sqlDropTable);
                System.out.println("Table '" + tableName + "' dropped successfully. ");
            }
            else
            {
                System.out.println("Table '" + tableName + "' does not exist");
            }
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        
    }
    
    /* tableExists method
    
    Parameters: Connection connection, String tableName
    Returns: boolean
    Description: If the tableName exists under the connection, true is returned else false as the table does not exist.
    */
    public boolean tableExists(Connection connection, String tableName) {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, tableName.toUpperCase(), null);
            return tables.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // An error occurred; assume the table doesn't exist
        }
    }
    
}
