package dao;
import model.Developer;
import model.Team;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateTeamDAO {

    public Team addTeam(String nameTeam, int size){
        Session session = HibernateSessionfactory.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer teamId = null;
        transaction = session.beginTransaction();
        Team team = new Team(nameTeam, size);
        //New team created.. " + team

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
        //First we should create developers list..
        HibernateDeveloperDAO hibernateDeveloperDAO = new HibernateDeveloperDAO();
        List<Developer> developerList = hibernateDeveloperDAO.addDevelopers(team, sizeOfTeam);
        //List created:" + developerList.get(0));

        //secondly we should map list of developers to team
        Session session = HibernateSessionfactory.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer teamId = null;
        transaction = session.beginTransaction();

        //get our saved Team from DB.."
        Team tempTeam = (Team) session.load(Team.class, team.getId());

        //set Developers list to this team..
        tempTeam.setDevelopers(developerList);
        //resave Team with developers list..
        session.saveOrUpdate(tempTeam);
         transaction.commit();

        if(transaction.wasCommitted()) {
            session.close();
            return tempTeam;
        }
        return null;
    }
}
