/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foreverhome;

/**
 *
 * @author AnnGa
 */
public class ForeverHomeDB 
{
    // Instance variables
    private ForeverHomeDBOperations dbOperations;
    private ForeverHomeDBQueries dbQueries;
    
    // Constructor
    public ForeverHomeDB(ForeverHomeDBOperations dbOperations, ForeverHomeDBQueries dbQueries)
    {
        this.setDbOperations(dbOperations);
        this.setDbQueries(dbQueries);
    }

    
    // METHODS
    public ForeverHomeDBOperations getDbOperations() {
        return dbOperations;
    }

    public void setDbOperations(ForeverHomeDBOperations dbOperations) {
        this.dbOperations = dbOperations;
    }

    public ForeverHomeDBQueries getDbQueries() {
        return dbQueries;
    }

    public void setDbQueries(ForeverHomeDBQueries dbQueries) {
        this.dbQueries = dbQueries;
    }
}
