package rs.edu.raf.homework3.services;

import org.springframework.stereotype.Service;
import rs.edu.raf.homework3.model.Group;
import rs.edu.raf.homework3.repositories.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService implements IService<Group, Long> {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    public List<Group> findAll() {
        return (List<Group>) groupRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }
}
