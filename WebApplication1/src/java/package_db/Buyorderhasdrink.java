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
@Table(name = "BUYORDERHASDRINK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buyorderhasdrink.findAll", query = "SELECT b FROM Buyorderhasdrink b"),
    @NamedQuery(name = "Buyorderhasdrink.findByBuyorderhasdrinkid", query = "SELECT b FROM Buyorderhasdrink b WHERE b.buyorderhasdrinkid = :buyorderhasdrinkid"),
    @NamedQuery(name = "Buyorderhasdrink.findByQuantity", query = "SELECT b FROM Buyorderhasdrink b WHERE b.quantity = :quantity"),
    @NamedQuery(name = "Buyorderhasdrink.findByDrinkid", query = "SELECT b FROM Buyorderhasdrink b WHERE b.drinkid = :drinkid"),
    @NamedQuery(name = "Buyorderhasdrink.findByBuyorderid", query = "SELECT b FROM Buyorderhasdrink b WHERE b.buyorderid = :buyorderid")})
public class Buyorderhasdrink implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BUYORDERHASDRINKID")
    private Integer buyorderhasdrinkid;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Size(max = 30)
    @Column(name = "DRINKID")
    private String drinkid;
    @Column(name = "BUYORDERID")
    private Integer buyorderid;

    public Buyorderhasdrink() {
    }

    public Buyorderhasdrink(Integer buyorderhasdrinkid) {
        this.buyorderhasdrinkid = buyorderhasdrinkid;
    }

    public Integer getBuyorderhasdrinkid() {
        return buyorderhasdrinkid;
    }

    public void setBuyorderhasdrinkid(Integer buyorderhasdrinkid) {
        this.buyorderhasdrinkid = buyorderhasdrinkid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDrinkid() {
        return drinkid;
    }

    public void setDrinkid(String drinkid) {
        this.drinkid = drinkid;
    }

    public Integer getBuyorderid() {
        return buyorderid;
    }

    public void setBuyorderid(Integer buyorderid) {
        this.buyorderid = buyorderid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (buyorderhasdrinkid != null ? buyorderhasdrinkid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buyorderhasdrink)) {
            return false;
        }
        Buyorderhasdrink other = (Buyorderhasdrink) object;
        if ((this.buyorderhasdrinkid == null && other.buyorderhasdrinkid != null) || (this.buyorderhasdrinkid != null && !this.buyorderhasdrinkid.equals(other.buyorderhasdrinkid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Buyorderhasdrink[ buyorderhasdrinkid=" + buyorderhasdrinkid + " ]";
    }
    
}
