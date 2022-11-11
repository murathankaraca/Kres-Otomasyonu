package meltem.services.data_access;

import meltem.services.logging.Logger;

import java.sql.*;

public abstract class PersistentDataService<T> implements IRepository<T> {
    public static String connectionString = "jdbc:sqlserver://desktop-rtotag4;Database=meltemDB;user=admin;Password=1234;Trusted_Connection=true;";
    protected Connection connection;

    public static void main(String[] args) {
    }

    public void connect() {
        try {
            this.connection = DriverManager.getConnection(connectionString);
            Logger.LogDebug("Connection was established!");
        }
        catch(SQLException ex) {
            Logger.LogError(ex.getMessage());
        }
    }
    public void close() {
        try {
            this.connection.close();
        }
        catch(SQLException ex) {
            Logger.LogError(ex.getMessage());
        }
    }
}
