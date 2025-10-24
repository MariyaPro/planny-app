package com.prokofeva.reportgenerator.service;

import com.prokofeva.reportgenerator.enums.MessageFormat;

public interface ReportService {

    String save(MessageFormat format, String report);
}
