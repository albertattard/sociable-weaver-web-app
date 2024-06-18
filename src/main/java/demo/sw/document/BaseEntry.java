package demo.sw.document;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class BaseEntry implements Entry {

    @NotNull
    protected UUID id;

    public BaseEntry() {
        this(UUID.randomUUID());
    }

    public BaseEntry(final UUID id) {
        this.id = requireNonNull(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = requireNonNull(id);
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof final BaseEntry other
                && Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BaseEntry[id=%s]".formatted(id);
    }
}
