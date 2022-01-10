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
@Table(name = "BUYORDERHASDESSERT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buyorderhasdessert.findAll", query = "SELECT b FROM Buyorderhasdessert b"),
    @NamedQuery(name = "Buyorderhasdessert.findByBuyorderhasdessertid", query = "SELECT b FROM Buyorderhasdessert b WHERE b.buyorderhasdessertid = :buyorderhasdessertid"),
    @NamedQuery(name = "Buyorderhasdessert.findByBuyorderid", query = "SELECT b FROM Buyorderhasdessert b WHERE b.buyorderid = :buyorderid"),
    @NamedQuery(name = "Buyorderhasdessert.findByDessertid", query = "SELECT b FROM Buyorderhasdessert b WHERE b.dessertid = :dessertid"),
    @NamedQuery(name = "Buyorderhasdessert.findByQuantity", query = "SELECT b FROM Buyorderhasdessert b WHERE b.quantity = :quantity")})
public class Buyorderhasdessert implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BUYORDERHASDESSERTID")
    private Integer buyorderhasdessertid;
    @Column(name = "BUYORDERID")
    private Integer buyorderid;
    @Size(max = 30)
    @Column(name = "DESSERTID")
    private String dessertid;
    @Column(name = "QUANTITY")
    private Integer quantity;

    public Buyorderhasdessert() {
    }

    public Buyorderhasdessert(Integer buyorderhasdessertid) {
        this.buyorderhasdessertid = buyorderhasdessertid;
    }

    public Integer getBuyorderhasdessertid() {
        return buyorderhasdessertid;
    }

    public void setBuyorderhasdessertid(Integer buyorderhasdessertid) {
        this.buyorderhasdessertid = buyorderhasdessertid;
    }

    public Integer getBuyorderid() {
        return buyorderid;
    }

    public void setBuyorderid(Integer buyorderid) {
        this.buyorderid = buyorderid;
    }

    public String getDessertid() {
        return dessertid;
    }

    public void setDessertid(String dessertid) {
        this.dessertid = dessertid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (buyorderhasdessertid != null ? buyorderhasdessertid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buyorderhasdessert)) {
            return false;
        }
        Buyorderhasdessert other = (Buyorderhasdessert) object;
        if ((this.buyorderhasdessertid == null && other.buyorderhasdessertid != null) || (this.buyorderhasdessertid != null && !this.buyorderhasdessertid.equals(other.buyorderhasdessertid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Buyorderhasdessert[ buyorderhasdessertid=" + buyorderhasdessertid + " ]";
    }
    
}
