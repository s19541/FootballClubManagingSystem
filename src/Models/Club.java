package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Club")
public class Club {
    private long id;
    private String name;
    private List<Match> matches = new ArrayList<>();
    private List<Stats> statsList = new ArrayList<>();

    public Club(){}

    public Club(String name){
        this.name = name;
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(
            mappedBy = "club",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void addMatch(Match match) {
        getMatches().add(match);
        match.setClub(this);
    }

    public void removeMatch(Match match) {
        getMatches().remove(match);
        match.setClub(null);
    }

    @OneToMany(
            mappedBy = "club",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Stats> getStats() {
        return statsList;
    }

    private void setStats(List<Stats> statsList) {
        this.statsList = statsList;
    }

    public void addStats(Stats stats) {
        getStats().add(stats);
    }

    private void removeStats(Stats stats) {
        getStats().remove(stats);
        stats.setClub(null);
    }

    public void addLeague(Season season, int matchWon, int matchDrawn, int matchLost, int goalsFor, int goalsAgainst) throws Exception{
        Stats stats = new Stats(matchWon, matchDrawn, matchLost, goalsFor, goalsAgainst, this, season);
        addStats(stats);
        season.addStats(stats);
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", matches=" + matches +
                ", statsList=" + statsList +
                '}';
    }
}
