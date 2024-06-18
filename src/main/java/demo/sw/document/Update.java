package demo.sw.document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Base64;
import java.util.UUID;

import static demo.sw.document.Document.readDocument;

public final class Update {

    private static final Logger LOGGER = LoggerFactory.getLogger(Update.class);

    public static String updateEntry(
            final String base64Path,
            final UUID id,
            final Entry entry,
            final BindingResult bindingResult,
            final Model model) {

        if (bindingResult.hasErrors()) {
            LOGGER.warn("Update has errors!!");
            return "fragments/%s :: editor".formatted(entry.fragmentName());
        }

        final String decodedPath = new String(Base64.getDecoder().decode(base64Path), StandardCharsets.UTF_8);
        final Document document = readDocument(Path.of(decodedPath));
        document.set(id, entry);
        document.save();

        model.addAttribute("entry", entry);
        return "fragments/%s :: reader".formatted(entry.fragmentName());
    }

    private Update() {}
}
