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

/**
 *
 * @author kieum
 */
@Entity
@Table(name = "food_order_item")
@NamedQueries({
    @NamedQuery(name = "FoodOrderItem.findAll", query = "SELECT f FROM FoodOrderItem f"),
    @NamedQuery(name = "FoodOrderItem.findById", query = "SELECT f FROM FoodOrderItem f WHERE f.id = :id"),
    @NamedQuery(name = "FoodOrderItem.findByQuantity", query = "SELECT f FROM FoodOrderItem f WHERE f.quantity = :quantity"),
    @NamedQuery(name = "FoodOrderItem.findByPrice", query = "SELECT f FROM FoodOrderItem f WHERE f.price = :price")})
public class FoodOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @JoinColumn(name = "food_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Food foodId;
    @JoinColumn(name = "food_order_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FoodOrder foodOrderId;

    public FoodOrderItem() {
    }

    public FoodOrderItem(Integer id) {
        this.id = id;
    }

    public FoodOrderItem(Integer id, int quantity, double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Food getFoodId() {
        return foodId;
    }

    public void setFoodId(Food foodId) {
        this.foodId = foodId;
    }

    public FoodOrder getFoodOrderId() {
        return foodOrderId;
    }

    public void setFoodOrderId(FoodOrder foodOrderId) {
        this.foodOrderId = foodOrderId;
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
        if (!(object instanceof FoodOrderItem)) {
            return false;
        }
        FoodOrderItem other = (FoodOrderItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kmt.pojo.FoodOrderItem[ id=" + id + " ]";
    }
    
}
