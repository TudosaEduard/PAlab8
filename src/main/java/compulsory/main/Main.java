package compulsory.main;

import compulsory.dao.actors.ActorsDaoImplement;
import compulsory.dao.director.DirectorsDaoImplement;
import compulsory.dao.genre.GenreDaoImplement;
import compulsory.dao.movie.MovieDaoImplement;
import compulsory.model.Actor;
import compulsory.model.Director;
import compulsory.model.Genre;
import compulsory.model.Movie;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Movie movie = new Movie();
        String str = "2022-09-01";

        /** Setam toate datele pentru film*/

        movie.setId(1);
        movie.setTitle("Cum sa ai note mari la java?");
        movie.setScore(1);
        movie.setDuration(1);
        movie.setReleaseDate(str);

        /** Cream un obiect de tip MovieDaoImplement care o sa acceseze metodele respective*/
        MovieDaoImplement movieDao = new MovieDaoImplement();
        movieDao.createMovieTable();
        movieDao.add(movie);
        System.out.println(movieDao.getMovie(1));
        movieDao.delete(42);

        /** Cream un obiect de tip Genre si GenreDaoImplement*/

        Genre genre = new Genre();
        GenreDaoImplement genreDao = new GenreDaoImplement();

        genreDao.createGenreTable();
        genre.setId(2);
        genre.setName("Drama");
        genreDao.addGenre(genre);
        System.out.println(genreDao.getGenre(2));
        genreDao.deleteGenre(1);

        /** Creeam un obiect de tip actor si adaugam in tabela actors*/

        Actor actor = new Actor();
        actor.setName("Studentul");
        actor.setId(1);

        ActorsDaoImplement actorsDao= new ActorsDaoImplement();
        actorsDao.createActorTable();
        actorsDao.addActor(actor);
        System.out.println(actorsDao.getActors(1));

        /** Creeam un obiect de tip director si adaugam in tabela directors*/

        Director director = new Director();
        director.setName("Frasinaru");
        director.setId(1);

        DirectorsDaoImplement directorsDao= new DirectorsDaoImplement();
        directorsDao.createDirectorTable();
        directorsDao.addDirector(director);
        System.out.println(directorsDao.getDirector(1));

        ImportData importData = new ImportData();
        importData.createImbdTable();
        //importData.impData();

    }
}

