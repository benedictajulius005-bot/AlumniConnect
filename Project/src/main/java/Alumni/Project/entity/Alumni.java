package Alumni.Project.entity;

import Alumni.Project.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alumni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Alumni extends User {

    @Column(nullable = true, unique = true)
    private String alumniId;

    @Column(nullable = true, unique = true)
    private String matricNumber;

    @Column(nullable = true)
    private String phoneNumber;

    @Column(nullable = true)
    private Integer graduationYear;

    @Column(nullable = true)
    private String degree;

    @Column(nullable = true)
    private String currentCompany;

    @Column(nullable = true)
    private String currentPosition;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;

    private String profilePicture;

    @Column(nullable = false)
    private Boolean employed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(mappedBy = "participants")
    private List<Event> events = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "alumni_skills",
            joinColumns = @JoinColumn(name = "alumni_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills = new ArrayList<>();
}