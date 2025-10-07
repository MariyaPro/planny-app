package com.prokofeva.dbplannyservice.repository;

import com.prokofeva.dbplannyservice.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findEventsByEventType_Name(String eventTypeName);

    @Query(value = """
            select * from events e
            where e.date_event <= :endPeriod and e.date_event >= :startPeriod
            """, nativeQuery = true)
    List<Event> getEventsPeriod(@Param("startPeriod") LocalDate startPeriod,
                                @Param("endPeriod") LocalDate endPeriod);

    @Query("""
            select e from Event e
            where e.dateEvent >= :startPeriod
                and e.dateEvent <= :endPeriod
                and e.active=true
                and e.eventType.active=true
                and (:owners is null or e.owner.name in :owners)
                and (:eventType is null or e.eventType.name = :eventType)
            """)
    List<Event> getEventsByDateAndTypeAndOwners(@Param("startPeriod") LocalDate startPeriod,
                                                @Param("endPeriod") LocalDate endPeriod,
                                                @Param("owners") List<String> owners,
                                                @Param("eventType") String eventType);
}