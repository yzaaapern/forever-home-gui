
package foreverhome;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author AnnGa
 * Name: Ann Del Rosario
 * Student ID: 21143100
 */
public class ForeverHomeDBQueries 
{    
    // Instance variables
    private ForeverHomeDBManager dbManager;
    
    // Constructor
    public ForeverHomeDBQueries(ForeverHomeDBManager dbManager) 
    {
        this.dbManager = dbManager;
    }
    
    // MAIN
    public static void main(String[] args) 
    {
        ForeverHomeDBManager dbManager = ForeverHomeDBManager.getInstance();
        ForeverHomeDBQueries dbQueries = new ForeverHomeDBQueries(dbManager);
        
        // Getting queries
        dbQueries.getQuery(ForeverHomeDB.USER_DATA_TABLE);
        dbQueries.getQuery(ForeverHomeDB.PET_TABLE);
        dbQueries.getQuery(ForeverHomeDB.FOOD_INVENTORY_TABLE);
        
        // Queries
        System.out.println(dbQueries.getAll(ForeverHomeDB.USER_DATA_TABLE));
        System.out.println(dbQueries.getAll(ForeverHomeDB.PET_TABLE));
        System.out.println(dbQueries.getAll(ForeverHomeDB.FOOD_INVENTORY_TABLE));
        System.out.println(dbQueries.getUserByUserName("anny"));
        System.out.println(dbQueries.getPetByPetNameAndUserName("anny", "Pookie"));
        System.out.println(dbQueries.getUserByFoodInventoryID("ecf77bba-0ca2-495c-b6f9-b6fb6fdb3aa6"));
        System.out.println(dbQueries.getPetByPetID("03a5f174-5ca8-4f99-a46b-380e5c195837"));
        System.out.println(dbQueries.getPetByPetID("843ff3bf-df2a-47f7-9542-0fe2664dc404"));
        System.out.println(dbQueries.getFoodInventoryByUserName("anny"));
        
        // Closing connection
        dbQueries.dbManager.closeConnection();
    }
    
    // METHODS
    
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
                    if(ForeverHomeDB.USER_DATA_TABLE.equals(tableName))
                    {
                        String userName = rs.getString("userName");
                        String userPassword = rs.getString("userPassword");
                        int userDabloons = rs.getInt("userDabloons");
                        boolean userHasPet = rs.getBoolean("userHasPet");
                    }
                    else if(ForeverHomeDB.PET_TABLE.equals(tableName))
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
                    else if(ForeverHomeDB.FOOD_INVENTORY_TABLE.equals(tableName))
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
            this.handleSQLException(e);
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
        String sqlQuery = "SELECT * FROM " + tableName;
        
        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlQuery);
            ResultSet rs = preparedStatement.executeQuery())
        {
            while(rs.next())
            {
                if (ForeverHomeDB.USER_DATA_TABLE.equals(tableName)) {
                    String userName = rs.getString("userName");
                    String userPassword = rs.getString("userPassword");
                    int userDabloons = rs.getInt("userDabloons");
                    boolean userHasPet = rs.getBoolean("userHasPet");

                    UserData userData = new UserData(userName, userPassword, userDabloons, userHasPet);
                    list.add(userData);
                }
                else if (ForeverHomeDB.PET_TABLE.equals(tableName)) {
                    String petID = rs.getString("petID");
                    String petName = rs.getString("petName");
                    String petInstance = rs.getString("petInstance");
                    int petLevel = rs.getInt("petLevel");
                    int petLevelXP = rs.getInt("petLevelXP");
                    int petHunger = rs.getInt("petHunger");
                    int petHygiene = rs.getInt("petHygiene");
                    int petHappiness = rs.getInt("petHappiness");
                    String userName = rs.getString("userName");

                    PetData petData = new PetData(petID, petName, petInstance, petLevel, petLevelXP, petHunger, petHygiene, petHappiness, userName);
                    list.add(petData);
                }
                else if (ForeverHomeDB.FOOD_INVENTORY_TABLE.equals(tableName)) {
                    String foodInventoryID = rs.getString("foodInventoryID");
                    String foodInventory = rs.getString("foodInventory");
                    String userName = rs.getString("userName");

                    FoodInventoryData foodInventoryData = new FoodInventoryData(foodInventoryID, foodInventory, userName);
                    list.add(foodInventoryData);
                }
            }
        } catch(SQLException e)
        {
            this.handleSQLException(e);
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
        String sqlQuery = "SELECT * FROM " + ForeverHomeDB.USER_DATA_TABLE + " WHERE userName=?";

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlQuery))
        {
            preparedStatement.setString(1, name);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    String userName = rs.getString("userName");
                    String userPassword = rs.getString("userPassword");
                    int userDabloons = rs.getInt("userDabloons");
                    boolean userHasPet = rs.getBoolean("userHasPet");

                    // Create and return a UserData object with the retrieved data
                    UserData u = new UserData(userName, userPassword, userDabloons, userHasPet);
                    return u;
                }
            }
        } catch (SQLException e) {
            this.handleSQLException(e);
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
        String sqlQuery = "SELECT u.userName, u.userPassword, u.userDabloons, u.userHasPet " +
                       "FROM user_data u " +
                       "INNER JOIN food_inventory f ON u.userName = f.userName " +
                       "WHERE f.foodInventoryID = ?";

        try (PreparedStatement statement = dbManager.getConnection().prepareStatement(sqlQuery)) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String userName = rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                int userDabloons = rs.getInt("userDabloons");
                boolean userHasPet = rs.getBoolean("userHasPet");

                UserData u = new UserData(userName, userPassword, userDabloons, userHasPet);
                return u;
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null; // Return null if no matching user is found
    }

    /* getPetByPetID method
    
    Parameters: String id
    Return: PetData object
    Description: The pet's data for a specified pet ID is returned 
    */
    public PetData getPetByPetID(String id) 
        {
        String query = "SELECT * FROM pet WHERE petID = ?";
        try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(query)) 
        {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) 
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
                return p;
            }
        } catch (SQLException e) {
            this.handleSQLException(e);
        }
        return null;
    }
    
    /* getPetByPetNameAndUserName method
    
    Parameters: String userName & String petName
    Return: PetData object
    Description: Returns pet's data for a specified user and petname
    */
    public PetData getPetByPetNameAndUserName(String userName, String petName) {
        try {
            String query = "SELECT * FROM pet WHERE userName=? AND petName=?";
            PreparedStatement preparedStatement = this.dbManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, petName);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String petID = rs.getString("petID");
                String petInstance = rs.getString("petInstance");
                int petLevel = rs.getInt("petLevel");
                int petLevelXP = rs.getInt("petLevelXP");
                int petHunger = rs.getInt("petHunger");
                int petHygiene = rs.getInt("petHygiene");
                int petHappiness = rs.getInt("petHappiness");

                // Create and return a PetData object with the retrieved data
                return new PetData(petID, petName, petInstance, petLevel, petLevelXP, petHunger, petHygiene, petHappiness, userName);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }
    
    /* getFoodInventoryByUserName method
    
    Parameters: String name
    Return: FoodInventoryData
    Description: Returns food inventory data for a specified username
    */
    public FoodInventoryData getFoodInventoryByUserName(String name) 
    {
        String selectQuery = "SELECT * FROM food_inventory WHERE userName = ?";
        try {
            PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(selectQuery);
            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String foodInventoryID = rs.getString("foodInventoryID");
                String foodInventory = rs.getString("foodInventory");
                String userName = rs.getString("userName");

                return new FoodInventoryData(foodInventoryID, foodInventory, userName);
            }
        } catch (SQLException e) {
            this.handleSQLException(e);
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
        String selectQuery = "SELECT * FROM food_inventory WHERE foodInventoryID = ?";
        try {
            PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(selectQuery);
            preparedStatement.setString(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String foodInventoryID = rs.getString("foodInventoryID");
                String foodInventory = rs.getString("foodInventory");
                String userName = rs.getString("userName");

                return new FoodInventoryData(foodInventoryID, foodInventory, userName);
            }
        } catch (SQLException e) {
            this.handleSQLException(e);
        }

        return null;
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
    
    private void handleSQLException(SQLException e) {
        Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
    }
    
    
}
