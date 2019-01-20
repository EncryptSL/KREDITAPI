package org.EncryptSL.kredit.api.MySQL;

import com.zaxxer.hikari.HikariDataSource;
import org.EncryptSL.kredit.api.KreditMainClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

    private static HikariDataSource hikariDataSource;

    public void ConnectToDatabase() {
        String database = KreditMainClass.getKreditMainClass().getConfig().getString("Database.MySQL.DATABASE");
        String username = KreditMainClass.getKreditMainClass().getConfig().getString("Database.MySQL.USERNAME");
        String password = KreditMainClass.getKreditMainClass().getConfig().getString("Database.MySQL.PASSWORD");

        hikariDataSource = new HikariDataSource();
        hikariDataSource.setConnectionTestQuery("SELECT - 1");
        hikariDataSource.setJdbcUrl(database);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        hikariDataSource.setPoolName("[KREDIT-HIKARICP]");
        CreateTable();
        try {
            hikariDataSource.setLoginTimeout(10);
            hikariDataSource.setMaximumPoolSize(10);
            hikariDataSource.setMaxLifetime(200);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CreateTable() {
        try {
            Connection connection = hikariDataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS KREDIT (ID INT NOT NULL AUTO_INCREMENT, PLAYER VARCHAR(16), UUID VARCHAR(36), KREDITS INT NOT NULL DEFAULT '0', PRIMARY KEY (ID))");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void CloseConnection() {
        hikariDataSource.close();
    }

    public static HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }
}
