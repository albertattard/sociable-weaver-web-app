package demo.sw.document;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Base64;
import java.util.UUID;

import static demo.sw.document.Document.readDocument;

@Controller
@RequestMapping("/")
public class DocumentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

    @GetMapping
    public String document(final Model model) {
        final Document document = new Document();

        model.addAttribute("document", document);
        model.addAttribute("path", "");
        return "index";
    }

    @GetMapping("document/{path}")
    public String document(final Model model, @PathVariable(name = "path") final String base64Path) {
        final String decodedPath = new String(Base64.getDecoder().decode(base64Path), StandardCharsets.UTF_8);
        final Document document = readDocument(Path.of(decodedPath));
        LOGGER.info("Loaded document with {} entries", document.size());

        model.addAttribute("document", document);
        return "index";
    }

    @HxRequest
    @GetMapping("document/{path}/entry/{id}/reader")
    public String reader(final Model model, @PathVariable(name = "path") final String base64Path, @PathVariable("id") final UUID id) {
        return fragment(model, base64Path, id, "reader");
    }

    @HxRequest
    @GetMapping("document/{path}/entry/{id}/editor")
    public String editor(final Model model, @PathVariable(name = "path") final String base64Path, @PathVariable("id") final UUID id) {
        return fragment(model, base64Path, id, "editor");
    }

    @HxRequest
    @ResponseBody
    @DeleteMapping("document/{path}/entry/{id}")
    public String delete(@PathVariable(name = "path") final String base64Path, @PathVariable("id") final UUID id) {
        final String decodedPath = new String(Base64.getDecoder().decode(base64Path), StandardCharsets.UTF_8);
        final Document document = readDocument(Path.of(decodedPath));
        document.delete(id);
        document.save();
        return "";
    }

    private static String fragment(final Model model, final String base64Path, final UUID id, final String fragment) {
        final String decodedPath = new String(Base64.getDecoder().decode(base64Path), StandardCharsets.UTF_8);
        final Document document = readDocument(Path.of(decodedPath));
        final Entry entry = document.get(id);

        model.addAttribute("id", id);
        model.addAttribute("entry", entry);
        return "fragments/%s :: %s".formatted(entry.fragmentName(), fragment);
    }
}
