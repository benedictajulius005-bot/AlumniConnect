
package Alumni.Project.service.impl;

import Alumni.Project.dto.response.AlumniResponse;
import Alumni.Project.dto.response.JobOpportunityResponse;
import Alumni.Project.dto.response.JobRecommendationResponse;
import Alumni.Project.dto.response.JobSkillResponse;
import Alumni.Project.exception.ResourceNotFoundException;
import Alumni.Project.service.AlumniService;
import Alumni.Project.service.JobOpportunityService;
import Alumni.Project.service.JobSkillService;
import Alumni.Project.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final AlumniService alumniService;
    private final JobOpportunityService jobOpportunityService;
    private final JobSkillService jobSkillService;

    @Value("${app.recommendation.threshold:60}")
    private double recommendationThreshold;

    @Override
    public List<JobRecommendationResponse> getRecommendationsForAlumni(Long alumniId) {

        AlumniResponse alumni = alumniService.getAlumniById(alumniId);

        if (alumni == null) {
            throw new ResourceNotFoundException(
                    "Alumni not found with ID: " + alumniId
            );
        }

        Set<String> alumniSkills = normalizeSkills(alumni.getSkills());

        List<JobOpportunityResponse> jobs =
                jobOpportunityService.getActiveJobOpportunities();

        List<JobRecommendationResponse> recommendations =
                new ArrayList<>();

        for (JobOpportunityResponse job : jobs) {

            List<JobSkillResponse> jobSkills =
                    jobSkillService.getSkillsByJob(job.getJobOpportunityId());

            Map<String, String> requiredSkills = new LinkedHashMap<>();

            if (jobSkills != null) {
                for (JobSkillResponse jobSkill : jobSkills) {

                    if (jobSkill == null
                            || !Boolean.TRUE.equals(jobSkill.getRequired())
                            || jobSkill.getSkillName() == null) {
                        continue;
                    }

                    String normalized =
                            normalizeSkill(jobSkill.getSkillName());

                    if (!normalized.isEmpty()) {
                        requiredSkills.putIfAbsent(
                                normalized,
                                jobSkill.getSkillName().trim()
                        );
                    }
                }
            }

            // Do not calculate a percentage when no required skills exist.
            if (requiredSkills.isEmpty()) {
                continue;
            }

            List<String> matchingSkills = new ArrayList<>();
            List<String> missingSkills = new ArrayList<>();

            for (Map.Entry<String, String> required
                    : requiredSkills.entrySet()) {

                if (alumniSkills.contains(required.getKey())) {
                    matchingSkills.add(required.getValue());
                } else {
                    missingSkills.add(required.getValue());
                }
            }

            double matchScore =
                    ((double) matchingSkills.size()
                            / requiredSkills.size()) * 100.0;

            matchScore = Math.round(matchScore * 100.0) / 100.0;

            boolean recommended =
                    matchScore >= recommendationThreshold;
// Only return jobs that meet the recommendation threshold.
            if (recommended) {
                recommendations.add(new JobRecommendationResponse(
                        job.getJobOpportunityId(),
                        job.getJobTitle(),
                        job.getCompanyName(),
                        job.getLocation(),
                        job.getEmploymentType(),
                        matchScore,
                        true,
                        matchingSkills,
                        missingSkills
                ));
            }
        }

        recommendations.sort((first, second) ->
                Double.compare(
                        second.getMatchScore(),
                        first.getMatchScore()
                )
        );

        return recommendations;
    }

    private Set<String> normalizeSkills(List<String> skills) {

        Set<String> normalizedSkills = new HashSet<>();

        if (skills == null) {
            return normalizedSkills;
        }

        for (String skill : skills) {
            String normalized = normalizeSkill(skill);

            if (!normalized.isEmpty()) {
                normalizedSkills.add(normalized);
            }
        }

        return normalizedSkills;
    }

    private String normalizeSkill(String skill) {
        return skill == null
                ? ""
                : skill.trim().toLowerCase(Locale.ROOT);
    }
}
