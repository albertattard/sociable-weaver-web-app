package demo.sw.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import demo.sw.document.heading.Heading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class Document {

    private static final Logger LOGGER = LoggerFactory.getLogger(Document.class);

    private final List<Entry> entries = new ArrayList<>();

    @JsonIgnore
    /* Defensive copy */
    private final List<Entry> unmodifiableEntries = Collections.unmodifiableList(entries);

    @JsonIgnore
    private Path path;

    public Document() {}

    public Document(final List<Entry> entries) {
        this.entries.addAll(entries);
    }

    public static Document of(final Entry entry) {
        requireNonNull(entry, "The entry cannot be null");
        return of(List.of(entry));
    }

    public static Document of(final List<Entry> entries) {
        requireNonNull(entries, "The list of entries cannot be null");
        return new Document(entries);
    }

    public static Document readDocument(final Path path) {
        requireNonNull(path, "The path to the JSON file cannot be null");

        try {
            final Document document = readDocument(Files.readString(path, StandardCharsets.UTF_8));
            document.path = path;
            return document;
        } catch (final IOException e) {
            throw new UncheckedIOException("Failed to read and parse the JSON file: ".concat(path.toString()), e);
        }
    }

    public static Document readDocument(final String json) {
        requireNonNull(json, "The JSON string cannot be null");

        try {
            return createJsonMapper()
                    .readValue(json, Document.class);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException("Failed to parse document", e);
        }
    }

    public void setEntries(final List<Entry> entries) {
        this.entries.clear();
        this.entries.addAll(entries);
    }

    public List<Entry> getEntries() {
        return unmodifiableEntries;
    }

    @Deprecated
    public Document set(final int index, final Entry entry) {
        requireNonNull(entry);
        entries.set(index, entry);
        return this;
    }

    public Document set(final UUID id, final Entry entry) {
        requireNonNull(id);
        requireNonNull(entry);

        int index = indexOf(id);
        if (index == -1) {
            throw new IllegalArgumentException("Entry with id %s was not found".formatted(id));
        }

        entries.set(index, entry);
        return this;
    }

    private int indexOf(UUID id) {
        for (int i = 0; i < entries.size(); i++) {
            if (id.equals(entries.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    @JsonIgnore
    public String getTitle() {
        return entries.stream()
                .filter(e -> e instanceof final Heading h && h.getLevel() == Heading.Level.H1)
                .map(e -> ((Heading) e))
                .findFirst()
                .map(Heading::getTitle)
                .orElse("No Level 1 Header");
    }

    @Deprecated
    public Entry get(int index) {
        return entries.get(index);
    }

    public Entry get(final UUID id) {
        return entries.stream()
                .filter(e -> id.equals(e.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Entry with id %s was not found".formatted(id)));
    }

    @JsonIgnore
    public Path getDirectory() {
        return path.toAbsolutePath().getParent();
    }

    public Path getPath() {
        return path;
    }

    @JsonIgnore
    public String getBase64Path() {
        return new String(Base64.getEncoder().encode(path.toAbsolutePath().toString().getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    public void setPath(final Path path) {
        this.path = path;
    }

    @JsonIgnore
    public int size() {
        return entries.size();
    }

    public void save() {
        if (path == null) {
            throw new RuntimeException("No path is set");
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(path), StandardCharsets.UTF_8))) {
            createJsonMapper()
                    .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                    .setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT)
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(writer, this);

            LOGGER.info("Document saved to {}", path);
        } catch (final IOException e) {
            throw new UncheckedIOException("Failed to save to file", e);
        }
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof final Document other
                && Objects.equals(entries, other.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries);
    }

    @Override
    public String toString() {
        return "Document[entries=%s]".formatted(entries);
    }

    private static JsonMapper createJsonMapper() {
        return JsonMapper.builder()
                .addModule(new Jdk8Module())
                .build();
    }

    public void delete(final UUID id) {
        final int index = indexOf(id);
        if (index == -1) {
            throw new IllegalArgumentException("Entry with id %s was not found".formatted(id));
        }

        entries.remove(index);
    }
}
