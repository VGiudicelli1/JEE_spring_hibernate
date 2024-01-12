package tsi.ensg.prjEval.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tsi.ensg.prjEval.models.Event;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    @GetMapping("/events")
    public String getAllEvents(Model model) {
        List<Event> events = new ArrayList<>();
        if (events.isEmpty()) {
            return "events_list_empty.html";
        }
        model.addAttribute("events", events);
        return "events_list.html";
    }

    @GetMapping("/events/{id_event}")
    public String getEventWithId(@PathVariable("id_event") long id_event, Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        return "event_infos.html";
    }
}
