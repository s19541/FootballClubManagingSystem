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



        Person person1 = new Person("Jan", "Kibic");
        Person person2 = new Person("Marcel", "Kowal");
        Person person3 = new Person("Marcin", "Nowak");
        Person person4 = new Person("Marcel", "Kowal");
        Person person5 = new Person("Cristiano", "Ronaldo");
        Person person6 = new Person("Robert", "Lewandowski");
        Supporter supporter1 = null, supporter2 = null;
        try {
            supporter1 = Supporter.createSupporter(person1, true);
            supporter2 = Supporter.createSupporter(person2, false);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


        Footballer footballer1 = null, footballer2 = null;
        Worker worker1 = null;
        Coach coach1 = null;
        Dietician dietician1 = null;
        try {
            worker1 = Worker.createWorker(person2 , 4321, LocalDate.now());
            dietician1 = Dietician.createDietician(person3, 3333, LocalDate.now());
            coach1 = Coach.createCoach(person4, 10000, LocalDate.now(), CoachRole.MAIN_COACH);
            footballer1 = Footballer.createFootballer(person5, 50000, LocalDate.now(), "Striker", 7);
            footballer2 = Footballer.createFootballer(person6, 30000, LocalDate.now(), "Striker", 9);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

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

        footballer1.addDietician(dietician1);
        dietician1.addFootballer(footballer2);

        Season season1 = new Season(LocalDate.of(2020, 12 ,1 ), LocalDate.of(2021, 12 , 1));

        Club club1 = new Club("Real Madrid");

        Match match1 = new Match(LocalDateTime.now(), 20, club1,3, 3);

        try {
            //club1.addLeague(league1, 3, 2, 3, 4, 4);
            season1.addClub(club1, 2, 2, 3, 4, 2);
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

            session.save(person1);
            session.save(person2);
            session.save(person3);
            session.save(person4);
            session.save(person5);
            session.save(person6);
            session.save(worker1);
            session.save(supporter1);
            session.save(supporter2);
            session.save(dietician1);
            session.save(coach1);
            session.save(footballer1);
            session.save(footballer2);
            session.save(training1);
            session.save(training2);
            session.save(pitch1);
            session.save(season1);
            session.save(club1);
            session.save(match1);

            List<Person> personsFromDb = session.createQuery("from Person").list();
            for ( var person : personsFromDb) {
                System.out.println(person);
            }
            List<Supporter> supportersFromDb = session.createQuery("from Supporter").list();
            for ( var supporter : supportersFromDb) {
                System.out.println(supporter);
            }
            List<Club> clubsFromDb = session.createQuery("from Club").list();
            for ( var club : clubsFromDb) {
                System.out.println(club);
            }
            List<Match> matchesFromDb = session.createQuery("from Match").list();
            for ( var match : matchesFromDb) {
                System.out.println(match.getFootballers());
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
