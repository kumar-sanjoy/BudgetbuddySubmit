package io;

public final class ReportFactory {
    private ReportFactory() {}

    public static ReportWriter create(String type) {
        switch (type.toLowerCase()) {
            case "html": return new HtmlReportWriter();
            case "txt":  return new TxtReportWriter();
            default: throw new IllegalArgumentException("Unknown report type: " + type);
        }
    }
}

// why final?
// Communicates intent: signals this is a static utility/factory, not meant to be extended.
// Prevents accidental subclassing that could change factory behavior or violate invariants.
// Safer: avoids fragile inheritance and unexpected overrides of factory logic.
// Combined with a private constructor, enforces non-instantiability and a clear static-API pattern.
// Slightly helps reasoning/optimizations by the compiler/JIT (minor).