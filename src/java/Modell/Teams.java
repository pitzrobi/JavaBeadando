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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.json.JSONObject;

/**
 *
 * @author Pitz Róbert
 */
@Entity
@Table(name = "teams")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teams.findAll", query = "SELECT t FROM Teams t")
    , @NamedQuery(name = "Teams.findById", query = "SELECT t FROM Teams t WHERE t.id = :id")
    , @NamedQuery(name = "Teams.findByName", query = "SELECT t FROM Teams t WHERE t.name = :name")
    , @NamedQuery(name = "Teams.findByState", query = "SELECT t FROM Teams t WHERE t.state = :state")
    , @NamedQuery(name = "Teams.findByOwner", query = "SELECT t FROM Teams t WHERE t.owner = :owner")
    , @NamedQuery(name = "Teams.findByStatus", query = "SELECT t FROM Teams t WHERE t.status = :status")})
public class Teams implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "State")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Owner")
    private String owner;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private int status;

    public Teams() {
    }

    public Teams(Integer id) {
        this.id = id;
    }

    public Teams(Integer id, String name, String state, String owner, int status) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.owner = owner;
        this.status = status;
    }
    
    public JSONObject toJSON(){
        JSONObject object = new JSONObject();
        object.put("id", this.id);
        object.put("name", this.name);
        object.put("state", this.state);
        object.put("owner", this.owner);
        object.put("status", this.status);
        
        return object;
    }
    
    public boolean valide(int id)
    {
        EntityManager em = Database.getDbCon();
        Teams test = em.find(Teams.class, id);
        if(test.getId() != null){
        
        return true;
        }
        else{
        return false;
        
        
        }
    }
    
    public static Teams getTeambyId(int id)
    {
        EntityManager em = Database.getDbCon();
        return em.find(Teams.class, id);
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
        if (!(object instanceof Teams)) {
            return false;
        }
        Teams other = (Teams) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modell.Teams[ id=" + id + " ]";
    }
    
}
