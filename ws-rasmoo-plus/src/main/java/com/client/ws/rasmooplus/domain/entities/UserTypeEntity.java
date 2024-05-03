package com.client.ws.rasmooplus.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Table(name = "user_type")
@Entity()
public class UserTypeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_type_id")
    private Long userId;

    private String name;

    private String description;

    public UserTypeEntity(Long userId, String name, String description) {
        this.userId = userId;
        this.name = name;
        this.description = description;
    }

    public UserTypeEntity() {
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

    public String getDescription() {
        return description;
    }

    public void setDescriptiom(String description) {
        this.description = description;
    }
}
