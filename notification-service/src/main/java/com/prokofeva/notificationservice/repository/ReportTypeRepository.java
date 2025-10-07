package com.prokofeva.notificationservice.repository;

import com.prokofeva.notificationservice.entity.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportTypeRepository extends JpaRepository<ReportType, UUID> {

    ReportType findByNameAndActive(String name, boolean isActive);
}
