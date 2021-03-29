package stream.wortex.graphqlserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @NotBlank(message = "First name is mandatory")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name = "DOB")
    private Date dob;

    @NotBlank(message = "Address is mandatory")
    @Column(name = "ADDRESS")
    private String address;

    @Positive
    @Column(name = "POST_ID")
    private Integer postId;

    public User(String firstName, String lastName, Date dob, String address, Integer postId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.postId = postId;
    }
}
