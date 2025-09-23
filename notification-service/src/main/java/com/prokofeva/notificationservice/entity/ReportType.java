package com.prokofeva.notificationservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "notifications", name = "report_types")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ReportType {

    @Id
    @UuidGenerator
    private UUID id;
    @NotEmpty
    private String name;
    private String description;
    private boolean active;
    @UpdateTimestamp
    private LocalDateTime updated;

}
