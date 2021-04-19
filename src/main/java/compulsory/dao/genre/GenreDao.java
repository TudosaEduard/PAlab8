package compulsory.dao.genre;

import compulsory.model.Genre;

import java.sql.SQLException;


public interface GenreDao {

    void createGenreTable()
            throws SQLException;
    int addGenre(Genre genre)
            throws SQLException;
    void deleteGenre(int id)
            throws SQLException;
    Genre  getGenre(int id)
            throws SQLException;

}
