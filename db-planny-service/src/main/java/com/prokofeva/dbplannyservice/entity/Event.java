package com.prokofeva.dbplannyservice.entity;

import com.prokofeva.dbplannyservice.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "events")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event {

    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String description;
//    private String location;
//    private LocalTime startTime;
//    private LocalTime endTime;
//    private Duration duration;

}
