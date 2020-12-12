package rs.edu.raf.homework3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "userType", cascade = {  CascadeType.ALL })
    @JsonIgnore
    @ToString.Exclude
    private List<User> users = new ArrayList<>();
}
