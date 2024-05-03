package com.client.ws.rasmooplus.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Table(name = "users")
@Entity()
public class UserEntity implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "users_id")
    private Long userId;

    private String name;

    private String email;

    private String phone;

    private String cpf;

    @Column(name = "dt_subscription")
    private LocalDate dtSubscripton = LocalDate.now();

    @Column(name = "dt_expiration")
    private LocalDate dtExpiration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type_id")
    private UserTypeEntity userType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriptions_type_id")
    private SubscriptionsTypeEntity subscriptionsType;

    public UserEntity(Long userId, String name, String email, String phone, String cpf, LocalDate dtSubscripton, LocalDate dtExpiration, UserTypeEntity userType, SubscriptionsTypeEntity subscriptionsType) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        this.dtSubscripton = dtSubscripton;
        this.dtExpiration = dtExpiration;
        this.userType = userType;
        this.subscriptionsType = subscriptionsType;
    }

    public UserEntity() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDtSubscripton() {
        return dtSubscripton;
    }

    public void setDtSubscripton(LocalDate dtSubscripton) {
        this.dtSubscripton = dtSubscripton;
    }

    public LocalDate getDtExpiration() {
        return dtExpiration;
    }

    public void setDtExpiration(LocalDate dtExpiration) {
        this.dtExpiration = dtExpiration;
    }

    public UserTypeEntity getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEntity userType) {
        this.userType = userType;
    }

    public SubscriptionsTypeEntity getSubscriptionsType() {
        return subscriptionsType;
    }

    public void setSubscriptionsType(SubscriptionsTypeEntity subscriptionsType) {
        this.subscriptionsType = subscriptionsType;
    }
}
