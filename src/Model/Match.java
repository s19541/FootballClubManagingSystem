package Model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents match
 */
@Entity(name = "Match")
public class Match {
    /**
     * Unique id of the match
     */
    private long id;
    /**
     * Start date and time of the match
     */
    private LocalDateTime date;
    /**
     * Match ticket price
     */
    private int ticketPrice;
    /**
     * Number of goals for
     */
    private Integer goalsFor;
    /**
     * Number of goals against
     */
    private Integer goalsAgainst;
    /**
     * Opponent
     */
    private Club club;
    /**
     * List of footballers in match
     */
    private List<Footballer> footballers = new ArrayList<>();
    /**
     * List of supporters in match
     */
    private List<Supporter> supporters = new ArrayList<>();
    /**
     * maximum number of supporters in match
     */
    public static int maxSupportersNumber = 30000;

    /**
     * Non-parameterized constructor
     */
    public Match(){}

    /**
     * Parameterized constructor without match result
     * @param date Start date and time of match
     * @param ticketPrice Match ticket price
     * @param opponent Opponent
     */
    public Match(LocalDateTime date, int ticketPrice, Club opponent){
        this.date = date;
        this.ticketPrice = ticketPrice;
        setClub(opponent);
    }

    /**
     * Parameterized constructor with match result
     * @param date Start date and time of match
     * @param ticketPrice Match ticket price
     * @param opponent Opponent
     * @param goalsFor Number of goals for
     * @param goalsAgainst Number of goals against
     */
    public Match(LocalDateTime date, int ticketPrice, Club opponent, Integer goalsFor, Integer goalsAgainst){
        this.date = date;
        this.ticketPrice = ticketPrice;
        setClub(opponent);
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    /**
     * Gets id of the match
     * @return Id of the match
     */
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    /**
     * Sets id of the match
     * @param id Id of the match
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets date of the match
     * @return Date of the match
     */
    @Basic
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets date of the match
     * @param date Date of the match
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Gets match ticket price
     * @return Match ticket price
     */
    @Basic
    public int getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Sets match ticket price
     * @param ticketPrice Match ticket price
     */
    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     * Sets number of goals for
     * @return Number of goals for
     */
    @Basic
    public Integer getGoalsFor() {
        return goalsFor;
    }

    /**
     * Sets number of goals for
     * @param goalsFor Number of goals for
     */
    public void setGoalsFor(Integer goalsFor) {
        this.goalsFor = goalsFor;
    }

    /**
     * Gets Number of goals against
     * @return Number of goals against
     */
    @Basic
    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    /**
     * Sets number of goals against
     * @param goalsAgainst Number of goals against
     */
    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    /**
     * Gets opponent
     * @return Opponent
     */
    @ManyToOne
    public Club getClub() {
        return club;
    }

    /**
     * Sets opponent
     * @param club Opponent
     */
    public void setClub(Club club) {
        this.club = club;
    }

    /**
     * Gets list of footballers in match
     * @return list of footballers in match
     */
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Match_Footballer",
            joinColumns = { @JoinColumn(name = "match_id") },
            inverseJoinColumns = { @JoinColumn(name = "footballer_id") }
    )
    public List<Footballer> getFootballers() {
        return footballers;
    }

    /**
     * Sets list of footballers in match
     * @param footballers List of footballers in match
     * @throws Exception Throws exception if number of footballers is not valid
     */
    public void setFootballers(List<Footballer> footballers) throws Exception{
        if(footballers.size() < 14 || footballers.size() > 20)
            throw new Exception("Wrong number of footballers in squad(Number must be in range: 14-20)");
        this.footballers = footballers;
    }

    /**
     * Adds footballer to list of footballers in match
     * @param footballer Footballer to add
     * @throws Exception Throws exception if number of footballers in squad is maximum
     */
    public void addFootballer(Footballer footballer) throws Exception{
        if(getFootballers().size() >= 20)
            throw new Exception("Too much footballers on match (maximum number is: 20)");
        if(!footballers.contains(footballer)) {
            getFootballers().add(footballer);
            footballer.getMatches().add(this);
        }
    }

    /**
     * Removes footballer from list of footballers in match
     * @param footballer Footballer to remove
     * @throws Exception Throws exception if number of footballers in squad is minimum
     */
    public void removeFootballer(Footballer footballer) throws Exception{
        if(footballers.size() <= 14)
            throw new Exception("Squad must have at least 14 footballers");
        getFootballers().remove(footballer);
        footballer.getMatches().remove(this);
    }

    /**
     * Gets list of supporters in match
     * @return List of supporters in match
     */
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Match_Supporter",
            joinColumns = { @JoinColumn(name = "match_id") },
            inverseJoinColumns = { @JoinColumn(name = "supporter_id") }
    )
    public List<Supporter> getSupporters() {
        return supporters;
    }

    /**
     * Sets list of supporters in match
     * @param supporters List of supporters in match
     */
    public void setSupporters(List<Supporter> supporters) {
        this.supporters = supporters;
    }

    /**
     * Adds supporter to list of supporters in match
     * @param supporter Supporter to add
     * @throws Exception Throws exception if number of supporters on match is maximum
     */
    public void addSupporter(Supporter supporter) throws Exception{
        if(getSupporters().size() >= Match.maxSupportersNumber)
            throw new Exception("Too much supporters on match (maximum number is: " + maxSupportersNumber + ")");
        if(!supporters.contains(supporter)) {
            getSupporters().add(supporter);
            supporter.getMatches().add(this);
        }
    }

    /**
     * Removes supporter from list of supporters in match
     * @param supporter Supporter to remove
     */
    public void removeSupporter(Supporter supporter) {
        getSupporters().remove(supporter);
        supporter.getMatches().remove(this);
    }

    /**
     * Check if match is finished
     * @return True if match is finished, else return false
     */
    @Transient
    public boolean isFinished(){
        if(date.plusMinutes(90).isBefore(LocalDateTime.now()))
            return true;
        return false;
    }

    /**
     * Check if match is running
     * @return True if match is running, else return false
     */
    @Transient
    public boolean isRunning(){
        if(date.minusNanos(1).isBefore(LocalDateTime.now()) && !isFinished())
            return true;
        return false;
    }

    /**
     * Override toString method
     * @return Information about match
     */
    @Override
    public String toString() {
        String retString = club.getName() + " ";
        if(goalsFor != null && goalsAgainst != null)
            retString += goalsFor+ ":" + goalsAgainst + " ";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        retString += date.format(format);
        return retString;
    }
}
