package tsi.ensg.prjEval.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tsi.ensg.prjEval.models.Event;
import tsi.ensg.prjEval.models.Participant;
import tsi.ensg.prjEval.services.EventService;
import tsi.ensg.prjEval.services.ParticipantService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class InitializerDatabaseController {
    @Autowired
    private EventService eventService;

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/initDb")
    public String initDbTest() {
        /*for (Participant p : participantService.findAll()) {
            participantService.delete(p);
        }*/
        for (Event event : eventService.findAll()) {
            eventService.delete(event);
        }

        Event e1 = new Event("Evenement1", "ceci est un evenement de test",
                new Date("01/01/2023"), 0.2, 5, null);
        Event e2 = new Event("Date spéciale", "C'est la prochaine date qui s'écrit avec 8 chiffres différents. Quelle est la précédente ?",
                new Date("17/06/2345"), 1, 4, null);
        Event e3 = new Event("Date spéciale", "C'est la dernière date qui s'ecrit avec 8 chiffres différents. Quelle est la prochaine ?",
                new Date("25/06/1987"), 1, 2, null);

        Participant p1 = new Participant("Anne", "Onym", "anne.onym@tsi.ensg", new Date("25/06/1987"), null, "est née à une date spéciale");
        Participant p2 = new Participant("Ain", "Conut", "ain.conut@tsi.ensg", new Date("17/06/2345"), null, "Qui suis-je ? Je ne sais plus");
        Participant p3 = new Participant("Aura", "Dèpakrèt", "aura.depakret@tsi.ensg", new Date("25/06/1987"), null, "Je me sens au plus bas aujourd'hui");
        Participant p4 = new Participant("Alain", "Provist", "alain.provist@tsi.ensg", new Date("01/01/2001"), null, "C'est ma spécialité: j'ai rien prévu :)");

        participantService.save(p1);
        participantService.save(p2);
        participantService.save(p3);
        participantService.save(p4);

        List<Participant> l1 = new ArrayList<>();
        l1.add(p1);
        l1.add(p2);
        e1.setParticipants(l1);
        eventService.save(e1);

        List<Participant> l2 = new ArrayList<>();
        l2.add(p1);
        l2.add(p2);
        l2.add(p3);
        e2.setParticipants(l2);
        eventService.save(e2);

        List<Participant> l3 = new ArrayList<>();
        l3.add(p1);
        l3.add(p4);
        e3.setParticipants(l3);
        eventService.save(e3);

        return "initialized.html";
    }
}
