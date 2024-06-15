package demo.sw;

import demo.sw.model.Document;
import demo.sw.model.Entry;
import demo.sw.model.Heading;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

@Controller
@RequestMapping("/")
public class EntryController {

    @GetMapping
    public String document(final Model model) {
        final Document document = readDocument();

        final String title = document.title().map(Heading::title).orElse("No Level 1 Header");

        model.addAttribute("pageTitle", title);
        model.addAttribute("entries", document.entries().stream().toList());
        return "index";
    }

    @HxRequest
    @GetMapping("/entry/{id}/reader")
    public String reader(final Model model, @PathVariable("id") final int id) {
        final Document document = readDocument();
        final Entry entry = document.get(id);

        model.addAttribute("id", id);
        model.addAttribute("entry", entry);
        return "fragments/%s :: reader".formatted(entry.fragmentName());
    }

    @HxRequest
    @GetMapping("/entry/{id}/editor")
    public String editor(final Model model, @PathVariable("id") final int id) {
        final Document document = readDocument();
        final Entry entry = document.get(id);

        model.addAttribute("id", id);
        model.addAttribute("entry", entry);
        return "fragments/%s :: editor".formatted(entry.fragmentName());
    }

    @HxRequest
    @PostMapping("/entry/{id}")
    public String update(final Model model, @PathVariable("id") final int id) {
        final Document document = readDocument();
        final Entry entry = document.get(id);

        model.addAttribute("entry", entry);
        return "fragments/%s :: reader".formatted(entry.fragmentName());
    }

    private static Document readDocument() {
        final Document document;
        try {
            final File file = ResourceUtils.getFile("classpath:static/sw-runbook.json");
            document = Document.parse(file.toPath());
        } catch (final IOException e) {
            throw new UncheckedIOException("", e);
        }
        return document;
    }
}
