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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByAvatar", query = "SELECT u FROM User u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role"),
    @NamedQuery(name = "User.findByIsActive", query = "SELECT u FROM User u WHERE u.isActive = :isActive")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 50)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 50)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "role")
    private String role;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<ServiceComment> serviceCommentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<FoodOrder> foodOrderSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<FoodComment> foodCommentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Set<Journey> journeySet;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Admin admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<ServiceOrder> serviceOrderSet;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Staff staff;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<ServiceLike> serviceLikeSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<FoodLike> foodLikeSet;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Passenger passenger;
    @OneToOne(mappedBy = "userId")
    private Phone phone;
    @OneToOne(mappedBy = "userId")
    private Email email;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String password, String role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Set<ServiceComment> getServiceCommentSet() {
        return serviceCommentSet;
    }

    public void setServiceCommentSet(Set<ServiceComment> serviceCommentSet) {
        this.serviceCommentSet = serviceCommentSet;
    }

    public Set<FoodOrder> getFoodOrderSet() {
        return foodOrderSet;
    }

    public void setFoodOrderSet(Set<FoodOrder> foodOrderSet) {
        this.foodOrderSet = foodOrderSet;
    }

    public Set<FoodComment> getFoodCommentSet() {
        return foodCommentSet;
    }

    public void setFoodCommentSet(Set<FoodComment> foodCommentSet) {
        this.foodCommentSet = foodCommentSet;
    }

    public Set<Journey> getJourneySet() {
        return journeySet;
    }

    public void setJourneySet(Set<Journey> journeySet) {
        this.journeySet = journeySet;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Set<ServiceOrder> getServiceOrderSet() {
        return serviceOrderSet;
    }

    public void setServiceOrderSet(Set<ServiceOrder> serviceOrderSet) {
        this.serviceOrderSet = serviceOrderSet;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Set<ServiceLike> getServiceLikeSet() {
        return serviceLikeSet;
    }

    public void setServiceLikeSet(Set<ServiceLike> serviceLikeSet) {
        this.serviceLikeSet = serviceLikeSet;
    }

    public Set<FoodLike> getFoodLikeSet() {
        return foodLikeSet;
    }

    public void setFoodLikeSet(Set<FoodLike> foodLikeSet) {
        this.foodLikeSet = foodLikeSet;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kmt.pojo.User[ id=" + id + " ]";
    }
    
}
