package Controllers;

import Model.Club;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for club data
 */
public class ClubController {
    /**
     * Gets all clubs from db
     * @return list of all club from db
     */
    public static List<Club> getClubsFromDb(){
        List<Club> clubsFromDb = new ArrayList<>();
        try{
            DbConnectionController.session.beginTransaction();
            clubsFromDb = DbConnectionController.session.createQuery("from Club").list();
            DbConnectionController.session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  clubsFromDb;
    }
}
