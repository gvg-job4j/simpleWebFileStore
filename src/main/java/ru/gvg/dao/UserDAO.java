package ru.gvg.dao;

import org.springframework.stereotype.Component;
import ru.gvg.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Valeriy Gyrievskikh
 * @since 01.09.2020
 */
@Component
public class UserDAO {
    /**
     * Connection to the database.
     */
    private static Connection connection;

    /**
     * Method establishes a connection to the database.
     */
    static {
        String url = null;
        String user = null;
        String password = null;
        try (InputStream in = UserDAO.class.getClassLoader()
                .getResourceAsStream("persistence.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (url != null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method retrieves data from all users in the database.
     *
     * @return Users list.
     */
    public List<User> getUsers() {
        List<User> usersList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = createUser(rs);
                usersList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    /**
     * Method create new user from database data.
     *
     * @param rs Data from database.
     * @return New user.
     * @throws SQLException Possible exception.
     */
    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setName(rs.getString(2));
        user.setEmail(rs.getString(3));
        user.setPassword(rs.getString(4));
        return user;
    }

    /**
     * Method adds a new record with user data to the database.
     *
     * @param user User data.
     * @return Result of the operation.
     */
    public boolean addUser(User user) {
        boolean created = false;
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users(name, email, pass)"
                    + " VALUES (?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            created = ps.executeUpdate() != 0;
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return created;
    }

    /**
     * Method reads a record with user data from the database.
     *
     * @param email User email.
     * @return User data.
     */
    public User getUser(String email) {
        User user = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = createUser(rs);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Method updates user data in the database.
     *
     * @param user User data.
     * @return Result of the operation.
     */
    public boolean updateUser(User user) {
        boolean updated = false;
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE users "
                    + "SET name = ?, pass = ? WHERE email = ?");
//                    + "SET name = '" + user.getName()
//                    + "'" + ", password = '" + user.getPassword()
//                    + "'" + "where email = '" + user.getEmail() + "'");
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            updated = ps.executeUpdate() != 0;
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    /**
     * Method deletes user data from the database.
     *
     * @param user User data.
     * @return Result of the operation.
     */
    public boolean deleteUser(User user) {
        boolean deleted = true;
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE email = ?");
            ps.setString(1, user.getEmail());
            deleted = ps.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }
}
