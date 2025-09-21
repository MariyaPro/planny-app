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
            where e.date_start <= :endPeriod and e.date_end >= :startPeriod
            """, nativeQuery = true)
    List<Event> getEventsPeriod(@Param("startPeriod") LocalDate startPeriod,
                                @Param("endPeriod") LocalDate endPeriod);
}