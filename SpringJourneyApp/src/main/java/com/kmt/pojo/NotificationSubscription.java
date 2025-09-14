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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author kieum
 */
@Entity
@Table(name = "notification_subscription", catalog = "qlhtdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "NotificationSubscription.findAll", query = "SELECT n FROM NotificationSubscription n"),
    @NamedQuery(name = "NotificationSubscription.findById", query = "SELECT n FROM NotificationSubscription n WHERE n.id = :id"),
    @NamedQuery(name = "NotificationSubscription.findByCreatedAt", query = "SELECT n FROM NotificationSubscription n WHERE n.createdAt = :createdAt"),
    @NamedQuery(name = "NotificationSubscription.findByJourney", query = "SELECT n FROM NotificationSubscription n WHERE n.journeyName.name = :journeyName"),
    @NamedQuery(name = "NotificationSubscription.existsByJourneyAndUser", query = "SELECT COUNT(n) > 0 FROM NotificationSubscription n WHERE n.journeyName.name = :journeyName AND n.userId.id = :userId")})
public class NotificationSubscription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @JoinColumn(name = "journey_name", referencedColumnName = "name")
    @ManyToOne(optional = false)
    private Journey journeyName;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public NotificationSubscription() {
    }

    public NotificationSubscription(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificationSubscription)) {
            return false;
        }
        NotificationSubscription other = (NotificationSubscription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kmt.pojo.NotificationSubscription[ id=" + id + " ]";
    }

}
