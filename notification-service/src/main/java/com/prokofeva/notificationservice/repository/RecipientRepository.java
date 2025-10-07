package com.prokofeva.notificationservice.repository;

import com.prokofeva.notificationservice.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RecipientRepository extends JpaRepository<Recipient, UUID> {

    @Query("""
            select r.codeConfig from Recipient r
            where r.name in :recipients
                        and r.active = true
            """)
    List<String> getCodeConfigs(UUID reportTypeId, @Param("recipients") List<String> recipients);
}
