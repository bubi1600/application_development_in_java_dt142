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
@Table(name = "BUYORDERHASAPPETIZER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buyorderhasappetizer.findAll", query = "SELECT b FROM Buyorderhasappetizer b"),
    @NamedQuery(name = "Buyorderhasappetizer.findByBuyorderhasappetizerid", query = "SELECT b FROM Buyorderhasappetizer b WHERE b.buyorderhasappetizerid = :buyorderhasappetizerid"),
    @NamedQuery(name = "Buyorderhasappetizer.findByBuyorderid", query = "SELECT b FROM Buyorderhasappetizer b WHERE b.buyorderid = :buyorderid"),
    @NamedQuery(name = "Buyorderhasappetizer.findByAppetizerid", query = "SELECT b FROM Buyorderhasappetizer b WHERE b.appetizerid = :appetizerid"),
    @NamedQuery(name = "Buyorderhasappetizer.findByQuantity", query = "SELECT b FROM Buyorderhasappetizer b WHERE b.quantity = :quantity")})
public class Buyorderhasappetizer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BUYORDERHASAPPETIZERID")
    private Integer buyorderhasappetizerid;
    @Column(name = "BUYORDERID")
    private Integer buyorderid;
    @Size(max = 30)
    @Column(name = "APPETIZERID")
    private String appetizerid;
    @Column(name = "QUANTITY")
    private Integer quantity;

    public Buyorderhasappetizer() {
    }

    public Buyorderhasappetizer(Integer buyorderhasappetizerid) {
        this.buyorderhasappetizerid = buyorderhasappetizerid;
    }

    public Integer getBuyorderhasappetizerid() {
        return buyorderhasappetizerid;
    }

    public void setBuyorderhasappetizerid(Integer buyorderhasappetizerid) {
        this.buyorderhasappetizerid = buyorderhasappetizerid;
    }

    public Integer getBuyorderid() {
        return buyorderid;
    }

    public void setBuyorderid(Integer buyorderid) {
        this.buyorderid = buyorderid;
    }

    public String getAppetizerid() {
        return appetizerid;
    }

    public void setAppetizerid(String appetizerid) {
        this.appetizerid = appetizerid;
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
        hash += (buyorderhasappetizerid != null ? buyorderhasappetizerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buyorderhasappetizer)) {
            return false;
        }
        Buyorderhasappetizer other = (Buyorderhasappetizer) object;
        if ((this.buyorderhasappetizerid == null && other.buyorderhasappetizerid != null) || (this.buyorderhasappetizerid != null && !this.buyorderhasappetizerid.equals(other.buyorderhasappetizerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Buyorderhasappetizer[ buyorderhasappetizerid=" + buyorderhasappetizerid + " ]";
    }
    
}
