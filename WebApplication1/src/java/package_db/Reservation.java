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
@Table(name = "RESERVATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByReservationid", query = "SELECT r FROM Reservation r WHERE r.reservationid = :reservationid"),
    @NamedQuery(name = "Reservation.findByDiningtableid", query = "SELECT r FROM Reservation r WHERE r.diningtableid = :diningtableid"),
    @NamedQuery(name = "Reservation.findByNrofpeople", query = "SELECT r FROM Reservation r WHERE r.nrofpeople = :nrofpeople"),
    @NamedQuery(name = "Reservation.findByEmail", query = "SELECT r FROM Reservation r WHERE r.email = :email"),
    @NamedQuery(name = "Reservation.findByDate", query = "SELECT r FROM Reservation r WHERE r.date = :date"),
    @NamedQuery(name = "Reservation.findByPhonenr", query = "SELECT r FROM Reservation r WHERE r.phonenr = :phonenr"),
    @NamedQuery(name = "Reservation.findByFname", query = "SELECT r FROM Reservation r WHERE r.fname = :fname"),
    @NamedQuery(name = "Reservation.findByLname", query = "SELECT r FROM Reservation r WHERE r.lname = :lname"),
    @NamedQuery(name = "Reservation.findByTime", query = "SELECT r FROM Reservation r WHERE r.time = :time")})
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RESERVATIONID")
    private Integer reservationid;
    @Column(name = "DININGTABLEID")
    private Integer diningtableid;
    @Column(name = "NROFPEOPLE")
    private Integer nrofpeople;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 25)
    @Column(name = "DATE")
    private String date;
    @Size(max = 20)
    @Column(name = "PHONENR")
    private String phonenr;
    @Size(max = 30)
    @Column(name = "FNAME")
    private String fname;
    @Size(max = 30)
    @Column(name = "LNAME")
    private String lname;
    @Size(max = 15)
    @Column(name = "TIME")
    private String time;

    public Reservation() {
    }

    public Reservation(Integer reservationid) {
        this.reservationid = reservationid;
    }

    public Integer getReservationid() {
        return reservationid;
    }

    public void setReservationid(Integer reservationid) {
        this.reservationid = reservationid;
    }

    public Integer getDiningtableid() {
        return diningtableid;
    }

    public void setDiningtableid(Integer diningtableid) {
        this.diningtableid = diningtableid;
    }

    public Integer getNrofpeople() {
        return nrofpeople;
    }

    public void setNrofpeople(Integer nrofpeople) {
        this.nrofpeople = nrofpeople;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhonenr() {
        return phonenr;
    }

    public void setPhonenr(String phonenr) {
        this.phonenr = phonenr;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationid != null ? reservationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.reservationid == null && other.reservationid != null) || (this.reservationid != null && !this.reservationid.equals(other.reservationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "package_db.Reservation[ reservationid=" + reservationid + " ]";
    }
    
}
