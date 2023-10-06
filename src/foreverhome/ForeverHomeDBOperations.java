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
//        dbOperations.dropPetTable();
//        dbOperations.dropFoodInventoryTable();
//        dbOperations.dropUserTable();
        
        
        // Create tables
//        dbOperations.createUserTable();
//        dbOperations.createPetTable();
//        dbOperations.createFoodInventoryTable();
        
        // Getting queries
        dbOperations.getUserQuery();
        dbOperations.getPetQuery();
        dbOperations.getFoodInventoryQuery();
        
        // Prompts
//        System.out.println(dbOperations.getUserByUserName("anny"));
//        System.out.println(dbOperations.getPetByPetNameAndUserName("anny", "Pookie"));
//        System.out.println(dbOperations.getAllUser());
        System.out.println(dbOperations.getAllPet());
        
        // Closing connection
        dbOperations.dbManager.closeConnection();
    }
    
    // Create the "user_data" table method
    public void createUserTable() 
    {
        String sqlCreateUserTable = "CREATE TABLE user_data (" +
                "userName VARCHAR(20) PRIMARY KEY," +
                "userPassword VARCHAR(25)," +
                "userDabloons INT," +
                "userHasPet BOOLEAN" +
                ")";
        
        try
        {
            Statement statement = dbManager.getConnection().createStatement();
            
            // Check if the table already exists
//            ResultSet rs = statement.executeQuery("SELECT 1 FROM user_data");
            
            if (!this.tableExists(dbManager.getConnection(), "user_data")) 
            {
                // Table doesn't exist, so create it
                statement.executeUpdate(sqlCreateUserTable);
                System.out.println("Table 'user_data' created successfully.");
                
                // Insert sample data
                String sqlInsertData1 = "INSERT INTO user_data (userName, userPassword, userDabloons, userHasPet) VALUES ('anny', '123', 100, true)";
                String sqlInsertData2 = "INSERT INTO user_data (userName, userPassword, userDabloons, userHasPet) VALUES ('fruit cake', '321', 20, false)";
                String sqlInsertData3 = "INSERT INTO user_data (userName, userPassword, userDabloons, userHasPet) VALUES ('desmond', '000', 50, true)";

                statement.executeUpdate(sqlInsertData1);
                statement.executeUpdate(sqlInsertData2);
                statement.executeUpdate(sqlInsertData3);

                System.out.println("Sample data inserted successfully.");
            } 
            else 
            {
                System.out.println("Table 'user_data' already exists.");
            }
            
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
                "FOREIGN KEY (userName) REFERENCES user_data(userName)" + // Define a foreign key relationship to the u table
                ")";

        try 
        {
            Statement statement = dbManager.getConnection().createStatement();
            
//            ResultSet rs = statement.executeQuery("SELECT 1 FROM food_inventory");
            
            if(!this.tableExists(dbManager.getConnection(), "food_inventory"))
            {
                statement.executeUpdate(sqlCreateFoodInventoryTable);
                System.out.println("Table 'food_inventory' created successfully.");
            }
            else
            {
                System.out.println("Table 'food_inventory' already exists.");
            }
            
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }
    
    // Create the "pet" table
    public void createPetTable() 
    {
        String sqlCreatePetTable = "CREATE TABLE pet (" +
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

        try
        {
            Statement statement = dbManager.getConnection().createStatement();
            
//            ResultSet rs = statement.executeQuery("SELECT 1 FROM pet");
            
            if(!this.tableExists(dbManager.getConnection(), "pet"))
            {
                statement.executeUpdate(sqlCreatePetTable);
                System.out.println("Table 'pet' created successfully.");
                
                // Insert sample data
                String sqlInsertData1 = "INSERT INTO pet (petID, petName, petInstance, petLevel, petLevelXP, petHunger, petHygiene, petHappiness, userName) " +
                        "VALUES ('pet1', 'Pookie', 'Dog', 5, 1, 75, 70, 80, 'anny')";
                String sqlInsertData2 = "INSERT INTO pet (petID, petName, petInstance, petLevel, petLevelXP, petHunger, petHygiene, petHappiness, userName) " +
                        "VALUES ('pet2', 'Whiskers', 'Cat', 10, 0, 20, 60, 70, 'desmond')";

                statement.executeUpdate(sqlInsertData1);
                statement.executeUpdate(sqlInsertData2);
            }
            else
            {
                System.out.println("Table 'pet' already exists.");
            }
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
            System.out.println(" getting user_data query...");
            Statement statement = dbManager.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            String sqlQuery = "SELECT userName, userPassword, userDabloons, userHasPet FROM user_data";
            
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
    
    public List<UserData> getAllUser()
    {
        List<UserData> userList = new ArrayList<>();
        ResultSet rs = dbManager.myQuery("SELECT * FROM user_data");
        
        try
        {
            while(rs.next())
            {
                String userName = rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                int userDabloons = rs.getInt("userDabloons");
                boolean userHasPet = rs.getBoolean("userHasPet");
                
                UserData u = new UserData(userName, userPassword, userDabloons, userHasPet);
                userList.add(u);
            }
        } catch(SQLException e)
        {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        
        return userList;
    }
    
    public List<PetData> getAllPet()
    {
        List<PetData> petList = new ArrayList<>();
        ResultSet rs = dbManager.myQuery("SELECT * FROM Pet");
        
        try
        {
            while(rs.next())
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
                petList.add(p);
            }
        } catch(SQLException e)
        {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        return petList;
    }
    
    public List<FoodInventoryData> getAllFoodInventory()
    {
        List<FoodInventoryData> foodInventoryList = new ArrayList<>();
        ResultSet rs = dbManager.myQuery("SELECT * FROM food_inventory");
        
        try
        {
            while (rs.next()) {
                String foodInventoryID = rs.getString("foodInventoryID");
                String foodInventory = rs.getString("foodInventory");
                String userName = rs.getString("userName");
            }
        } catch(SQLException e)
        {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        return foodInventoryList;
    }
    
    
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
            
    
    // DROP TABLE METHODS (Deletes the specified table)
    
    public void dropUserTable() 
    {
        String sqlDropUserTable = "DROP TABLE user_data";

        try 
        {
            Statement statement = dbManager.getConnection().createStatement();
            statement.executeUpdate(sqlDropUserTable);
            System.out.println("Table 'user_data' dropped successfully.");
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }
    
    public void dropPetTable() 
    {
        String sqlDropUserTable = "DROP TABLE pet";

        try 
        {
            Statement statement = dbManager.getConnection().createStatement();
            statement.executeUpdate(sqlDropUserTable);
            System.out.println("Table 'pet' dropped successfully.");
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }
    
    public void dropFoodInventoryTable() 
    {
        String sqlDropUserTable = "DROP TABLE food_inventory";

        try 
        {
            Statement statement = dbManager.getConnection().createStatement();
            statement.executeUpdate(sqlDropUserTable);
            System.out.println("Table 'food_inventory' dropped successfully.");
        } catch (SQLException e) {
            Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }
    
    // tableExists method
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
