package com.prokofeva.dbplannyservice.repository;

import com.prokofeva.dbplannyservice.entity.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventTypeRepository extends JpaRepository<EventType, UUID> {

    EventType findEventTypeByName(String name);
}
