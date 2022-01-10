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
@Table(name = "BUYORDERHASMENU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buyorderhasmenu.findAll", query = "SELECT b FROM Buyorderhasmenu b"),
    @NamedQuery(name = "Buyorderhasmenu.findByBuyorderhasmenuid", query = "SELECT b FROM Buyorderhasmenu b WHERE b.buyorderhasmenuid = :buyorderhasmenuid"),
    @NamedQuery(name = "Buyorderhasmenu.findByQuantity", query = "SELECT b FROM Buyorderhasmenu b WHERE b.quantity = :quantity"),
    @NamedQuery(name = "Buyorderhasmenu.findByMenuid", query = "SELECT b FROM Buyorderhasmenu b WHERE b.menuid = :menuid"),
    @NamedQuery(name = "Buyorderhasmenu.findByBuyorderid", query = "SELECT b FROM Buyorderhasmenu b WHERE b.buyorderid = :buyorderid")})
public class Buyorderhasmenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BUYORDERHASMENUID")
    private Integer buyorderhasmenuid;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Size(max = 30)
    @Column(name = "MENUID")
    private String menuid;
    @Column(name = "BUYORDERID")
    private Integer buyorderid;

    public Buyorderhasmenu() {
    }

    public Buyorderhasmenu(Integer buyorderhasmenuid) {
        this.buyorderhasmenuid = buyorderhasmenuid;
    }

    public Integer getBuyorderhasmenuid() {
        return buyorderhasmenuid;
    }

    public void setBuyorderhasmenuid(Integer buyorderhasmenuid) {
        this.buyorderhasmenuid = buyorderhasmenuid;
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

    public Integer getBuyorderid() {
        return buyorderid;
    }

    public void setBuyorderid(Integer buyorderid) {
        this.buyorderid = buyorderid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (buyorderhasmenuid != null ? buyorderhasmenuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buyorderhasmenu)) {
            return false;
        }
        Buyorderhasmenu other = (Buyorderhasmenu) object;
        if ((this.buyorderhasmenuid == null && other.buyorderhasmenuid != null) || (this.buyorderhasmenuid != null && !this.buyorderhasmenuid.equals(other.buyorderhasmenuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Buyorderhasmenu[ buyorderhasmenuid=" + buyorderhasmenuid + " ]";
    }
    
}
