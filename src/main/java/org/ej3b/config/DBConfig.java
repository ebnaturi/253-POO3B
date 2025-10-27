package org.ej3b.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class DBConfig {
    private static HikariDataSource dataSource;
    public static DataSource getDataSource() {
        if (dataSource == null) {
            String host = "localhost";
            String dbName="pruebas3b";
            String user="root";
            String password="";
            String url = "jdbc:mysql://" + host + ":3306/" + dbName;

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(password);
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");

            dataSource = new HikariDataSource(config);

        }
        return dataSource;
    }

}
