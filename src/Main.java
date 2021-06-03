import Enums.CoachRole;
import Models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;

        Worker worker1 = new Worker("Marcin", "Nowak", 4321, LocalDate.now());
        Worker worker2 = new Worker("Marcel", "Kowal", 6321, LocalDate.now());

        Supporter supporter1 = new Supporter("Jan", "Kibic", true);
        Supporter supporter2 = new Supporter("Maciek", "Los", false);

        Dietician dietician = new Dietician("Marek", "Dieta", 3333, LocalDate.now());

        Footballer footballer1 = null, footballer2 = null;
        try {
            footballer1 = new Footballer("Cristiano", "Ronaldo", 40000, LocalDate.now(), "Striker", 7);
            footballer2 = new Footballer("Robert", "Lewandowski", 30000, LocalDate.now(), "Striker", 9);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Coach coach1 = new Coach("Paweł", "Trener", 10000, LocalDate.now(), CoachRole.MAIN_COACH);

        Pitch pitch1 = new Pitch(400, "Stawowa 12");

        Training training1 = new Training(LocalDateTime.now(), pitch1);
        Training training2 = new Training(LocalDateTime.now(), pitch1);

        try {
            training1.addCoach(coach1);
            training1.addFootballer(footballer1);
            training1.addFootballer(footballer2);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        footballer1.addDietician(dietician);
        dietician.addFootballer(footballer2);

        League league1 = new League("La liga", "Spain");

        Club club1 = new Club("Real Madrid");

        Match match1 = new Match(LocalDateTime.now(), club1,3, 3);

        try {
            //club1.addLeague(league1, 3, 2, 3, 4, 4);
            league1.addClub(club1, 2, 2, 3, 4, 2);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(worker1);
            session.save(worker2);
            session.save(supporter1);
            session.save(supporter2);
            session.save(dietician);
            session.save(coach1);
            session.save(footballer1);
            session.save(footballer2);
            session.save(training1);
            session.save(training2);
            session.save(pitch1);
            session.save(league1);
            session.save(club1);
            session.save(match1);

            List<Person> personsFromDb = session.createQuery("from Person").list();
            for ( var person : personsFromDb) {
                System.out.println(person);
            }
            List<Club> clubsFromDb = session.createQuery("from Club").list();
            for ( var club : clubsFromDb) {
                System.out.println(club);
            }
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}
