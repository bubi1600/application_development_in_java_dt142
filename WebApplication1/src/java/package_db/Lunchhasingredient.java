/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package package_db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author omarm
 */
@Entity
@Table(name = "LUNCHHASINGREDIENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lunchhasingredient.findAll", query = "SELECT l FROM Lunchhasingredient l"),
    @NamedQuery(name = "Lunchhasingredient.findByLunchhasingredientid", query = "SELECT l FROM Lunchhasingredient l WHERE l.lunchhasingredientid = :lunchhasingredientid"),
    @NamedQuery(name = "Lunchhasingredient.findByQuantity", query = "SELECT l FROM Lunchhasingredient l WHERE l.quantity = :quantity"),
    @NamedQuery(name = "Lunchhasingredient.findByDayid", query = "SELECT l FROM Lunchhasingredient l WHERE l.dayid = :dayid"),
    @NamedQuery(name = "Lunchhasingredient.findByIngredientid", query = "SELECT l FROM Lunchhasingredient l WHERE l.ingredientid = :ingredientid")})
public class Lunchhasingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LUNCHHASINGREDIENTID")
    private Integer lunchhasingredientid;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Size(max = 30)
    @Column(name = "DAYID")
    private String dayid;
    @Column(name = "INGREDIENTID")
    private Integer ingredientid;

    public Lunchhasingredient() {
    }

    public Lunchhasingredient(Integer lunchhasingredientid) {
        this.lunchhasingredientid = lunchhasingredientid;
    }

    public Integer getLunchhasingredientid() {
        return lunchhasingredientid;
    }

    public void setLunchhasingredientid(Integer lunchhasingredientid) {
        this.lunchhasingredientid = lunchhasingredientid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDayid() {
        return dayid;
    }

    public void setDayid(String dayid) {
        this.dayid = dayid;
    }

    public Integer getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(Integer ingredientid) {
        this.ingredientid = ingredientid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lunchhasingredientid != null ? lunchhasingredientid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lunchhasingredient)) {
            return false;
        }
        Lunchhasingredient other = (Lunchhasingredient) object;
        if ((this.lunchhasingredientid == null && other.lunchhasingredientid != null) || (this.lunchhasingredientid != null && !this.lunchhasingredientid.equals(other.lunchhasingredientid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Lunchhasingredient[ lunchhasingredientid=" + lunchhasingredientid + " ]";
    }
    
}
