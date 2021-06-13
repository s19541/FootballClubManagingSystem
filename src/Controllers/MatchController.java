package Controllers;

import Models.Club;
import Models.Footballer;
import Models.Match;
import Models.Person;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Controllers.DbConnectionController.sessionFactory;

public class MatchController {
    private static List<Match> getMatches(){
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
        return  matchesFromDb;
    }

    public static List<Match> getMatchSchedule(){
        return getMatches().stream().filter(x -> x.getDate().isAfter(LocalDateTime.now())).collect(Collectors.toList());
    }

    public static List<Match> getFinishedMatches(){
        return getMatches().stream().filter(x -> x.getDate().isBefore(LocalDateTime.now()) || x.getDate().equals(LocalDateTime.now())).collect(Collectors.toList());
    }
    public static void addMatch(Match match){
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(match);
            session.getTransaction().commit();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void updateMatch(Match match){
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(match);
            session.getTransaction().commit();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static List<Footballer> getMatchSquad(Match match){
        List<Footballer> matchSquad = new ArrayList<>();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<Match> matchesFromDb = session.createQuery("from Match where id = " + match.getId()).list();
            matchSquad = matchesFromDb.get(0).getFootballers();
            System.out.println(matchSquad);
            session.getTransaction().commit();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return matchSquad;
    }
}
