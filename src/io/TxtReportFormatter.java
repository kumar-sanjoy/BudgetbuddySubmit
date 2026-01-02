package io;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import model.Expense;
import util.TextUtils;

public class TxtReportFormatter implements ReportFormatter {
    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter monthFormatter;


    public TxtReportFormatter() {
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.monthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

    }

    public String formatHeader() {
        return """
            =====================================
                    BUDGETBUDDY EXPENSE REPORT
            =====================================

            """;
    }

    public String formatMonthlySummary(Map<YearMonth, Double> monthlySummaries) {
        StringBuilder sb = new StringBuilder("MONTHLY SUMMARY\n");
        sb.append(TextUtils.separator(60)).append("\n");
        for (Map.Entry<YearMonth, Double> entry : monthlySummaries.entrySet()) {
            sb.append(String.format("%-10s : %12.2f\n", formatMonth(entry.getKey()), entry.getValue()));
        }
        sb.append("\n");
        return sb.toString();
    }

    public String formatCategoryBreakdown(Map<String, Double> categoryTotals, double maxAmount) {
        StringBuilder sb = new StringBuilder("CATEGORY BREAKDOWN (All Time)\n");
        sb.append(TextUtils.separator(60)).append("\n");
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            sb.append(String.format("%-15s %12.2f  %s\n",
                    entry.getKey(),
                    entry.getValue(),
                    TextUtils.createBar(entry.getValue(), maxAmount, 30)));
        }
        sb.append("\n");
        return sb.toString();
    }

    public String formatRecentEntries(List<Expense> recentExpenses) {
        StringBuilder sb = new StringBuilder("\nRECENT ENTRIES (Last 10)\n");
        sb.append(TextUtils.separator(60)).append("\n");
        int count = 0;
        for (int i = recentExpenses.size() - 1; i >= 0 && count < 10; i--, count++) {
            Expense expense = recentExpenses.get(i);
            sb.append(String.format("%s  %-12s %10.2f  %s\n",
                    formatDate(expense.getDate()),
                    expense.getCategory(),
                    expense.getAmount(),
                    expense.getNotes()));
        }
        return sb.toString();
    }

    public String formatGrandTotal(double total) {
        StringBuilder sb = new StringBuilder();
        sb.append(TextUtils.separator(60)).append("\n");
        sb.append(String.format("GRAND TOTAL: %.2f\n", total));
        sb.append(TextUtils.separator(60)).append("\n");
        return sb.toString();
    }

    public String formatFooter() {
        return TextUtils.separator(60) + "\n";
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
