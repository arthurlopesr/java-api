package com.client.ws.rasmooplus.domain.entities.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_type")
@Entity()
public class UserTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_type_id")
    private Long userTypeId;

    private String name;

    private String description;
}
