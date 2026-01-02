package io;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import model.Expense;

public interface ReportFormatter {

    String formatHeader();

    String formatFooter();

    String formatMonthlySummary(Map<YearMonth, Double> monthlySummaries);

    String formatCategoryBreakdown(Map<String, Double> categoryTotals, double maxAmount);

    String formatRecentEntries(List<Expense> recentExpenses);

    String formatGrandTotal(double total);
}
