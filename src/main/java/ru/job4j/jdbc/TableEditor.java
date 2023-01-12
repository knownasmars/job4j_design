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
        try {
            connection = getConnection(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection(Properties properties) throws Exception {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, username, password);
    }

    public void createStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                "create table if not exists %s(%s, %s);",
                tableName,
                "id serial primary key",
                "name text"
        );
        createStatement(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "drop table if exists %s;", tableName
        );
        System.out.println("Таблица " + tableName + " удалена");
        createStatement(sql);
    }

    public void addColumn(
            String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "alter table if exists %s add column %s %s;",
                tableName, columnName, type
        );
        System.out.println("Таблица " + tableName + " обновлена");
        createStatement(sql);
    }

    public void dropColumn(
            String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "alter table if exists %s drop column %s;",
                tableName, columnName
        );
        System.out.println("Столбец " + columnName + " удален");
        createStatement(sql);
    }

    public void renameColumn(
            String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format(
                "alter table if exists %s rename column %s to %s;",
                tableName, columnName, newColumnName
        );
        System.out.println("Столбец " + columnName + " удален");
        createStatement(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
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

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream(
                        "resources.properties")) {
            properties.load(in);
            TableEditor te = new TableEditor(properties);
            String tableName = "demo_table";
            te.createTable(tableName);
            System.out.println(te.getTableScheme(tableName));
            te.dropTable(tableName);
            System.out.println(te.getTableScheme(tableName));
            te.createTable(tableName);
            System.out.println(te.getTableScheme(tableName));
            te.addColumn(tableName, "age", "integer");
            System.out.println(te.getTableScheme(tableName));
            te.dropColumn(tableName, "age");
            System.out.println(te.getTableScheme(tableName));
            te.renameColumn(tableName, "id", "id_number");
            System.out.println(te.getTableScheme(tableName));
        } catch (SQLException sqlex) {
            sqlex.getErrorCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}