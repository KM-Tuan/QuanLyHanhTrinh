/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author kieum
 */
@Entity
@Table(name = "train_route")
@NamedQueries({
    @NamedQuery(name = "TrainRoute.findAll", query = "SELECT t FROM TrainRoute t"),
    @NamedQuery(name = "TrainRoute.findById", query = "SELECT t FROM TrainRoute t WHERE t.id = :id"),
    @NamedQuery(name = "TrainRoute.findByTrainId", query = "SELECT t FROM TrainRoute t WHERE t.trainId.id = :trainId"),
    @NamedQuery(name = "TrainRoute.findByDistance", query = "SELECT t FROM TrainRoute t WHERE t.distance = :distance"),
    @NamedQuery(name = "TrainRoute.findByTravelTime", query = "SELECT t FROM TrainRoute t WHERE t.travelTime = :travelTime"),
    @NamedQuery(name = "TrainRoute.findByStopOrder", query = "SELECT t FROM TrainRoute t WHERE t.stopOrder = :stopOrder"),
    //Ga đi và ga đến nằm trong cùng 1 đoạn
    @NamedQuery(name = "TrainRoute.findTrainsByAdjacentStations", query = "SELECT DISTINCT tr.trainId FROM TrainRoute tr " + "WHERE tr.departureStationId.id = :departureStationId " + "AND tr.arrivalStationId.id = :arrivalStationId"),
    //Ga đi và ga đến nằm ở 2 đoạn khác nhau
    @NamedQuery(name = "TrainRoute.findTrainsByStations", query = "SELECT DISTINCT tr1.trainId FROM TrainRoute tr1, TrainRoute tr2 " + "WHERE tr1.trainId = tr2.trainId " + "AND tr1.departureStationId.id = :departureStationId " + "AND tr2.arrivalStationId.id = :arrivalStationId " + "AND tr1.stopOrder < tr2.stopOrder"),
    @NamedQuery(name = "TrainRoute.findRoutesBetweenStations", query = "SELECT tr FROM TrainRoute tr " + "WHERE tr.trainId.id = :trainId " + "AND tr.stopOrder >= (SELECT tr1.stopOrder FROM TrainRoute tr1 WHERE tr1.trainId.id = :trainId AND tr1.departureStationId.id = :departureStationId) " + "AND tr.stopOrder <= (SELECT tr2.stopOrder FROM TrainRoute tr2 WHERE tr2.trainId.id = :trainId AND tr2.arrivalStationId.id = :arrivalStationId) " + "ORDER BY tr.stopOrder ASC"),
    @NamedQuery(name = "TrainRoute.findLastByTrainId", query = "SELECT t FROM TrainRoute t WHERE t.trainId.id = :trainId ORDER BY t.stopOrder DESC"),
    @NamedQuery(name = "TrainRoute.shiftStopOrdersUp", query = "UPDATE TrainRoute r SET r.stopOrder = r.stopOrder + 1 " + "WHERE r.trainId.id = :trainId AND r.stopOrder >= :stopOrder"),
    @NamedQuery(name = "TrainRoute.shiftStopOrdersDown", query = "UPDATE TrainRoute r SET r.stopOrder = r.stopOrder - 1 " + "WHERE r.trainId.id = :trainId AND r.stopOrder > :stopOrder")})
public class TrainRoute implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "distance")
    private int distance;
    @Basic(optional = false)
    @NotNull
    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column(name = "travel_time")
    private LocalTime travelTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stop_order")
    private int stopOrder;
    @JoinColumn(name = "departure_station_id", referencedColumnName = "id")
    @ManyToOne
    private Station departureStationId;
    @JoinColumn(name = "arrival_station_id", referencedColumnName = "id")
    @ManyToOne
    private Station arrivalStationId;
    @JoinColumn(name = "train_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Train trainId;

    public TrainRoute() {
    }

    public TrainRoute(Integer id) {
        this.id = id;
    }

    public TrainRoute(Integer id, int distance, LocalTime travelTime, int stopOrder) {
        this.id = id;
        this.distance = distance;
        this.travelTime = travelTime;
        this.stopOrder = stopOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public LocalTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalTime travelTime) {
        this.travelTime = travelTime;
    }

    public int getStopOrder() {
        return stopOrder;
    }

    public void setStopOrder(int stopOrder) {
        this.stopOrder = stopOrder;
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

    public Train getTrainId() {
        return trainId;
    }

    public void setTrainId(Train trainId) {
        this.trainId = trainId;
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
        if (!(object instanceof TrainRoute)) {
            return false;
        }
        TrainRoute other = (TrainRoute) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kmt.pojo.TrainRoute[ id=" + id + " ]";
    }

}
