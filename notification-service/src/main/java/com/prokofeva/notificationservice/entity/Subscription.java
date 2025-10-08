package com.prokofeva.notificationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "notifications", name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @UuidGenerator
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;
    @ManyToOne
    @JoinColumn(name = "report_type_id")
    private ReportType reportType;
    private String deliveryMethod;
    private boolean active;
    @UpdateTimestamp
    private LocalDateTime updated;
}
