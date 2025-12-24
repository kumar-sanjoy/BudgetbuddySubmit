package io;

import java.time.LocalDate;
import java.time.YearMonth;

public interface ReportFormatter {
    public String formatDate(LocalDate date);

    public String formatMonth(YearMonth month);

    public String formatAmount(double amount);

    public String createBar(double value, double maxValue);
}
