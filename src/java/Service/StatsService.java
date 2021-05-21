
package Service;

import Modell.Database;
import Modell.Players;
import Modell.Stats;
import Repository.StatsRepo;
import java.util.List;
import javax.persistence.EntityManager;


public class StatsService {
    public static String addNewStat(Stats s){
    EntityManager em = Database.getDbCon();
    Players test = em.find(Players.class, s.getPlayerId());
    
    if(test.getName()!=null){
        if (StatsRepo.addNewStat(s)) {
            return "Stat rögzítése sikeres";
        }
        else {
            return "Már létezik ehhez a játékoshoz stat";
        }
      }
    else{
        return "Nem létezik a játékos akihez ez a stat kerülne";
    }
    
  }
    
    public static List<Stats> getAllActiveStats(){

    return StatsRepo.getAllActiceStats();
}
    
    
    public static String logdeleteStat(Stats s){
        if (StatsRepo.logdeleteStat(s)) {
        return "A stat törlése sikeres volt";
    }
        else{
        
        return "A törlés nem sikerült";
        }
}
    
    public static String updateStat(Stats s){
    if(s.getPoints()<500){
        
        if (StatsRepo.updateStat(s)) {
            return "Sikeres update";
        }
        else{

            return "Sikertelen update";
        }
    }    
    else{
        return "Valós pontszám megadása kötelező";
    }
}
    
    public static Integer getscore(Stats s)
    {
        if (StatsRepo.getscore(s)) {
            return (s.getBlock()+s.getPass()+s.getPoints())/3;
        }
        else{

            return 0;
        }
    }
    
    
    
    
}
