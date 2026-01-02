package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.Expense;
import service.ExpenseRepository;
import service.Summarizer;

public abstract class ReportWriter {

    protected abstract ReportFormatter createFormatter(); 
    
    public void writeReport(String filePath, ExpenseRepository repository) throws IOException {
        List<Expense> allExpenses = repository.findAll();
        Summarizer summarizer = new Summarizer(allExpenses);
        Map<String, Double> categoryTotals = summarizer.categoryTotals(null);

        ReportFormatter formatter = createFormatter();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(formatter.formatHeader());
            writer.write(formatter.formatMonthlySummary(summarizer.monthlyTotals()));
            writer.write(formatter.formatCategoryBreakdown(
                    categoryTotals,
                    summarizer.maxCategoryTotal()));
            writer.write(formatter.formatGrandTotal(summarizer.grandTotal()));
            writer.write(formatter.formatRecentEntries(getRecentEntries(allExpenses)));
            writer.write(formatter.formatFooter());
        }

        System.out.println("Report written to: " + filePath);
    }

    private List<Expense> getRecentEntries(List<Expense> expenses) {
        int count = Math.min(10, expenses.size());
        return expenses.subList(expenses.size() - count, expenses.size());
    }
}
