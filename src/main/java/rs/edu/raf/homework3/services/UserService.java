package rs.edu.raf.homework3.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import rs.edu.raf.homework3.model.User;
import rs.edu.raf.homework3.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements  IService<User, Long>{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findByFirstNameAndLastNameAndUserTypeAndGroup( String firstName,
                                                                     String lastName,
                                                                     String userType,
                                                                     String group) {

        return userRepository.findByFirstNameAndLastNameAndUserTypeAndGroup(firstName, lastName, userType, group);
    }
}
