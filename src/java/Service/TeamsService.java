
package Service;

import Modell.Teams;
import Repository.TeamsRepo;
import java.util.List;


public class TeamsService {
    public static String addNewTeam(Teams t){
    if(t.getState().length()>3){
        if (TeamsRepo.addNewTeam(t)) {
            return "Csapat rögzítése sikeres";
        }
        else {
            return "Adat megfelelő, de a rögzítés nem sikerült";
        }
      }
    else{
        return "Államnév nem lehet rövidített";
    }
    
    }
    
    
public static List<Teams> getAllActiveTeams(){

    return TeamsRepo.getAllActiceTeams();
}


public static String logdeleteTeam(Teams t){
        if (TeamsRepo.logdeleteTeam(t)) {
        return "A csapat törlése sikeres volt";
    }
        else{
        
        return "A törlés nem sikerült";
        }
}


public static String updateTeam(Teams t){
    if (TeamsRepo.updateTeam(t)) {
        return "Sikeres update";
    }
    else{
    
        return "Sikertelen update";
    }
}
public static String changeTeamName(Teams t){
    if(t.getName().length()<50){
        if (TeamsRepo.changeTeamName(t)) {
            return "Sikeres névváltás";
        }
        else{

            return "Sikertelen névváltás";
        }
    }
    else{
        return "Csapatnév nem lehet hosszabb mint 50 karakter";
    }
}


}


/*
public static String logDelMovie(Movies m){
        if(MoviesRepo.logDelMovie(m)){
                return "A film törlése sikeresen megtörtént!";
            }
            else{
                return "A törlés nem sikerült";
            }
    }*/