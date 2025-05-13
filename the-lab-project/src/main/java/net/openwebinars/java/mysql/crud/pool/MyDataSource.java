package net.openwebinars.java.mysql.crud.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MyDataSource {

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource dataSource;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/TiendaM3?allowPublicKeyRetrieval=true" +
                "&useSSL=false&useUnicode=true&serverTimezone=Europe/Madrid");
        config.setUsername("root");        // <-- Cambia "user" por tu usuario MySQL real
        config.setPassword("root");    // <-- Cambia "password" por tu contraseña real
        config.setMaximumPoolSize(1);

        // Configuración recomendada para MySQL
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);
    }

    private MyDataSource() {}

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
