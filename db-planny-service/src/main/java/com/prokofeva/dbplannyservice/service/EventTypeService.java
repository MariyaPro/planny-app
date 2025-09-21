package com.prokofeva.dbplannyservice.service;

import com.prokofeva.dbplannyservice.entity.EventType;

public interface EventTypeService {

    EventType findEventTypeByName(String name);
}
