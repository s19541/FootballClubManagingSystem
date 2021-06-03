package Models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Supporter")
public class Supporter extends Person{
    private boolean haveSupporterCard;
    private List<Match> matches = new ArrayList<>();

    public Supporter(){}

    public Supporter(String firstName, String lastName, boolean haveSupporterCard){
        super(firstName,lastName);
        this.haveSupporterCard = haveSupporterCard;
    }

    @Basic
    public boolean getHaveSupporterCard(){
        return  haveSupporterCard;
    }

    public void setHaveSupporterCard(boolean haveSupporterCard){
        this.haveSupporterCard = haveSupporterCard;
    }

    @ManyToMany(mappedBy = "supporters")
    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void addMatch(Match match) throws Exception{
        if(match.getSupporters().size() >= Match.maxSupportersNumber)
            throw new Exception("Too much supporters on match (maximum number is: " + Match.maxSupportersNumber + ")");
        if(!matches.contains(match)) {
            getMatches().add(match);
            match.getSupporters().add(this);
        }
    }

    public void removeMatch(Match match) {
        getMatches().remove(match);
        match.getSupporters().remove(this);
    }
}
