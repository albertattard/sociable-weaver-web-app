package demo.sw.model;

import static demo.sw.common.Converter.markdownToHtml;
import static java.util.Objects.requireNonNull;

public record Heading(Level level, String title) implements Entry {

    public Heading {
        requireNonNull(level, "The level cannot be null");
        requireNonNull(title, "The caption cannot be null");
    }

    public static Heading of(final Level level, final String title) {
        return new Heading(level, title);
    }

    public static Heading h1(final String title) {
        return of(Level.H1, title);
    }

    public static Heading h3(final String title) {
        return of(Level.H3, title);
    }

    public String titleAsHtml() {
        /* See: https://github.com/vsch/flexmark-java/issues/166 */
        final String html = markdownToHtml(title);
        return html.trim().substring(3, html.length() - 5);
    }

    public enum Level {
        H1,
        H2,
        H3,
        H4,
        H5,
    }
}
