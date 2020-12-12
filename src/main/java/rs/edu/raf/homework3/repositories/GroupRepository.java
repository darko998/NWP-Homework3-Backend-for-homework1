package rs.edu.raf.homework3.repositories;

import org.springframework.data.repository.CrudRepository;
import rs.edu.raf.homework3.model.Group;

public interface GroupRepository extends CrudRepository<Group, Long> {
}
