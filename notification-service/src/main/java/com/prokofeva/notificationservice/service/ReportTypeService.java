package com.prokofeva.notificationservice.service;

import com.prokofeva.notificationservice.dto.ReportTypeDto;
import jakarta.validation.constraints.NotNull;

public interface ReportTypeService {
    ReportTypeDto findByName(@NotNull String name);
}
