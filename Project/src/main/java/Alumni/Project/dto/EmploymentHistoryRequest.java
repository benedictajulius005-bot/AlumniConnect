
package Alumni.Project.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class EmploymentHistoryRequest {

    private Long alumniId;
    private String companyName;
    private String jobPosition;
    private LocalDate startDate;
    private LocalDate endDate;
}
