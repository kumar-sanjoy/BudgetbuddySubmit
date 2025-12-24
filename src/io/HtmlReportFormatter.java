package io;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class HtmlReportFormatter implements ReportFormatter {
    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter monthFormatter;

    public HtmlReportFormatter() {
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
        int barWidth = (int) Math.round((value * 200) / maxValue);
        return String.format("<div class=\"bar\" style=\"width: %dpx;\"></div>", barWidth);
    }
}
