package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "League")
public class League {
    private long id;
    private String name;
    private String country;
    private List<Stats> statsList = new ArrayList<>();

    public League(){}

    public League(String name, String country){
        this.name = name;
        this.country = country;
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

    @Basic
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @OneToMany(
            mappedBy = "league",
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
        stats.setLeague(this);
    }

    private void removeStats(Stats stats) {
        getStats().remove(stats);
        stats.setLeague(null);
    }

    public void addClub(Club club, int matchWon, int matchDrawn, int matchLost, int goalsFor, int goalsAgainst) throws Exception{
        Stats stats = new Stats(matchWon, matchDrawn, matchLost, goalsFor, goalsAgainst, club, this);
        addStats(stats);
        club.addStats(stats);
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", statsList=" + statsList +
                '}';
    }
}
