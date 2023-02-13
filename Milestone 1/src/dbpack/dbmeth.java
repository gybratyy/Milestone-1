package dbpack;

import java.sql.*;

public class dbmeth implements dbinter{
    private Connection connection;

    @Override
    public void connect() {
        try {
            
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Pm1508nvm_*");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ResultSet result(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}