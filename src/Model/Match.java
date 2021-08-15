package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Match")
public class Match {
    private long id;
    private LocalDateTime date;
    private int ticketPrice;
    private int goalsFor = -1;
    private int goalsAgainst = -1;
    private Club club;
    private List<Footballer> footballers = new ArrayList<>();
    private List<Supporter> supporters = new ArrayList<>();
    public static int maxSupportersNumber = 30000;

    public Match(){}

    public Match(LocalDateTime date, int ticketPrice, Club opponent){
        this.date = date;
        this.ticketPrice = ticketPrice;
        setClub(opponent);
    }

    public Match(LocalDateTime date, int ticketPrice, Club opponent, int goalsFor, int goalsAgainst){
        this.date = date;
        this.ticketPrice = ticketPrice;
        setClub(opponent);
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Basic
    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Basic
    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    @Basic
    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    @ManyToOne
    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Match_Footballer",
            joinColumns = { @JoinColumn(name = "match_id") },
            inverseJoinColumns = { @JoinColumn(name = "footballer_id") }
    )
    public List<Footballer> getFootballers() {
        return footballers;
    }

    public void setFootballers(List<Footballer> footballers) throws Exception{
        if(footballers.size() < 14 || footballers.size() > 20)
            throw new Exception("Wrong number of footballers in squad(Number must be in range: 14-20)");
        this.footballers = footballers;
    }

    public void addFootballer(Footballer footballer) throws Exception{
        if(getFootballers().size() >= 20)
            throw new Exception("Too much footballers on match (maximum number is: 20)");
        if(!footballers.contains(footballer)) {
            getFootballers().add(footballer);
            footballer.getMatches().add(this);
        }
    }
    public void removeFootballer(Footballer footballer) throws Exception{
        if(footballers.size() <= 14)
            throw new Exception("Squad must have at least 14 footballers");
        getFootballers().remove(footballer);
        footballer.getMatches().remove(this);
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Match_Supporter",
            joinColumns = { @JoinColumn(name = "match_id") },
            inverseJoinColumns = { @JoinColumn(name = "supporter_id") }
    )
    public List<Supporter> getSupporters() {
        return supporters;
    }

    public void setSupporters(List<Supporter> supporters) {
        this.supporters = supporters;
    }

    public void addSupporter(Supporter supporter) throws Exception{
        if(getSupporters().size() >= Match.maxSupportersNumber)
            throw new Exception("Too much footballers on match (maximum number is: " + maxSupportersNumber + ")");
        if(!supporters.contains(supporter)) {
            getSupporters().add(supporter);
            supporter.getMatches().add(this);
        }
    }
    public void removeSupporter(Supporter supporter) {
        getSupporters().remove(supporter);
        supporter.getMatches().remove(this);
    }

    @Transient
    public boolean isFinished(){
        if(date.plusMinutes(90).isBefore(LocalDateTime.now()))
            return true;
        return false;
    }
    @Transient
    public boolean isRunning(){
        if(date.minusNanos(1).isBefore(LocalDateTime.now()) && !isFinished())
            return true;
        return false;
    }
    @Override
    public String toString() {
        String retString = club.getName() + " ";
        if(goalsFor != -1 && goalsAgainst != -1)
            retString += goalsFor+ ":" + goalsAgainst + " ";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        retString += date.format(format);
        return retString;
    }
}