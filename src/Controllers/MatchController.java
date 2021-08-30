package Controllers;

import Model.Footballer;
import Model.Match;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for match data
 */
public class MatchController {
    /**
     * Gets all matches from db
     * @return list of all matches from db
     */
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

    /**
     * Gets upcoming matches from db
     * @return list of upcoming matches from db
     */
    public static List<Match> getUpcomingMatches(){
        return getMatches().stream().filter(x -> x.getDate().isAfter(LocalDateTime.now())).collect(Collectors.toList());
    }

    /**
     * Gets started matches from db
     * @return list of started matches from db
     */
    public static List<Match> getStartedMatches(){
        return getMatches().stream().filter(x -> x.isFinished() || x.isRunning()).collect(Collectors.toList());
    }

    /**
     * Method which add match to db
     * @param match Match to be added
     */
    public static void addMatch(Match match){
        try{
            DbConnectionController.session.beginTransaction();
            DbConnectionController.session.save(match);
            DbConnectionController.session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method which update match in db
     * @param match Match to be updated
     */
    public static void updateMatch(Match match){
        try{
            DbConnectionController.session.beginTransaction();
            DbConnectionController.session.update(match);
            DbConnectionController.session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method which remove footballer from match squad in db
     * @param match Match to which footballer is being removed
     * @param footballer Footballer to remove from match
     */
    public static void removeFootballerFromSquad(Match match, Footballer footballer){
        try{
            match.getFootballers().remove(footballer);
            updateMatch(match);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method which add footballer to match squad in db
     * @param match Match to which footballer is being added
     * @param footballer Footballer to add to match
     */
    public static void addFootballerToSquad(Match match, Footballer footballer){
        try{
            match.getFootballers().add(footballer);
            updateMatch(match);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method which return footballers out of match squad
     * @param match
     * @return List of footballers out of match squad
     */
    public static List<Footballer> getFootballersOutOfSquad(Match match){
        List<Footballer> footballers = new ArrayList<>();
        List<Footballer> footballersFromDb = PersonController.getFootballers();
        for(Footballer footballer : footballersFromDb){
            if(!match.getFootballers().contains(footballer))
                footballers.add(footballer);
        }
        return footballers;
    }
}
