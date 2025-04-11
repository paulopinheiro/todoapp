package br.jus.trt12.paulopinheiro.todoapp.database;

import br.jus.trt12.paulopinheiro.todoapp.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHandler extends Configs {
    private Connection dbConnection;
    private final String connectionURL = "jdbc:derby://" + dbHost + ":" + dbPort + "/" + dbName;

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        if (dbConnection==null)
            dbConnection = DriverManager.getConnection(connectionURL,dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(User user) {
        try {
            String insert = "INSERT INTO " + Const.USERS_TABLE + "("
                    + Const.USERS_FIRSTNAME + ", "
                    + Const.USERS_LASTNAME + ", "
                    + Const.USERS_USERNAME + ", "
                    + Const.USERS_PASSWORD + ", "
                    + Const.USERS_LOCATION + ", "
                    + Const.USERS_GENDER + ") VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.setString(6, user.getGender());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//
//    public void signUpUser(String firstname, String lastname, String username, String password, String location, String gender) {
//        try {
//            String insert = "INSERT INTO " + Const.USERS_TABLE + "("
//                    + Const.USERS_FIRSTNAME + ", "
//                    + Const.USERS_LASTNAME + ", "
//                    + Const.USERS_USERNAME + ", "
//                    + Const.USERS_PASSWORD + ", "
//                    + Const.USERS_LOCATION + ", "
//                    + Const.USERS_GENDER + ") VALUES(?,?,?,?,?,?)";
//            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
//            preparedStatement.setString(1, firstname);
//            preparedStatement.setString(2, lastname);
//            preparedStatement.setString(3, username);
//            preparedStatement.setString(4, password);
//            preparedStatement.setString(5, location);
//            preparedStatement.setString(6, gender);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
