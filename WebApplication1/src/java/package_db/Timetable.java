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
@Table(name = "TIMETABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timetable.findAll", query = "SELECT t FROM Timetable t"),
    @NamedQuery(name = "Timetable.findByTimetableid", query = "SELECT t FROM Timetable t WHERE t.timetableid = :timetableid"),
    @NamedQuery(name = "Timetable.findByEmployeeid", query = "SELECT t FROM Timetable t WHERE t.employeeid = :employeeid"),
    @NamedQuery(name = "Timetable.findByDate", query = "SELECT t FROM Timetable t WHERE t.date = :date"),
    @NamedQuery(name = "Timetable.findByWorking", query = "SELECT t FROM Timetable t WHERE t.working = :working")})
public class Timetable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TIMETABLEID")
    private Integer timetableid;
    @Column(name = "EMPLOYEEID")
    private Integer employeeid;
    @Size(max = 25)
    @Column(name = "DATE")
    private String date;
    @Size(max = 30)
    @Column(name = "WORKING")
    private String working;

    public Timetable() {
    }

    public Timetable(Integer timetableid) {
        this.timetableid = timetableid;
    }

    public Integer getTimetableid() {
        return timetableid;
    }

    public void setTimetableid(Integer timetableid) {
        this.timetableid = timetableid;
    }

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timetableid != null ? timetableid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Timetable)) {
            return false;
        }
        Timetable other = (Timetable) object;
        if ((this.timetableid == null && other.timetableid != null) || (this.timetableid != null && !this.timetableid.equals(other.timetableid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Timetable[ timetableid=" + timetableid + " ]";
    }
    
}
