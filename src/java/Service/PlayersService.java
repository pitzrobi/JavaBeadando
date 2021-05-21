
package Service;
import Modell.Database;
import Modell.Players;
import Modell.Teams;
import Repository.PlayersRepo;
import java.util.List;
import javax.persistence.EntityManager;

public class PlayersService {
    
    public static String addNewPlayer(Players p){
    if(p.getPosition().length()<5){
        if (PlayersRepo.addNewTeam(p)) {
            return "Játékos rögzítése sikeres";
        }
        else {
            return "Adat megfelelő, de a rögzítés nem sikerült";
        }
      }
    else{
        return "Pozíció csak rövidítve adható meg";
    }
    
    }
    
    
    public static List<Players> getAllActivePlayers(){

    return PlayersRepo.getAllActicePlayers();
}
    
    
    public static String logdeletePlayer(Players p){
        if (PlayersRepo.logdeletePlayer(p)) {
        return "A játékos törlése sikeres volt";
    }
        else{
        
        return "A törlés nem sikerült";
        }
}
    
    public static String updatePlayer(Players p){
        
    if(p.getPosition().length()<5){    
        
        if (PlayersRepo.updatePlayer(p)) {
        
            return "Sikeres update";
        }
        else{
    
            return "Adat megfelelő, de sikertelen update";
        }
    }
    else{
        return "Pozíció csak rövidítve adható meg";
    }
}
    
    public static String changePlayerTeam(Players p){
        EntityManager em = Database.getDbCon();
        Teams t = em.find(Teams.class, p.getTeamId());
        
        
    if(t.valide(t.getId())){    
        
        if (PlayersRepo.changePlayerTeam(p)) {
        
            return "Sikeres váltás";
        }
        else{
    
            return "Adat megfelelő, de sikertelen update";
        }
    }
    else{
        return "Nincs ilyen csapat ID";
    }
}
    
}
