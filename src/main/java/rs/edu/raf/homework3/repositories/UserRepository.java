package rs.edu.raf.homework3.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.edu.raf.homework3.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

/*
    @Query("SELECT u FROM User u " +
            "WHERE u.firstName LIKE CONCAT('%',:firstName,'%') AND " +
            "u.lastName LIKE CONCAT('%',:lastName,'%') AND " +
            "(u.group.name LIKE CONCAT('%',:group,'%') OR :group is null OR :group LIKE '%%') AND " +
            "u.userType.name LIKE CONCAT('%',:userType,'%')")
    List<User> findByFirstNameAndLastNameAndUserTypeAndGroup(@Param("firstName") String firstName,
                                                             @Param("lastName") String lastName,
                                                             @Param("userType") String userType,
                                                             @Param("group") String group);
*/

/*
    @Query("SELECT u FROM User u " +
            "WHERE (u.group is null AND " +
            "u.firstName LIKE CONCAT('%',:firstName,'%') AND " +
            "u.lastName LIKE CONCAT('%',:lastName,'%') AND " +
            "u.userType.name LIKE CONCAT('%',:userType,'%')) " +
            "OR " +
            "(u.firstName LIKE CONCAT('%',:firstName,'%') AND " +
            "u.lastName LIKE CONCAT('%',:lastName,'%') AND " +
            "u.group.name LIKE CONCAT('%',:group,'%') AND " +
            "u.userType.name LIKE CONCAT('%',:userType,'%'))")
    List<User> findByFirstNameAndLastNameAndUserTypeAndGroup(@Param("firstName") String firstName,
                                                             @Param("lastName") String lastName,
                                                             @Param("userType") String userType,
                                                             @Param("group") String group);
*/

    @Query("SELECT u FROM User u " +
            "WHERE (:firstName is null or u.firstName LIKE CONCAT('%',:firstName,'%')) AND " +
            "(:lastName is null or u.lastName LIKE CONCAT('%',:lastName,'%')) AND " +
            "(:group is null or u.group.name LIKE CONCAT('%',:group,'%')) AND " +
            "(:userType is null or u.userType.name LIKE CONCAT('%',:userType,'%'))")
    List<User> findByFirstNameAndLastNameAndUserTypeAndGroup(@Param("firstName") String firstName,
                                                             @Param("lastName") String lastName,
                                                             @Param("userType") String userType,
                                                             @Param("group") String group);
}
