/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modell;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.json.JSONObject;

/**
 *
 * @author Pitz Róbert
 */
@Entity
@Table(name = "players")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Players.findAll", query = "SELECT p FROM Players p")
    , @NamedQuery(name = "Players.findById", query = "SELECT p FROM Players p WHERE p.id = :id")
    , @NamedQuery(name = "Players.findByName", query = "SELECT p FROM Players p WHERE p.name = :name")
    , @NamedQuery(name = "Players.findByPosition", query = "SELECT p FROM Players p WHERE p.position = :position")
    , @NamedQuery(name = "Players.findByBirth", query = "SELECT p FROM Players p WHERE p.birth = :birth")
    , @NamedQuery(name = "Players.findByTeamId", query = "SELECT p FROM Players p WHERE p.teamId = :teamId")
    , @NamedQuery(name = "Players.findByStatus", query = "SELECT p FROM Players p WHERE p.status = :status")})
public class Players implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "Position")
    private String position;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Birth")
    @Temporal(TemporalType.DATE)
    private Date birth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "teamId")
    private int teamId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private int status;

    public Players() {
    }

    public Players(Integer id) {
        this.id = id;
    }

    public Players(Integer id, String name, String position, Date birth, int teamId, int status) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.birth = birth;
        this.teamId = teamId;
        this.status = status;
    }
    public JSONObject toJSON(){
        JSONObject object = new JSONObject();
        object.put("id", this.id);
        object.put("name", this.name);
        object.put("position", this.position);
        object.put("birth", this.birth);
        object.put("teamId", this.teamId);
        object.put("status", this.status);
        return object;
    }
    
    public boolean valide(int id)
    {
        EntityManager em = Database.getDbCon();
        Players test = em.find(Players.class, id);
        if(test.getId() != null){
        
        return true;
        }
        else{
        return false;
        
        
        }
    }
    
    public static Players getPlayerbyId(int id)
    {
        EntityManager em = Database.getDbCon();
        return em.find(Players.class, id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
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
        if (!(object instanceof Players)) {
            return false;
        }
        Players other = (Players) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modell.Players[ id=" + id + " ]";
    }
    
}
