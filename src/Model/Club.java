package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a club
 */
@Entity(name = "Club")
public class Club {
    /**
     * Unique id of the club
     */
    private long id;
    /**
     * Name of the club
     */
    private String name;
    /**
     * Club match list
     */
    private List<Match> matches = new ArrayList<>();
    /**
     * Club season stats list
     */
    private List<Stats> statsList = new ArrayList<>();

    /**
     * Non-parameterized constructor
     */
    public Club(){}

    /**
     * Parametrized constructor
     * @param name Name of the club
     */
    public Club(String name){
        this.name = name;
    }

    /**
     * Gets club id
     * @return Club id
     */
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    /**
     * Sets club id
     * @param id Club id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets club name
     * @return Club name
     */
    @Basic
    public String getName() {
        return name;
    }

    /**
     * Sets club name
     * @param name Club name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets club match list
     * @return Club match list
     */
    @OneToMany(
            mappedBy = "club",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Match> getMatches() {
        return matches;
    }

    /**
     * Sets club match list
     * @param matches Club match list
     */
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    /**
     * Adds new match to club match list
     * @param match New match
     */
    public void addMatch(Match match) {
        getMatches().add(match);
        match.setClub(this);
    }

    /**
     * Removes match from club match list
     * @param match Match to remove
     */
    public void removeMatch(Match match) {
        getMatches().remove(match);
        match.setClub(null);
    }

    /**
     * Gets club season stats list
     * @return Club season stats list
     */
    @OneToMany(
            mappedBy = "club",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Stats> getStats() {
        return statsList;
    }

    /**
     * Sets club season stats list
     * @param statsList Club season stats list
     */
    private void setStats(List<Stats> statsList) {
        this.statsList = statsList;
    }

    /**
     * Adds new season stats to club season stats list
     * @param stats New club season stats
     */
    public void addStats(Stats stats) {
        getStats().add(stats);
    }

    /**
     * Removes eason stats from club season stats list
     * @param stats Club season stats to remove
     */
    private void removeStats(Stats stats) {
        getStats().remove(stats);
        stats.setClub(null);
    }

    /**
     * Creates season to club with stats
     * @param season Season
     * @param matchWon Number of matches won
     * @param matchDrawn Number of matches drawn
     * @param matchLost Number of matches lost
     * @param goalsFor Number of goals for
     * @param goalsAgainst Number of goals against
     * @throws Exception Throws exception if stats this club in season already exists
     */
    public void addSeason(Season season, int matchWon, int matchDrawn, int matchLost, int goalsFor, int goalsAgainst) throws Exception{
        Stats stats = new Stats(matchWon, matchDrawn, matchLost, goalsFor, goalsAgainst, this, season);
        addStats(stats);
        season.addStats(stats);
    }

    /**
     * Override toString method
     * @return name of the club
     */
    @Override
    public String toString() {
        return getName();
    }
}
