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

    @GetMapping("/events/new")
    public String addEvent(Model model) {
        model.addAttribute("event", new Event());
        return "add_event.html";
    }

    @PostMapping("/events/new")
    public String addEvent(@Validated Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_event.html";
        }
        eventService.save(event);
        return "redirect:/events/" + event.getId();
    }
}
