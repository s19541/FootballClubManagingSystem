package Controllers;

import Enums.CoachRole;
import Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class DbConnectionController {
    public static StandardServiceRegistry registry = null;
    public static SessionFactory sessionFactory = null;
    public static Session session = null;

    public static void startConnectionWithDb(){
        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            session = sessionFactory.openSession();
        }catch(Exception e){
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void stopConnectionWithDb(){
        if (sessionFactory != null) {
            sessionFactory.close();
            session.close();
        }
    }

    public static void prepareExampleData(){
        Person person1 = new Person("Jan", "Kibic");
        Person person2 = new Person("Maciek", "Los");
        Person person3 = new Person("Marek", "Supp");
        Person person4 = new Person("Jakub", "Trener");
        Person person5 = new Person("Marek", "Dieta");
        Person person6 = new Person("Cristiano", "Ronaldo");
        Person person7 = new Person("Robert", "Lewandowski");
        Person person8 = new Person("Wojciech", "Szczęsny");
        Person person9 = new Person("Jan", "Bednarek");
        Person person10 = new Person("Kamil", "Glik");
        Person person11 = new Person("Bartosz", "Bereszyński");
        Person person12 = new Person("Piotr", "Zieliński");
        Person person13 = new Person("Mateusz", "Klich");
        Person person14 = new Person("Kamil", "Jóźwiak");
        Person person15 = new Person("Kylian", "Mbappe");
        Person person16 = new Person("Hugo", "Lloris");
        Person person17 = new Person("Raphael", "Varane");
        Person person18 = new Person("N'Golo", "Kante");
        Person person19 = new Person("Paul", "Pogba");
        Person person20 = new Person("Ciro", "Immobile");
        Person person21 = new Person("Giorgio", "Chiellini");
        Person person22 = new Person("Gianluigi", "Donnarumma");
        Person person23 = new Person("Kevin", "De Bruyne");
        Person person24 = new Person("Eden", "Hazard");
        Person person25 = new Person("Sergio", "Ramos");
        Person person26 = new Person("Romelu", "Lukaku");
        Person person27 = new Person("Bernardo", "Silva");
        Person person28 = new Person("Alvaro", "Morata");
        Person person29 = new Person("Bruno", "Fernandes");
        Person person30 = new Person("Cristian", "Eriksen");
        Supporter supporter1 = null, supporter2 = null, supporter3 = null, supporter4 = null;
        Dietician dietician1 = null, dietician2 = null;
        Footballer footballer1 = null, footballer2 = null, footballer3 = null, footballer4 = null, footballer5 = null,
                footballer6 = null, footballer7 = null, footballer8 = null, footballer9 = null, footballer10 = null,
                footballer11 = null, footballer12 = null, footballer13 = null, footballer14 = null, footballer15 = null,
                footballer16 = null, footballer17 = null, footballer18 = null, footballer19 = null, footballer20 = null,
                footballer21 = null, footballer22 = null, footballer23 = null, footballer24 = null, footballer25 = null;
        List<Footballer>footballers = new ArrayList<>();
        Coach coach1 = null, coach2 = null;
        try{
            supporter1 = Supporter.createSupporter(person1, true);
            supporter2 = Supporter.createSupporter(person2, false);
            supporter3 = Supporter.createSupporter(person3, false);
            supporter4 = Supporter.createSupporter(person4, true);
            dietician1 = Dietician.createDietician(person5, 3333, LocalDate.now());
            dietician2 = Dietician.createDietician(person2, 5000, LocalDate.now());
            footballer1 = Footballer.createFootballer(person6, 40000, LocalDate.now(), "Striker", 7);
            footballer2 = Footballer.createFootballer(person7, 30000, LocalDate.now(), "Striker", 9);
            footballer3 = Footballer.createFootballer(person8, 17000, LocalDate.now(), "Goalkeeper", 1);
            footballer4 = Footballer.createFootballer(person9, 12000, LocalDate.now(), "Defender", 5);
            footballer5 = Footballer.createFootballer(person10, 18000, LocalDate.now(), "Defender", 4);
            footballer6 = Footballer.createFootballer(person11, 8000, LocalDate.now(), "Defender", 14);
            footballer7 = Footballer.createFootballer(person12, 15000, LocalDate.now(), "Midfielder", 10);
            footballer8 = Footballer.createFootballer(person13, 12000, LocalDate.now(), "Midfielder", 11);
            footballer9 = Footballer.createFootballer(person14, 11000, LocalDate.now(), "Midfielder", 12);
            footballer10 = Footballer.createFootballer(person15, 70000, LocalDate.now(), "Striker", 8);
            footballer11 = Footballer.createFootballer(person16, 20000, LocalDate.now(), "Goalkeeper", 44);
            footballer12 = Footballer.createFootballer(person17, 20000, LocalDate.now(), "Defender", 45);
            footballer13 = Footballer.createFootballer(person18, 40000, LocalDate.now(), "Midfielder", 36);
            footballer14 = Footballer.createFootballer(person19, 30000, LocalDate.now(), "Midfielder", 48);
            footballer15 = Footballer.createFootballer(person20, 20000, LocalDate.now(), "Striker", 46);
            footballer16 = Footballer.createFootballer(person21, 10000, LocalDate.now(), "Defender", 47);
            footballer17 = Footballer.createFootballer(person22, 20000, LocalDate.now(), "Goalkeeper", 49);
            footballer18 = Footballer.createFootballer(person23, 40000, LocalDate.now(), "Midfielder", 50);
            footballer19 = Footballer.createFootballer(person24, 30000, LocalDate.now(), "Striker", 51);
            footballer20 = Footballer.createFootballer(person25, 20000, LocalDate.now(), "Defender", 52);
            footballer21 = Footballer.createFootballer(person26, 20000, LocalDate.now(), "Striker", 53);
            footballer22 = Footballer.createFootballer(person27, 23000, LocalDate.now(), "Midfielder", 90);
            footballer23 = Footballer.createFootballer(person28, 10000, LocalDate.now(), "Striker", 67);
            footballer24 = Footballer.createFootballer(person29, 30000, LocalDate.now(), "Midfielder", 68);
            footballer25 = Footballer.createFootballer(person30, 20000, LocalDate.now(), "Midfielder", 99);
            coach1 = Coach.createCoach(person4, 10000, LocalDate.now(), CoachRole.MAIN_COACH);
            coach2 = Coach.createCoach(person3, 2000, LocalDate.now(), CoachRole.COACH_ASSISTANT);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

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
            training1.addFootballer(footballer5);
            training1.addFootballer(footballer7);
            training1.addFootballer(footballer15);
            training1.addFootballer(footballer18);
            training1.addFootballer(footballer19);
            training1.addFootballer(footballer21);
            training2.addFootballer(footballer1);
            training2.addFootballer(footballer3);
            training2.addFootballer(footballer7);
            training2.addFootballer(footballer9);
            training2.addFootballer(footballer21);
            training3.addFootballer(footballer1);
            training3.addFootballer(footballer21);
            training3.addFootballer(footballer11);
            training3.addFootballer(footballer13);
            training3.addFootballer(footballer14);
            training4.addFootballer(footballer1);
            training4.addFootballer(footballer12);
            training4.addFootballer(footballer13);
            training4.addFootballer(footballer11);
            training4.addFootballer(footballer16);
            training4.addFootballer(footballer19);
            training5.addFootballer(footballer1);
            training5.addFootballer(footballer12);
            training5.addFootballer(footballer13);
            training5.addFootballer(footballer21);
            training5.addFootballer(footballer25);
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

        Match match1 = new Match(LocalDateTime.of(2021, Month.MAY, 8, 20, 30), 22, club1,3, 3);
        Match match2 = new Match(LocalDateTime.of(2021, Month.NOVEMBER, 10, 20, 30), 33, club2);
        Match match3 = new Match(LocalDateTime.of(2021, Month.OCTOBER, 12, 20, 30), 44, club3);
        Match match4 = new Match(LocalDateTime.of(2021, Month.MAY, 10, 20, 30), 50, club4,4, 3);
        Match match5 = new Match(LocalDateTime.now(), 20, club1);

        try {
            match1.addFootballer(footballer1);
            match1.addFootballer(footballer2);
            match1.addFootballer(footballer3);
            match1.addFootballer(footballer4);
            match1.addFootballer(footballer6);
            match1.addFootballer(footballer8);
            match1.addFootballer(footballer9);
            match1.addFootballer(footballer11);
            match1.addFootballer(footballer12);
            match1.addFootballer(footballer13);
            match1.addFootballer(footballer14);
            match1.addFootballer(footballer15);
            match1.addFootballer(footballer16);
            match1.addFootballer(footballer17);
            match1.addFootballer(footballer18);
            match2.addFootballer(footballer19);
            match2.addFootballer(footballer2);
            match2.addFootballer(footballer3);
            match2.addFootballer(footballer4);
            match2.addFootballer(footballer21);
            match2.addFootballer(footballer22);
            match2.addFootballer(footballer23);
            match2.addFootballer(footballer24);
            match2.addFootballer(footballer25);
            match2.addFootballer(footballer11);
            match2.addFootballer(footballer12);
            match2.addFootballer(footballer13);
            match2.addFootballer(footballer14);
            match2.addFootballer(footballer15);
            match2.addFootballer(footballer16);
            match2.addFootballer(footballer17);
            match2.addFootballer(footballer18);
            match2.addFootballer(footballer8);
            match2.addFootballer(footballer7);
            match2.addFootballer(footballer6);
            match3.addFootballer(footballer1);
            match3.addFootballer(footballer2);
            match3.addFootballer(footballer3);
            match3.addFootballer(footballer4);
            match3.addFootballer(footballer6);
            match3.addFootballer(footballer5);
            match3.addFootballer(footballer7);
            match3.addFootballer(footballer8);
            match3.addFootballer(footballer9);
            match3.addFootballer(footballer10);
            match3.addFootballer(footballer11);
            match3.addFootballer(footballer12);
            match3.addFootballer(footballer13);
            match3.addFootballer(footballer14);
            match3.addFootballer(footballer15);
            match3.addFootballer(footballer17);
            match3.addFootballer(footballer16);
            match3.addFootballer(footballer18);
            match4.addFootballer(footballer25);
            match4.addFootballer(footballer24);
            match4.addFootballer(footballer23);
            match4.addFootballer(footballer22);
            match4.addFootballer(footballer21);
            match4.addFootballer(footballer20);
            match4.addFootballer(footballer19);
            match4.addFootballer(footballer18);
            match4.addFootballer(footballer17);
            match4.addFootballer(footballer15);
            match4.addFootballer(footballer14);
            match4.addFootballer(footballer13);
            match4.addFootballer(footballer12);
            match4.addFootballer(footballer11);
            match4.addFootballer(footballer10);
            match4.addFootballer(footballer9);
            match4.addFootballer(footballer3);
            match5.addFootballer(footballer10);
            match5.addFootballer(footballer9);
            match5.addFootballer(footballer8);
            match5.addFootballer(footballer7);
            match5.addFootballer(footballer5);
            match5.addFootballer(footballer6);
            match5.addFootballer(footballer4);
            match5.addFootballer(footballer3);
            match5.addFootballer(footballer2);
            match5.addFootballer(footballer1);
            match5.addFootballer(footballer11);
            match5.addFootballer(footballer14);
            match5.addFootballer(footballer16);
            match5.addFootballer(footballer18);
            match5.addFootballer(footballer21);
            match5.addFootballer(footballer22);
            match5.addFootballer(footballer24);
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
            session.beginTransaction();

            session.save(person1);
            session.save(person2);
            session.save(person3);
            session.save(person4);
            session.save(person5);
            session.save(person6);
            session.save(person7);
            session.save(person8);
            session.save(person9);
            session.save(person10);
            session.save(person11);
            session.save(person12);
            session.save(person13);
            session.save(person14);
            session.save(person15);
            session.save(person16);
            session.save(person17);
            session.save(person18);
            session.save(person19);
            session.save(person20);
            session.save(person21);
            session.save(person22);
            session.save(person23);
            session.save(person24);
            session.save(person25);
            session.save(person26);
            session.save(person27);
            session.save(person28);
            session.save(person29);
            session.save(person30);
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
            session.save(footballer6);
            session.save(footballer7);
            session.save(footballer8);
            session.save(footballer9);
            session.save(footballer10);
            session.save(footballer11);
            session.save(footballer12);
            session.save(footballer13);
            session.save(footballer14);
            session.save(footballer15);
            session.save(footballer16);
            session.save(footballer17);
            session.save(footballer18);
            session.save(footballer19);
            session.save(footballer20);
            session.save(footballer21);
            session.save(footballer22);
            session.save(footballer23);
            session.save(footballer24);
            session.save(footballer25);
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
            session.save(match5);
            session.save(league);
            session.save(season1);

            session.getTransaction().commit();
        }
        catch(Exception e){
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
