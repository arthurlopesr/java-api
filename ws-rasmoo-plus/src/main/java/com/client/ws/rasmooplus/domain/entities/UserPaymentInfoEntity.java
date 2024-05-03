package com.client.ws.rasmooplus.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "user_payment_info")
@Entity
public class UserPaymentInfoEntity implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_payment_info_id")
    private Long userPaymentInfoId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_expiration_month")
    private Long cardExpirationMonth;

    @Column(name = "card_expiration_year")
    private Long privateExpirationYear;

    @Column(name = "card_security_code")
    private String cardSecurityCode;

    private BigDecimal price;

    private Integer instalments;

    @Column(name = "dt_payment")
    private LocalDate dtPayment;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public UserPaymentInfoEntity(Long userPaymentInfoId, String cardNumber, Long cardExpirationMonth, Long privateExpirationYear, String cardSecurityCode, BigDecimal price, Integer instalments, LocalDate dtPayment, UserEntity user) {
        this.userPaymentInfoId = userPaymentInfoId;
        this.cardNumber = cardNumber;
        this.cardExpirationMonth = cardExpirationMonth;
        this.privateExpirationYear = privateExpirationYear;
        this.cardSecurityCode = cardSecurityCode;
        this.price = price;
        this.instalments = instalments;
        this.dtPayment = dtPayment;
        this.user = user;
    }

    public UserPaymentInfoEntity() {
    }

    public Long getUserPaymentInfoId() {
        return userPaymentInfoId;
    }

    public void setUserPaymentInfoId(Long userPaymentInfoId) {
        this.userPaymentInfoId = userPaymentInfoId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getCardExpirationMonth() {
        return cardExpirationMonth;
    }

    public void setCardExpirationMonth(Long cardExpirationMonth) {
        this.cardExpirationMonth = cardExpirationMonth;
    }

    public Long getPrivateExpirationYear() {
        return privateExpirationYear;
    }

    public void setPrivateExpirationYear(Long privateExpirationYear) {
        this.privateExpirationYear = privateExpirationYear;
    }

    public String getCardSecurityCode() {
        return cardSecurityCode;
    }

    public void setCardSecurityCode(String cardSecurityCode) {
        this.cardSecurityCode = cardSecurityCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getInstalments() {
        return instalments;
    }

    public void setInstalments(Integer instalments) {
        this.instalments = instalments;
    }

    public LocalDate getDtPayment() {
        return dtPayment;
    }

    public void setDtPayment(LocalDate dtPayment) {
        this.dtPayment = dtPayment;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
