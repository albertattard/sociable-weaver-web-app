package demo.sw.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.nio.file.Path;
import java.util.OptionalInt;

import static java.util.Objects.requireNonNull;

public record DisplayFile(Path path,
                          @JsonProperty("from_line") OptionalInt fromLine,
                          @JsonProperty("number_of_lines") OptionalInt numberOfLines) implements Entry {

    public DisplayFile {
        requireNonNull(path, "The path cannot be null");
        requireNonNull(fromLine, "The from line number cannot be null");
        requireNonNull(numberOfLines, "The number of lines cannot be null");
    }

    public static DisplayFile of(final Path path) {
        requireNonNull(path, "The path cannot be null");

        return new DisplayFile(path, OptionalInt.empty(), OptionalInt.empty());
    }

    public static DisplayFile of(final Path path, final OptionalInt fromLine, final OptionalInt numberOfLines) {
        requireNonNull(path, "The path cannot be null");
        requireNonNull(fromLine, "The from line number cannot be null");
        requireNonNull(numberOfLines, "The number of lines cannot be null");

        return new DisplayFile(path, fromLine, numberOfLines);
    }
}
