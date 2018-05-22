package com.ylinor.greedomatic.handlers;

import com.ylinor.greedomatic.Greedomatic;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.sql.SqlService;

import javax.inject.Singleton;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Singleton
public class DatabaseHandler {
    private String JDBC_URL = "jdbc:sqlite:./greedomatic.db";
    private SqlService sqlService;
    public DataSource getDatasource() throws SQLException {
        if (sqlService == null) {
            sqlService = Sponge.getServiceManager().provide(SqlService.class).get();
        }
        return sqlService.getDataSource(JDBC_URL);
    }

    public void execute(String query){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getDatasource().getConnection();
            statement = connection.prepareStatement(query);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            Greedomatic.getInstance().getLogger().error("Error while creating respawning dialog table : " + e.getMessage());
        } finally {
            closeConnection(connection, statement, null);
        }
    }


    public DatabaseHandler() {
    }

    /**
     * Close a database connection
     * @param connection Connection to close
     */
    public void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                Greedomatic.getInstance().getLogger().error("Error while closing result set : " + e.getMessage());
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                Greedomatic.getInstance().getLogger().error("Error while closing statement : " + e.getMessage());
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                Greedomatic.getInstance().getLogger().error("Error while closing connection : " + e.getMessage());
            }
        }
    }
}
