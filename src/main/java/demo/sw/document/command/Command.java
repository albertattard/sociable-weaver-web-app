package demo.sw.document.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import demo.sw.document.BaseEntry;
import demo.sw.document.Entry;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Command extends BaseEntry implements Entry {

    private List<String> commands;
    private @JsonProperty("should_fail") boolean shouldFail;
    private @JsonProperty("on_failure_commands") List<String> onFailureCommands;
    private @JsonProperty("working_dir") String workingDir;
    private CommandOutput output;
    private Set<String> tags;

    public List<String> getCommands() {
        return commands;
    }

    @JsonIgnore
    public String getCommandsAsText() {
        return String.join("\n", commands);
    }

    public void setCommandsAsText(final String commands) {
        setCommands(Arrays.asList(commands.split("\n")));
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public boolean isShouldFail() {
        return shouldFail;
    }

    public void setShouldFail(boolean shouldFail) {
        this.shouldFail = shouldFail;
    }

    public List<String> getOnFailureCommands() {
        return onFailureCommands;
    }

    public void setOnFailureCommands(List<String> onFailureCommands) {
        this.onFailureCommands = onFailureCommands;
    }

    public String getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(String workingDir) {
        this.workingDir = workingDir;
    }

    public CommandOutput getOutput() {
        return output;
    }

    public void setOutput(CommandOutput output) {
        this.output = output;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof final Command other
                && shouldFail == other.shouldFail
                && Objects.equals(id, other.id)
                && Objects.equals(commands, other.commands)
                && Objects.equals(onFailureCommands, other.onFailureCommands)
                && Objects.equals(workingDir, other.workingDir)
                && Objects.equals(output, other.output)
                && Objects.equals(tags, other.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commands, shouldFail, onFailureCommands, workingDir, output, tags);
    }

    @Override
    public String toString() {
        return "Command[id=%s, commands=%s, shouldFail=%s, onFailureCommands=%s, workingDir=%s, output=%s, tags=%s]"
                .formatted(id, commands, shouldFail, onFailureCommands, workingDir, output, tags);
    }

    public static class CommandOutput {
        private String caption;
        private @JsonProperty("content_type") String contentType;

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        @Override
        public boolean equals(final Object object) {
            return object instanceof final CommandOutput other
                    && Objects.equals(caption, other.caption)
                    && Objects.equals(contentType, other.contentType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(caption, contentType);
        }

        @Override
        public String toString() {
            return "CommandOutput[caption=%s, contentType=%s]"
                    .formatted(caption, contentType);
        }
    }
}
