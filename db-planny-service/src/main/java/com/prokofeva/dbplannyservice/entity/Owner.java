package com.prokofeva.dbplannyservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "db_events", name = "owners")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Owner {

    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    private Long userIdTg;
    private String description;

    private  boolean active;
    @UpdateTimestamp
    private LocalDateTime updated;

}
