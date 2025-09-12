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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author kieum
 */
@Entity
@Table(name = "otp", catalog = "qlhtdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Otp.findAll", query = "SELECT o FROM Otp o"),
    @NamedQuery(name = "Otp.findById", query = "SELECT o FROM Otp o WHERE o.id = :id"),
    @NamedQuery(name = "Otp.findByOtpCode", query = "SELECT o FROM Otp o WHERE o.otpCode = :otpCode"),
    @NamedQuery(name = "Otp.findByEmaiId", query = "SELECT o FROM Otp o WHERE o.emailId.id = :emailId"),
    @NamedQuery(name = "Otp.findByExpiryTime", query = "SELECT o FROM Otp o WHERE o.expiryTime = :expiryTime")})
public class Otp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "otp_code")
    private String otpCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiry_time")
    private LocalDateTime expiryTime;
    @JoinColumn(name = "email_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Email emailId;

    public Otp() {
    }

    public Otp(Integer id) {
        this.id = id;
    }

    public Otp(Integer id, String otpCode, LocalDateTime expiryTime) {
        this.id = id;
        this.otpCode = otpCode;
        this.expiryTime = expiryTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Email getEmailId() {
        return emailId;
    }

    public void setEmailId(Email emailId) {
        this.emailId = emailId;
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
        if (!(object instanceof Otp)) {
            return false;
        }
        Otp other = (Otp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kmt.pojo.Otp[ id=" + id + " ]";
    }
    
}
