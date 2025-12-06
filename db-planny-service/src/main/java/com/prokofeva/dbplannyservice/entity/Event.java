package com.prokofeva.dbplannyservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(schema = "db_events", name = "events")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Event {

    @Id
    @UuidGenerator
    private UUID id;
    @NotEmpty
    private String title;
//    @ManyToOne
    @JoinColumn(name = "owner_id")
    private String ownerId;
    @ManyToOne
    private EventType eventType;

    @NotNull
    private LocalDate dateEvent;
    private LocalTime startTime;
    private LocalTime endTime;

    private String description;
    private String location;

    private boolean active;
    @UpdateTimestamp
    private LocalDateTime updated;

}
