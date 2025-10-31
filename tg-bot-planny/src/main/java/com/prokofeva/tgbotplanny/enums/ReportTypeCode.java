package com.prokofeva.tgbotplanny.enums;

import lombok.Getter;

@Getter
public enum ReportTypeCode {

    TODAY_REPORT("на сегодня"),
    TOMORROW_REPORT("на завтра"),
    WEEK_REPORT("на неделю");
//    MONTH_REPORT("на месяц");
    //    FREE_REPORT("произвольный период")

    private final String description;

    ReportTypeCode(String description) {
        this.description = description;
    }

}
