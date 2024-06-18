package demo.sw.document.heading;

import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.sw.document.BaseEntry;
import demo.sw.document.Entry;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.UUID;

import static demo.sw.common.Converter.markdownToHtml;

public class Heading extends BaseEntry implements Entry {

    @NotNull
    private Level level;

    @Size(min = 4, max = 128)
    private String title;

    public Heading() {}

    public Heading(final UUID id, final Level level, final String title) {
        super(id);
        this.level = level;
        this.title = title;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(final Level level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    @JsonIgnore
    public String getTitleAsHtml() {
        /* See: https://github.com/vsch/flexmark-java/issues/166 */
        final String html = markdownToHtml(title);
        return html.trim().substring(3, html.length() - 5);
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof final Heading other
                && Objects.equals(id, other.id)
                && Objects.equals(level, other.level)
                && Objects.equals(title, other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, level, title);
    }

    @Override
    public String toString() {
        return "UpdateHeadingTo[id=%s, level=%s, title=%s]".formatted(id, level, title);
    }

    public enum Level {
        H1,
        H2,
        H3,
        H4,
        H5,
        ;

        public Heading heading(final UUID id, final String title) {
            return new Heading(id, this, title);
        }
    }
}
