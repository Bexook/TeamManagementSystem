package com.example.TeamManagementSystem.domain.enumTypes;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum CalendarDayType {

    HOLIDAY(0),
    WEEKEND(1),
    WORKING_DAY(3),
    PAID_DAY_OFF(4),
    NOT_PAID_DAY_OFF(6),
    SICK_LEAVE(7),
    ILLNESS(8);


    private Integer code;

    CalendarDayType(Integer code) {
        this.code = code;
    }

    public static CalendarDayType getByCode(final Integer code) {
        return Arrays.stream(values())
                .filter(t -> t.code.equals(code))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public static Integer getCode(final CalendarDayType calendarDayType) {
        return calendarDayType.code;
    }
}
