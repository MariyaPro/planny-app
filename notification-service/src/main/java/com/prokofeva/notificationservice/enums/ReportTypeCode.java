package com.prokofeva.notificationservice.enums;

public enum ReportTypeCode {
    CUSTOM_REPORT("custom"),
    WEEK_REPORT("week"),
    DAY_REPORT("day"),
    EVENT_TYPE_REPORT("type");

    public final String name;

    ReportTypeCode(String name) {
        this.name = name;
    }

    public ReportTypeCode getReportTypeByName(String name) {
        for (ReportTypeCode reportTypeCode : ReportTypeCode.values()) {
            if (reportTypeCode.name.equals(name)) {
                return reportTypeCode;
            }
        }
        return null;
    }
}
