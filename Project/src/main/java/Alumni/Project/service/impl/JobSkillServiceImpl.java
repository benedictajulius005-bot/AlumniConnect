package Alumni.Project.service.impl;

import Alumni.Project.dto.JobSkillRequest;
import Alumni.Project.dto.response.JobSkillResponse;
import Alumni.Project.dto.update.JobSkillUpdateRequest;
import Alumni.Project.entity.JobOpportunity;
import Alumni.Project.entity.JobSkill;
import Alumni.Project.entity.Skill;
import Alumni.Project.exception.DuplicateResourceException;
import Alumni.Project.exception.ResourceNotFoundException;
import Alumni.Project.repository.JobOpportunityRepository;
import Alumni.Project.repository.JobSkillRepository;
import Alumni.Project.repository.SkillRepository;
import Alumni.Project.service.JobSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobSkillServiceImpl
        implements JobSkillService {

    private final JobSkillRepository
            jobSkillRepository;

    private final JobOpportunityRepository
            jobOpportunityRepository;

    private final SkillRepository
            skillRepository;


    @Override
    @Transactional
    public JobSkillResponse createJobSkill(
            JobSkillRequest request
    ) {
        if (request.getJobOpportunityId() == null ||
                request.getSkillId() == null) {
            throw new IllegalArgumentException(
                    "Job opportunity ID and skill ID are required"
            );
        }
        JobOpportunity jobOpportunity =
                jobOpportunityRepository
                        .findById(
                                request.getJobOpportunityId()
                        )
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Job opportunity not found with ID: "
                                                        + request.getJobOpportunityId()
                                        )
                        );


        Skill skill =
                skillRepository
                        .findById(
                                request.getSkillId()
                        )
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Skill not found with ID: "
                                                        + request.getSkillId()
                                        )
                        );


        boolean alreadyExists =
                jobSkillRepository
                        .existsByJobOpportunityJobOpportunityIdAndSkillId(
                                request.getJobOpportunityId(),
                                request.getSkillId()
                        );


        if (alreadyExists) {

            throw new DuplicateResourceException(
                    "This skill has already been added to this job opportunity"
            );
        }


        JobSkill jobSkill =
                new JobSkill();


        jobSkill.setJobOpportunity(
                jobOpportunity
        );

        jobSkill.setSkill(
                skill
        );

        jobSkill.setRequired(
                request.getRequired() != null
                        ? request.getRequired()
                        : true
        );


        JobSkill savedJobSkill =
                jobSkillRepository.save(
                        jobSkill
                );


        return convertToResponse(
                savedJobSkill
        );
    }


    @Override
    @Transactional(readOnly = true)
    public List<JobSkillResponse>
    getAllJobSkills() {

        return jobSkillRepository
                .findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public JobSkillResponse
    getJobSkillById(
            Long id
    ) {

        JobSkill jobSkill =
                findJobSkillById(id);


        return convertToResponse(
                jobSkill
        );
    }


    @Override
    @Transactional(readOnly = true)
    public List<JobSkillResponse>
    getSkillsByJob(
            Long jobOpportunityId
    ) {

        return jobSkillRepository
                .findByJobOpportunityJobOpportunityId(
                        jobOpportunityId
                )
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<JobSkillResponse>
    getJobsBySkill(
            Long skillId
    ) {

        return jobSkillRepository
                .findBySkillId(
                        skillId
                )
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    @Override
    @Transactional
    public JobSkillResponse
    updateJobSkill(
            Long id,
            JobSkillUpdateRequest request
    ) {

        JobSkill jobSkill =
                findJobSkillById(id);


        if (request.getRequired() != null) {

            jobSkill.setRequired(
                    request.getRequired()
            );
        }


        JobSkill updatedJobSkill =
                jobSkillRepository.save(
                        jobSkill
                );


        return convertToResponse(
                updatedJobSkill
        );
    }


    @Override
    @Transactional
    public void deleteJobSkill(
            Long id
    ) {

        JobSkill jobSkill =
                findJobSkillById(id);


        jobSkillRepository.delete(
                jobSkill
        );
    }


    private JobSkill findJobSkillById(
            Long id
    ) {

        return jobSkillRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Job skill not found with ID: "
                                                + id
                                )
                );
    }


    private JobSkillResponse
    convertToResponse(
            JobSkill jobSkill
    ) {

        JobSkillResponse response =
                new JobSkillResponse();


        response.setId(
                jobSkill.getId()
        );


        response.setJobOpportunityId(
                jobSkill
                        .getJobOpportunity()
                        .getJobOpportunityId()
        );


        response.setJobTitle(
                jobSkill
                        .getJobOpportunity()
                        .getJobTitle()
        );


        response.setSkillId(
                jobSkill
                        .getSkill()
                        .getId()
        );


        response.setSkillName(
                jobSkill
                        .getSkill()
                        .getSkillName()
        );


        response.setRequired(
                jobSkill.getRequired()
        );


        return response;
    }
}