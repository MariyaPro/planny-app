package com.prokofeva.reportgenerator.service.impl.generator;

import com.prokofeva.reportgenerator.enums.MessageFormat;
import com.prokofeva.reportgenerator.service.GeneratorFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeneratorFormatFactory {
    private final GeneratorFormatJson generatorFormatJson;
    private final GeneratorFormatXml generatorFormatXml;
    private final GeneratorFormatTxt generatorFormatTxt;

    public GeneratorFormat getGeneratorByFormat(MessageFormat format) {
        return switch (format) {
            case JSON -> generatorFormatJson;
            case TXT -> generatorFormatTxt;
            case XML -> generatorFormatXml;
        };
    }
}
