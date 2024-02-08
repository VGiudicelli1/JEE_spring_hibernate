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

    @GetMapping("/participants")
    public String list(Model model) {
        model.addAttribute("participants", participantService.findAll());
        return "participant/list";
    }

    @GetMapping("/participants/{id_participant}")
    public String info(@PathVariable("id_participant") long id_participant, Model model) {
        Participant participant = participantService.findById(id_participant).orElse(null);
        System.out.println(participant);
        model.addAttribute("participant", participant);
        return "participant/info";
    }

    @GetMapping("/participants/new")
    public String add(Model model) {
        model.addAttribute("participant", new Participant());
        return "participant/save";
    }

    @PostMapping("/participants/new")
    public String add(@Validated Participant participant, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "participant/save";
        }
        participantService.save(participant);
        return "redirect:/participants/" + participant.getId();
    }

    @GetMapping("/participants/edit/{id_participant}")
    public String edit(@PathVariable long id_participant, Model model) {
        Optional<Participant> participant = participantService.findById(id_participant);
        if (participant.isEmpty()) {
            return "redirect:/participants/" + id_participant;
        } else {
            model.addAttribute("participant", participant.get());
            return "participant/save";
        }
    }


    @PostMapping("/participants/edit/{id_participant}")
    public String edit(@PathVariable long id_participant, @Validated Participant participant, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "participant/save/";
        }
        Participant participantOriginal = participantService.findById(id_participant).orElse(null);
        if (participantOriginal == null) {
            // une erreur s'est produite
            return "redirect:/participants";
        }
        participant.setId(id_participant);

        participant.setEvents(participantOriginal.getEvents()); // restore participant

        participantService.save(participant);
        return "redirect:/participants/"+id_participant;
    }


}
