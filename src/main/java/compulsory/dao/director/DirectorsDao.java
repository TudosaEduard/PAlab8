package compulsory.dao.director;

import compulsory.model.Director;

import java.sql.SQLException;

public interface DirectorsDao {
    void createDirectorTable()
            throws SQLException;
    int addDirector(Director director)
            throws SQLException;
    Director getDirector(int id)
            throws SQLException;
}
