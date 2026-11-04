package Alumni.Project.dto.update;

import Alumni.Project.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlumniUpdateRequest {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String matricNumber;

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

    private List<Long> skillIds;
}