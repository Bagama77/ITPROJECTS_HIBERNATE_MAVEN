package model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "team")
    private List<Developer> developers = new ArrayList<Developer>();

    @Column(name = "size")
    private int size;

    public Team(){}

    public Team(int id, String name, int size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public Team(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public int getId() {
        return id;
    }

    public void setId(int idTeam) {
        this.id = idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameTeam) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setDevelopers(List<Developer> developers) {
        for(Developer dev: developers){
//            developers.add(dev);//???
            addDeveloper(dev);
        }
    }

    public void addDeveloper(Developer developer){
        this.developers.add(developer);
        if(developer.getTeam() != this){
            developer.setTeam(this);
        }
    }

    @Override
    public String toString() {
        String s = "";
        for(Developer d: developers){
            s += "" + d;
        }
        return "idTeam=" + id +
                ", nameTeam='" + name + '\'' +
                ", size=" + size + "\r\n" + "Developers:\r\n" + s;
    }
}
