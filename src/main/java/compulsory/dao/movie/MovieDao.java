package compulsory.dao.movie;

import compulsory.model.Movie;

import java.sql.SQLException;


public interface MovieDao {
    void createMovieTable()
            throws SQLException;
    int add(Movie movie)
            throws SQLException;
    void delete(int id)
            throws SQLException;
    Movie  getMovie(int id)
            throws SQLException;

}