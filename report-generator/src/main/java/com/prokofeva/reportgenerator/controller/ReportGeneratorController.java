package com.prokofeva.reportgenerator.controller;

import com.prokofeva.reportgenerator.dto.ReportRequest;
import com.prokofeva.reportgenerator.enums.MessageFormat;
import com.prokofeva.reportgenerator.fasade.ReportGeneratorFacade;
import com.prokofeva.reportgenerator.util.LogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report-generator")
@RequiredArgsConstructor
public class ReportGeneratorController {
    private final ReportGeneratorFacade reportGeneratorFacade;

    @PostMapping("/generate-xml")
    @LogRequest
    public ResponseEntity<Object> generateReportXml(ReportRequest request) {
        return ResponseEntity.ok().body(reportGeneratorFacade.startProcess(MessageFormat.XML, request));
    }

    @PostMapping("/generate-json")
    @LogRequest
    public ResponseEntity<Object> generateReportJson(ReportRequest request) {
        return ResponseEntity.ok().body(reportGeneratorFacade.startProcess(MessageFormat.JSON, request));
    }

    @PostMapping("/generate-txt")
    @LogRequest
    public ResponseEntity<Object> generateReportTxt(ReportRequest request) {
        return ResponseEntity.ok().body(reportGeneratorFacade.startProcess(MessageFormat.TXT, request));
    }

}
