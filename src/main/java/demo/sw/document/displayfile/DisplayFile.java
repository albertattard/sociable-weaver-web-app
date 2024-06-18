package demo.sw.document.displayfile;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.sw.document.BaseEntry;
import demo.sw.document.Entry;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class DisplayFile extends BaseEntry implements Entry {

    private String path;
    private @JsonProperty("from_line") int fromLine;
    private @JsonProperty("number_of_lines") int numberOfLines;

    public static DisplayFile of(final UUID id, final String path) {
        requireNonNull(path, "The path cannot be null");

        return new DisplayFile(id, path, 0, 0);
    }

    public static DisplayFile of(final UUID id, final String path, final int fromLine, final int numberOfLines) {
        requireNonNull(path, "The path cannot be null");

        return new DisplayFile(id, path, fromLine, numberOfLines);
    }

    public DisplayFile() {}

    public DisplayFile(final UUID id, final String path, final int fromLine, final int numberOfLines) {
        super(id);
        this.path = path;
        this.fromLine = fromLine;
        this.numberOfLines = numberOfLines;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getFromLine() {
        return fromLine;
    }

    public void setFromLine(int fromLine) {
        this.fromLine = fromLine;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof final DisplayFile other
                && Objects.equals(id, other.id)
                && Objects.equals(path, other.path)
                && fromLine == other.fromLine
                && numberOfLines == other.numberOfLines;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, fromLine, numberOfLines);
    }

    @Override
    public String toString() {
        return "DisplayFile[id=%s, path=%s, fromLine=%s, numberOfLines=%s]"
                .formatted(id, path, fromLine, numberOfLines);
    }
}
