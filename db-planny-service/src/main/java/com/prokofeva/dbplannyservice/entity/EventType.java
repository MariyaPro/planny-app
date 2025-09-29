package com.prokofeva.dbplannyservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "db_events", name = "event_types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventType {

    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    private String description;

    private  boolean active;
    private LocalDateTime updated;

}
