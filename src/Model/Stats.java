package Model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents stats
 */
@Entity(name = "Stats")
public class Stats {
    /**
     * Unique stats id
     */
    private long id;
    /**
     * Number of matches won
     */
    private int matchWon;
    /**
     * Number of matches drawn
     */
    private int matchDrawn;
    /**
     * Number of matches lost
     */
    private int matchLost;
    /**
     * Number of goals for
     */
    private int goalsFor;
    /**
     * Number of goals against
     */
    private int goalsAgainst;
    /**
     * Club
     */
    private Club club;
    /**
     * Season
     */
    private Season season;
    /**
     * Extension of stats
     */
    private static List<Stats> extent = new ArrayList<>();

    /**
     * Non-parameterized constructor
     */
    public Stats(){}

    /**
     * Parameterized constructor
     * @param matchWon Number of matches won
     * @param matchDrawn Number of matches drawn
     * @param matchLost Number of matches lost
     * @param goalsFor Number of goals for
     * @param goalsAgainst Number of goals against
     * @param club Club
     * @param season Season
     * @throws Exception Throws Exception season stats of club already exist
     */
    public Stats(int matchWon, int matchDrawn, int matchLost, int goalsFor, int goalsAgainst, Club club, Season season) throws Exception{
        if(exists(club, season))
            throw new Exception("Stats for this club and league already exist");
        this.matchWon = matchWon;
        this.matchDrawn = matchDrawn;
        this.matchLost = matchLost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        setSeason(season);
        setClub(club);

        extent.add(this);
    }

    /**
     * Gets id of the stats
     * @return Id of the stats
     */
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    /**
     * Sets id of the stats
     * @param id Id of the stats
     */
    @Basic
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets number of matches won
     * @return Number of matches won
     */
    public int getMatchWon() {
        return matchWon;
    }

    /**
     * Sets number of matches won
     * @param matchWon Number of matches won
     */
    public void setMatchWon(int matchWon) {
        this.matchWon = matchWon;
    }

    /**
     * Gets number of matches drawn
     * @return Number of matches drawn
     */
    @Basic
    public int getMatchDrawn() {
        return matchDrawn;
    }

    /**
     * Sets number of matches drawn
     * @param matchDrawn Number of matches drawn
     */
    public void setMatchDrawn(int matchDrawn) {
        this.matchDrawn = matchDrawn;
    }

    /**
     * Gets number of matches lost
     * @return Number of matches lost
     */
    @Basic
    public int getMatchLost() {
        return matchLost;
    }

    /**
     * Sets number of matches lost
     * @param matchLost Number of matches lost
     */
    public void setMatchLost(int matchLost) {
        this.matchLost = matchLost;
    }

    /**
     * Gets number of goals for
     * @return number of goals for
     */
    @Basic
    public int getGoalsFor() {
        return goalsFor;
    }

    /**
     * Sets number of goals for
     * @param goalsFor Number of goals for
     */
    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    /**
     * Gets number of goals against
     * @return Number of goals against
     */
    @Basic
    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    /**
     * Sets number of goals against
     * @param goalsAgainst Number of goals against
     */
    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    /**
     * Gets club
     * @return Club
     */
    @ManyToOne
    public Club getClub() {
        return club;
    }

    /**
     * Sets club
     * @param club Club
     */
    public void setClub(Club club) {
        this.club = club;
    }

    /**
     * Gets season
     * @return Season
     */
    @ManyToOne
    public Season getSeason() {
        return season;
    }

    /**
     * Sets season
     * @param season Season
     */
    public void setSeason(Season season) {
        this.season = season;
    }

    /**
     * Check if stats exist in extension
     * @param club Club
     * @param season Season
     * @return True if stats already exists, else return false
     */
    private boolean exists(Club club, Season season){
        for(Stats stats : extent)
            if(stats.club == club && stats.season == season)
                return true;
        return false;
    }

    /**
     * Override toString method
     * @return Information about season stats of the club
     */
    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", matchWon=" + matchWon +
                ", matchDrawn=" + matchDrawn +
                ", matchLost=" + matchLost +
                ", goalsFor=" + goalsFor +
                ", goalsAgainst=" + goalsAgainst +
                ", club=" + club.getName() +
                '}';
    }
}
