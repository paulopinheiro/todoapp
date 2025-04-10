package br.jus.trt12.paulopinheiro.todoapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler extends Configs {
    private Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:derby://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("org.apache.derby.jdbc.ClientDataSource");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);
        return dbConnection;
    }
}
