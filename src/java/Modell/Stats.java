/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modell;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.json.JSONObject;

/**
 *
 * @author Pitz Róbert
 */
@Entity
@Table(name = "stats")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stats.findAll", query = "SELECT s FROM Stats s")
    , @NamedQuery(name = "Stats.findById", query = "SELECT s FROM Stats s WHERE s.id = :id")
    , @NamedQuery(name = "Stats.findByPlayerId", query = "SELECT s FROM Stats s WHERE s.playerId = :playerId")
    , @NamedQuery(name = "Stats.findByPoints", query = "SELECT s FROM Stats s WHERE s.points = :points")
    , @NamedQuery(name = "Stats.findByPass", query = "SELECT s FROM Stats s WHERE s.pass = :pass")
    , @NamedQuery(name = "Stats.findByBlock", query = "SELECT s FROM Stats s WHERE s.block = :block")
    , @NamedQuery(name = "Stats.findByStatus", query = "SELECT s FROM Stats s WHERE s.status = :status")})
public class Stats implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "playerId")
    private int playerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Points")
    private int points;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Pass")
    private int pass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Block")
    private int block;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private int status;

    public Stats() {
    }

    public Stats(Integer id) {
        this.id = id;
    }

    public Stats(Integer id, int playerId, int points, int pass, int block, int status) {
        this.id = id;
        this.playerId = playerId;
        this.points = points;
        this.pass = pass;
        this.block = block;
        this.status = status;
    }
    
    public JSONObject toJSON(){
        JSONObject object = new JSONObject();
        object.put("id", this.id);
        object.put("playerId", this.playerId);
        object.put("points", this.points);
        object.put("pass", this.pass);
        object.put("block", this.block);
        object.put("status", this.status);
        return object;
    }
    
    
    public static Stats getStatbyId(int id)
    {
        EntityManager em = Database.getDbCon();
        return em.find(Stats.class, id);
    }
    
    public boolean valide(int id)
    {
        EntityManager em = Database.getDbCon();
        Stats test = em.find(Stats.class, id);
        if(test.getId() != null){
        
        return true;
        }
        else{
        return false;
        
        
        }
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stats)) {
            return false;
        }
        Stats other = (Stats) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modell.Stats[ id=" + id + " ]";
    }
    
}
