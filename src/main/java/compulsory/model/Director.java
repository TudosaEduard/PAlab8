package compulsory.model;

public class Director {
    private int id;
    private String name;

    public Director(int newId, String newName){
        this.id = newId;
        this.name = newName;
    }

    public Director() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
