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
@Table(name = "DESSERT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dessert.findAll", query = "SELECT d FROM Dessert d"),
    @NamedQuery(name = "Dessert.findByDessertid", query = "SELECT d FROM Dessert d WHERE d.dessertid = :dessertid"),
    @NamedQuery(name = "Dessert.findByPrice", query = "SELECT d FROM Dessert d WHERE d.price = :price"),
    @NamedQuery(name = "Dessert.deleteByName", query = "DELETE FROM Dessert d WHERE d.dessertid = :dessertid"),
    @NamedQuery(name = "Dessert.findByTime", query = "SELECT d FROM Dessert d WHERE d.time = :time")})
public class Dessert implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DESSERTID")
    private String dessertid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "TIME")
    private Integer time;

    public Dessert() {
    }

    public Dessert(String dessertid) {
        this.dessertid = dessertid;
    }

    public String getDessertid() {
        return dessertid;
    }

    public void setDessertid(String dessertid) {
        this.dessertid = dessertid;
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
        hash += (dessertid != null ? dessertid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dessert)) {
            return false;
        }
        Dessert other = (Dessert) object;
        if ((this.dessertid == null && other.dessertid != null) || (this.dessertid != null && !this.dessertid.equals(other.dessertid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Dessert[ dessertid=" + dessertid + " ]";
    }
    
}
