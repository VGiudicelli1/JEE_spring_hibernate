package tsi.ensg.prjEval.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tsi.ensg.prjEval.models.Participant;

@Controller
public class StaticController {
    @GetMapping("/") public String getIndex(Model model) {
        return "index";
    }
}
