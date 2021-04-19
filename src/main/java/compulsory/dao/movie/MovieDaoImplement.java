package compulsory.dao.movie;

import compulsory.main.DatabaseConfiguration;
import compulsory.model.Movie;

import java.sql.*;

public class MovieDaoImplement implements MovieDao {

    Connection conn = DatabaseConfiguration.getConnection();

    @Override
    public void createMovieTable() throws SQLException {
        Statement statement = null;

        try {
            statement = conn.createStatement();
            statement.execute("CREATE OR REPLACE TABLE movies (id INT PRIMARY KEY, title VARCHAR(50), " +
                    "release_date DATE, duration DECIMAL(3,2), score DECIMAL(2,1));");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int add(Movie movie) throws SQLException {
        String query = "INSERT INTO movies VALUES (?, ?, ?, ?, ?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, movie.getId());
        stmt.setString(2, movie.getTitle());
        stmt.setString(3,  movie.getReleaseDate());
        stmt.setInt(4, movie.getDuration());
        stmt.setInt(5, movie.getScore());
        int n = stmt.executeUpdate();
        return n;
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE from movies where ID =?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();

    }

    @Override
    public Movie getMovie(int id) throws SQLException {

        String query = "SELECT * FROM movies where ID=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);

        Movie movie = new Movie();
        ResultSet result = stmt.executeQuery();
        boolean check = false;

        while(result.next()){
            check = true;
            movie.setId(result.getInt("ID"));
            movie.setTitle(result.getString("TITLE"));
            movie.setReleaseDate(result.getString("RELEASE_DATE"));
            movie.setDuration(result.getInt("DURATION"));
            movie.setScore(result.getInt("SCORE"));
        }
        if(check == true){

            return movie;
        }
        else{
            return null;
        }
    }

}
