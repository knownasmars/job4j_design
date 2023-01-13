package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties cfg;

    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(x -> {
                String[] lines = x.split(";");
                if (lines.length < 2) {
                    throw new IllegalArgumentException(
                            "Number of arguments has to be equal two");
                }
                if (lines[0].isBlank() || lines[1].isBlank()) {
                    throw new IllegalArgumentException(
                            "Arguments cannot be empty"
                    );
                }
                users.add(
                        new User(lines[0], lines[1]));
            });
        }
        return users;
    }

    public void save(List<User> users)
            throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            String sql = String.format(
                    "create table if not exists %s(%s, %s, %s);",
                    "users",
                    "id serial primary key",
                    "name text",
                    "email text"
            );
            Statement statement = cnt.createStatement();
            statement.execute(sql);
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "insert into users(name, email) values(?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in =
                     ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}