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
@Table(name = "MENUHASINGREDIENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menuhasingredient.findAll", query = "SELECT m FROM Menuhasingredient m"),
    @NamedQuery(name = "Menuhasingredient.findByMenuhasingredientid", query = "SELECT m FROM Menuhasingredient m WHERE m.menuhasingredientid = :menuhasingredientid"),
    @NamedQuery(name = "Menuhasingredient.findByQuantity", query = "SELECT m FROM Menuhasingredient m WHERE m.quantity = :quantity"),
    @NamedQuery(name = "Menuhasingredient.findByMenuid", query = "SELECT m FROM Menuhasingredient m WHERE m.menuid = :menuid"),
    @NamedQuery(name = "Menuhasingredient.findByIngredientid", query = "SELECT m FROM Menuhasingredient m WHERE m.ingredientid = :ingredientid")})
public class Menuhasingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MENUHASINGREDIENTID")
    private Integer menuhasingredientid;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Size(max = 30)
    @Column(name = "MENUID")
    private String menuid;
    @Column(name = "INGREDIENTID")
    private Integer ingredientid;

    public Menuhasingredient() {
    }

    public Menuhasingredient(Integer menuhasingredientid) {
        this.menuhasingredientid = menuhasingredientid;
    }

    public Integer getMenuhasingredientid() {
        return menuhasingredientid;
    }

    public void setMenuhasingredientid(Integer menuhasingredientid) {
        this.menuhasingredientid = menuhasingredientid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
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
        hash += (menuhasingredientid != null ? menuhasingredientid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menuhasingredient)) {
            return false;
        }
        Menuhasingredient other = (Menuhasingredient) object;
        if ((this.menuhasingredientid == null && other.menuhasingredientid != null) || (this.menuhasingredientid != null && !this.menuhasingredientid.equals(other.menuhasingredientid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Menuhasingredient[ menuhasingredientid=" + menuhasingredientid + " ]";
    }
    
}
