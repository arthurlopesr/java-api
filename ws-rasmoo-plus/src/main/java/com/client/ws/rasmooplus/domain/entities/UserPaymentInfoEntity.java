package com.client.ws.rasmooplus.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_payment_info")
public class UserPaymentInfoEntity implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_payment_info_id")
    private Long userPaymentInfoId;

    @Column(name = "card_number", unique = true)
    private String cardNumber;

    @Column(name = "card_expiration_month")
    private Long cardExpirationMonth;

    @Column(name = "card_expiration_year")
    private Long cardExpirationYear;

    @Column(name = "card_security_code")
    private String cardSecurityCode;

    private BigDecimal price;

    private Integer instalments;

    @Column(name = "dt_payment")
    private LocalDate dtPayment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
