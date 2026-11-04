package Alumni.Project.service;

import Alumni.Project.dto.response.AlumniResponse;
import Alumni.Project.dto.AlumniRequest;
import Alumni.Project.dto.update.AlumniUpdateRequest;

import java.util.List;

public interface AlumniService {

    AlumniResponse createAlumni(AlumniRequest request);

    List<AlumniResponse> getAllAlumni();

    AlumniResponse getAlumniById(Long id);

    AlumniResponse getAlumniByAlumniId(String alumniId);

    AlumniResponse getAlumniByMatricNumber(String matricNumber);

    AlumniResponse updateAlumni(
            Long id,
            AlumniUpdateRequest request
    );

    void deleteAlumni(Long id);

    AlumniResponse addSkillToAlumni(
            Long alumniId,
            Long skillId
    );

    AlumniResponse removeSkillFromAlumni(
            Long alumniId,
            Long skillId
    );
}