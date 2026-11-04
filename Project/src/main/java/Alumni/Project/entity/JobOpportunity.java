package Alumni.Project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_opportunities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobOpportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobOpportunityId;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false, length = 2000)
    private String jobDescription;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String employmentType;

    @Column(nullable = false)
    private Double salary;

    @Column(nullable = false)
    private LocalDate applicationDeadline;

    @Column(nullable = false)
    private LocalDate postedDate;

    @Column(nullable = false)
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @OneToMany(
            mappedBy = "jobOpportunity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<JobSkill> jobSkills = new ArrayList<>();
}