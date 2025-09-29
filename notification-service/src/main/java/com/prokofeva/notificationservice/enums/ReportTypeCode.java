package com.prokofeva.notificationservice.enums;

import lombok.Getter;

@Getter
public enum ReportTypeCode {
    CUSTOM_REPORT("custom"),
    WEEK_REPORT("week"),
    DAY_REPORT("day"),
    EVENT_TYPE_REPORT("type"),
    PERSON_REPORT("person");

    public final String name;

    ReportTypeCode(String name) {
        this.name = name;
    }

    public static ReportTypeCode getReportTypeByName(String name) {
        for (ReportTypeCode reportTypeCode : ReportTypeCode.values()) {
            if (reportTypeCode.name.equals(name)) {
                return reportTypeCode;
            }
        }
        return null;
    }
}
