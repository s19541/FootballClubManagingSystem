package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Season")
public class Season {
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Stats> statsList = new ArrayList<>();
    private League league;

    public Season(){}

    public Season(LocalDate startDate, LocalDate endDate){
        this.startDate = startDate;
        this.endDate = endDate;
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
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Basic
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @OneToMany(
            mappedBy = "season",
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
        stats.setSeason(this);
    }

    private void removeStats(Stats stats) {
        getStats().remove(stats);
        stats.setSeason(null);
    }

    public void addClub(Club club, int matchWon, int matchDrawn, int matchLost, int goalsFor, int goalsAgainst) throws Exception{
        Stats stats = new Stats(matchWon, matchDrawn, matchLost, goalsFor, goalsAgainst, club, this);
        addStats(stats);
        club.addStats(stats);
    }

    @ManyToOne
    public League getLeague() {
        return league;
    }
    public void setLeague(League league) {
        this.league = league;
    }

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
