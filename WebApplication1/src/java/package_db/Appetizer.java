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
@Table(name = "APPETIZER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appetizer.findAll", query = "SELECT a FROM Appetizer a"),
    @NamedQuery(name = "Appetizer.findByAppetizerid", query = "SELECT a FROM Appetizer a WHERE a.appetizerid = :appetizerid"),
    @NamedQuery(name = "Appetizer.findByPrice", query = "SELECT a FROM Appetizer a WHERE a.price = :price"),
    @NamedQuery(name = "Appetizer.deleteByName", query = "DELETE FROM Appetizer a WHERE a.appetizerid = :appetizerid"),
    @NamedQuery(name = "Appetizer.findByTime", query = "SELECT a FROM Appetizer a WHERE a.time = :time")})
public class Appetizer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "APPETIZERID")
    private String appetizerid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "TIME")
    private Integer time;

    public Appetizer() {
    }

    public Appetizer(String appetizerid) {
        this.appetizerid = appetizerid;
    }

    public String getAppetizerid() {
        return appetizerid;
    }

    public void setAppetizerid(String appetizerid) {
        this.appetizerid = appetizerid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appetizerid != null ? appetizerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appetizer)) {
            return false;
        }
        Appetizer other = (Appetizer) object;
        if ((this.appetizerid == null && other.appetizerid != null) || (this.appetizerid != null && !this.appetizerid.equals(other.appetizerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Appetizer[ appetizerid=" + appetizerid + " ]";
    }
    
}
