package com.prokofeva.reportgenerator.service.impl.generator;

import com.prokofeva.reportgenerator.dto.EventDto;
import com.prokofeva.reportgenerator.dto.ReportRequest;
import com.prokofeva.reportgenerator.service.GeneratorFormat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneratorFormatTxt implements GeneratorFormat {
    @Override
    public String generate(ReportRequest request, List<EventDto> data) {
        return null;
    }
}
