package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity(name = "League")
public class League {
    private long id;
    private String name;
    private String country;
    private List<Season> seasons;

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
    private List<Season> getSeasons() {
        return seasons;
    }
    public void addSeason(Season season) {
        getSeasons().add(season);
        season.setLeague(this);
    }
    public void removeSeason(Season season) {
        getSeasons().remove(season);
        season.setLeague(null);
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }
}
