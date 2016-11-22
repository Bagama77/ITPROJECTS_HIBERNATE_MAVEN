package controler;
import dao.HibernateDeveloperDAO;
import dao.HibernateProjectDAO;
import dao.HibernateSessionfactory;
import dao.HibernateTeamDAO;
import model.Developer;
import model.Project;
import model.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class DeveloperRunner {
    private static HibernateSessionfactory hibernateSessionfactory;
    private static HibernateDeveloperDAO hibernateDeveloperDAO;
    private static HibernateTeamDAO hibernateTeamDAO;
    private static HibernateProjectDAO hibernateProjectDAO;

    public static void main(String[] args) {
        hibernateSessionfactory = new HibernateSessionfactory();
        hibernateDeveloperDAO = new HibernateDeveloperDAO();
        hibernateTeamDAO = new HibernateTeamDAO();
        hibernateProjectDAO = new HibernateProjectDAO();

//        System.out.println("main: Adding new project");
//        hibernateProjectDAO.addProject("New Hib Project2", "team of new Hib project2", 5);
        System.out.println("Deleting project:" + hibernateProjectDAO.removeProject(1));
        ArrayList<Project> projectArrayList = (ArrayList<Project>)hibernateProjectDAO.projectList();
        for(Project p: projectArrayList){
            System.out.println(p);
        }

//        System.out.println("main: Adding new team..");
//        hibernateTeamDAO.addTeam("New hibernate team", 2);

//        System.out.println("main:Deleting hibernateTeam.. ");
//        hibernateTeamDAO.removeTeam(4);

//        System.out.println("Adding new developer to team..");
//        Integer idDeveloper = hibernateDeveloperDAO.addDeveloper(1);


//        System.out.println("List of developers");
//        List<Developer> developers = developerRunner.listDevelopers();
//        for (Developer developer : developers) {
//            System.out.println(developer);
//        }
//        System.out.println("===================================");
//        System.out.println("Removing Some Developer and updating developer");
//        developerRunner.updateDeveloper(10, 3);
//        developerRunner.removeDeveloper(41);
//
//        System.out.println("Final list of developers");
//        developers = developerRunner.listDevelopers();
//        for (Developer developer : developers) {
//            System.out.println(developer);
//        }
        System.out.println("===================================");
    }
//        public Integer addDeveloper(String nameDeveloper, String speciality, int salary) {
//            Session session = sessionFactory.openSession();
//            Transaction transaction = null;
//
//            transaction = session.beginTransaction();
//            Developer developer = new Developer(nameDeveloper, speciality, salary);
//            System.out.println("Created developer.." + developer);
//            Integer idDeveloper = (Integer) session.save(developer);
//            transaction.commit();
//            session.close();
//            return idDeveloper;
//        }
//
//        public List<Developer> listDevelopers() {
//            Session session = this.sessionFactory.openSession();
//            Transaction transaction = null;
//
//            transaction = session.beginTransaction();
//            List<Developer> developers = session.createQuery("FROM model.Developer").list();
//
//            transaction.commit();
//            session.close();
//            return developers;
//        }
//
//        public void updateDeveloper(int developerId, int salary) {
//            Session session = this.sessionFactory.openSession();
//            Transaction transaction = null;
//
//            transaction = session.beginTransaction();
//            Developer developer = (Developer) session.get(Developer.class, developerId);
//            developer.setSalary(salary);
//            session.update(developer);
//            transaction.commit();
//            session.close();
//        }
//
//        public void removeDeveloper(int developerId) {
//            Session session = this.sessionFactory.openSession();
//            Transaction transaction = null;
//
//            transaction = session.beginTransaction();
//            Developer developer = (Developer) session.get(Developer.class, developerId);
//            session.delete(developer);
//            transaction.commit();
//            session.close();
//        }





}


