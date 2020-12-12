package rs.edu.raf.homework3.services;

import org.springframework.stereotype.Service;
import rs.edu.raf.homework3.model.UserType;
import rs.edu.raf.homework3.repositories.UserTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserTypeService implements IService<UserType, Long> {

    private final UserTypeRepository userTypeRepository;

    public UserTypeService(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public UserType save(UserType userType) {
        return userTypeRepository.save(userType);
    }

    @Override
    public Optional<UserType> findById(Long id) {
        return userTypeRepository.findById(id);
    }

    @Override
    public List<UserType> findAll() {
        return (List<UserType>) userTypeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userTypeRepository.deleteById(id);
    }
}
