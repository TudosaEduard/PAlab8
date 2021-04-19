package compulsory.dao.actors;

import compulsory.model.Actor;

import java.sql.SQLException;

public interface ActorsDao {
    void createActorTable()
            throws SQLException;
    int addActor(Actor actor)
            throws SQLException;
    Actor  getActors(int id)
            throws SQLException;
}

