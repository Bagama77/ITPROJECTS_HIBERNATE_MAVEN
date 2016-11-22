package controler;

import dao.HibernateProjectDAO;
import dao.HibernateSessionfactory;
import model.Project;
import view.MainMenu;
import view.MenuAddProject;
import view.MenuDeleteProject;
import view.ViewAllProjects;

import java.util.ArrayList;
import java.util.Scanner;

public class MainController {
    HibernateSessionfactory hibernateSessionfactory = new HibernateSessionfactory();
    MainMenu mainMenu = new MainMenu();
    MenuAddProject menuAddProject = new MenuAddProject();
    MenuDeleteProject menuDeleteProject = new MenuDeleteProject();
    ViewAllProjects viewAllProjects = new ViewAllProjects();

    HibernateProjectDAO hibernateProjectDAO = new HibernateProjectDAO();

    public void mainLogic(){
        while(true) {
            System.out.println(mainMenu.showMainMenu());
            System.out.println("Please enter your choice:");
            int mainMenuItem = getChoice(1);
            switch (mainMenuItem) {
                case 1:
                    System.out.println(menuAddProject.showMenuAddProject());
                    menuAddProjectLogic();
                    break;
                case 2:
                    System.out.println(menuDeleteProject.showMenuDeleteProject());
                    menuDeleteProjectLogic();
                    break;
                case 3:
                    System.out.println(viewAllProjects.showViewAllProjects());
                    menuListProjects();
                    break;
                case 4:
                    System.out.println("Exit..");
//                    hibernateProjectDAO.closeConnection();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please choose item from 1 to 4");
                    break;
            }
        }
    }

    public void menuAddProjectLogic(){
        String[] newProjectInitialData = getChoice();
        String tempSizeString = newProjectInitialData[2].trim();
        int size = Integer.valueOf(tempSizeString);
        hibernateProjectDAO.addProject(newProjectInitialData[0].trim(), newProjectInitialData[1].trim(), size);
    }

    public void menuDeleteProjectLogic(){
        int idOfDeletingProject = getChoice(1);
        hibernateProjectDAO.removeProject(idOfDeletingProject);
    }

    public void menuListProjects(){
        ArrayList<Project> projectArrayList = (ArrayList<Project>)hibernateProjectDAO.projectList();
        for (Project p: projectArrayList){
            System.out.println(p);
        }
    }

    public int getChoice(int integer){
        Scanner sc=new Scanner(System.in);
        int choice = sc.nextInt();
        return choice;
    }

    public String[] getChoice(){
        Scanner sc=new Scanner(System.in);
        String choice = sc.nextLine();
        String[] strArr = choice.split(",", 3);
        return strArr;
    }

}
