package tsi.ensg.prjEval.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tsi.ensg.prjEval.models.Event;

@Repository
public interface EventRepo extends CrudRepository<Event, Long> {
}
