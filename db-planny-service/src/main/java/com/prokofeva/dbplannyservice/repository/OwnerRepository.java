package com.prokofeva.dbplannyservice.repository;

import com.prokofeva.dbplannyservice.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OwnerRepository extends JpaRepository<Owner, UUID> {

    Owner findOwnerByName(String name);
}
