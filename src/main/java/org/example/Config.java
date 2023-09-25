package org.example;

public class Config {
    private Config() {}

    private static String jdbcUrl = "jdbc:mysql://localhost:3306/osbb3";
    private static String username = "2admin";
    private static String password = "sql489";

    public static String getJdbcUrl() {
        return jdbcUrl;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
