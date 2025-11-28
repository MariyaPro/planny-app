package com.prokofeva.dbplannyservice.specifications;

import com.prokofeva.dbplannyservice.entity.Event;
import com.prokofeva.dbplannyservice.entity.EventType;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public class EventSpecifications {

    public static Specification<Event> dateAfterOrEqual(LocalDate startDate) {
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("dateEvent"), startDate);
    }

    public static Specification<Event> dateBeforeOrEqual(LocalDate endDate) {
        return (root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("dateEvent"), endDate);
    }

    public static Specification<Event> isActive() {
        return (root, query, cb) -> cb.isTrue(root.get("active"));
    }

    public static Specification<Event> eventType(String eventTypeName) {
        return (root, query, cb) -> {
            Join<Event, EventType> typeJoin = root.join("eventType");
            Predicate typeEquals = cb.equal(typeJoin.get("name"), eventTypeName);
            Predicate typeActive = cb.isTrue(typeJoin.get("active"));
            return cb.and(typeEquals, typeActive);
        };
    }

    public static Specification<Event> ownersInList(List<String> owners) {
        return (root, query, cb) -> cb.isTrue(root.get("ownerId").in(owners));
    }
}
