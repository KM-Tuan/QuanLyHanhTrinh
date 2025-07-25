/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author kieum
 */
@Entity
@Table(name = "journey")
@NamedQueries({
    @NamedQuery(name = "Journey.findAll", query = "SELECT j FROM Journey j"),
    @NamedQuery(name = "Journey.findById", query = "SELECT j FROM Journey j WHERE j.id = :id"),
    @NamedQuery(name = "Journey.findByName", query = "SELECT j FROM Journey j WHERE j.name = :name"),
    @NamedQuery(name = "Journey.findByTicketCode", query = "SELECT j FROM Journey j WHERE j.ticketCode = :ticketCode"),
    @NamedQuery(name = "Journey.findByTrainId", query = "SELECT j FROM Journey j WHERE j.trainId = :trainId"),
    @NamedQuery(name = "Journey.findByDepartureStationId", query = "SELECT j FROM Journey j WHERE j.departureStationId = :departureStationId"),
    @NamedQuery(name = "Journey.findByArrivalStationId", query = "SELECT j FROM Journey j WHERE j.arrivalStationId = :arrivalStationId"),
    @NamedQuery(name = "Journey.findByDepartureTime", query = "SELECT j FROM Journey j WHERE j.departureTime = :departureTime"),
    @NamedQuery(name = "Journey.findByArrivalTime", query = "SELECT j FROM Journey j WHERE j.arrivalTime = :arrivalTime"),
    @NamedQuery(name = "Journey.findByTotalDistance", query = "SELECT j FROM Journey j WHERE j.totalDistance = :totalDistance"),
    @NamedQuery(name = "Journey.findByTotalTravelTime", query = "SELECT j FROM Journey j WHERE j.totalTravelTime = :totalTravelTime"),
    @NamedQuery(name = "Journey.findByStatus", query = "SELECT j FROM Journey j WHERE j.status = :status"),
    @NamedQuery(name = "Journey.findByCreatedAt", query = "SELECT j FROM Journey j WHERE j.createdAt = :createdAt")})
public class Journey implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ticket_code")
    private String ticketCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "train_id")
    private int trainId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "departure_station_id")
    private int departureStationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "arrival_station_id")
    private int arrivalStationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "departure_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "arrival_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_distance")
    private int totalDistance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_travel_time")
    @Temporal(TemporalType.TIME)
    private Date totalTravelTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "journeyId")
    private Set<FoodOrder> foodOrderSet;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User createdBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "journeyId")
    private Set<ServiceOrder> serviceOrderSet;

    public Journey() {
    }

    public Journey(Integer id) {
        this.id = id;
    }

    public Journey(Integer id, String name, String ticketCode, int trainId, int departureStationId, int arrivalStationId, Date departureTime, Date arrivalTime, int totalDistance, Date totalTravelTime, String status) {
        this.id = id;
        this.name = name;
        this.ticketCode = ticketCode;
        this.trainId = trainId;
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalDistance = totalDistance;
        this.totalTravelTime = totalTravelTime;
        this.status = status;
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

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public int getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(int arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Date getTotalTravelTime() {
        return totalTravelTime;
    }

    public void setTotalTravelTime(Date totalTravelTime) {
        this.totalTravelTime = totalTravelTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<FoodOrder> getFoodOrderSet() {
        return foodOrderSet;
    }

    public void setFoodOrderSet(Set<FoodOrder> foodOrderSet) {
        this.foodOrderSet = foodOrderSet;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Set<ServiceOrder> getServiceOrderSet() {
        return serviceOrderSet;
    }

    public void setServiceOrderSet(Set<ServiceOrder> serviceOrderSet) {
        this.serviceOrderSet = serviceOrderSet;
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
        if (!(object instanceof Journey)) {
            return false;
        }
        Journey other = (Journey) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kmt.pojo.Journey[ id=" + id + " ]";
    }
    
}
