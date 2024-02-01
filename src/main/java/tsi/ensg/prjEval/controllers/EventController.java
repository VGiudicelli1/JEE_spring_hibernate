package tsi.ensg.prjEval.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
        model.addAttribute("events", events);
        return "event/list";
    }

    @GetMapping("/events/{id_event}")
    public String getEventWithId(@PathVariable("id_event") long id_event, Model model) {
        Event event = eventService.findById(id_event).orElse(null);
        model.addAttribute("event", event);
        return "event/info";
    }

    @GetMapping("/events/new")
    public String addEvent(Model model) {
        model.addAttribute("event", new Event());
        return "event/add";
    }

    @PostMapping("/events/new")
    public String addEvent(@Validated Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "event/add";
        }
        eventService.save(event);
        return "redirect:/events/" + event.getId();
    }

    @GetMapping("/events/edit/{id_event}")
    public String editEvent(@PathVariable long id_event, Model model) {
        Optional<Event> event = eventService.findById(id_event);
        if (event.isEmpty()) {
            return "redirect:/events/"+id_event;
        } else {
            System.out.println(event.get());
            model.addAttribute("event", event.get());
            return "event/edit";
        }
    }

    @PostMapping("/events/edit/{id_event}")
    public String editEvent(@PathVariable long id_event, @Validated Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "event/edit/"+id_event;
        }
        Event eventOriginal = eventService.findById(id_event).orElse(null);
        if (eventOriginal == null) {
            // une erreur s'est produite
            return "redirect:/events";
        }
        event.setId(id_event);
        event.setParticipants(eventOriginal.getParticipants()); // restore participants
        if (event.getPlacesFree() < 0) {
            event.setNbUsersMax(event.getPlaceOccuped());
        }
        eventService.save(event);
        return "redirect:/events/"+id_event;
    }


}
