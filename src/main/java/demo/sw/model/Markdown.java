package demo.sw.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static demo.sw.common.Converter.markdownToHtml;
import static java.util.Objects.requireNonNull;

public record Markdown(List<String> contents, Optional<Set<String>> tags) implements Entry {

    public Markdown {
        contents = List.copyOf(contents);
        tags = tags.map(Set::copyOf);
    }

    public static Markdown of(final List<String> contents) {
        requireNonNull(contents, "The list of contents cannot be null");
        return new Markdown(contents, Optional.empty());
    }

    public static Markdown of(final List<String> contents, Set<String> tags) {
        requireNonNull(contents, "The list of contents cannot be null");
        requireNonNull(contents, "The set of tags cannot be null");
        return new Markdown(contents, Optional.of(tags));
    }

    public String contentsAsHtml() {
        return markdownToHtml(String.join("\n", contents()));
    }
}
