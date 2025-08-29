/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author kieum
 */
@Entity
@Table(name = "train")
@NamedQueries({
    @NamedQuery(name = "Train.findAll", query = "SELECT t FROM Train t"),
    @NamedQuery(name = "Train.findById", query = "SELECT t FROM Train t WHERE t.id = :id"),
    @NamedQuery(name = "Train.findByName", query = "SELECT t FROM Train t WHERE t.name = :name"),
    @NamedQuery(name = "Train.findAllPaginated", query = "SELECT t FROM Train t ORDER BY t.id"),
    @NamedQuery(name = "Train.countAll", query = "SELECT COUNT(t) FROM Train t"),})
public class Train implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainId")
    @JsonIgnore
    private Set<TrainRoute> trainRouteSet;

    public Train() {
    }

    public Train(Integer id) {
        this.id = id;
    }

    public Train(Integer id, String name) {
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

    public Set<TrainRoute> getTrainRouteSet() {
        return trainRouteSet;
    }

    public void setTrainRouteSet(Set<TrainRoute> trainRouteSet) {
        this.trainRouteSet = trainRouteSet;
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
        if (!(object instanceof Train)) {
            return false;
        }
        Train other = (Train) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kmt.pojo.Train[ id=" + id + " ]";
    }

}
