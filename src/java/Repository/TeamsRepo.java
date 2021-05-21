
package Repository;

import Modell.Database;
import Modell.Teams;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;


public class TeamsRepo {
    public static boolean addNewTeam(Teams t){
    
    try{
        EntityManager em = Database.getDbCon();
        
        StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewTeam");
        
        spq.registerStoredProcedureParameter("nameIn", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("stateIn", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("ownerIn", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("statusIn", Integer.class, ParameterMode.IN);
        
        
        spq.setParameter("nameIn", t.getName());
        spq.setParameter("stateIn", t.getState());
        spq.setParameter("ownerIn", t.getOwner());
        spq.setParameter("statusIn", t.getStatus());
        
        spq.execute();
        
        return true;
    }
    catch(Exception ex){
        System.out.println(ex.getMessage());
        return false;
    }
    
    
    }
    
    public static List<Teams> getAllActiceTeams(){
        List<Teams> result = new ArrayList();
    try{
        EntityManager em = Database.getDbCon();
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllActiveTeams");
        
        List<Object[]> teams = spq.getResultList();
        for(Object[] team : teams){
            int id = Integer.parseInt(team[0].toString());
            Teams t = em.find(Teams.class, id);
            result.add(t);
        }
        
    }
    catch(Exception ex){
    }
    finally{
        return result;
    }
    }
    
    
    public static boolean logdeleteTeam(Teams t){
        try{
            EntityManager em = Database.getDbCon();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("logicalDeleteByTeamId");
            spq.registerStoredProcedureParameter("idIn", Integer.class, ParameterMode.IN);
            spq.setParameter("idIn", t.getId());
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
    
    
    
    
    public static boolean updateTeam(Teams t){
        
        try{
            EntityManager em = Database.getDbCon();
            em.getTransaction().begin();
            
            Teams uj = em.find(Teams.class, t.getId());
            uj.setOwner(t.getOwner());
            
            em.getTransaction().commit();
            return true;
        }
        
        catch(Exception ex){
            
            
            return false;
        }
    }
    
    public static boolean changeTeamName(Teams t){
        
        try{
            EntityManager em = Database.getDbCon();
            em.getTransaction().begin();
            
            Teams uj = em.find(Teams.class, t.getId());
            uj.setName(t.getName());
            
            
            em.getTransaction().commit();
            return true;
        }
        
        catch(Exception ex){
            
            
            return false;
        }
    }
    
}

/*
public static boolean logDelMovie(Movies m){
        try{
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("logicalDeleteMovieById");
        
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", m.getId());
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }*/