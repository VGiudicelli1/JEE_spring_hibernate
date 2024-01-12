package tsi.ensg.prjEval.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tsi.ensg.prjEval.models.Event;
import tsi.ensg.prjEval.services.EventService;

import java.util.List;
import java.util.Optional;

@Controller
public class EventController {

    @Autowired private EventService eventService;

    @GetMapping("/events")
    public String getAllEvents(Model model) {
        List<Event> events = eventService.findAll();
        if (events.isEmpty()) {
            return "events_list_empty.html";
        }
        model.addAttribute("events", events);
        return "events_list.html";
    }

    @GetMapping("/events/{id_event}")
    public String getEventWithId(@PathVariable("id_event") long id_event, Model model) {
        Optional<Event> event = eventService.findById(id_event);
        if (event.isEmpty()) {
            return "event_info_error.html";
        }
        model.addAttribute("event", event.get());
        return "event_info.html";
    }
}
