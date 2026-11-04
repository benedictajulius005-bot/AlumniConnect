package Alumni.Project.service.impl;

import Alumni.Project.dto.JobOpportunityRequest;
import Alumni.Project.dto.response.JobOpportunityResponse;
import Alumni.Project.dto.update.JobOpportunityUpdateRequest;
import Alumni.Project.entity.Department;
import Alumni.Project.entity.JobOpportunity;
import Alumni.Project.entity.JobSkill;
import Alumni.Project.entity.Skill;
import Alumni.Project.entity.User;
import Alumni.Project.exception.ResourceNotFoundException;
import Alumni.Project.repository.DepartmentRepository;
import Alumni.Project.repository.JobOpportunityRepository;
import Alumni.Project.repository.SkillRepository;
import Alumni.Project.repository.UserRepository;
import Alumni.Project.service.JobOpportunityService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobOpportunityServiceImpl
        implements JobOpportunityService {

    private final JobOpportunityRepository
            jobOpportunityRepository;

    private final DepartmentRepository
            departmentRepository;

    private final SkillRepository
            skillRepository;

    private final UserRepository
            userRepository;


    // =========================================================
    // CREATE JOB
    // =========================================================

    @Override
    public JobOpportunityResponse createJobOpportunity(
            JobOpportunityRequest request
    ) {

        Department department =
                departmentRepository
                        .findById(
                                request.getDepartmentId()
                        ).orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Department not found with ID: "
                                                        + request.getDepartmentId()
                                        )
                                     );


        // Validate application deadline
        if (request.getApplicationDeadline() != null &&
                request.getApplicationDeadline().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Application deadline cannot be in the past"
            );
        }
        // Get the currently logged-in user
        User currentUser = getCurrentUser();


        JobOpportunity jobOpportunity =
                new JobOpportunity();


        jobOpportunity.setJobTitle(
                request.getJobTitle()
        );

        jobOpportunity.setJobDescription(
                request.getJobDescription()
        );

        jobOpportunity.setCompanyName(
                request.getCompanyName()
        );

        jobOpportunity.setLocation(
                request.getLocation()
        );

        jobOpportunity.setEmploymentType(
                request.getEmploymentType()
        );

        jobOpportunity.setSalary(
                request.getSalary()
        );

        jobOpportunity.setApplicationDeadline(
                request.getApplicationDeadline()
        );

        jobOpportunity.setPostedDate(
                LocalDate.now()
        );

        jobOpportunity.setActive(
                request.getActive() != null
                        ? request.getActive()
                        : true
        );

        jobOpportunity.setDepartment(
                department
        );


        // Save the logged-in user as the creator
        jobOpportunity.setCreatedBy(
                currentUser
        );


        List<JobSkill> jobSkills =
                createJobSkills(
                        request.getSkillIds(),
                        jobOpportunity
                );

        jobOpportunity.setJobSkills(
                jobSkills
        );


        JobOpportunity savedJob =
                jobOpportunityRepository.save(
                        jobOpportunity
                );


        return convertToResponse(
                savedJob
        );
    }


    // =========================================================
    // GET ALL JOBS
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public List<JobOpportunityResponse>
    getAllJobOpportunities() {

        return jobOpportunityRepository
                .findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // =========================================================
    // GET ACTIVE JOBS
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public List<JobOpportunityResponse>
    getActiveJobOpportunities() {

        return jobOpportunityRepository
                .findByActiveTrueAndApplicationDeadlineGreaterThanEqual(
                        LocalDate.now()
                )
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // =========================================================
    // GET JOB BY ID
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public JobOpportunityResponse
    getJobOpportunityById(
            Long id
    ) {

        JobOpportunity job =
                findJobById(id);


        return convertToResponse(
                job
        );
    }


    // =========================================================
    // GET JOBS BY DEPARTMENT
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public List<JobOpportunityResponse>
    getJobsByDepartment(
            Long departmentId
    ) {

        return jobOpportunityRepository
                .findByDepartmentId(
                        departmentId
                )
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // =========================================================
    // GET JOBS BY COMPANY
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public List<JobOpportunityResponse>
    getJobsByCompany(
            String companyName
    ) {

        return jobOpportunityRepository
                .findByCompanyNameIgnoreCase(
                        companyName
                )
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // =========================================================
    // GET JOBS BY EMPLOYMENT TYPE
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public List<JobOpportunityResponse>
    getJobsByEmploymentType(
            String employmentType
    ) {

        return jobOpportunityRepository
                .findByEmploymentTypeIgnoreCase(
                        employmentType
                )
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // =========================================================
    // UPDATE JOB
    // =========================================================

    @Override
    @Transactional
    public JobOpportunityResponse
    updateJobOpportunity(
            Long id,
            JobOpportunityUpdateRequest request
    ) {

        JobOpportunity job =
                findJobById(id);


        // Check whether the current user is allowed
        // to modify this particular job.
        checkJobOwnership(job);


        Department department =
                departmentRepository
                        .findById(
                                request.getDepartmentId()
                        )
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Department not found with ID: "
                                                        + request.getDepartmentId()
                                        )
                        );


        job.setJobTitle(
                request.getJobTitle()
        );

        job.setJobDescription(
                request.getJobDescription()
        );

        job.setCompanyName(
                request.getCompanyName()
        );

        job.setLocation(
                request.getLocation()
        );

        job.setEmploymentType(
                request.getEmploymentType()
        );

        job.setSalary(
                request.getSalary()
        );

        job.setApplicationDeadline(
                request.getApplicationDeadline()
        );

        job.setActive(
                request.getActive()
        );

        job.setDepartment(
                department
        );


        // Replace the existing required skills
        job.getJobSkills().clear();


        List<JobSkill> newJobSkills =
                createJobSkills(
                        request.getSkillIds(),
                        job
                );


        job.getJobSkills().addAll(
                newJobSkills
        );


        JobOpportunity updatedJob =
                jobOpportunityRepository.save(
                        job
                );


        return convertToResponse(
                updatedJob
        );
    }


    // =========================================================
    // DELETE JOB
    // =========================================================

    @Override
    @Transactional
    public void deleteJobOpportunity(
            Long id
    ) {

        JobOpportunity job =
                findJobById(id);


        // Check whether the current user is allowed
        // to delete this particular job.
        checkJobOwnership(job);


        jobOpportunityRepository.delete(
                job
        );
    }


    // =========================================================
    // GET CURRENT LOGGED-IN USER
    // =========================================================

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();


        String email =
                authentication.getName();


        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Authenticated user not found: "
                                                + email
                                )
                );
    }


    // =========================================================
    // CHECK JOB OWNERSHIP
    // =========================================================

    private void checkJobOwnership(
            JobOpportunity job
    ) {

        User currentUser =
                getCurrentUser();


        // ADMIN can update/delete any job.
        if (currentUser.getRole().name().equals("ADMIN")) {
            return;
        }


        // EMPLOYER can update/delete only
        // jobs created by that employer.
        if (currentUser.getRole().name().equals("EMPLOYER")) {

            if (job.getCreatedBy() == null
                    || job.getCreatedBy().getId() == null
                    || !job.getCreatedBy()
                    .getId()
                    .equals(currentUser.getId())) {

                throw new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "You are not allowed to manage this job"
                );
            }

            return;
        }


        // ALUMNI, STUDENT, or any other role
        // cannot update or delete jobs.
        throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "You are not allowed to manage this job"
        );
    }


    // =========================================================
    // FIND JOB BY ID
    // =========================================================

    private JobOpportunity findJobById(
            Long id
    ) {

        return jobOpportunityRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Job opportunity not found with ID: "
                                                + id
                                )
                );
    }


    // =========================================================
    // CREATE JOB SKILLS
    // =========================================================

    private List<JobSkill> createJobSkills(
            List<Long> skillIds,
            JobOpportunity jobOpportunity
    ) {

        List<JobSkill> jobSkills =
                new ArrayList<>();


        if (skillIds == null
                || skillIds.isEmpty()) {

            return jobSkills;
        }


        List<Skill> skills =
                skillRepository.findAllById(
                        skillIds
                );


        if (skills.size()
                != skillIds.size()) {

            throw new ResourceNotFoundException(
                    "One or more selected skills were not found"
            );
        }


        for (Skill skill : skills) {

            JobSkill jobSkill =
                    new JobSkill();


            jobSkill.setJobOpportunity(
                    jobOpportunity
            );

            jobSkill.setSkill(
                    skill
            );

            jobSkill.setRequired(
                    true
            );


            jobSkills.add(
                    jobSkill
            );
        }


        return jobSkills;
    }


    // =========================================================
    // CONVERT ENTITY TO RESPONSE
    // =========================================================

    private JobOpportunityResponse
    convertToResponse(
            JobOpportunity job
    ) {

        JobOpportunityResponse response =
                new JobOpportunityResponse();


        response.setJobOpportunityId(
                job.getJobOpportunityId()
        );

        response.setJobTitle(
                job.getJobTitle()
        );

        response.setJobDescription(
                job.getJobDescription()
        );

        response.setCompanyName(
                job.getCompanyName()
        );

        response.setLocation(
                job.getLocation()
        );

        response.setEmploymentType(
                job.getEmploymentType()
        );

        response.setSalary(
                job.getSalary()
        );

        response.setApplicationDeadline(
                job.getApplicationDeadline()
        );

        response.setPostedDate(
                job.getPostedDate()
        );

        response.setActive(
                job.getActive()
        );


        if (job.getDepartment()
                != null) {

            response.setDepartmentId(
                    job.getDepartment()
                            .getId()
            );

            response.setDepartmentName(
                    job.getDepartment()
                            .getDepartmentName()
            );
        }


        if (job.getJobSkills()
                != null) {

            List<String> skillNames =
                    job.getJobSkills()
                            .stream()
                            .map(
                                    jobSkill ->
                                            jobSkill
                                                    .getSkill()
                                                    .getSkillName()
                            )
                            .toList();


            response.setRequiredSkills(
                    skillNames
            );
            List<Long> skillIds =
                    job.getJobSkills()
                            .stream()
                            .map(jobSkill -> jobSkill.getSkill().getId())
                            .toList();

            response.setRequiredSkillIds(skillIds);
        }


        return response;
    }
}