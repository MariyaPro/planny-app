package com.prokofeva.tgbotplanny.service.impl;

import com.prokofeva.tgbotplanny.exceptions.ProcessFailException;
import com.prokofeva.tgbotplanny.repository.ReportRepository;
import com.prokofeva.tgbotplanny.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    @Override
    public String getContent(String id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ProcessFailException("Not found by id=" + id)).getContent();
    }
}
