/*
 * Copyright 2016 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.gs.dmn.signavio.feel.lib.type.time.mixed;

import com.gs.dmn.feel.lib.type.time.mixed.MixedDateTimeLib;
import com.gs.dmn.signavio.feel.lib.SignavioUtil;
import com.gs.dmn.signavio.feel.lib.type.time.SignavioBaseDateTimeLib;
import com.gs.dmn.signavio.feel.lib.type.time.SignavioDateTimeLib;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class MixedSignavioDateTimeLib extends SignavioBaseDateTimeLib implements SignavioDateTimeLib<Number, LocalDate, OffsetTime, ZonedDateTime> {
    private static final MixedDateTimeLib MIXED_DATE_TIME_LIB = new MixedDateTimeLib();

    @Override
    public LocalDate date(String literal) {
        return MIXED_DATE_TIME_LIB.date(literal);
    }

    @Override
    public OffsetTime time(String literal) {
        return MIXED_DATE_TIME_LIB.time(literal);
    }

    @Override
    public ZonedDateTime dateAndTime(String from) {
        return MIXED_DATE_TIME_LIB.dateAndTime(from);
    }

    @Override
    public Integer year(LocalDate date) {
        return MIXED_DATE_TIME_LIB.year(date);
    }

    @Override
    public Integer yearDateTime(ZonedDateTime dateTime) {
        return MIXED_DATE_TIME_LIB.yearDateTime(dateTime);
    }

    @Override
    public Integer month(LocalDate date) {
        return MIXED_DATE_TIME_LIB.month(date);
    }

    @Override
    public Integer monthDateTime(ZonedDateTime dateTime) {
        return MIXED_DATE_TIME_LIB.monthDateTime(dateTime);
    }

    @Override
    public Integer day(LocalDate date) {
        return MIXED_DATE_TIME_LIB.day(date);
    }

    @Override
    public Integer dayDateTime(ZonedDateTime dateTime) {
        return MIXED_DATE_TIME_LIB.dayDateTime(dateTime);
    }

    @Override
    public Integer hour(OffsetTime time) {
        return MIXED_DATE_TIME_LIB.hour(time);
    }

    @Override
    public Integer hourDateTime(ZonedDateTime dateTime) {
        return MIXED_DATE_TIME_LIB.hourDateTime(dateTime);
    }

    @Override
    public Integer minute(OffsetTime time) {
        return MIXED_DATE_TIME_LIB.minute(time);
    }

    @Override
    public Integer minuteDateTime(ZonedDateTime dateTime) {
        return MIXED_DATE_TIME_LIB.minuteDateTime(dateTime);
    }

    @Override
    public LocalDate toDate(Object from) {
        return MIXED_DATE_TIME_LIB.toDate(from);
    }

    @Override
    public OffsetTime toTime(Object from) {
        return MIXED_DATE_TIME_LIB.toTime(from);
    }

    @Override
    public ZonedDateTime toDateTime(Object from) {
        return MIXED_DATE_TIME_LIB.toDateTime(from);
    }

    //
    // Common methods
    //

    //
    // Specific methods
    //
    @Override
    public LocalDate yearAdd(LocalDate date, Number yearsToAdd) {
        if (date == null || yearsToAdd == null) {
            return null;
        }

        return date.plusYears(yearsToAdd.longValue());
    }
    @Override
    public ZonedDateTime yearAddDateTime(ZonedDateTime dateTime, Number yearsToAdd) {
        if (dateTime == null || yearsToAdd == null) {
            return null;
        }

        return dateTime.plusYears(yearsToAdd.longValue());
    }

    @Override
    public Long yearDiff(Object date1, Object date2) {
        if (!SignavioUtil.areNullSafe(date1, date2)) {
            return null;
        }

        return ChronoUnit.YEARS.between(toDate(date1), toDate(date2));
    }

    @Override
    public LocalDate monthAdd(LocalDate date, Number monthsToAdd) {
        if (!SignavioUtil.areNullSafe(date, monthsToAdd)) {
            return null;
        }

        return date.plusMonths(monthsToAdd.longValue());
    }
    @Override
    public ZonedDateTime monthAddDateTime(ZonedDateTime dateTime, Number monthsToAdd) {
        if (!SignavioUtil.areNullSafe(dateTime, monthsToAdd)) {
            return null;
        }

        return dateTime.plusMonths(monthsToAdd.longValue());
    }

    @Override
    public Long monthDiff(Object date1, Object date2) {
        if (!SignavioUtil.areNullSafe(date1, date2)) {
            return null;
        }

        return ChronoUnit.MONTHS.between(toDate(date1), toDate(date2));
    }

    @Override
    public LocalDate dayAdd(LocalDate date, Number daysToAdd) {
        if (!SignavioUtil.areNullSafe(date, daysToAdd)) {
            return null;
        }

        return date.plusDays(daysToAdd.longValue());
    }
    @Override
    public ZonedDateTime dayAddDateTime(ZonedDateTime dateTime, Number daysToAdd) {
        if (!SignavioUtil.areNullSafe(dateTime, daysToAdd)) {
            return null;
        }

        return dateTime.plusDays(daysToAdd.longValue());
    }

    @Override
    public Long dayDiff(Object date1, Object date2) {
        if (!SignavioUtil.areNullSafe(date1, date2)) {
            return null;
        }

        return ChronoUnit.DAYS.between(toDate(date1), toDate(date2));
    }

    @Override
    public Long hourDiff(Object time1, Object time2) {
        if (!SignavioUtil.areNullSafe(time1, time2)) {
            return null;
        }

        return Duration.between((Temporal) time1, (Temporal) time2).toHours();
    }

    @Override
    public Long minutesDiff(Object time1, Object time2) {
        if (!SignavioUtil.areNullSafe(time1, time2)) {
            return null;
        }

        return Duration.between((Temporal) time1, (Temporal) time2).toMinutes();
    }

    @Override
    public Integer weekday(LocalDate date) {
        if (!SignavioUtil.areNullSafe(date)) {
            return null;
        }

        return date.getDayOfWeek().getValue();
    }
    @Override
    public Integer weekdayDateTime(ZonedDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        return dateTime.getDayOfWeek().getValue();
    }

    @Override
    public LocalDate today() {
        return LocalDate.now();
    }

    @Override
    public ZonedDateTime now() {
        return ZonedDateTime.now();
    }
}
