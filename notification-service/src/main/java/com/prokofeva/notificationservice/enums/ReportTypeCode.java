package com.prokofeva.notificationservice.enums;

import lombok.Getter;

@Getter
public enum ReportTypeCode {
    FREE_REPORT("free"),
    WEEK_REPORT("week"),
    TODAY_REPORT("today"),
    TOMORROW_REPORT("tomorrow"),
    MONTH_REPORT("month");

    private final String name;

    ReportTypeCode(String name) {
        this.name = name;
    }

    public static ReportTypeCode getReportTypeByName(String name) {
        for (ReportTypeCode reportTypeCode : ReportTypeCode.values()) {
            if (reportTypeCode.name.equals(name)) {
                return reportTypeCode;
            }
        }
        throw new IllegalArgumentException("Invalid report type code: " + name);
    }
}
