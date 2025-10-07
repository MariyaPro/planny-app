package com.prokofeva.notificationservice.service.impl;

import com.prokofeva.notificationservice.dto.ReportTypeDto;
import com.prokofeva.notificationservice.entity.ReportType;
import com.prokofeva.notificationservice.mapper.Mapper;
import com.prokofeva.notificationservice.repository.ReportTypeRepository;
import com.prokofeva.notificationservice.service.ReportTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportTypeServiceImpl implements ReportTypeService {
    private final ReportTypeRepository reportTypeRepository;
    private final Mapper<ReportType,ReportTypeDto> mapper;

    @Override
    public ReportTypeDto findByName(String name) {
        return mapper.toDto(reportTypeRepository.findByNameAndActive(name,true));
    }
}
