package com.prokofeva.reportgenerator.service.impl;

import com.prokofeva.reportgenerator.entity.ReportDoc;
import com.prokofeva.reportgenerator.enums.MessageFormat;
import com.prokofeva.reportgenerator.repository.ReportRepository;
import com.prokofeva.reportgenerator.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    @Override
    public String save(MessageFormat format, String report) {
        var rep = ReportDoc.builder()
                .content(report)
                .format(format)
                .build();

        return reportRepository.save(rep).get_id();
    }
}
