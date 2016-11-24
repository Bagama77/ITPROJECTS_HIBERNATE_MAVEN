package model;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    int id;

    @Column(name="name")
    String name;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_Team")
    Team team;

    public Team getTeam() {
        return team;
    }

    public Project(){}

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Project(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public int getIdTeam() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int idProject) {
        this.id = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameProject) {
        this.name = nameProject;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "\r\nProject: " +
                "id=" + id +
                ", name='" + name + "\r\nteam: " + team;
    }
}
