package tsi.ensg.prjEval.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tsi.ensg.prjEval.models.Event;
import tsi.ensg.prjEval.repositories.EventRepo;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired private EventRepo repository;

    public List<Event> findAll() {
        return (List<Event>) repository.findAll();
    }

    public void save(Event event) {
        repository.save(event);
    }

    public Optional<Event> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Event event) {
        repository.delete(event);
    }
}
