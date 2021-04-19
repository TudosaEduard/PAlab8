package compulsory.dao.director;

import compulsory.main.DatabaseConfiguration;
import compulsory.model.Director;

import java.sql.*;

public class DirectorsDaoImplement implements DirectorsDao {

    Connection conn = DatabaseConfiguration.getConnection();

    @Override
    public void createDirectorTable() throws SQLException {
        Statement statement = null;

        try {
            statement = conn.createStatement();
            statement.execute("CREATE OR REPLACE TABLE directors( id INT, nume VARCHAR(30)," +
                                        " FOREIGN KEY (id) REFERENCES movies (id))");
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
    public int addDirector(Director director) throws SQLException {
        String query = "INSERT INTO directors VALUES(?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, director.getId());
        stmt.setString(2, director.getName());

        int n = stmt.executeUpdate();
        return n;
    }

    @Override
    public Director getDirector(int id) throws SQLException {
        String query = "SELECT * FROM directors WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);

        Director director = new Director();
        ResultSet resultSet = stmt.executeQuery();
        boolean check = false;

        while (resultSet.next()) {
            check = true;
            director.setId(resultSet.getInt("id"));
            director.setName(resultSet.getString("nume"));
        }

        if (check == true) {
            return director;
        } else return null;
    }
}

