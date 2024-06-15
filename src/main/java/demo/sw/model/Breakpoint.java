package demo.sw.model;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public record Breakpoint(Optional<String> comment) implements Entry {

    public Breakpoint {
        requireNonNull(comment);
    }

    public static Breakpoint of(final String comment) {
        return new Breakpoint(Optional.ofNullable(comment));
    }

    public static Breakpoint empty() {
        return new Breakpoint(Optional.empty());
    }

    public String commentAsHtml() {
        return comment().orElse("");
    }
}
