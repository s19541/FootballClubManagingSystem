package Model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents season
 */
@Entity(name = "Season")
public class Season {
    /**
     * Unique id of the season
     */
    private long id;
    /**
     * Start date of the season
     */
    private LocalDate startDate;
    /**
     * End date of the season
     */
    private LocalDate endDate;
    /**
     * List of teams stats in the season
     */
    private List<Stats> statsList = new ArrayList<>();
    /**
     * League of season
     */
    private League league;

    /**
     * Non-parameterized constructor
     */
    public Season(){}

    /**
     * Parameterized constructor
     * @param startDate Start date of the season
     * @param endDate End date of the season
     */
    public Season(LocalDate startDate, LocalDate endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Gets id of the season
     * @return Id of the season
     */
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    /**
     * Sets id of the season
     * @param id Id of the season
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets start date of the season
     * @return Start date of the season
     */
    @Basic
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets start date of the season
     * @param startDate Start date of the season
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date of the season
     * @return End date of the season
     */
    @Basic
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of the season
     * @param endDate End date of the season
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets list of teams stats in the season
     * @return List of teams stats in the season
     */
    @OneToMany(
            mappedBy = "season",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Stats> getStats() {
        return statsList;
    }

    /**
     * Sets list of teams stats in the season
     * @param statsList List of teams stats in the season
     */
    private void setStats(List<Stats> statsList) {
        this.statsList = statsList;
    }

    /**
     * Adds stats to list of teams stats in the season
     * @param stats Stats to add
     */
    public void addStats(Stats stats) {
        getStats().add(stats);
        stats.setSeason(this);
    }

    /**
     * Removes stats to list of teams stats in the season
     * @param stats List of teams stats in the season
     */
    private void removeStats(Stats stats) {
        getStats().remove(stats);
        stats.setSeason(null);
    }

    /**
     * Adds club to season with stats
     * @param club Club
     * @param matchWon Number of matches won
     * @param matchDrawn Number of matches drawn
     * @param matchLost Number of matches lost
     * @param goalsFor Number of goals for
     * @param goalsAgainst Number of goals against
     * @throws Exception Throws exception if stats this club in season already exists
     */
    public void addClub(Club club, int matchWon, int matchDrawn, int matchLost, int goalsFor, int goalsAgainst) throws Exception{
        Stats stats = new Stats(matchWon, matchDrawn, matchLost, goalsFor, goalsAgainst, club, this);
        addStats(stats);
        club.addStats(stats);
    }

    /**
     * Gets league of season
     * @return League of season
     */
    @ManyToOne
    public League getLeague() {
        return league;
    }

    /**
     * Sets league of season
     * @param league League of season
     */
    public void setLeague(League league) {
        this.league = league;
    }

    /**
     * Override toString
     * @return Information about season
     */
    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", start date='" + startDate + '\'' +
                ", end date='" + endDate + '\'' +
                ", statsList=" + statsList +
                '}';
    }
}
