package Controllers;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DbConnectionController {
    public static StandardServiceRegistry registry = null;
    public static SessionFactory sessionFactory = null;

    public static void startConnectionWithDb(){
        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        }catch(Exception e){
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    public static void stopConnectionWithDb(){
        if (sessionFactory != null)
            sessionFactory.close();
    }
}
