package demo.sw.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public record Document(List<Entry> entries) {

    public Document {
        entries = List.copyOf(entries);
    }

    public static Document of(final Entry entry) {
        requireNonNull(entry, "The entry cannot be null");
        return of(List.of(entry));
    }

    public static Document of(final List<Entry> entries) {
        requireNonNull(entries, "The list of entries cannot be null");
        return new Document(entries);
    }

    public static Document parse(final Path path) {
        requireNonNull(path, "The path to the JSON file cannot be null");

        try {
            return parse(Files.readString(path, StandardCharsets.UTF_8));
        } catch (final IOException e) {
            throw new UncheckedIOException("Failed to read and parse the JSON file: ".concat(path.toString()), e);
        }
    }

    public static Document parse(final String json) {
        requireNonNull(json, "The JSON string cannot be null");

        try {
            return JsonMapper.builder()
                    .addModule(new Jdk8Module())
                    .build()
                    .readValue(json, Document.class);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException("Failed to parse document", e);
        }
    }

    public Optional<Heading> title() {
        return entries.stream()
                .filter(e -> e instanceof final Heading h && h.level() == Heading.Level.H1)
                .map(e -> ((Heading) e))
                .findFirst();
    }

    public Entry get(int index) {
        return entries.get(index);
    }
}
