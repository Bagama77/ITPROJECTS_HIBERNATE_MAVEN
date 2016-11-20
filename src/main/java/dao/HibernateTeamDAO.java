package dao;
import model.Developer;
import model.Team;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateTeamDAO {

    public Team addTeam(String nameTeam, int size){
        System.out.println("Inside addTeam method..");
        Session session = HibernateSessionfactory.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer teamId = null;
        transaction = session.beginTransaction();
        Team team = new Team(nameTeam, size);
        System.out.println("New team created.. " + team);



        teamId= (Integer) session.save(team);
        transaction.commit();
        team.setId(teamId);
        System.out.println("Team is saved in DB.. " + team + "\r\ntransaction was commited:" + transaction.wasCommitted());


        team = fetchTeamToDevelopers(team, size);
        session.close();

        return team;
    }

    public Team removeTeam(int idTeam2){
        System.out.println("Inside removeTeam method..");
        Session session = HibernateSessionfactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Team as T WHERE T.id = ?");
        query.setParameter(0, idTeam2);
        List<Team> teamListForDeleting = query.list();

        System.out.println("Teams for deleting:" + teamListForDeleting);
        Team team = (Team) session.get(Team.class, idTeam2);
        Transaction transaction = session.beginTransaction();
        session.delete(team);
        transaction.commit();
        System.out.println("Team is deleted from DB.. transaction was commited:" + transaction.wasCommitted());
        session.close();

        if (transaction.wasCommitted())
            return team;
        else
            return null;
    }

    public Team fetchTeamToDevelopers(Team team, int sizeOfTeam){
        System.out.println("inside fetchTeamToDevelopers method..");

        System.out.println("First we should create developers list..");
        HibernateDeveloperDAO hibernateDeveloperDAO = new HibernateDeveloperDAO();
        List<Developer> developerList = hibernateDeveloperDAO.addDevelopers(team, sizeOfTeam);
        System.out.println("List created:" + developerList.get(0));

        //secondly we should map list of developers to team
        Session session = HibernateSessionfactory.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer teamId = null;
        transaction = session.beginTransaction();

        System.out.println("get our saved Team from DB..");
        Team tempTeam = (Team) session.load(Team.class, team.getId());
        System.out.println("got team from DB:" + team);


        System.out.println("set Developers list to this team..");
        tempTeam.setDevelopers(developerList);
        //resave Team with developers list..
        session.saveOrUpdate(tempTeam);
        System.out.println("Saved in DB tempTeam: " + tempTeam);
        transaction.commit();
//        session.close();

        if(transaction.wasCommitted()) {
            session.close();
            return tempTeam;
        }
        return null;
    }
}
