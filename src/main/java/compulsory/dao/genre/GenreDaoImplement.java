package compulsory.dao.genre;

import compulsory.main.DatabaseConfiguration;
import compulsory.model.Genre;

import java.sql.*;

public class GenreDaoImplement implements GenreDao {

    Connection conn = DatabaseConfiguration.getConnection();

    @Override
    public void createGenreTable() throws SQLException {
        Statement statement = null;

        try {
            statement = conn.createStatement();
            statement.execute("CREATE OR REPLACE TABLE genres (id VARCHAR(30) PRIMARY KEY, nume VARCHAR(50));");

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
    public int addGenre(Genre genre) throws SQLException {
        String query = "INSERT INTO genres VALUES(?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, genre.getId());
        stmt.setString(2, genre.getName());

        int n = stmt.executeUpdate();
        return n;
    }

    @Override
    public void deleteGenre(int id) throws SQLException {
        String query = "DELETE from genres where ID =?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    @Override
    public Genre getGenre(int id) throws SQLException {
        String query = "SELECT * FROM genres where ID=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);

        Genre movie = new Genre();
        ResultSet result = stmt.executeQuery();
        boolean check = false;

        while(result.next()){
            check = true;
            movie.setId(result.getInt("ID"));
            movie.setName(result.getString("NUME"));
        }
        if(check == true){

            return movie;
        }
        else{
            return null;
        }
    }
}

