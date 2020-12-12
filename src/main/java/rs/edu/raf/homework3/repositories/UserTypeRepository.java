package rs.edu.raf.homework3.repositories;

import org.springframework.data.repository.CrudRepository;
import rs.edu.raf.homework3.model.UserType;

public interface UserTypeRepository extends CrudRepository<UserType, Long> {
}
