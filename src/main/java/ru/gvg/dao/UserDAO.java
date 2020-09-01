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
    private static Connection connection;

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

    public List<User> getUsers() {
        List<User> usersList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString(1));
                user.setEmail(rs.getString(2));
                user.setPassword(rs.getString(3));
                usersList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }
}
