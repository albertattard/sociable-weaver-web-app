package demo.sw.model;

import demo.sw.common.Converter;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public record Todo(Optional<List<String>> comments) implements Entry {

    public Todo {
        requireNonNull(comments);
    }

    public static Todo empty() {
        return new Todo(Optional.empty());
    }

    public static Todo of(final List<String> contents) {
        requireNonNull(contents, "The list of comments cannot be null");
        return new Todo(Optional.of(contents));
    }

    public String commentsAsHtml() {
        return comments()
                .map(comments -> String.join("\n", comments))
                .map(Converter::markdownToHtml)
                .orElse("");
    }
}
