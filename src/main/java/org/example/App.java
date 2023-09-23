package org.example;

import org.example.data.DataExporter;
import org.example.data.FlywayApp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args )
    {
        System.out.println( "The program has started" );

        try (FlywayApp flywayApp = new FlywayApp().init()){

            List<String> owners = flywayApp.readOwners();
            for (String name : flywayApp.readOwners())
                System.out.println(name);

            DataExporter exporter = new DataExporter();
            exporter.saveToTextFile(owners, "Owners.txt");
        }catch (SQLException | IOException e){
            throw new RuntimeException(e);
        }

    }
}
