/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kieum
 */
@Entity
@Table(name = "food_order")
@NamedQueries({
    @NamedQuery(name = "FoodOrder.findAll", query = "SELECT f FROM FoodOrder f"),
    @NamedQuery(name = "FoodOrder.findById", query = "SELECT f FROM FoodOrder f WHERE f.id = :id"),
    @NamedQuery(name = "FoodOrder.findByName", query = "SELECT f FROM FoodOrder f WHERE f.name = :name"),
    @NamedQuery(name = "FoodOrder.findByTotalAmount", query = "SELECT f FROM FoodOrder f WHERE f.totalAmount = :totalAmount"),
    @NamedQuery(name = "FoodOrder.findByCreatedAt", query = "SELECT f FROM FoodOrder f WHERE f.createdAt = :createdAt")})
public class FoodOrder implements Serializable {

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
    @Column(name = "total_amount")
    private double totalAmount;
    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JoinColumn(name = "journey_name", referencedColumnName = "name")
    @JsonIgnoreProperties({"id", "trainId", "departureStationId", "arrivalStationId",
        "departureTime", "arrivalTime", "totalDistance", "totalTravelTime",
        "status", "createdAt", "createdBy", "foodOrderSet", "serviceOrderSet"})
    @ManyToOne(optional = false)
    private Journey journeyName;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodOrderId")
    @JsonManagedReference
    private List<FoodOrderItem> foodOrderItemList = new ArrayList<>();

    public FoodOrder() {
    }

    public FoodOrder(Integer id) {
        this.id = id;
    }

    public FoodOrder(Integer id, String name, double totalAmount) {
        this.id = id;
        this.name = name;
        this.totalAmount = totalAmount;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Journey getJourneyName() {
        return journeyName;
    }

    public void setJourneyName(Journey journeyName) {
        this.journeyName = journeyName;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public List<FoodOrderItem> getFoodOrderItemList() {
        return foodOrderItemList;
    }

    public void setFoodOrderItemList(List<FoodOrderItem> foodOrderItemList) {
        this.foodOrderItemList = foodOrderItemList;
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
        if (!(object instanceof FoodOrder)) {
            return false;
        }
        FoodOrder other = (FoodOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kmt.pojo.FoodOrder[ id=" + id + " ]";
    }

}
