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
@Table(name = "LUNCH")
@XmlRootElement
@NamedQueries({
     @NamedQuery(name = "Lunch.findAll", query = "SELECT\n" +
"     l FROM Lunch l ORDER BY CASE l.dayid\n" +        
"          WHEN  'Tisdag' THEN 1\n" +
"          WHEN  'Onsdag' THEN 2\n" +
"          WHEN  'Torsdag' THEN 3\n" +
"          WHEN  'Fredag' THEN 4\n" +             
"          ELSE 0\n" +
"     END ASC"),
    @NamedQuery(name = "Lunch.findByDayid", query = "SELECT l FROM Lunch l WHERE l.dayid = :dayid"),
    @NamedQuery(name = "Lunch.findByLunchname", query = "SELECT l FROM Lunch l WHERE l.lunchname = :lunchname"),
    @NamedQuery(name = "Lunch.findByDescription", query = "SELECT l FROM Lunch l WHERE l.description = :description"),
    @NamedQuery(name = "Lunch.findByPrice", query = "SELECT l FROM Lunch l WHERE l.price = :price"),
    @NamedQuery(name = "Lunch.findByTime", query = "SELECT l FROM Lunch l WHERE l.time = :time"),
    @NamedQuery(name = "Lunch.deleteByName", query = "DELETE FROM Lunch l WHERE l.dayid = :dayid"),
    @NamedQuery(name = "Lunch.findByPicture", query = "SELECT l FROM Lunch l WHERE l.picture = :picture")})
public class Lunch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DAYID")
    private String dayid;
    @Size(max = 30)
    @Column(name = "LUNCHNAME")
    private String lunchname;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "TIME")
    private Integer time;
    @Size(max = 200)
    @Column(name = "PICTURE")
    private String picture;

    public Lunch() {
    }

    public Lunch(String dayid) {
        this.dayid = dayid;
    }

    public String getDayid() {
        return dayid;
    }

    public void setDayid(String dayid) {
        this.dayid = dayid;
    }

    public String getLunchname() {
        return lunchname;
    }

    public void setLunchname(String lunchname) {
        this.lunchname = lunchname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dayid != null ? dayid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lunch)) {
            return false;
        }
        Lunch other = (Lunch) object;
        if ((this.dayid == null && other.dayid != null) || (this.dayid != null && !this.dayid.equals(other.dayid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Lunch[ dayid=" + dayid + " ]";
    }
    
}
