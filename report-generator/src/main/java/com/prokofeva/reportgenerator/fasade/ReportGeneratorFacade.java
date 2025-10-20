package com.prokofeva.reportgenerator.fasade;

import com.prokofeva.reportgenerator.dto.ReportRequest;
import com.prokofeva.reportgenerator.enums.MessageFormat;
import com.prokofeva.reportgenerator.exceptions.ProcessStopException;
import com.prokofeva.reportgenerator.service.DbEventsService;
import com.prokofeva.reportgenerator.service.ReportService;
import com.prokofeva.reportgenerator.service.impl.generator.GeneratorFormatFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportGeneratorFacade {
    private final DbEventsService dbEventsService;
    private final GeneratorFormatFactory generatorFormatFactory;
    private final ReportService reportService;

    public UUID startProcess(MessageFormat format, ReportRequest request) {
        var dataFromDb = dbEventsService.getDataForReport(request);
        if (dataFromDb == null || dataFromDb.isEmpty()) {
            throw new ProcessStopException("No data found for period.");
        }
        var generator = generatorFormatFactory.getGeneratorByFormat(format);
        var report = generator.generate(request, dataFromDb);
        // todo save
        UUID reportId = reportService.save(report);
        log.info("Success. Report ID: {}", reportId);
        return reportId;
    }
}
