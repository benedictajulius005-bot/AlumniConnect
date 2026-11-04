package Alumni.Project.dto.response;

import jakarta.transaction.Transactional;
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
@Transactional
public class EventResponse {

    private Long eventId;

    private String title;

    private String description;

    private String location;

    private LocalDate eventDate;

    private Integer maxParticipants;

    private Boolean active;

    private Long adminId;

    private String adminCode;

    private String adminName;

    private Integer numberOfParticipants;

    private List<Long> participantIds =
            new ArrayList<>();

    private List<String> participantNames =
            new ArrayList<>();
}