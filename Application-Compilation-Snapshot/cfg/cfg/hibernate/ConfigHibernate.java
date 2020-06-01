package cfg.hibernate;
  
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConfigHibernate {

    public final static String HIBERNATE_CONFIG = "/cfg/hibernate/hibernate.cfg.xml"; 
    /*
    CREATE AND RETURN SESSION FACTORY
     */
    public static SessionFactory createSessionFactory(String config, Class entityClass) {        
        return new Configuration().configure(config).addAnnotatedClass(entityClass).buildSessionFactory(); 
        
    }
}
