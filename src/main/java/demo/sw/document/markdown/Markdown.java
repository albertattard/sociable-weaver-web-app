package demo.sw.document.markdown;

import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.sw.document.BaseEntry;
import demo.sw.document.Entry;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static demo.sw.common.Converter.markdownToHtml;
import static java.util.Objects.requireNonNull;

public class Markdown extends BaseEntry implements Entry {

    private List<String> contents;
    private Set<String> tags;

    public Markdown() {
        this.contents = List.of();
    }

    public Markdown(UUID id, List<String> contents, Set<String> tags) {
        super(id);
        this.contents = contents;
        this.tags = tags;
    }

    public static Markdown of(final UUID id, final List<String> contents) {
        requireNonNull(contents, "The list of contents cannot be null");
        return new Markdown(id, contents, null);
    }

    public static Markdown of(final UUID id, final List<String> contents, Set<String> tags) {
        requireNonNull(contents, "The list of contents cannot be null");
        requireNonNull(contents, "The set of tags cannot be null");
        return new Markdown(id, contents, tags);
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    @JsonIgnore
    public String getContentsAsHtml() {
        return markdownToHtml(String.join("\n", contents));
    }

    @JsonIgnore
    public String getContentsAsText() {
        return String.join("\n", contents);
    }

    @JsonIgnore
    public void setContentsAsText(final String contents) {
        setContents(Arrays.asList(contents.split("\n")));
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof final Markdown other
                && Objects.equals(id, other.id)
                && Objects.equals(contents, other.contents)
                && Objects.equals(tags, other.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contents, tags);
    }

    @Override
    public String toString() {
        return "Markdown[id=%s, contents=%s, tags=%s]"
                .formatted(id, contents, tags);
    }
}
