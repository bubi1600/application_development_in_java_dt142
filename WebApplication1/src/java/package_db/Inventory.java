/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package package_db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author omarm
 */
@Entity
@Table(name = "INVENTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i"),
    @NamedQuery(name = "Inventory.findByInventorynameid", query = "SELECT i FROM Inventory i WHERE i.inventorynameid = :inventorynameid"),
    @NamedQuery(name = "Inventory.findByQuantity", query = "SELECT i FROM Inventory i WHERE i.quantity = :quantity")})
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "INVENTORYNAMEID")
    private String inventorynameid;
    @Column(name = "QUANTITY")
    private Integer quantity;

    public Inventory() {
    }

    public Inventory(String inventorynameid) {
        this.inventorynameid = inventorynameid;
    }

    public String getInventorynameid() {
        return inventorynameid;
    }

    public void setInventorynameid(String inventorynameid) {
        this.inventorynameid = inventorynameid;
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
        hash += (inventorynameid != null ? inventorynameid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventory)) {
            return false;
        }
        Inventory other = (Inventory) object;
        if ((this.inventorynameid == null && other.inventorynameid != null) || (this.inventorynameid != null && !this.inventorynameid.equals(other.inventorynameid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Inventory[ inventorynameid=" + inventorynameid + " ]";
    }
    
}
