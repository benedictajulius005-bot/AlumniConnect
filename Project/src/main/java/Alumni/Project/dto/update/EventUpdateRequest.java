package Alumni.Project.dto.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventUpdateRequest {

    @NotBlank(message = "Event title is required")
    @Size(max = 150, message = "Event title cannot exceed 150 characters")
    private String title;

    private String description;

    @NotBlank(message = "Event location is required")
    private String location;

    @NotNull(message = "Event date is required")
    private LocalDate eventDate;

    @Positive(message = "Maximum participants must be greater than zero")
    private Integer maxParticipants;

    @NotNull(message = "Event active status is required")
    private Boolean active;
}