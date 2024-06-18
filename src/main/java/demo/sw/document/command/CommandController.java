package demo.sw.document.command;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static demo.sw.document.Update.updateEntry;

@Controller
@RequestMapping("/")
public class CommandController {

    @HxRequest
    @PutMapping("document/{path}/entry/{id}/command")
    public String update(@PathVariable(name = "path") final String base64Path,
                         @PathVariable("id") final UUID id,
                         @Valid @ModelAttribute("entry") final Command entry,
                         final BindingResult bindingResult,
                         final Model model) {

        return updateEntry(base64Path, id, entry, bindingResult, model);
    }
}
