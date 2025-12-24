package io;

import java.io.IOException;

import service.ExpenseRepository;

public interface ReportWriter {
    
    public void writeReport(String filePath, ExpenseRepository repository) throws IOException;
}
