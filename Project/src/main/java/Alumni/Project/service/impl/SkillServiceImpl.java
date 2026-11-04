package Alumni.Project.service.impl;

import Alumni.Project.dto.SkillRequest;
import Alumni.Project.dto.response.SkillResponse;
import Alumni.Project.dto.update.SkillUpdateRequest;
import Alumni.Project.entity.Skill;
import Alumni.Project.exception.DuplicateResourceException;
import Alumni.Project.exception.ResourceNotFoundException;
import Alumni.Project.repository.SkillRepository;
import Alumni.Project.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl
        implements SkillService {

    private final SkillRepository skillRepository;


    @Override
    public SkillResponse createSkill(
            SkillRequest request
    ) {
        if (request.getSkillName() == null ||
                request.getSkillName().isBlank()) {
            throw new IllegalArgumentException("Skill name is required");
        }

        if (skillRepository
                .existsBySkillNameIgnoreCase(
                        request.getSkillName()
                )) {

            throw new DuplicateResourceException(
                    "Skill already exists"
            );
        }


        Skill skill = new Skill();

        skill.setSkillName(request.getSkillName().trim());

        skill.setDescription(
                request.getDescription()
        );


        Skill savedSkill =
                skillRepository.save(skill);


        return convertToResponse(
                savedSkill
        );
    }


    @Override
    public List<SkillResponse>
    getAllSkills() {

        return skillRepository
                .findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    @Override
    public SkillResponse getSkillById(
            Long id
    ) {

        Skill skill = skillRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Skill not found with ID: "
                                                + id
                                )
                );


        return convertToResponse(
                skill
        );
    }


    @Override
    public SkillResponse getSkillByName(
            String skillName
    ) {

        Skill skill = skillRepository
                .findBySkillNameIgnoreCase(
                        skillName
                )
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Skill not found with name: "
                                                + skillName
                                )
                );


        return convertToResponse(
                skill
        );
    }


    @Override
    public SkillResponse updateSkill(
            Long id,
            SkillUpdateRequest request
    ) {

        Skill skill = skillRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Skill not found with ID: "
                                                + id
                                )
                );


        if (request.getSkillName() == null ||
                request.getSkillName().isBlank()) {
            throw new IllegalArgumentException("Skill name is required");
        }

        boolean skillNameChanged =
                !skill.getSkillName().equalsIgnoreCase(
                        request.getSkillName().trim()
                );

        if (skillNameChanged &&
                skillRepository.existsBySkillNameIgnoreCase(
                        request.getSkillName().trim()
                )) {
            throw new DuplicateResourceException(
                    "Another skill with this name already exists"
            );
        }


        skill.setSkillName(request.getSkillName().trim());

        skill.setDescription(
                request.getDescription()
        );


        Skill updatedSkill =
                skillRepository.save(skill);


        return convertToResponse(
                updatedSkill
        );
    }


    @Override
    public void deleteSkill(
            Long id
    ) {

        Skill skill = skillRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Skill not found with ID: "
                                                + id
                                )
                );


        skillRepository.delete(skill);
    }


    private SkillResponse convertToResponse(
            Skill skill
    ) {

        SkillResponse response =
                new SkillResponse();


        response.setId(
                skill.getId()
        );

        response.setSkillName(
                skill.getSkillName()
        );

        response.setDescription(
                skill.getDescription()
        );


        return response;
    }
}