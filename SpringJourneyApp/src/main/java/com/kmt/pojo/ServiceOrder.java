/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author kieum
 */
@Entity
@Table(name = "service_order")
@NamedQueries({
    @NamedQuery(name = "ServiceOrder.findAll", query = "SELECT s FROM ServiceOrder s"),
    @NamedQuery(name = "ServiceOrder.findById", query = "SELECT s FROM ServiceOrder s WHERE s.id = :id"),
    @NamedQuery(name = "ServiceOrder.findByName", query = "SELECT s FROM ServiceOrder s WHERE s.name = :name"),
    @NamedQuery(name = "ServiceOrder.findByCreatedAt", query = "SELECT s FROM ServiceOrder s WHERE s.createdAt = :createdAt"),
//    @NamedQuery(name = "ServiceOrder.findByUserId", query = "SELECT s FROM ServiceOrder s WHERE s.userId.id = :userId"),
    @NamedQuery(name = "ServiceOrder.findByUserId", query = "SELECT s FROM ServiceOrder s " + "JOIN FETCH s.journeyName " + "JOIN FETCH s.stationId " + "WHERE s.userId.id = :userId"),
    @NamedQuery(name = "ServiceOrder.existServiceOrder", query = "SELECT s FROM ServiceOrder s WHERE s.userId.id = :userId AND s.stationId.id = :stationId AND s.journeyName.name = :journeyName AND s.serviceId.id = :serviceId")})
public class ServiceOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Column(name = "created_at")
    private Date createdAt;
    @JoinColumn(name = "journey_name", referencedColumnName = "name")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"id", "trainId", "departureStationId", "arrivalStationId", 
                       "departureTime", "arrivalTime", "totalDistance", "totalTravelTime", 
                       "status", "createdAt", "createdBy", "foodOrderSet", "serviceOrderSet"})
    private Journey journeyName;
    @JsonIgnore
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Service serviceId;
    @JoinColumn(name = "station_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"id", "image", "serviceSet", "file", "distance"})
    private Station stationId;
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public ServiceOrder() {
    }

    public ServiceOrder(Integer id) {
        this.id = id;
    }

    public ServiceOrder(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Journey getJourneyName() {
        return journeyName;
    }

    public void setJourneyName(Journey journeyName) {
        this.journeyName = journeyName;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }

    public Station getStationId() {
        return stationId;
    }

    public void setStationId(Station stationId) {
        this.stationId = stationId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrder)) {
            return false;
        }
        ServiceOrder other = (ServiceOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kmt.pojo.ServiceOrder[ id=" + id + " ]";
    }

}
