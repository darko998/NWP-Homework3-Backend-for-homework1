package rs.edu.raf.homework3.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @ManyToOne
    @JoinColumn(name = "USER_TYPE_ID", referencedColumnName = "ID")
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "MY_GROUP_ID", referencedColumnName = "ID")
    private Group group;
}
