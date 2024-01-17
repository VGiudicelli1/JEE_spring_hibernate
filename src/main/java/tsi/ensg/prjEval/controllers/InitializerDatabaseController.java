package tsi.ensg.prjEval.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tsi.ensg.prjEval.models.Event;
import tsi.ensg.prjEval.models.Participant;
import tsi.ensg.prjEval.services.EventService;
import tsi.ensg.prjEval.services.ParticipantService;

import java.util.Date;

@Controller
public class InitializerDatabaseController {
    @Autowired
    private EventService eventService;

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/initDb")
    public String initDbTest() {
        Event e1 = new Event("Evenement1", "ceci est un evenement de test",
                new Date("01/01/2023"), 0.2, 5, null);
        Event e2 = new Event("Date spéciale", "C'est la prochaine date qui s'écrit avec 8 chiffres différents. Quelle est la précédente ?",
                new Date("17/06/2345"), 1, 1000, null);
        Event e3 = new Event("Date spéciale", "C'est la dernière date qui s'ecrit avec 8 chiffres différents. Quelle est la prochaine ?",
                new Date("25/06/1987"), 1, 1000, null);

        Participant p1 = new Participant("Anne", "Onym", "anne.onym@tsi.ensg", new Date("25/06/1987"), null, "est née à une date spéciale");
        Participant p2 = new Participant("Ain", "Conut", "ain.conut@tsi.ensg", new Date("17/06/2345"), null, "Qui suis-je ? Je ne sais plus");
        Participant p3 = new Participant("Aura", "Dèpakrèt", "aura.depakret@tsi.ensg", new Date("25/06/1987"), null, "Je me sens au plus bas aujourd'hui");
        Participant p4 = new Participant("Alain", "Provist", "alain.provist@tsi.ensg", new Date("01/01/2001"), null, "C'est ma spécialité: j'ai rien prévu :)");

        participantService.save(p1);
        participantService.save(p2);
        participantService.save(p3);
        participantService.save(p4);

        eventService.save(e1);
        eventService.save(e2);
        eventService.save(e3);

        return "initialized.html";
    }
}
