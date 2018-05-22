package com.ylinor.greedomatic.access;

import com.ylinor.greedomatic.Greedomatic;
import com.ylinor.greedomatic.beans.WalletBean;
import com.ylinor.greedomatic.handlers.DatabaseHandler;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Singleton
public class WallletDao {

    @Inject
    private DatabaseHandler databaseHandler;
    public void createTableIfNotExist() {
        String query = "CREATE TABLE IF NOT EXISTS wallet (id INT PRIMARY KEY, player VARCHAR(70), money BIGINT)";
        databaseHandler.execute(query);
    }

    public WallletDao() {
    }

    public void insertWallet(WalletBean wallet) {
        String query = "INSERT INTO objective(player,money) values(?,?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = databaseHandler.getDatasource().getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, wallet.getPlayer());
            statement.setInt(2, wallet.getMoney());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            Greedomatic.getInstance().getLogger().error(e.getSQLState());
        } finally {
            databaseHandler.closeConnection(connection,statement,null);
        }
    }
    public Optional<WalletBean> getWallet(WalletBean wallet){
        String query = "SELECT TOP 1 * FROM wallet where player = ?";
        Connection connection = null;
        PreparedStatement statement = null;
         Optional<WalletBean> walletOpt = Optional.empty();
        try {
            connection = databaseHandler.getDatasource().getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1,wallet.getPlayer());
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                walletOpt = Optional.of(new WalletBean(results.getString("player"), results.getInt("money")));
            }
        } catch(SQLException e){
            Greedomatic.getInstance().getLogger().error(e.getSQLState());
        }
        finally {
            databaseHandler.closeConnection(connection,statement,null);
        }
        return walletOpt;
    }
}
