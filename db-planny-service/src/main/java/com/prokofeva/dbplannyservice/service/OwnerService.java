package com.prokofeva.dbplannyservice.service;

import com.prokofeva.dbplannyservice.entity.Owner;

public interface OwnerService {
    Owner findOwnerByName(String name);
}
