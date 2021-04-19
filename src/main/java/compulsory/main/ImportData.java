package compulsory.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImportData {
    /**
     * Citim dintr-un fisier .csv linie cu linie, despartim coloanele prin "," si creem un vector de String-uri care contine cate un string pentru fiecare element din fisier csv
     * pe care il vom insera in baza de date
     */

    Connection conn = DatabaseConfiguration.getConnection();

    public void createImbdTable() {
        Statement statement = null;

        try {
            statement = conn.createStatement();
            statement.execute("CREATE OR REPLACE TABLE imbd (title VARCHAR(30) PRIMARY KEY, ord INT, nume VARCHAR(50));");
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

    public void impData() {
        String csvFile = "C:\\Users\\Eduard\\Desktop\\IMDb movies.csv";

        int batchSize = 20;

        try {
            //conn.setAutoCommit(false);
            String query = "INSERT INTO imdb VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            BufferedReader lineRead = new BufferedReader(new FileReader(csvFile));
            String lineText = null;

            int count = 0;

            lineRead.readLine();

            while ((lineText = lineRead.readLine()) != null) {

                List <String> data = new ArrayList <String>();
                data = Arrays.asList(lineText.split(","));

                String imdbTitle = data.get(0);
                String newOrdering = data.get(1);
                String imdbName = data.get(2);

                stmt.setString(1, imdbTitle);

                Integer integer = Integer.parseInt(newOrdering);

                stmt.setInt(2, integer);
                stmt.setString(3, imdbName);

                stmt.addBatch();


                if (count % batchSize == 0) {
                    stmt.executeBatch();
                }

            }
            lineRead.close();
            stmt.executeBatch();
            conn.commit();
            conn.close();

        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();

            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}