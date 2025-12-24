package io;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import util.TextUtils;

public class TxtReportFormatter implements ReportFormatter {
    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter monthFormatter;

    public TxtReportFormatter() {
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.monthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
    }
    public String formatDate(LocalDate date) {
        return date.format(dateFormatter);
    }

    public String formatMonth(YearMonth month) {
        return month.format(monthFormatter);
    }

    public String formatAmount(double amount) {
        return String.format("%.2f", amount);
    }

    public String createBar(double value, double maxValue) {
        return TextUtils.createBar(value, maxValue, 30);
    }
}
