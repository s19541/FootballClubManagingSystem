package Model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;

/**
 * Represents league
 */
@Entity(name = "League")
public class League {
    /**
     * Unique id of the league
     */
    private long id;
    /**
     * Name of the league
     */
    private String name;
    /**
     * Country of the league
     */
    private String country;
    /**
     * List of season of the league
     */
    private List<Season> seasons;

    /**
     * Non-parameterized constructor
     */
    public League(){}

    /**
     * Parameterized constructor
     * @param name Name of the league
     * @param country Country of the league
     */
    public League(String name, String country){
        this.name = name;
        this.country = country;
    }

    /**
     * Gets id of the league
     * @return id of the league
     */
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }

    /**
     * Sets id of the league
     * @param id Id of the league
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets name of the league
     * @return Name of the league
     */
    @Basic
    public String getName() {
        return name;
    }

    /**
     * Sets name of the league
     * @param name Name of the league
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets country of the league
     * @return Country of the league
     */
    @Basic
    public String getCountry() {
        return country;
    }

    /**
     * Sets country of the league
     * @param country Country of the league
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets list of seasons of the league
     * @return list of seasons of the league
     */
    @OneToMany(
            mappedBy = "league",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Season> getSeasons() {
        return seasons;
    }

    /**
     * Sets list of seasons of the league
     * @param seasons List of seasons of the league
     */
    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    /**
     * Adds new season to list of seasons of the league
     * @param season Season to add
     */
    public void addSeason(Season season) {
        getSeasons().add(season);
        season.setLeague(this);
    }

    /**
     * Removes season from list of seasons of the league
     * @param season Season to remove
     */
    public void removeSeason(Season season) {
        getSeasons().remove(season);
        season.setLeague(null);
    }
}
