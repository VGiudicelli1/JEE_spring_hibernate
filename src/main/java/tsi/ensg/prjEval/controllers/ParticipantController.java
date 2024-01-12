package tsi.ensg.prjEval.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tsi.ensg.prjEval.models.Participant;
import tsi.ensg.prjEval.services.ParticipantService;

import java.util.Optional;

@Controller
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/participants/{id_participant}")
    public String getEventWithId(@PathVariable("id_participant") long id_participant, Model model) {
        Optional<Participant> participant = participantService.findById(id_participant);
        if (participant.isEmpty()) {
            return "participant_info_error.html";
        }
        model.addAttribute("participant", participant.get());
        return "participant_info.html";
    }
}
