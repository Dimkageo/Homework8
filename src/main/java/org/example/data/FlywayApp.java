package org.example.data;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.example.Config.*;

public class FlywayApp implements Closeable {

    private Connection conn = null;

    private void fwMigration(){
        System.out.println( "Flyway migration execute" );

        org.flywaydb.core.Flyway.configure()
                .dataSource(jdbcUrl, username, password)
                .locations("classpath:flyway/scripts")
                .load()
                .migrate();
    }
    public FlywayApp init() throws SQLException {

        fwMigration();
        System.out.println("Мигрировали");

        conn = DriverManager.getConnection(jdbcUrl, username, password);

        return this;
    }

    @Override
    public void close() throws IOException {
        try {
            conn.close();
            conn = null;
        }catch (SQLException e){
            throw new IOException(e);
        }
    }
}
