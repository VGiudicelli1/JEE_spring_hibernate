package tsi.ensg.prjEval.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tsi.ensg.prjEval.models.Event;
import tsi.ensg.prjEval.models.Participant;
import tsi.ensg.prjEval.services.ParticipantService;

import java.util.Optional;

@Controller
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/participants/{id_participant}")
    public String getEventWithId(@PathVariable("id_participant") long id_participant, Model model) {
        Participant participant = participantService.findById(id_participant).orElse(null);
        System.out.println(participant);
        model.addAttribute("participant", participant);
        return "participant/info";
    }

    @GetMapping("/participants/new")
    public String newParticipant(Model model) {
        model.addAttribute("participant", new Participant());
        return "participant/add";
    }

    @PostMapping("/participants/new")
    public String addEvent(@Validated Participant participant, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "participant/add";
        }
        participantService.save(participant);
        return "redirect:/participants/" + participant.getId();
    }
}
