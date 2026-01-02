package io;

/**
 * Writes plain-text expense reports with ASCII formatting.
 */
public class TxtReportWriter extends ReportWriter {
    
    @Override
    protected ReportFormatter createFormatter() {
        return new TxtReportFormatter();
    }
}
