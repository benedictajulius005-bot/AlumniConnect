package Alumni.Project.service;

import Alumni.Project.dto.response.JobRecommendationResponse;

import java.util.List;

public interface RecommendationService {

    List<JobRecommendationResponse> getRecommendationsForAlumni(
            Long alumniId
    );
}