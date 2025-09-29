package com.prokofeva.notificationservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "notifications", name = "recipients")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Recipient {

    @Id
    @UuidGenerator
    private UUID id;
    @NotEmpty
    @UniqueElements
    private String name;
    @Email
    private String email;
    private String phone;
    private boolean active;
    @UpdateTimestamp
    private LocalDateTime updated;

}
