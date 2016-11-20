package model;

import javax.persistence.*;

@Entity
@Table(name="developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="speciality")
    private String speciality;

    @Column(name="salary")
    private int salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Team")
    private Team team;

    public Developer(){}

    public Developer(String name, String speciality, int salary, Team team) {
        this.name = name;
        this.speciality = speciality;
        this.salary = salary;
        this.team = team;
    }

    public Developer(String name, String speciality, int salary) {
        this.name = name;
        this.speciality = speciality;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        if(!team.getDevelopers().contains(this)){
            team.getDevelopers().add(this);
        }
    }

    @Override
    public String toString() {
        System.out.printf("\t\t\tid=%d , nameDeveloper=%-20s , \tspeciality=%-10s , \tsalary=%d, \tid_Team=%d \r\n, ",
                id, name, speciality, salary, team.getId());
        return "";
    }


}
