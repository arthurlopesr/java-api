package com.client.ws.rasmooplus.domain.entities.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@Entity()
public class UserEntity implements Serializable {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long userId;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String cpf;

    @Column(name = "dt_subscription")
    private LocalDate dtSubscription = LocalDate.now();

    @Column(name = "dt_expiration")
    private LocalDate dtExpiration = LocalDate.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_type_id")
    private UserTypeEntity userType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subscriptions_type_id")
    private SubscriptionsTypeEntity subscriptionsType;
}
