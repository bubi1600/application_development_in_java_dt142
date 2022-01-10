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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author omarm
 */
@Entity
@Table(name = "DININGTABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diningtable.findAll", query = "SELECT d FROM Diningtable d"),
    @NamedQuery(name = "Diningtable.findByDiningtableid", query = "SELECT d FROM Diningtable d WHERE d.diningtableid = :diningtableid")})
public class Diningtable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DININGTABLEID")
    private Integer diningtableid;

    public Diningtable() {
    }

    public Diningtable(Integer diningtableid) {
        this.diningtableid = diningtableid;
    }

    public Integer getDiningtableid() {
        return diningtableid;
    }

    public void setDiningtableid(Integer diningtableid) {
        this.diningtableid = diningtableid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diningtableid != null ? diningtableid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diningtable)) {
            return false;
        }
        Diningtable other = (Diningtable) object;
        if ((this.diningtableid == null && other.diningtableid != null) || (this.diningtableid != null && !this.diningtableid.equals(other.diningtableid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Diningtable[ diningtableid=" + diningtableid + " ]";
    }
    
}
