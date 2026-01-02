package io;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import model.Expense;

public class HtmlReportFormatter implements ReportFormatter {
    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter monthFormatter;

    public HtmlReportFormatter() {
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.monthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
    }
    
    public String formatHeader() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
            <title>BudgetBuddy Expense Report</title>
            <style>
            body { font-family: Arial, sans-serif; margin: 20px; }
            table { border-collapse: collapse; width: 100%; margin-bottom: 20px; }
            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
            th { background-color: #4CAF50; color: white; }
            .bar { background-color: #4CAF50; height: 20px; display: inline-block; }
            .total { font-weight: bold; font-size: 1.2em; color: #4CAF50; }
            </style>
            </head>
            <body>
            <h1>BudgetBuddy Expense Report</h1>
            """;
    }

    public String formatMonthlySummary(Map<YearMonth, Double> monthlySummaries) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Monthly Summary</h2>\n");
        sb.append("<table>\n<tr><th>Month</th><th>Total Amount</th></tr>\n");
        for (Map.Entry<YearMonth, Double> entry : monthlySummaries.entrySet()) {
            sb.append(String.format("<tr><td>%s</td><td>%.2f</td></tr>\n", 
                formatMonth(entry.getKey()), entry.getValue()));
        }
        sb.append("</table>\n");
        return sb.toString();
    }

    public String formatCategoryBreakdown(Map<String, Double> categoryTotals, double maxAmount) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Category Breakdown (All Time)</h2>\n");
        sb.append("<table>\n<tr><th>Category</th><th>Total Amount</th><th>Visual</th></tr>\n");
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            sb.append(String.format("<tr><td>%s</td><td>%.2f</td><td>%s</td></tr>\n", 
                entry.getKey(), entry.getValue(), createBarHtml(entry.getValue(), maxAmount)));
        }
        sb.append("</table>\n");
        return sb.toString();
    }

    public String formatRecentEntries(List<Expense> recentExpenses) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Recent Entries (Last 10)</h2>\n");
        sb.append("<table>\n<tr><th>Date</th><th>Category</th><th>Amount</th><th>Notes</th></tr>\n");
        int count = 0;
        for (int i = recentExpenses.size() - 1; i >= 0 && count < 10; i--, count++) {
            Expense expense = recentExpenses.get(i);
            sb.append(String.format("<tr><td>%s</td><td>%s</td><td>%.2f</td><td>%s</td></tr>\n", 
                formatDate(expense.getDate()),
                expense.getCategory(),
                expense.getAmount(),
                expense.getNotes()));
        }
        sb.append("</table>\n");
        return sb.toString();
    }

    public String formatGrandTotal(double total) {
        return String.format("<p class=\"total\">Grand Total: %.2f</p>\n", total);
    }

    private String formatDate(LocalDate date) {
        return date.format(dateFormatter);
    }

    private String formatMonth(YearMonth month) {
        return month.format(monthFormatter);
    }

    private String createBarHtml(double value, double maxValue) {
        int barWidth = (int) (value / maxValue * 200);
        return String.format("<div class=\"bar\" style=\"width: %dpx;\"></div>", barWidth);
    }

    public String formatFooter() {
        return "</body>\n</html>\n";
    }
}
