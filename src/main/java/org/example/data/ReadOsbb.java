package org.example.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.Config.*;

public class ReadOsbb {

    private static ReadOsbb instance;
    private static final String sqlQuery = """
            Select
            apartment_owners.full_name,
            apartment_owners.email,
            people_rols.rois,
            apartaments.number_apartaments,
            apartaments.square,
            buildings.adress,
            buildings.house_number,
            apartment_teants.right_of_way
                    
            From apartment_owners
                    
            inner join people_rols on people_rols.id_people_rols=apartment_owners.people_rols_id
            inner join apartaments on apartaments.id_apartaments=apartment_owners.id_apartment_owners
            inner join buildings on buildings.id_buildings=apartaments.buildings_id
            inner join apartment_teants on apartment_teants.id_teants=apartment_owners.id_apartment_owners
                    
            WHERE    apartment_owners.full_name NOT IN (  SELECT apartment_owners.full_name
                    FROM    apartment_owners
                    GROUP BY    full_name
                    HAVING   COUNT(*) > 1 )
            AND   apartment_teants.right_of_way = false
            AND   apartment_owners.full_name = apartment_teants.full_name""";

    public static synchronized ReadOsbb getInstance() {
        if (instance == null)
            instance = new ReadOsbb();
        return instance;
    }
    private Connection conn = null;
    public ReadOsbb init() throws SQLException {
        conn = DriverManager.getConnection(getJdbcUrl(), getUsername(), getPassword());
        return this;
    }

    public List<String> readOwners() {

        final ArrayList<String> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                Отримання даних з результатів
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String rois = resultSet.getString("rois");
                String numberApartments = resultSet.getString("number_apartaments");
                String square = resultSet.getString("square");
                String address = resultSet.getString("adress");
                String houseNumber = resultSet.getString("house_number");
                String rightOfWay = resultSet.getInt("right_of_way") == 0 ? "false" : "true";
//                Форматування рядка та додавання його до списку
                String row = String.format("%s, %s, %s, %s, %s, %s, %s, %s",
                        fullName, email, rois, numberApartments, square, address, houseNumber, rightOfWay);
                result.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
