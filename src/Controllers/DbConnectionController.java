package Controllers;

import Enums.CoachRole;
import Models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

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
    public static void prepareExampleData(){
        Supporter supporter1 = new Supporter("Jan", "Kibic", true);
        Supporter supporter2 = new Supporter("Maciek", "Los", false);
        Supporter supporter3 = new Supporter("Marek", "Tu", false);
        Supporter supporter4 = new Supporter("Jakub", "Trzy", false);

        Dietician dietician1 = new Dietician("Marek", "Dieta", 3333, LocalDate.now());
        Dietician dietician2 = new Dietician("Marcin", "Banan", 5000, LocalDate.now());

        Footballer footballer1 = null, footballer2 = null, footballer3 = null, footballer4 = null, footballer5 = null;
        try {
            footballer1 = new Footballer("Cristiano", "Ronaldo", 40000, LocalDate.now(), "Striker", 7);
            footballer2 = new Footballer("Robert", "Lewandowski", 30000, LocalDate.now(), "Striker", 9);
            footballer3 = new Footballer("Wojciech", "Szczęsny", 17000, LocalDate.now(), "GoalKeeper", 1);
            footballer4 = new Footballer("Jan", "Bednarek", 12000, LocalDate.now(), "Defender", 5);
            footballer5 = new Footballer("Kamil", "Glik", 18000, LocalDate.now(), "Defender", 4);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Coach coach1 = new Coach("Paweł", "Trener", 10000, LocalDate.now(), CoachRole.MAIN_COACH);
        Coach coach2 = new Coach("Jerzy", "Brzęczek", 2000, LocalDate.now(), CoachRole.COACH_ASSISTANT);

        Pitch pitch1 = new Pitch(400, "Stawowa 12");
        Pitch pitch2 = new Pitch(600, "Treningowa 12");

        Training training1 = new Training(LocalDateTime.now(), pitch1);
        Training training2 = new Training(LocalDateTime.of(2021, Month.APRIL, 30, 22, 30), pitch1);
        Training training3 = new Training(LocalDateTime.of(2021, Month.JUNE, 30, 22, 30), pitch2);
        Training training4 = new Training(LocalDateTime.of(2021, Month.JUNE, 28, 22, 30), pitch2);
        Training training5 = new Training(LocalDateTime.of(2021, Month.JULY, 12, 22, 30), pitch2);

        try {
            training1.addCoach(coach1);
            training1.addFootballer(footballer1);
            training1.addFootballer(footballer2);
            training2.addCoach(coach2);
            training3.addCoach(coach2);
            training4.addCoach(coach2);
            training5.addCoach(coach2);
            training2.addCoach(coach1);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        footballer1.addDietician(dietician1);
        dietician1.addFootballer(footballer2);

        League league = new League("My league", "Poland");
        Season season1 = new Season(LocalDate.of(2020, 12 ,1 ), LocalDate.of(2021, 12 , 1));
        season1.setLeague(league);

        Club club1 = new Club("Real Madrid");
        Club club2 = new Club("Manchester City");
        Club club3 = new Club("Manchester United");
        Club club4 = new Club("Liverpool Fc");

        Match match1 = new Match(LocalDateTime.of(2021, Month.MAY, 8, 20, 30), club1,3, 3);
        Match match2 = new Match(LocalDateTime.of(2021, Month.JULY, 10, 20, 30), club2);
        Match match3 = new Match(LocalDateTime.of(2021, Month.JULY, 12, 20, 30), club3);
        Match match4 = new Match(LocalDateTime.of(2021, Month.MAY, 10, 20, 30), club4,4, 3);

        try {
            match2.addFootballer(footballer1);
            match2.addFootballer(footballer2);
            match2.addFootballer(footballer3);
            match2.addFootballer(footballer4);
            match3.addFootballer(footballer1);
            match3.addFootballer(footballer4);
            match3.addFootballer(footballer5);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        try {
            //club1.addLeague(league1, 3, 2, 3, 4, 4);
            season1.addClub(club1, 2, 2, 3, 4, 2);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(supporter1);
            session.save(supporter3);
            session.save(supporter4);
            session.save(supporter2);
            session.save(dietician1);
            session.save(dietician2);
            session.save(coach1);
            session.save(coach2);
            session.save(footballer1);
            session.save(footballer2);
            session.save(footballer3);
            session.save(footballer4);
            session.save(footballer5);
            session.save(training1);
            session.save(training2);
            session.save(training3);
            session.save(training4);
            session.save(training5);
            session.save(pitch1);
            session.save(pitch2);
            session.save(season1);
            session.save(club1);
            session.save(club2);
            session.save(club3);
            session.save(club4);
            session.save(match1);
            session.save(match2);
            session.save(match3);
            session.save(match4);
            session.save(league);
            session.save(season1);

            session.getTransaction().commit();
            session.close();
        }
        catch(Exception e){
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
