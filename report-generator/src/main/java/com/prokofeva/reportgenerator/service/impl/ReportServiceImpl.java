package com.prokofeva.reportgenerator.service.impl;

import com.prokofeva.reportgenerator.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
//    private final ReportRepository reportRepository;

    @Override
    public UUID save(String report) {
        return UUID.randomUUID();
//        return reportRepository.save(report).getId();
    }

}
