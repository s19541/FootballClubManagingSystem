package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Stats")
public class Stats {
    private long id;
    private int matchWon;
    private int matchDrawn;
    private int matchLost;
    private int goalsFor;
    private int goalsAgainst;
    private Club club;
    private League league;
    private static List<Stats> extent = new ArrayList<>();

    public Stats(){}

    public Stats(int matchWon, int matchDrawn, int matchLost, int goalsFor, int goalsAgainst, Club club, League league) throws Exception{
        if(exists(club,league))
            throw new Exception("Stats for this club and league already exist");
        this.matchWon = matchWon;
        this.matchDrawn = matchDrawn;
        this.matchLost = matchLost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        setLeague(league);
        setClub(club);

        extent.add(this);
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    @Basic
    public void setId(long id) {
        this.id = id;
    }

    public int getMatchWon() {
        return matchWon;
    }

    public void setMatchWon(int matchWon) {
        this.matchWon = matchWon;
    }

    @Basic
    public int getMatchDrawn() {
        return matchDrawn;
    }

    public void setMatchDrawn(int matchDrawn) {
        this.matchDrawn = matchDrawn;
    }

    @Basic
    public int getMatchLost() {
        return matchLost;
    }

    public void setMatchLost(int matchLost) {
        this.matchLost = matchLost;
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

    @ManyToOne
    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    private boolean exists(Club club, League league){
        for(Stats stats : extent)
            if(stats.club == club && stats.league == league)
                return true;
        return false;
    }

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
                ", league=" + league.getName() +
                '}';
    }
}
