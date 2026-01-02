package io;

/**
 * Writes HTML expense reports with basic inline styling.
 */
public class HtmlReportWriter extends ReportWriter {

    @Override
    protected ReportFormatter createFormatter() {
        return new HtmlReportFormatter();
    }
}
