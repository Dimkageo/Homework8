package org.example.data;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.flywaydb.core.Flyway;
import org.example.Config;

public class FlywayApp implements Closeable {

    private Connection conn = null;

    private void fwMigration() {
        System.out.println("Flyway migration execute");

        Flyway.configure()
                .dataSource(Config.getJdbcUrl(), Config.getUsername(), Config.getPassword())
                .locations("classpath:flyway/scripts")
                .load()
                .migrate();
    }

    public FlywayApp init() throws SQLException {

        fwMigration();
        System.out.println("Migration completed");

        conn = DriverManager.getConnection(Config.getJdbcUrl(), Config.getUsername(), Config.getPassword());

        return this;
    }

    @Override
    public void close() throws IOException {
        try {
            conn.close();
            conn = null;
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }
}
