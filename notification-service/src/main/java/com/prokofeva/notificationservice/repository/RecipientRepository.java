package com.prokofeva.notificationservice.repository;

import com.prokofeva.notificationservice.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {
}
