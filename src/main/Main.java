package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/test";
        Connection connection1 = null;
        try {
            connection1 = DriverManager.getConnection(url);
            Statement statement = connection1.createStatement();
            String SQLMovieCreator = "CREATE TABLE movies (id NUMBER(6) PRIMARY KEY, title VARCHAR2(30) NOT NULL, " +
                    "release_date DATE, duration NUMBER(3), score NUMBER(2));";
            statement.execute(SQLMovieCreator);
            String SQLGenreCreator = "CREATE TABLE genres (id NUMBER(6) PRIMARY KEY, name VARCHAR2(30) NOT NULL);";
            statement.execute(SQLGenreCreator);
            String SQLMovieGenreAssociation = "CREATE TABLE association (movie_id NUMBER(6) REFERENCES movies(id), " +
                    "genre_id NUMBER(6) REFERENCES genres(id));";
            statement.execute(SQLMovieGenreAssociation);
            connection1.close();
        } catch (SQLException exception) {
            System.err.println("Exception: Database not found");
        }
    }
}
