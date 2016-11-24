package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateSessionfactory {
    private static final SessionFactory sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();

    public HibernateSessionfactory(){}

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
