package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        connection = null;
    }

    private Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        return DriverManager.getConnection(url, login, password);
    }

    private void executeQuery(String query) throws Exception {
        if (connection == null) {
            connection = getConnection();
        }
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
    }

    public void createTable(String tableName) throws Exception {
        String query = String.format("create table if not exists %s();", tableName);
        executeQuery(query);
    }

    public void dropTable(String tableName) throws Exception {
        String query = String.format("drop table if exists %s;", tableName);
        executeQuery(query);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String query = String.format("alter table %s add %s %s;",
                 tableName, columnName, type);
        executeQuery(query);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String query = String.format("alter table %s drop column %s;",
                 tableName, columnName);
        executeQuery(query);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String query = String.format("alter table %s rename column %s to %s;",
                 tableName, columnName, newColumnName);
        executeQuery(query);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        ClassLoader loader = TableEditor.class.getClassLoader();
        try (InputStream io = loader.getResourceAsStream("app.properties")) {
            properties.load(io);
        }
        try (TableEditor te = new TableEditor(properties)) {
            te.createTable("cars_table");
            te.addColumn("cars_table", "name", "text");
            te.addColumn("cars_table", "year_of_production", "int");
            te.dropColumn("cars_table", "name");
            te.renameColumn("cars_table", "year_of_production", "horse_power");
            TableEditor.getTableScheme(te.connection, "cars_table");
        }
    }
}