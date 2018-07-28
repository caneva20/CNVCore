package me.caneva20.Core;

import java.sql.*;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class MySQL {
    private Connection connection;
    private Statement statement;
    private String host;
    private String db;
    private String user;
    private String password;
    private int port;

    private boolean isConnected;

    public MySQL(String host, String db, String user, String password, int port) {
        this.host = host;
        this.db = db;
        this.user = user;
        this.password = password;
        this.port = port;

        isConnected = setupDB();

        if (isConnected) {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
                isConnected = false;
            }
        }
    }

    private boolean setupDB () {
        try {
            openConnection(host, db, user, password, port);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private synchronized void openConnection (String host, String db, String user, String pass, int port) throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, user, pass);
    }

    public boolean isConnected() {
        return isConnected;
    }

    public ResultSet query (String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public int update (String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeUpdate();
    }
}
