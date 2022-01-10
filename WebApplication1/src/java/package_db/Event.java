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
@Table(name = "EVENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),
    @NamedQuery(name = "Event.findByEventid", query = "SELECT e FROM Event e WHERE e.eventid = :eventid"),
    @NamedQuery(name = "Event.findByEventname", query = "SELECT e FROM Event e WHERE e.eventname = :eventname"),
    @NamedQuery(name = "Event.findByGuestname", query = "SELECT e FROM Event e WHERE e.guestname = :guestname"),
    @NamedQuery(name = "Event.findByDescription", query = "SELECT e FROM Event e WHERE e.description = :description"),
    @NamedQuery(name = "Event.findByTime", query = "SELECT e FROM Event e WHERE e.time = :time"),
    @NamedQuery(name = "Event.deleteByName", query = "DELETE FROM Event e WHERE e.eventid = :eventid"),
    @NamedQuery(name = "Event.findByDate", query = "SELECT e FROM Event e WHERE e.date = :date")})
public class Event implements Serializable {

    @Size(max = 50)
    @Column(name = "EVENTNAME")
    private String eventname;
    @Size(max = 40)
    @Column(name = "GUESTNAME")
    private String guestname;
    @Size(max = 150)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 20)
    @Column(name = "TIME")
    private String time;
    @Size(max = 25)
    @Column(name = "DATE")
    private String date;
    @Size(max = 200)
    @Column(name = "PICTURE")
    private String picture;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EVENTID")
    private Integer eventid;

    public Event() {
    }

    public Event(Integer eventid) {
        this.eventid = eventid;
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventid != null ? eventid.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.eventid == null && other.eventid != null) || (this.eventid != null && !this.eventid.equals(other.eventid))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "package_db.Event[ eventid=" + eventid + " ]";
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    
}
