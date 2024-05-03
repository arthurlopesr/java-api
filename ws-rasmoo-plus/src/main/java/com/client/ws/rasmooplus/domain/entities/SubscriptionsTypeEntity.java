package com.client.ws.rasmooplus.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "subscriptions_type")
@Entity()
public class SubscriptionsTypeEntity implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subscriptions_type_id")
    private Long subscriptionsTypeId;

    private String name;

    @Column(name = "access_months")
    private Integer accessMonths;

    private BigDecimal price;

    @Column(name = "product_key`")
    private String productKey;

    public SubscriptionsTypeEntity(Long subscriptionsTypeId, String name, Integer accessMonths, BigDecimal price, String productKey) {
        this.subscriptionsTypeId = subscriptionsTypeId;
        this.name = name;
        this.accessMonths = accessMonths;
        this.price = price;
        this.productKey = productKey;
    }

    public SubscriptionsTypeEntity() {
    }

    public Long getSubscriptionsTypeId() {
        return subscriptionsTypeId;
    }

    public void setSubscriptionsTypeId(Long subscriptionsTypeId) {
        this.subscriptionsTypeId = subscriptionsTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAccessMonths() {
        return accessMonths;
    }

    public void setAccessMonths(Integer accessMonths) {
        this.accessMonths = accessMonths;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }
}
