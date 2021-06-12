package Controllers;

import Models.Club;
import Models.Match;
import Models.Person;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Controllers.DbConnectionController.sessionFactory;

public class MatchController {
    public static List<Match> getMatchSchedule(){
        List<Match> matchesFromDb = new ArrayList<>();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            matchesFromDb = session.createQuery("from Match").list();
            session.getTransaction().commit();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        matchesFromDb = matchesFromDb.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now())).collect(Collectors.toList());
        return matchesFromDb;
    }
}
