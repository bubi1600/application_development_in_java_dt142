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
@Table(name = "DRINK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Drink.findAll", query = "SELECT d FROM Drink d"),
    @NamedQuery(name = "Drink.findByDrinkid", query = "SELECT d FROM Drink d WHERE d.drinkid = :drinkid"),
    @NamedQuery(name = "Drink.findByPrice", query = "SELECT d FROM Drink d WHERE d.price = :price")})
public class Drink implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DRINKID")
    private String drinkid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;

    public Drink() {
    }

    public Drink(String drinkid) {
        this.drinkid = drinkid;
    }

    public String getDrinkid() {
        return drinkid;
    }

    public void setDrinkid(String drinkid) {
        this.drinkid = drinkid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (drinkid != null ? drinkid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drink)) {
            return false;
        }
        Drink other = (Drink) object;
        if ((this.drinkid == null && other.drinkid != null) || (this.drinkid != null && !this.drinkid.equals(other.drinkid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Drink[ drinkid=" + drinkid + " ]";
    }
    
}
