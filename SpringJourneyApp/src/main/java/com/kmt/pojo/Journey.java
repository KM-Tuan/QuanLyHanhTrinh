/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

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
    @NamedQuery(name = "Journey.findByTrainId", query = "SELECT j FROM Journey j WHERE j.trainId = :trainId"),
    @NamedQuery(name = "Journey.findByDepartureStationId", query = "SELECT j FROM Journey j WHERE j.departureStationId = :departureStationId"),
    @NamedQuery(name = "Journey.findByArrivalStationId", query = "SELECT j FROM Journey j WHERE j.arrivalStationId = :arrivalStationId"),
    @NamedQuery(name = "Journey.findByDepartureTime", query = "SELECT j FROM Journey j WHERE j.departureTime = :departureTime"),
    @NamedQuery(name = "Journey.findByArrivalTime", query = "SELECT j FROM Journey j WHERE j.arrivalTime = :arrivalTime"),
    @NamedQuery(name = "Journey.findByTotalDistance", query = "SELECT j FROM Journey j WHERE j.totalDistance = :totalDistance"),
    @NamedQuery(name = "Journey.findByTotalTravelTime", query = "SELECT j FROM Journey j WHERE j.totalTravelTime = :totalTravelTime"),
    @NamedQuery(name = "Journey.findByStatus", query = "SELECT j FROM Journey j WHERE j.status = :status"),
    @NamedQuery(name = "Journey.findByCreatedAt", query = "SELECT j FROM Journey j WHERE j.createdAt = :createdAt"),
    @NamedQuery(name = "Journey.findCompleted", query = "SELECT j FROM Journey j WHERE j.status = :status"),
    @NamedQuery(name = "Journey.findByStatusNot", query = "SELECT j FROM Journey j WHERE j.status <> :status"
    )})
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
    @ManyToOne(optional = false)
    @JoinColumn(name = "train_id", referencedColumnName = "id")
    private Train trainId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "departure_station_id", referencedColumnName = "id")
    private Station departureStationId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "arrival_station_id", referencedColumnName = "id")
    private Station arrivalStationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "departure_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime departureTime;
    @Basic(optional = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;
    @NotNull
    @Column(name = "total_distance")
    private int totalDistance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_travel_time")
    private String totalTravelTime;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private JourneyStatus status;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "created_at")
    private LocalDateTime createdAt;
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

    public Journey(Integer id, String name, Train trainId, Station departureStationId, Station arrivalStationId, LocalDateTime departureTime, LocalDateTime arrivalTime, int totalDistance, String totalTravelTime, JourneyStatus status) {
        this.id = id;
        this.name = name;
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

    public Train getTrainId() {
        return trainId;
    }

    public void setTrainId(Train trainId) {
        this.trainId = trainId;
    }

    public Station getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(Station departureStationId) {
        this.departureStationId = departureStationId;
    }

    public Station getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(Station arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getTotalTravelTime() {
        return totalTravelTime;
    }

    public void setTotalTravelTime(String totalTravelTime) {
        this.totalTravelTime = totalTravelTime;
    }

    public JourneyStatus getStatus() {
        return status;
    }

    public void setStatus(JourneyStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
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

    public enum JourneyStatus {
        WAITTING, RUNNING, COMPLETED
    }

}
