package tsi.ensg.prjEval.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tsi.ensg.prjEval.models.Event;
import tsi.ensg.prjEval.models.Participant;
import tsi.ensg.prjEval.services.EventService;
import tsi.ensg.prjEval.services.ParticipantService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class InitializerDatabaseController {
    @Autowired
    private EventService eventService;

    @Autowired
    private ParticipantService participantService;

    private List<Participant> readNames() {
        String file = "noms.txt";

        List<Participant> participants = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e)  {
            return participants;
        }


        String line;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            List<String> a = List.of(line.split(" "));
            try {
                String firstName = a.get(0);
                String lastName = a.get(1);
                Participant p = new Participant(firstName, lastName, firstName + "." + lastName + "@tsi.ensg", new Date(ThreadLocalRandom.current().nextInt() * 1000L), null, null, null);
                participants.add(p);
            } catch (Exception ignored) {}
        };
        try {
            reader.close();
        } catch (Exception ignored) {}

        return participants;
    }

    public static <T> T getRandom(List<T> list) {
        return list.get((new Random()).nextInt(list.size()));
    }

    @GetMapping("/initDb")
    public String initDbTest() {

        for (Event event : eventService.findAll()) {
            eventService.delete(event);
        }
        for (Participant p : participantService.findAll()) {
            participantService.delete(p);
        }

        Event e1 = new Event("Evenement1", "ceci est un evenement de test",
                new Date("01/01/2023"), 0.2, 5, null);
        Event e2 = new Event("Date spéciale", "C'est la prochaine date qui s'écrit avec 8 chiffres différents. Quelle est la précédente ?",
                new Date("17/06/2345"), 1, 4, null);
        Event e3 = new Event("Date spéciale", "C'est la dernière date qui s'ecrit avec 8 chiffres différents. Quelle est la prochaine ?",
                new Date("25/06/1987"), 1, 2, null);

        List<Participant> participants = this.readNames();

        participants.add(new Participant("Anne", "Onym", "anne.onym@tsi.ensg", new Date("25/06/1987"), null, "est née à une date spéciale"));
        participants.add(new Participant("Ain", "Conut", "ain.conut@tsi.ensg", new Date("17/06/2345"), null, "Qui suis-je ? Je ne sais plus"));
        participants.add(new Participant("Aura", "Dèpakrèt", "aura.depakret@tsi.ensg", new Date("25/06/1987"), null, "Je me sens au plus bas aujourd'hui"));
        participants.add(new Participant("Alain", "Provist", "alain.provist@tsi.ensg", new Date("01/01/2001"), null, "C'est ma spécialité: j'ai rien prévu :)"));

        for (Participant p : participants) {
            participantService.save(p);
        }

        e1.addParticipant(getRandom(participants));
        e1.addParticipant(getRandom(participants));
        eventService.save(e1);

        e2.addParticipant(getRandom(participants));
        e2.addParticipant(getRandom(participants));
        e2.addParticipant(getRandom(participants));
        eventService.save(e2);
        
        e3.addParticipant(getRandom(participants));
        e3.addParticipant(getRandom(participants));
        e3.addParticipant(getRandom(participants));
        e3.addParticipant(getRandom(participants));
        eventService.save(e3);

        return "initialized";
    }
}
