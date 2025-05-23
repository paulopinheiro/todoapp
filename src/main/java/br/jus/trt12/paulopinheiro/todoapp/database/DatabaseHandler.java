package br.jus.trt12.paulopinheiro.todoapp.database;

import br.jus.trt12.paulopinheiro.todoapp.model.Task;
import br.jus.trt12.paulopinheiro.todoapp.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    public User getUserFromUsername(String username) {
        User user = null;
        try {            
            String query = "SELECT * FROM " + Const.USERS_TABLE + " "
                         + "WHERE " + Const.USERS_USERNAME + "=?";

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt(Const.USERS_USERID),
                                rs.getString(Const.USERS_FIRSTNAME),
                                rs.getString(Const.USERS_LASTNAME),
                                rs.getString(Const.USERS_USERNAME),
                                rs.getString(Const.USERS_PASSWORD),
                                rs.getString(Const.USERS_LOCATION),
                                rs.getString(Const.USERS_GENDER));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public void insertTask(Task task) {
        try {
            String insert = "INSERT INTO " + Const.TASKS_TABLE + "("
                    + Const.TASKS_USERID + ", "
                    + Const.TASKS_TASK + ", "
                    + Const.TASKS_DESCRIPTION + ", "
                    + Const.TASKS_DATECREATED + ") VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, task.getUserid());
            preparedStatement.setString(2, task.getTask());
            preparedStatement.setString(3,task.getDescription());
            preparedStatement.setTimestamp(4, task.getDateCreated());

            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Task> getTasksByUser(User user) {
        List<Task> taskList = new ArrayList<>();
        try {            
            String query = "SELECT * FROM " + Const.TASKS_TABLE + " "
                         + "WHERE " + Const.TASKS_USERID + "=?";

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, user.getUserid());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Task task = new Task(rs.getInt(Const.TASKS_TASKID),
                                     rs.getInt(Const.TASKS_USERID),
                                     rs.getString(Const.TASKS_TASK),
                                     rs.getString(Const.TASKS_DESCRIPTION),
                                     rs.getTimestamp(Const.TASKS_DATECREATED)
                                     );
                taskList.add(task);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taskList;
    }

    public void deleteTask(Task task) {
        try {
            System.out.println("Delete " + task + " task");
            
            String delete = "DELETE FROM " + Const.TASKS_TABLE
                    + " WHERE " + Const.TASKS_TASKID + " = ?";
            
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setInt(1, task.getTaskid());

            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTask(Task task) {
        try {
            String update = "UPDATE " + Const.TASKS_TABLE + " SET "
                    + Const.TASKS_USERID + " = ?,"
                    + Const.TASKS_TASK + " = ?, "
                    + Const.TASKS_DESCRIPTION + " = ?, "
                    + Const.TASKS_DATECREATED + " = ? "
                    + "WHERE " + Const.TASKS_TASKID + " = ?";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
            preparedStatement.setInt(1, task.getUserid());
            preparedStatement.setString(2, task.getTask());
            preparedStatement.setString(3,task.getDescription());
            preparedStatement.setTimestamp(4, task.getDateCreated());
            preparedStatement.setInt(5, task.getTaskid());

            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
