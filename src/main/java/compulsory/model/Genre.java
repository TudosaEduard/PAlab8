package compulsory.model;

public class Genre {

    private int id;
    private String name;

    public Genre() {

    }

    Genre(int newId, String newName) {
        this.id = newId;
        this.name = newName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
