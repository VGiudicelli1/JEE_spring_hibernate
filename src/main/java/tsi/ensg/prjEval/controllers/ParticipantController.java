package tsi.ensg.prjEval.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tsi.ensg.prjEval.models.Participant;

@Controller
public class ParticipantController {

    @GetMapping("/participants/{id_participant}")
    public String getEventWithId(@PathVariable("id_participant") long id_participant, Model model) {
        Participant participant = new Participant();
        model.addAttribute("participant", participant);
        return "participant_infos.html";
    }
}
