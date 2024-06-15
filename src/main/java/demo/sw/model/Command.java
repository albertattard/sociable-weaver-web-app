package demo.sw.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public record Command(List<String> commands,
                      @JsonProperty("should_fail") boolean shouldFail,
                      @JsonProperty("on_failure_commands") Optional<List<String>> onFailureCommands,
                      @JsonProperty("working_dir") Optional<Path> working_dir,
                      Optional<CommandOutput> output,
                      Optional<Set<String>> tags) implements Entry {

    public Command {
        commands = List.copyOf(commands);
        onFailureCommands = onFailureCommands.map(List::copyOf);
        tags = tags.map(Set::copyOf);
    }

    public record CommandOutput(boolean show, String caption, @JsonProperty("content_type") String contentType) {}

    public String commandsAsText() {
        return String.join("\n", commands());
    }
}
