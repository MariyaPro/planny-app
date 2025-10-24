package com.prokofeva.reportgenerator.service;

import com.prokofeva.reportgenerator.dto.EventDto;
import com.prokofeva.reportgenerator.dto.ReportRequest;

import java.util.List;

public interface GeneratorFormat {

    String generate(ReportRequest request, List<EventDto> data);

}
