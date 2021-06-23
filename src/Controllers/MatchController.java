package Controllers;

import Models.Club;
import Models.Footballer;
import Models.Match;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MatchController {
    private static List<Match> getMatches(){
        List<Match> matchesFromDb = new ArrayList<>();
        try{
            DbConnectionController.session.beginTransaction();
            matchesFromDb = DbConnectionController.session.createQuery("from Match").list();
            DbConnectionController.session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  matchesFromDb;
    }

    public static List<Match> getMatchSchedule(){
        return getMatches().stream().filter(x -> x.getDate().isAfter(LocalDateTime.now())).collect(Collectors.toList());
    }

    public static List<Match> getFinishedMatches(){
        return getMatches().stream().filter(x -> x.isFinished() || x.isRunning()).collect(Collectors.toList());
    }
    public static void addMatch(Match match){
        try{
            DbConnectionController.session.beginTransaction();
            DbConnectionController.session.save(match);
            DbConnectionController.session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void updateMatch(Match match){
        try{
            DbConnectionController.session.beginTransaction();
            DbConnectionController.session.update(match);
            DbConnectionController.session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static List<Footballer> getMatchSquad(Match match){
        List<Footballer> matchSquad = new ArrayList<>();
        try{
            DbConnectionController.session.beginTransaction();
            List<Match> matchesFromDb = DbConnectionController.session.createQuery("from Match where id = " + match.getId()).list();
            matchSquad = matchesFromDb.get(0).getFootballers();
            System.out.println(matchSquad);
            DbConnectionController.session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return matchSquad;
    }

    public static void removeFootballerFromSquad(Match match, Footballer footballer){
        try{
            DbConnectionController.session.beginTransaction();
            match.getFootballers().remove(footballer);
            DbConnectionController.session.update(match);
            DbConnectionController.session.getTransaction().commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void addFootballerToSquad(Match match, Footballer footballer){
        try{
            DbConnectionController.session.beginTransaction();
            match.getFootballers().add(footballer);
            DbConnectionController.session.update(match);
            DbConnectionController.session.getTransaction().commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static List<Footballer> getFootballersOutOfSquad(Match match){
        List<Footballer> footballers = new ArrayList<>();
        try{
            DbConnectionController.session.beginTransaction();
            List<Footballer> footballersFromDb = DbConnectionController.session.createQuery("from Footballer").list();
            for(Footballer footballer : footballersFromDb){
                if(!match.getFootballers().contains(footballer))
                    footballers.add(footballer);
            }
            DbConnectionController.session.getTransaction().commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return footballers;
    }

    public static List<Club> getClubsFromDb(){
        List<Club> clubsFromDb = new ArrayList<>();
        try{
            DbConnectionController.session.beginTransaction();
            clubsFromDb = DbConnectionController.session.createQuery("from Club").list();
            DbConnectionController.session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  clubsFromDb;
    }
}
