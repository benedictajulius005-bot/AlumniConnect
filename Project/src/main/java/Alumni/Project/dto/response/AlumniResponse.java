package Alumni.Project.dto.response;

import Alumni.Project.enums.Gender;
import Alumni.Project.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlumniResponse {

    // User information

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;


    // Alumni information

    private String alumniId;

    private String matricNumber;

    private String phoneNumber;

    private Integer graduationYear;

    private String degree;

    private String currentCompany;

    private String currentPosition;

    private String address;

    private LocalDate dateOfBirth;

    private Gender gender;

    private String profilePicture;

    private Boolean employed;


    // Department information

    private Long departmentId;

    private String departmentName;


    // Skills

    private List<String> skills = new ArrayList<>();
}