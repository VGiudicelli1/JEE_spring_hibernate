package tsi.ensg.prjEval.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tsi.ensg.prjEval.models.Participant;

@Repository
public interface ParticipantRepo extends CrudRepository<Participant, Long> {
}
