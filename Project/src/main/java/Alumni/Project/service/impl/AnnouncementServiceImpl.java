package Alumni.Project.service.impl;

import Alumni.Project.dto.AnnouncementRequest;
import Alumni.Project.dto.response.AnnouncementResponse;
import Alumni.Project.dto.update.AnnouncementUpdateRequest;
import Alumni.Project.entity.Admin;
import Alumni.Project.entity.Announcement;
import Alumni.Project.exception.ResourceNotFoundException;
import Alumni.Project.repository.AdminRepository;
import Alumni.Project.repository.AnnouncementRepository;
import Alumni.Project.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementServiceImpl
        implements AnnouncementService {

    private final AnnouncementRepository
            announcementRepository;

    private final AdminRepository
            adminRepository;


    @Override
    public AnnouncementResponse
    createAnnouncement(
            AnnouncementRequest request
    ) {

        Admin admin =
                adminRepository
                        .findById(
                                request.getAdminId()
                        )
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Admin not found with ID: "
                                                        + request.getAdminId()
                                        )
                        );


        Announcement announcement =
                new Announcement();


        announcement.setTitle(
                request.getTitle()
        );

        announcement.setMessage(
                request.getMessage()
        );

        announcement.setPostedDate(
                LocalDateTime.now()
        );

        announcement.setActive(
                request.getActive() != null
                        ? request.getActive()
                        : true
        );

        announcement.setAdmin(
                admin
        );


        Announcement savedAnnouncement =
                announcementRepository.save(
                        announcement
                );


        return convertToResponse(
                savedAnnouncement
        );
    }


    @Override
    public List<AnnouncementResponse>
    getAllAnnouncements() {

        return announcementRepository
                .findAllByOrderByPostedDateDesc()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    @Override
    public List<AnnouncementResponse>
    getActiveAnnouncements() {

        return announcementRepository
                .findByActiveTrueOrderByPostedDateDesc()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    @Override
    public AnnouncementResponse
    getAnnouncementById(
            Long id
    ) {

        Announcement announcement =
                findAnnouncementById(
                        id
                );


        return convertToResponse(
                announcement
        );
    }


    @Override
    public List<AnnouncementResponse>
    getAnnouncementsByAdmin(
            Long adminId
    ) {

        if (!adminRepository.existsById(
                adminId
        )) {

            throw new ResourceNotFoundException(
                    "Admin not found with ID: "
                            + adminId
            );
        }


        return announcementRepository
                .findByAdminId(
                        adminId
                )
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    @Override
    public AnnouncementResponse
    updateAnnouncement(
            Long id,
            AnnouncementUpdateRequest request
    ) {

        Announcement announcement =
                findAnnouncementById(
                        id
                );


        announcement.setTitle(
                request.getTitle()
        );

        announcement.setMessage(
                request.getMessage()
        );

        announcement.setActive(
                request.getActive()
        );


        Announcement updatedAnnouncement =
                announcementRepository.save(
                        announcement
                );


        return convertToResponse(
                updatedAnnouncement
        );
    }


    @Override
    public void deleteAnnouncement(
            Long id
    ) {

        Announcement announcement =
                findAnnouncementById(
                        id
                );


        announcementRepository.delete(
                announcement
        );
    }


    private Announcement
    findAnnouncementById(
            Long id
    ) {

        return announcementRepository
                .findById(
                        id
                )
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Announcement not found with ID: "
                                                + id
                                )
                );
    }


    private AnnouncementResponse
    convertToResponse(
            Announcement announcement
    ) {

        AnnouncementResponse response =
                new AnnouncementResponse();


        response.setAnnouncementId(
                announcement
                        .getAnnouncementId()
        );

        response.setTitle(
                announcement.getTitle()
        );

        response.setMessage(
                announcement.getMessage()
        );

        response.setPostedDate(
                announcement.getPostedDate()
        );

        response.setActive(
                announcement.getActive()
        );


        if (announcement.getAdmin()
                != null) {

            response.setAdminId(
                    announcement
                            .getAdmin()
                            .getId()
            );

            response.setAdminCode(
                    announcement
                            .getAdmin()
                            .getAdminId()
            );

            response.setAdminName(
                    announcement
                            .getAdmin()
                            .getFirstName()
                            + " "
                            + announcement
                            .getAdmin()
                            .getLastName()
            );
        }


        return response;
    }
}