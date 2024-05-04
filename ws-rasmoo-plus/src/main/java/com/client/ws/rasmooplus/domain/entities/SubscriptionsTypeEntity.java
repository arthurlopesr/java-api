package com.client.ws.rasmooplus.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subscriptions_type")
@Entity()
public class SubscriptionsTypeEntity implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscriptions_type_id")
    private Long subscriptionsTypeId;

    private String name;

    @Column(name = "access_months")
    private Integer accessMonths;

    private BigDecimal price;

    @Column(name = "product_key", unique = true)
    private String productKey;
}
