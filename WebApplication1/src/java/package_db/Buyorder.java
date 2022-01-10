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
@Table(name = "BUYORDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buyorder.findAll", query = "SELECT b FROM Buyorder b"),
    @NamedQuery(name = "Buyorder.findByBuyorderid", query = "SELECT b FROM Buyorder b WHERE b.buyorderid = :buyorderid"),
    @NamedQuery(name = "Buyorder.findByDiningtableid", query = "SELECT b FROM Buyorder b WHERE b.diningtableid = :diningtableid"),
    @NamedQuery(name = "Buyorder.findByBillid", query = "SELECT b FROM Buyorder b WHERE b.billid = :billid"),
    @NamedQuery(name = "Buyorder.findByNotes", query = "SELECT b FROM Buyorder b WHERE b.notes = :notes"),
    @NamedQuery(name = "Buyorder.findByStatusdessert", query = "SELECT b FROM Buyorder b WHERE b.statusdessert = :statusdessert"),
    @NamedQuery(name = "Buyorder.findByStatusmenu", query = "SELECT b FROM Buyorder b WHERE b.statusmenu = :statusmenu"),
    @NamedQuery(name = "Buyorder.findByStatusappetizer", query = "SELECT b FROM Buyorder b WHERE b.statusappetizer = :statusappetizer")})
public class Buyorder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BUYORDERID")
    private Integer buyorderid;
    @Column(name = "DININGTABLEID")
    private Integer diningtableid;
    @Column(name = "BILLID")
    private Integer billid;
    @Size(max = 200)
    @Column(name = "NOTES")
    private String notes;
    @Size(max = 20)
    @Column(name = "STATUSDESSERT")
    private String statusdessert;
    @Size(max = 20)
    @Column(name = "STATUSMENU")
    private String statusmenu;
    @Size(max = 20)
    @Column(name = "STATUSAPPETIZER")
    private String statusappetizer;

    public Buyorder() {
    }

    public Buyorder(Integer buyorderid) {
        this.buyorderid = buyorderid;
    }

    public Integer getBuyorderid() {
        return buyorderid;
    }

    public void setBuyorderid(Integer buyorderid) {
        this.buyorderid = buyorderid;
    }

    public Integer getDiningtableid() {
        return diningtableid;
    }

    public void setDiningtableid(Integer diningtableid) {
        this.diningtableid = diningtableid;
    }

    public Integer getBillid() {
        return billid;
    }

    public void setBillid(Integer billid) {
        this.billid = billid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatusdessert() {
        return statusdessert;
    }

    public void setStatusdessert(String statusdessert) {
        this.statusdessert = statusdessert;
    }

    public String getStatusmenu() {
        return statusmenu;
    }

    public void setStatusmenu(String statusmenu) {
        this.statusmenu = statusmenu;
    }

    public String getStatusappetizer() {
        return statusappetizer;
    }

    public void setStatusappetizer(String statusappetizer) {
        this.statusappetizer = statusappetizer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (buyorderid != null ? buyorderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buyorder)) {
            return false;
        }
        Buyorder other = (Buyorder) object;
        if ((this.buyorderid == null && other.buyorderid != null) || (this.buyorderid != null && !this.buyorderid.equals(other.buyorderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Buyorder[ buyorderid=" + buyorderid + " ]";
    }
    
}
