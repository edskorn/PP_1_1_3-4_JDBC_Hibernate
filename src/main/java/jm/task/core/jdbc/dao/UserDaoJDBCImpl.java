package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        //Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "CREATE TABLE Users (id BIGINT NOT NULL AUTO_INCREMENT, Name varchar(255), LastName varchar(255), Age TINYINT, PRIMARY KEY (id))";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.commit();
            System.out.println("Table created");
        } catch (SQLException e) {
            Util.RollbackQuietly(connection);
            System.out.println("Table didn't create");
            //throw new RuntimeException(e);
        } finally {
            Util.CloseQuietly(preparedStatement);
            //Util.CloseQuietly(connection);
        }
    }

    public void dropUsersTable() {
        //Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE Users";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.commit();
            System.out.println("Table dropped");
        } catch (SQLException e) {
            Util.RollbackQuietly(connection);
            System.out.println("Table didn't drop");
            //throw new RuntimeException(e);
        } finally {
            Util.CloseQuietly(preparedStatement);
            //Util.CloseQuietly(connection);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        //Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO Users (Name, Lastname, Age) VALUES (?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.execute();
            connection.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            Util.RollbackQuietly(connection);
            System.out.println("User didn't add");
            //throw new RuntimeException(e);
        } finally {
            Util.CloseQuietly(preparedStatement);
            //Util.CloseQuietly(connection);
        }
    }

    public void removeUserById(long id) {
        //Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM Users WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.execute();
            connection.commit();
            System.out.println("User deleted");
        } catch (SQLException e) {
            Util.RollbackQuietly(connection);
            System.out.println("User didn't delete");
            //throw new RuntimeException(e);
        } finally {
            Util.CloseQuietly(preparedStatement);
            //Util.CloseQuietly(connection);
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new LinkedList<>();
        //Connection connection = Util.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Users";
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User usr = new User();
                usr.setId(rs.getLong("id"));
                usr.setName(rs.getString("Name"));
                usr.setLastName(rs.getString("Lastname"));
                usr.setAge(rs.getByte("Age"));
                result.add(usr);
            }

            connection.commit();
            System.out.println("List Users");
        } catch (SQLException e) {
            Util.RollbackQuietly(connection);
            System.out.println("List Users error");
            //throw new RuntimeException(e);
        } finally {
            Util.CloseQuietly(stmt);
            //Util.CloseQuietly(connection);
            Util.CloseQuietly(rs);
        }

        return result;
    }

    public void cleanUsersTable() {
        //Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "TRUNCATE TABLE Users";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.commit();
            System.out.println("Users cleared");
        } catch (SQLException e) {
            Util.RollbackQuietly(connection);
            System.out.println("Users didn't clear");
            //throw new RuntimeException(e);
        } finally {
            Util.CloseQuietly(preparedStatement);
            //Util.CloseQuietly(connection);
        }
    }

}
