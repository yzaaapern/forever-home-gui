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
import java.sql.PreparedStatement;

/**
 *
 * @author annga
 */
public class ForeverHomeDBOperations 
{
    // Constants
    private static final String USER_DATA_TABLE = "user_data";
    private static final String PET_TABLE = "pet";
    private static final String FOOD_INVENTORY_TABLE = "food_inventory";
    
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
        
        // Drop tables
//        dbOperations.dropTable(PET_TABLE);
//        dbOperations.dropTable(FOOD_INVENTORY_TABLE);
//        dbOperations.dropTable(USER_DATA_TABLE);
        
        // Create tables
        dbOperations.createTable(USER_DATA_TABLE);
        dbOperations.createTable(PET_TABLE);
        dbOperations.createTable(FOOD_INVENTORY_TABLE);
        
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
        String sqlCreateTable = generateCreateTableSQL(tableName);

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlCreateTable)) 
        {
            if (!tableExists(dbManager.getConnection(), tableName)) 
            {
                preparedStatement.executeUpdate();
                System.out.println("Table '" + tableName + "' created successfully.");
                insertSampleData(tableName);
            } 
            else 
            {
                System.out.println("Table '" + tableName + "' already exists.");
            }
        } catch (SQLException e) {
            this.handleSQLException(e);
        }
    }
    
    private String generateCreateTableSQL(String tableName) 
    {
        // Generate SQL for creating the specified table
        if (USER_DATA_TABLE.equals(tableName)) {
            return "CREATE TABLE user_data (" +
                "userName VARCHAR(20) PRIMARY KEY," +
                "userPassword VARCHAR(25)," +
                "userDabloons INT," +
                "userHasPet BOOLEAN" +
                ")";
        } else if (PET_TABLE.equals(tableName)) {
            return "CREATE TABLE pet (" +
                "petID VARCHAR(36) PRIMARY KEY," +
                "petName VARCHAR(25)," +
                "petInstance VARCHAR(10)," +
                "petLevel INT," +
                "petLevelXP INT," +
                "petHunger INT," +
                "petHygiene INT," +
                "petHappiness INT," +
                "userName VARCHAR(20)," +
                "FOREIGN KEY (userName) REFERENCES user_data(userName)" +
                ")";
        } else if (FOOD_INVENTORY_TABLE.equals(tableName)) {
            return "CREATE TABLE food_inventory (" +
                "foodInventoryID VARCHAR(36) PRIMARY KEY," +
                "foodInventory VARCHAR(255)," +
                "userName VARCHAR(20)," +
                "FOREIGN KEY (userName) REFERENCES user_data(userName)" +
                ")";
        }
        return "";
    }

    // INSERT DATA METHODS
    
    // INSERTING
    public void insertData(String tableName, Object data) 
    {
        String sqlInsert = generateInsertDataSQL(tableName);

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlInsert)) {
            if (tableExists(dbManager.getConnection(), tableName)) 
            {
                setInsertDataParameters(preparedStatement, tableName, data);
                preparedStatement.executeUpdate();
                System.out.println("Data inserted into table '" + tableName + "' successfully.");
            } else {
                System.out.println("Table '" + tableName + "' does not exist.");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    
    // SAMPLE DATA
    private void insertSampleData(String tableName) 
    {
        String sqlInsert = generateInsertDataSQL(tableName);

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlInsert)) 
        {
            if (tableExists(dbManager.getConnection(), tableName)) {
                // Set the sample data parameters based on the table name
                if (USER_DATA_TABLE.equals(tableName)) {
                    // Example sample data for user_data table
                    preparedStatement.setString(1, "anny");
                    preparedStatement.setString(2, "123");
                    preparedStatement.setInt(3, 100);
                    preparedStatement.setBoolean(4, true);
                } else if (PET_TABLE.equals(tableName)) {
                    // Example sample data for pet table
                    preparedStatement.setString(1, "1234");
                    preparedStatement.setString(2, "pookie");
                    preparedStatement.setString(3, "dog");
                    preparedStatement.setInt(4, 1);
                    preparedStatement.setInt(5, 0);
                    preparedStatement.setInt(6, 100);
                    preparedStatement.setInt(7, 100);
                    preparedStatement.setInt(8, 100);
                    preparedStatement.setString(9, "anny");
                } else if (FOOD_INVENTORY_TABLE.equals(tableName)) {
                    // Example sample data for food_inventory table
                    preparedStatement.setString(1, "5323");
                    preparedStatement.setString(2, "SampleFood1");
                    preparedStatement.setString(3, "anny");
                }

                // Execute the update
                preparedStatement.executeUpdate();
                System.out.println("Sample data for '" + tableName + "' inserted successfully.");
            } else {
                System.out.println("Table '"+ tableName +"' does not exist.");
            }
        } catch (SQLException e) {
            this.handleSQLException(e);
        }
    }
    
    private String generateInsertDataSQL(String tableName) {
        if (USER_DATA_TABLE.equals(tableName)) {
            return "INSERT INTO user_data (userName, userPassword, userDabloons, userHasPet) VALUES (?, ?, ?, ?)";
        } else if (PET_TABLE.equals(tableName)) {
            return "INSERT INTO pet (petID, petName, petInstance, petLevel, petLevelXP, petHunger, petHygiene, petHappiness, userName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        } else if (FOOD_INVENTORY_TABLE.equals(tableName)) {
            return "INSERT INTO food_inventory (foodInventoryID, foodInventory, userName) VALUES (?, ?, ?)";
        }
        return "";
    }    
    
    private void setInsertDataParameters(PreparedStatement preparedStatement, String tableName, Object data) throws SQLException 
    {
        if (USER_DATA_TABLE.equals(tableName) && data instanceof UserData) {
            UserData userData = (UserData) data;
            preparedStatement.setString(1, userData.getUserName());
            preparedStatement.setString(2, userData.getUserPassword());
            preparedStatement.setInt(3, userData.getUserDabloons());
            preparedStatement.setBoolean(4, userData.isUserHasPet());
        } else if (PET_TABLE.equals(tableName) && data instanceof PetData) {
            PetData petData = (PetData) data;
            preparedStatement.setString(1, petData.getPetID());
            preparedStatement.setString(2, petData.getPetName());
            preparedStatement.setString(3, petData.getPetInstance());
            preparedStatement.setInt(4, petData.getPetLevel());
            preparedStatement.setInt(5, petData.getPetLevelXP());
            preparedStatement.setInt(6, petData.getPetHunger());
            preparedStatement.setInt(7, petData.getPetHygiene());
            preparedStatement.setInt(8, petData.getPetHappiness());
            preparedStatement.setString(9, petData.getUserName());
        } else if (FOOD_INVENTORY_TABLE.equals(tableName) && data instanceof FoodInventoryData) {
            FoodInventoryData foodInventoryData = (FoodInventoryData) data;
            preparedStatement.setString(1, foodInventoryData.getFoodInventoryID());
            preparedStatement.setString(2, foodInventoryData.getFoodInventory());
            preparedStatement.setString(3, foodInventoryData.getUserName());
        }
    }
    
    // REMOVE DATA METHODS
    
    /**
    * removeData method
    *
    * Parameters: String tableName, String primaryKey
    * Return: None
    * Description: Removes a record from the specified table using the primary key.
    */
   public void removeData(String tableName, String primaryKey) {
       if (!tableExists(dbManager.getConnection(), tableName)) {
           System.out.println("Table '" + tableName + "' does not exist.");
           return;
       }

       if (!primaryKeyExists(tableName, primaryKey)) {
           System.out.println("Record with the specified primary key does not exist in table '" + tableName + "'.");
           return;
       }

       String sqlRemove = generateRemoveDataSQL(tableName);

       try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlRemove)) {
           setRemoveDataParameters(preparedStatement, tableName, primaryKey);
           int rowCount = preparedStatement.executeUpdate();
           if (rowCount > 0) {
               System.out.println("Record with primary key '" + primaryKey + "' removed from table '" + tableName + "'.");
           } else {
               System.out.println("Record removal failed.");
           }
       } catch (SQLException e) {
           handleSQLException(e);
       }
   }

   private String generateRemoveDataSQL(String tableName) {
       return "DELETE FROM " + tableName + " WHERE primaryKey = ?";
   }

   private void setRemoveDataParameters(PreparedStatement preparedStatement, String tableName, String primaryKey) throws SQLException {
       preparedStatement.setString(1, primaryKey);
   }
   
   // UPDATE DATA METHODS
   
   public void updateData(String tableName, Object data) 
   {
        String sqlUpdate = generateUpdateDataSQL(tableName);

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlUpdate)) {
            if (tableExists(dbManager.getConnection(), tableName)) {
                setUpdateDataParameters(preparedStatement, tableName, data);
                int rowCount = preparedStatement.executeUpdate();
                if (rowCount > 0) {
                    System.out.println("Data updated in table '" + tableName + "' successfully.");
                } else {
                    System.out.println("No records were updated.");
                }
            } else {
                System.out.println("Table '" + tableName + "' does not exist.");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private String generateUpdateDataSQL(String tableName) 
    {
        // Create the SQL statement for updating data in the specified table
        // Adapt this part to your specific use case
        if (USER_DATA_TABLE.equals(tableName)) {
            return "UPDATE user_data SET userPassword=?, userDabloons=?, userHasPet=? WHERE userName=?";
        } else if (PET_TABLE.equals(tableName)) {
            return "UPDATE pet SET petName=?, petInstance=?, petLevel=?, petLevelXP=?, petHunger=?, petHygiene=?, petHappiness=? WHERE petID=?";
        } else if (FOOD_INVENTORY_TABLE.equals(tableName)) {
            return "UPDATE food_inventory SET foodInventory=? WHERE foodInventoryID=?";
        }
        return "";
    }

    private void setUpdateDataParameters(PreparedStatement preparedStatement, String tableName, Object data) throws SQLException 
    {
        // Set the parameters for the update statement based on the table name and data object
        // Adapt this part to your specific use case
        if (USER_DATA_TABLE.equals(tableName) && data instanceof UserData) {
            UserData userData = (UserData) data;
            preparedStatement.setString(1, userData.getUserPassword());
            preparedStatement.setInt(2, userData.getUserDabloons());
            preparedStatement.setBoolean(3, userData.isUserHasPet());
            preparedStatement.setString(4, userData.getUserName());
        } else if (PET_TABLE.equals(tableName) && data instanceof PetData) {
            PetData petData = (PetData) data;
            preparedStatement.setString(1, petData.getPetName());
            preparedStatement.setString(2, petData.getPetInstance());
            preparedStatement.setInt(3, petData.getPetLevel());
            preparedStatement.setInt(4, petData.getPetLevelXP());
            preparedStatement.setInt(5, petData.getPetHunger());
            preparedStatement.setInt(6, petData.getPetHygiene());
            preparedStatement.setInt(7, petData.getPetHappiness());
            preparedStatement.setString(8, petData.getPetID());
        } else if (FOOD_INVENTORY_TABLE.equals(tableName) && data instanceof FoodInventoryData) {
            FoodInventoryData foodInventoryData = (FoodInventoryData) data;
            preparedStatement.setString(1, foodInventoryData.getFoodInventory());
            preparedStatement.setString(2, foodInventoryData.getFoodInventoryID());
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
    
    private void handleSQLException(SQLException e) {
        Logger.getLogger(ForeverHomeDBOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
    }
    
    /**
    * primaryKeyExists method
    *
    * Parameters: String tableName, String primaryKey
    * Return: boolean
    * Description: Checks if a record with the specified primary key exists in the table.
    */
   public boolean primaryKeyExists(String tableName, String primaryKey) {
       if (!tableExists(dbManager.getConnection(), tableName)) {
           System.out.println("Table '" + tableName + "' does not exist.");
           return false;
       }

       String sqlCheckExists = generateCheckPrimaryKeySQL(tableName);

       try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlCheckExists)) {
           setCheckPrimaryKeyParameters(preparedStatement, primaryKey);
           try (ResultSet rs = preparedStatement.executeQuery()) {
               return rs.next(); // Returns true if a record with the specified primary key exists
           }
       } catch (SQLException e) {
           handleSQLException(e);
           return false; // Default to false if an error occurs
       }
   }
   
   private String generateCheckPrimaryKeySQL(String tableName) {
    return "SELECT 1 FROM " + tableName + " WHERE primaryKey = ?";
    }

    private void setCheckPrimaryKeyParameters(PreparedStatement preparedStatement, String primaryKey) throws SQLException {
        preparedStatement.setString(1, primaryKey);
    }
}
