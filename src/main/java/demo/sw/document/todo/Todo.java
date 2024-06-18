package demo.sw.document.todo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.sw.document.BaseEntry;
import demo.sw.document.Entry;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static demo.sw.common.Converter.markdownToHtml;
import static java.util.Objects.requireNonNull;

public class Todo extends BaseEntry implements Entry {

    private List<String> comments;

    public Todo() {}

    public Todo(UUID id, List<String> comments) {
        super(id);
        this.comments = comments;
    }

    public static Todo empty(UUID id) {
        return new Todo(id, null);
    }

    public static Todo of(UUID id, final List<String> contents) {
        requireNonNull(contents, "The list of comments cannot be null");
        return new Todo(id, contents);
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    @JsonIgnore
    public String getCommentsAsHtml() {
        return comments == null
                ? ""
                : markdownToHtml(String.join("\n", comments));
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof final Todo other
                && Objects.equals(id, other.id)
                && Objects.equals(comments, other.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comments);
    }

    @Override
    public String toString() {
        return "Todo[id=%s, comments=%s]".formatted(id, comments);
    }
}
