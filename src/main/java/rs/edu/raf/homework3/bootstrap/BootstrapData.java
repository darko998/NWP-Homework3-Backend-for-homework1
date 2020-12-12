package rs.edu.raf.homework3.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rs.edu.raf.homework3.model.Group;
import rs.edu.raf.homework3.model.User;
import rs.edu.raf.homework3.model.UserType;
import rs.edu.raf.homework3.repositories.GroupRepository;
import rs.edu.raf.homework3.repositories.UserRepository;
import rs.edu.raf.homework3.repositories.UserTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final GroupRepository groupRepository;

    public BootstrapData(UserRepository userRepository, UserTypeRepository userTypeRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        System.out.println("Loading data...");
        
        String[] USER_FIRST_NAME_LIST = { "Mika", "Pera", "Zika" };
        String[] USER_LAST_NAME_LIST = { "Mikic", "Peric", "Zikic" };
        String[] USER_TYPE_LIST = { "User" , "Administrator" };
        String[] GROUP_LIST = { "Grupa1", "Grupa2" };
        
        
        List<User> users = new ArrayList<>();
        List<UserType> userTypes = new ArrayList<>();
        List<Group> groups = new ArrayList<>();

        Random random = new Random();

        for(int i = 0 ; i < GROUP_LIST.length ; i++) {
            Group tmpGroup = new Group();
            tmpGroup.setName(GROUP_LIST[i]);

            groups.add(tmpGroup);
            System.out.println(groupRepository.save(tmpGroup));
        }


        for(int i = 0 ; i < USER_TYPE_LIST.length ; i++) {
            UserType tmpUserType = new UserType();
            tmpUserType.setName(USER_TYPE_LIST[i]);

            userTypes.add(tmpUserType);
            System.out.println(userTypeRepository.save(tmpUserType));
        }


        for(int i = 0 ; i < USER_FIRST_NAME_LIST.length ; i++) {
            User tmpUser = new User();
            tmpUser.setFirstName(USER_FIRST_NAME_LIST[i]);
            tmpUser.setLastName(USER_LAST_NAME_LIST[i]);

            if(i % 2 == 0) {
                tmpUser.setGroup(groups.get(0));
                tmpUser.setUserType(userTypes.get(0));
            }
            else {
                tmpUser.setGroup(groups.get(1));
                tmpUser.setUserType(userTypes.get(1));
            }
            users.add(tmpUser);
            System.out.println(userRepository.save(tmpUser));
        }

        System.out.println("Data loaded!");
    }
}
