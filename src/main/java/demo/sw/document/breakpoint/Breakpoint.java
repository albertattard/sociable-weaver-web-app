package demo.sw.document.breakpoint;

import demo.sw.document.BaseEntry;
import demo.sw.document.Entry;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static demo.sw.common.Converter.markdownToHtml;
import static java.util.Objects.requireNonNull;

public class Breakpoint extends BaseEntry implements Entry {

    private String comment;

    public Breakpoint() {}

    public Breakpoint(final UUID id, final String comment) {
        super(id);
        this.comment = comment;
    }

    public static Breakpoint of(final UUID id, final String comment) {
        return new Breakpoint(id, comment);
    }

    public static Breakpoint empty(final UUID id) {
        return new Breakpoint(id, null);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public String getCommentAsHtml() {
        return markdownToHtml(comment);
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof final Breakpoint other
                && Objects.equals(id, other.id)
                && Objects.equals(comment, other.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment);
    }

    @Override
    public String toString() {
        return "Breakpoint[id=%s, comment=%s]".formatted(id, comment);
    }
}
