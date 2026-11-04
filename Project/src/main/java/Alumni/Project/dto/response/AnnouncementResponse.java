package Alumni.Project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementResponse {

    private Long announcementId;

    private String title;

    private String message;

    private LocalDateTime postedDate;

    private Boolean active;

    private Long adminId;

    private String adminCode;

    private String adminName;
}