package com.prokofeva.notificationservice.mapper.impl;

import com.prokofeva.notificationservice.dto.ReportTypeDto;
import com.prokofeva.notificationservice.entity.ReportType;
import com.prokofeva.notificationservice.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ReportTypeMapper implements Mapper<ReportType, ReportTypeDto> {
    @Override
    public ReportType toEntity(ReportTypeDto reportTypeDto) {
        return ReportType.builder()
                .name(reportTypeDto.name())
                .description(reportTypeDto.description())
                .active(reportTypeDto.active())
                .build();
    }

    @Override
    public ReportTypeDto toDto(ReportType reportType) {
        return ReportTypeDto.builder()
                .id(reportType.getId())
                .name(reportType.getName())
                .description(reportType.getDescription())
                .active(reportType.isActive())
                .updated(reportType.getUpdated())
                .build();
    }
}
