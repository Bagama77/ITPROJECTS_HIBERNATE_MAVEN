package dao;


import model.Project;
import model.Team;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateProjectDAO {

    public Project addProject(String nameProject, String nameTeam, int teamSize){
        System.out.println("Inside addProject method..");
        HibernateTeamDAO hibernateTeamDAO = new HibernateTeamDAO();
        Team team = hibernateTeamDAO.addTeam(nameTeam, teamSize);

        Session session = HibernateSessionfactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Project project = new Project(nameProject, team);//, team.getId());
        System.out.println("New project created.. " + project);
        int projectId= (Integer) session.save(project);
        transaction.commit();
        System.out.println("Project is saved in DB.. transaction was commited:" + transaction.wasCommitted());
        session.close();
        return project;
    }

    public Project removeProject(int idProject2){
        System.out.println("Inside removeProject method..");
        Session session = HibernateSessionfactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Project as P WHERE P.id = ?");
        query.setParameter(0, idProject2);
        List<Team> projectListForDeleting = query.list();

        System.out.println("Project for deleting:" + projectListForDeleting);
        Project project = (Project) session.get(Project.class, idProject2);
        Transaction transaction = session.beginTransaction();
        session.delete(project);
        transaction.commit();
        System.out.println("Project is deleted from DB.. transaction was commited:" + transaction.wasCommitted());
        session.close();

        if(transaction.wasCommitted())
            return project;
        else
            return null;
    }

    public List<Project> projectList(){
        System.out.println("Inside projectList method..");
        Session session = HibernateSessionfactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Project");
        List<Project> projectList = query.list();
        session.close();
        return projectList;
    }
}
