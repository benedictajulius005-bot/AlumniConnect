
package Alumni.Project.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class EmploymentHistoryResponse {

    private Long id;
    private Long alumniId;
    private String companyName;
    private String jobPosition;
    private LocalDate startDate;
    private LocalDate endDate;
}
