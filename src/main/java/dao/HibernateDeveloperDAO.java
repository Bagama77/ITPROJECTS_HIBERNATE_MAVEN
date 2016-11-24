package dao;
import model.Developer;
import model.Team;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HibernateDeveloperDAO {

    public Developer addDeveloper(Team team){

        Calendar calendar = Calendar.getInstance();
        String name_Developer = "" + calendar.getTimeInMillis();
        int random = getRandomNumberInRange(1, 5);
        String speciality = getSpeciality(random);
        int salary = getRandomNumberInRange(500, 3000);

        Developer developer = new Developer(name_Developer, speciality, salary, team);
        return developer;
    }

    public List<Developer> addDevelopers(Team team, int size){
        Developer developer;
        List<Developer> developerList = new ArrayList<Developer>(size);
        for(int i = 0; i < size; i++){
            developer = addDeveloper(team);
            developerList.add(developer);
        }
        return developerList;
    }

    public void removeDevelopers(int idTeam){
        Session session = HibernateSessionfactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM developers WHERE team =: idTeam");
        Transaction transaction = null;
        transaction = session.beginTransaction();
        List developers = query.list();
        for(int i = 0; i <developers.size(); i++){
            session.delete(developers.get(i));
            transaction.commit();
        }
        session.close();
    }

    private String getSpeciality(int random){
        switch (random) {
            case 1:
                return "Java";
            case 2:
                return "C++";
            case 3:
                return "HTML/CSS";
            case 4:
                return "Python";
            case 5:
                return "PHP";
            default:
                return "fullstack";
        }
    }

    public int getRandomNumberInRange(int min, int max) {

        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        int random = (int)(Math.random() * ((max - min) + 1)) + min;
        return random;
    }
}
