package com.prokofeva.notificationservice.service.impl.reportGenerator;

import com.prokofeva.notificationservice.enums.ReportTypeCode;
import com.prokofeva.notificationservice.service.ReportGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportGeneratorFactory {
    private final ReportGeneratorDay reportGeneratorDay;
    private final ReportGeneratorWeek reportGeneratorWeek;
    private final ReportGeneratorPerson reportGeneratorPerson;
    private final ReportGeneratorEventType reportGeneratorEventType;

    public ReportGenerator getReportGenerator(ReportTypeCode type) {
        return switch (type) {
            case DAY_REPORT -> reportGeneratorDay;
            case WEEK_REPORT -> reportGeneratorWeek;
            case EVENT_TYPE_REPORT -> reportGeneratorEventType;
            case PERSON_REPORT -> reportGeneratorPerson;
            case CUSTOM_REPORT -> null;
        };
    }

}
