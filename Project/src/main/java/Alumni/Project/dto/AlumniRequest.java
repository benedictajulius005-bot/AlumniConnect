package Alumni.Project.dto;

import Alumni.Project.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlumniRequest {

    // Fields inherited from User

    private String firstName;

    private String lastName;

    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 12, message = "Password must contain at least 12 characters")
    private String password;


    // Alumni fields

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

    private Long departmentId;

    private List<Long> skillIds = new ArrayList<>();
}